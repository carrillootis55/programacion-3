package views;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginView extends JPanel {
	Font fuente;
	
	public LoginView() {
		
		fuente = new Font("Times New Roman", Font.ITALIC , 17);
		setBackground(Color.WHITE);
		setLayout(null);
		
		inicializarComponentes();
		//setBackground(new Color(210,165,35));
	}
	
	private void inicializarComponentes() {
		crearBotones();
		crearFormulario();
	}
	
	private void crearBotones() {
		
		JButton boton = new JButton("Iniciar");
		boton.setBounds(215,315,160,40);
		boton.setBackground(Color.BLUE);
		boton.setForeground(Color.BLACK);
		boton.setToolTipText("Haz click aquí");
		boton.setFont(fuente);
		
		colocarIcono(boton, "../img/icono.png");
		
		add(boton);
		
	}
	
	private void crearFormulario() {
		JLabel lblSaludo = new JLabel("Inicio de sesion");
		
		lblSaludo.setForeground(Color.BLACK);
		lblSaludo.setFont(fuente);
		lblSaludo.setBounds(140,2,400,70);
		add(lblSaludo);
		
		JLabel lblEmail = new JLabel("*Correo: ");
		lblEmail.setForeground(Color.BLACK);
		lblEmail.setFont(fuente);
		lblEmail.setBounds(10,100,200,40);
		add(lblEmail);
		
		JTextField texto = new JTextField();
		texto.setForeground(Color.BLACK);
		texto.setFont(fuente);
		texto.setBounds(150,100,200,40);
		add(texto);
		
		JLabel lblEmailCorreccion = new JLabel ("Correo obligatorio");
		lblEmailCorreccion.setForeground(Color.RED);
		lblEmailCorreccion.setFont(fuente);
		lblEmailCorreccion.setBounds(155,125,220,50);
		
		lblEmailCorreccion.setFont(
				lblEmailCorreccion.getFont().deriveFont(14f)	
				);
		
		//lblEmailCorreccion.setBounds(150,100,200,40);
		add(lblEmailCorreccion);
		
		JLabel lblContrasena = new JLabel("*Contraseña: ");
		lblContrasena.setForeground(Color.BLACK);
		lblContrasena.setFont(fuente);
		lblContrasena.setBounds(10,160,200,40);
		add(lblContrasena);
		
		JPasswordField contrasena = new JPasswordField();
		contrasena.setForeground(Color.BLACK);
		contrasena.setFont(fuente);
		contrasena.setBounds(150,160,200,40);
		add(contrasena);
		
		JLabel lblContrasenaCorreccion = new JLabel ("Contraseña obligatoria");
		lblContrasenaCorreccion.setForeground(Color.RED);
		lblContrasenaCorreccion.setFont(fuente);
		lblContrasenaCorreccion.setBounds(155,185,220,50);
		
		lblContrasenaCorreccion.setFont(
				lblEmailCorreccion.getFont().deriveFont(14f)	
				);
		
		add(lblContrasenaCorreccion);
	}
	
	private void colocarIcono(JButton boton, String ruta) { 
		try {
			Image icono = ImageIO.read(getClass().getResource(ruta));
			icono = icono.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
			boton.setIcon(new ImageIcon(icono));			
		}catch(Exception ex) {
			System.out.println("No está la imagen del ícono");
		}
	}

}