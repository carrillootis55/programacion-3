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


import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import lib.SpringUtilities;

public class LoginView extends JPanel {
	private LoginWindow window;
	
	private Font fuente;
	
	private JTextField textoUsuario;
	
	private JPasswordField contrasena;
	
	private JLabel lblUsuarioCorreccion;
	
	private JLabel lblContrasenaCorreccion;
	
	private Color defaultButtonColor;
	
	private JButton botonLogin;
		
	public LoginView(LoginWindow window) {
		this.window= window;
		fuente = new Font("Times New Roman", Font.ITALIC , 17);
		
		setBackground(Color.WHITE);
		setOpaque(true);
		setLayout(new BorderLayout());
		
		inicializarComponentes();
		
	} 
	
	private void inicializarComponentes() {
		crearTitulo();
		crearFormulario();
		crearBotones();
		
	}
	
	//Getters
    public JPasswordField getContrasena() {
		return contrasena;
	}

	public LoginWindow getWindow() {
		return window;
	}

	public JTextField getTextoUsuario() {
		return textoUsuario;
	}

	public JLabel getLblUsuarioCorreccion() {
		return lblUsuarioCorreccion;
	}

	public JLabel getLblContrasenaCorreccion() {
		return lblContrasenaCorreccion;
	}
	
	public JButton getBotonLogin() {
        return botonLogin;
    }

    
	//METODO PARA CENTRAR INICIO DE SESION
    private void crearTitulo() {

        JLabel lblSaludo = new JLabel("Iniciar sesión", JLabel.CENTER);
        lblSaludo.setFont(fuente);
        lblSaludo.setHorizontalAlignment(JLabel.CENTER); 
        //Se centra el texto

        add(lblSaludo, BorderLayout.NORTH); 
        //Se ubicara en el norte 
    }
	
    //BOTON
	private void crearBotones() {

	    JPanel panelBoton = new JPanel();
	    panelBoton.setBackground(Color.WHITE);
	    panelBoton.setOpaque(true); 
	    
	    
	    botonLogin= new JButton("Iniciar");
	    defaultButtonColor = botonLogin.getBackground();
	    botonLogin.setBackground(Color.WHITE);
	    botonLogin.setForeground(Color.BLACK);
	    botonLogin.setFont(fuente);
		botonLogin.setToolTipText("Inicio de sesion");
	    
	    //Uso de mouse para cambiar color el boton
	    botonLogin.addMouseListener(new MouseAdapter() {
	        public void mouseEntered(MouseEvent e) {
	        	botonLogin.setForeground(Color.BLUE);
	        }
	
	        public void mouseExited(MouseEvent e) {
	        	botonLogin.setForeground(Color.BLACK);
	        }
	    });
	    	    		
	    colocarIcono(botonLogin, "../img/icono.png");

	    panelBoton.add(botonLogin);	    
	    add(panelBoton, BorderLayout.SOUTH);
	    
	   
	}

	//Formulario
	private void crearFormulario() {
		
		JPanel panelito = new JPanel (new SpringLayout());
		panelito.setBackground(Color.WHITE);
		panelito.setOpaque(true);
		
		//USUARIO
		JLabel lblEmail = new JLabel("*Usuario: ");
		lblEmail.setFont(fuente);
		
		//CAMBIOS PARA REDUCIR TAMAÑO DEL CAMPO DE TEXTO QUE ABARCA CASI TODA LA PANTALLA
		textoUsuario = new JTextField();
		textoUsuario.setFont(fuente);
		textoUsuario.setPreferredSize(new Dimension(180, 30));
        
        lblUsuarioCorreccion = new JLabel(" ");
        lblUsuarioCorreccion.setForeground(Color.RED);
        lblUsuarioCorreccion.setFont(fuente.deriveFont(13f));
     
        
        //CONTRASEÑA
        JLabel lblContrasena = new JLabel("*Contraseña: ");
        lblContrasena.setFont(fuente);
        
        contrasena = new JPasswordField();
        contrasena.setFont(fuente);
        contrasena.setPreferredSize(new java.awt.Dimension(180, 30));    
        
        lblContrasenaCorreccion = new JLabel(" ");
        lblContrasenaCorreccion.setForeground(Color.RED);
        lblContrasenaCorreccion.setFont(fuente.deriveFont(13f));

        
        //CheckBox para recordar al usuario
        JCheckBox chkRecordar = new JCheckBox("Recordar usuario", false);
        chkRecordar.setBackground(Color.WHITE);
        chkRecordar.setFont(fuente.deriveFont(14f));
		
		
        //SE AGREGA LO CREADO
		panelito.add(lblEmail);
		panelito.add(textoUsuario);

		//Se agrega debajo del correo
		panelito.add(new JLabel());
		panelito.add(lblUsuarioCorreccion);

		panelito.add(lblContrasena);
		panelito.add(contrasena);

		//Se agrega debajo de la contraseña
		panelito.add(new JLabel());
		panelito.add(lblContrasenaCorreccion);
		
		//Se agrega el Recordar Usuario
		panelito.add(new JLabel());
        panelito.add(chkRecordar);
		
		//Iguala el ancho de todas las columnas al componente mas grande
		SpringUtilities.makeCompactGrid(panelito, 5, 2, 20, 10, 15, 15);

		add(panelito, BorderLayout.CENTER);
		
	}
	
	//ICONO DEL BOTON
	private void colocarIcono(JButton boton, String ruta) { 
		try {
			Image icono = ImageIO.read(getClass().getResource(ruta));
			icono = icono.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
			boton.setIcon(new ImageIcon(icono));			
		}catch(Exception ex) {
			System.out.println("No está la imagen del ícono");
		}
	}

	
    //Limpia errores
	public void limpiarErrores() {
	    lblUsuarioCorreccion.setText(" ");
	    lblContrasenaCorreccion.setText(" ");
	}
	
    public void setErrorUsuario(String mensaje) {
        lblUsuarioCorreccion.setText(mensaje);
    }

    public void setErrorContrasena(String mensaje) {
        lblContrasenaCorreccion.setText(mensaje);
    }
	
    
}

