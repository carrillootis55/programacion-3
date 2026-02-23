package views;
import java.awt.BorderLayout;
import java.awt.Color;
/*1. Se importa la paqueteria lib
 * 2. TODOS los setBounds se van a eliminar
 * 3.Los add que agregan cosas directo al panel principal tambien se eliminan
 * 4. Agregamos en orden la tablita
 * 5. Se va a usar borderLayout porque necesitamos definir las restricciones de pantalla para que no se mire feo
 * 6. MODIFICAR los textfield con un tamano especifico para que no salga el jtextfield micro chiquito
 * 7. Para que respete el tamano correcto el textfield porque lo de poner el tamano no funciono se tienen que limitar
 * 
 */
import java.awt.Dimension;

import lib.SpringUtilities;
import java.awt.Font;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class LoginView extends JPanel {
	Font fuente;
	
	public LoginView() {
		
		fuente = new Font("Times New Roman", Font.ITALIC , 17);
		setBackground(Color.WHITE);
		//setLayout(new SpringLayout());
		setLayout(new java.awt.BorderLayout(0, 15)); //ESPACIO ENTRE ZONAS
		
		inicializarComponentes();
		//setBackground(new Color(210,165,35));
	}
	
	private void inicializarComponentes() {
		crearTitulo();
		crearFormulario();
		crearBotones();
	}
	
    //METODO PARA CENTRAR INICIO DE SESION
    private void crearTitulo() {

        JLabel lblSaludo = new JLabel("Inicio de sesión", JLabel.CENTER);
        lblSaludo.setFont(fuente);
        lblSaludo.setHorizontalAlignment(JLabel.CENTER); 
        //Se centra el texto

        add(lblSaludo, BorderLayout.NORTH); 
        //Se ubicara en el norte 
    }
	
	private void crearBotones() {

	    JPanel panelBoton = new JPanel();
	    panelBoton.setBackground(Color.WHITE);

	    JButton boton = new JButton("Iniciar");
	    boton.setBackground(Color.WHITE);
	    boton.setForeground(Color.BLACK);
	    boton.setFont(fuente);

	    colocarIcono(boton, "../img/icono.png");

	    panelBoton.add(boton);

	    add(panelBoton, BorderLayout.SOUTH);
	}

	
	private void crearFormulario() {
		
		JPanel panelito = new JPanel (new SpringLayout());
		panelito.setBackground(Color.WHITE);
		
		//SE DEBE QUITAR PORQUE LO HICE METODO APARTE
		/*JLabel lblSaludo = new JLabel("Inicio de sesion", JLabel.CENTER);
		lblSaludo.setFont(fuente);
		
		panelito.add(lblSaludo);
		panelito.add(new JLabel());*/
		
		JLabel lblEmail = new JLabel("*Correo: ");
		lblEmail.setFont(fuente);
		
		//LO COMENTO 22/02/2026
		/*
		JTextField texto = new JTextField(5);
		texto.setFont(fuente);*/
		
		//CAMBIOS PARA REDUCIR TAMAÑO DEL CAMPO DE TEXTO QUE ABARCA CASI TODA LA PANTALLA
		JTextField texto = new JTextField();
        texto.setFont(fuente);
        texto.setPreferredSize(new Dimension(180, 30));

        JLabel lblContrasena = new JLabel("*Contraseña: ");
        lblContrasena.setFont(fuente);
        
        JPasswordField contrasena = new JPasswordField();
        contrasena.setFont(fuente);
        contrasena.setPreferredSize(new java.awt.Dimension(180, 30));

		
		
		/*JLabel lblEmailCorreccion = new JLabel ("Correo obligatorio");
		lblEmailCorreccion.setForeground(Color.RED);
		lblEmailCorreccion.setFont(fuente);
		lblEmailCorreccion.setBounds(155,125,220,50);
		//Tamano de un texto especifico
		lblEmailCorreccion.setFont(
				lblEmailCorreccion.getFont().deriveFont(14f)	
				);
		//lblEmailCorreccion.setBounds(150,100,200,40);
		add(lblEmailCorreccion);
		*/
		
        //LO COMENTO 22/02/2026
		/*JLabel lblContrasena = new JLabel("*Contraseña: ");
		lblContrasena.setFont(fuente);
		
		JPasswordField contrasena = new JPasswordField(5);
		contrasena.setFont(fuente);*/
	
		panelito.add(lblEmail);
		panelito.add(texto);
		
		panelito.add(lblContrasena);
		panelito.add(contrasena);
		//Iguala el ancho de todas las columnas al componente mas grande
		SpringUtilities.makeCompactGrid(panelito, 2, 2, 20, 10, 15, 15);
		//add(panelito);
		add(panelito, BorderLayout.CENTER);
		
		//NUEVO 22/02/2026
		/*JPanel contenedor = new JPanel();
		contenedor.setBackground(Color.WHITE);
		contenedor.add(panelito);*/

		//LO COMENTO 22/02/2026
		//add(contenedor, BorderLayout.CENTER);
		
		/*JLabel lblContrasenaCorreccion = new JLabel ("Contraseña obligatoria");
		lblContrasenaCorreccion.setForeground(Color.RED);
		lblContrasenaCorreccion.setFont(fuente);
		lblContrasenaCorreccion.setBounds(155,185,220,50);
		
		lblContrasenaCorreccion.setFont(
				lblEmailCorreccion.getFont().deriveFont(14f)	
				);
		
		add(lblContrasenaCorreccion);*/
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