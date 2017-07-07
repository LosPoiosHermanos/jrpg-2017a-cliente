package mensajeria;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.google.gson.Gson;
import cliente.Cliente;
import comandos.Comando;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JTextArea;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
//REVISADO
public class ChatPrivado extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField chat;
	private JTextArea mensajes;
	private final Gson gson = new Gson();

	public ChatPrivado(final Cliente cliente, String receptor) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 460, 378);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 444, 309);
		contentPane.add(scrollPane);
		
		mensajes = new JTextArea();
		mensajes.setEditable(false);
		scrollPane.setViewportView(mensajes);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				cliente.getChatActivos().remove(receptor);
				setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			}
		});
		
		chat = new JTextField();
		chat.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent enter) {
				if (enter.getKeyCode() == KeyEvent.VK_ENTER && !chat.getText().isEmpty()) {
					try {
						PaqueteChat p = new PaqueteChat();
						p.setComando(Comando.ENVIARCHAT);
						p.setIp(cliente.getMiIp());
						p.setMensaje(chat.getText());
						p.setEmisor(cliente.getPaquetePersonaje().getNombre());
						p.setReceptor(receptor);
						cliente.getSalida().writeObject(gson.toJson(p));
						mensajes.append("Yo: " + chat.getText() + "\n");
						
						synchronized (cliente) {
							cliente.notify();
						}
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "Fallo al intentar enviar mensaje.");
						System.exit(1);
						e.printStackTrace();
					}
					chat.setText(null);
					chat.requestFocus();
				}
			}
		});
		chat.setBounds(0, 320, 444, 20);
		contentPane.add(chat);
		chat.setColumns(10);
		
		setVisible(true);
		chat.requestFocus();
	}
	
	public JTextArea getMensajes() {
		return mensajes;
	}

	public void setMensajes(JTextArea mensajes) {
		this.mensajes = mensajes;
	}

	public JTextField getChat() {
		return chat;
	}

	public void setChat(JTextField chat) {
		this.chat = chat;
	}
}
