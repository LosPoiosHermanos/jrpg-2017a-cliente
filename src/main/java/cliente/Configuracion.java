package cliente;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import frames.MenuInicio;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Configuracion extends JFrame {

	private JPanel contentPane;
	private static JTextField ip;
	private static JTextField puerto;

	public Configuracion(MenuInicio menuInicio) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(100, 100, 287, 226);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ip = new JTextField();
		ip.setBounds(96, 37, 125, 20);
		contentPane.add(ip);
		ip.setColumns(10);
		
		puerto = new JTextField();
		puerto.setColumns(10);
		puerto.setBounds(96, 91, 125, 20);
		contentPane.add(puerto);
		
		JLabel lblPuerto = new JLabel("Puerto :");
		lblPuerto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPuerto.setBounds(40, 91, 46, 14);
		contentPane.add(lblPuerto);
		
		JLabel lblIp = new JLabel("IP :");
		lblIp.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblIp.setBounds(62, 37, 24, 14);
		contentPane.add(lblIp);
		
		JButton btnAceptar = new JButton("ACEPTAR");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!ip.getText().isEmpty() && !puerto.getText().isEmpty()){
					dispose();
					//fer descomentar esto al descomentar todo fer
					menuInicio.getLblRegistrarse().setEnabled(true);
					menuInicio.getBtnRegistrar().setEnabled(true);
				}
				else
					JOptionPane.showMessageDialog(null, "Por favor, complete los campos de ip y puerto.");
			}
		});
		btnAceptar.setBounds(96, 133, 89, 23);
		contentPane.add(btnAceptar);
	}

	public static JTextField getIp() {
		return ip;
	}

	public static void setIp(JTextField ip) {
		Configuracion.ip = ip;
	}

	public static JTextField getPuerto() {
		return puerto;
	}

	public static void setPuerto(JTextField puerto) {
		Configuracion.puerto = puerto;
	}
}