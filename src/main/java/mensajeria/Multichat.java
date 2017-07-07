package mensajeria;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.google.gson.Gson;
import cliente.Cliente;
import comandos.Comando;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//REVISADO
public class Multichat extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField chat;
	private JTextArea mensajes;
	private static JList lista;
	private Cliente cliente;
	private final Gson gson = new Gson();

	public Multichat(final Cliente cliente) {
		this.cliente = cliente;
		
		setTitle("Multichat - User : " + cliente.getPaqueteUsuario().getUsername());
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 692, 518);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		chat = new JTextField();
		chat.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent enter) {
				if (enter.getKeyCode() == KeyEvent.VK_ENTER && !chat.getText().isEmpty()) {
					enviarMensaje();
				}
			}
		});
		chat.setBounds(0, 461, 577, 20);
		contentPane.add(chat);
		chat.setColumns(10);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(!chat.getText().isEmpty())
					enviarMensaje();
			}
		});
		btnEnviar.setBounds(587, 458, 89, 23);
		contentPane.add(btnEnviar);
		
		JScrollPane scrollPane = new JScrollPane();
 		scrollPane.setBounds(0, 0, 477, 453);
		contentPane.add(scrollPane);
		
		mensajes = new JTextArea();
		mensajes.setEditable(false);
		scrollPane.setViewportView(mensajes);
		
		JScrollPane scrollLista = new JScrollPane();
		scrollLista.setBounds(499, 0, 177, 453);
		contentPane.add(scrollLista);
		
		lista = new JList();
		scrollLista.setViewportView(lista);
		lista.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				if(click.getClickCount() == 2 && lista.getSelectedValue() != null){
					if(!cliente.getChatActivos().containsKey(lista.getSelectedValue()))
						abrirChatPrivado(lista.getSelectedValue().toString());
				}
			}
		});
		
		JLabel lblUsuariosConectados = new JLabel("Usuarios conectados :");
		lblUsuariosConectados.setHorizontalAlignment(SwingConstants.CENTER);
		scrollLista.setColumnHeaderView(lblUsuariosConectados);
		
		setVisible(true);
		chat.requestFocus();
	}

	public static JList getLista(){
		return lista;
	}
	
	public void enviarMensaje(){
		try {
			PaqueteChat p = new PaqueteChat();
			p.setComando(Comando.ENVIARCHAT);
			p.setIp(cliente.getMiIp());
			p.setMensaje(chat.getText());
			p.setEmisor(cliente.getPaquetePersonaje().getNombre());
			cliente.getSalida().writeObject(gson.toJson(p));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Fallo al intentar enviar mensaje.");
			System.exit(1);
			e.printStackTrace();
		}
		chat.setText(null);
		chat.requestFocus();
	}
	
	public void abrirChatPrivado(String user){
		ChatPrivado cp = new ChatPrivado(cliente, user);
		cp.setVisible(true);
		cp.setTitle("Chat privado con : " + user);
		cliente.getChatActivos().put(user, cp);
	}
	
	public JTextField getChat() {
		return chat;
	}

	public void setChat(JTextField chat) {
		this.chat = chat;
	}

	public JTextArea getMensajes() {
		return mensajes;
	}

	public void setMensajes(JTextArea mensajes) {
		this.mensajes = mensajes;
	}
}
