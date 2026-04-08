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

public class LoginView extends JPanel {
	LoginWindow window;
	Font fuente;
	JTextField textoUsuario;
	JPasswordField contrasena;
	JLabel lblUsuarioCorreccion;
	JLabel lblContrasenaCorreccion;
	Color defaultButtonColor;
	
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
	    
	    
	    JButton boton = new JButton("Iniciar");
	    defaultButtonColor = boton.getBackground();
	    boton.setBackground(Color.WHITE);
	    boton.setForeground(Color.BLACK);
	    boton.setFont(fuente);
	    
	    boton.addActionListener(e -> loginView());
	    /*
	    boton.addActionListener(e->{
			try {
				login();
			} catch (InvalidUserException e1) {
				// TODO Auto-generated catch block
				System.out.println(e1.getMessage());
			} catch (InvalidPasswordException e1) {
				// TODO Auto-generated catch block
				System.out.println(e1.getMessage());
			}
		});*/
	    
	    //Uso de mouse para cambiar color el boton
	    boton.addMouseListener(new MouseAdapter() {
	        public void mouseEntered(MouseEvent e) {
	            boton.setForeground(Color.BLUE);
	        }
	
	        public void mouseExited(MouseEvent e) {
	            boton.setForeground(Color.BLACK);
	        }
	    });
	    	    
	    
	    JButton btnRegister = new JButton("Regístrate");
	    btnRegister.setBackground(Color.WHITE);
	    btnRegister.setForeground(Color.BLACK);
	    btnRegister.setFont(fuente);
		btnRegister.setToolTipText("¿No tienes cuenta? Créala aquí");
		
		
		btnRegister.addActionListener(e-> registro());
		//Uso de mouse para cambiar color el boton
	    btnRegister.addMouseListener(new MouseAdapter() {
	        public void mouseEntered(MouseEvent e) {
	            btnRegister.setForeground(Color.BLUE);
	        }

	        public void mouseExited(MouseEvent e) {
	            btnRegister.setForeground(Color.BLACK);
	        }
	    });

			
		panelBoton.add(btnRegister);
		
	    colocarIcono(boton, "../img/icono.png");

	    panelBoton.add(boton);
	    panelBoton.add(btnRegister);
	    
	    add(panelBoton, BorderLayout.SOUTH);
	    
	   
	}

	
	private void crearFormulario() {
		
		JPanel panelito = new JPanel (new SpringLayout());
		panelito.setBackground(Color.WHITE);
		panelito.setOpaque(true);
		
		//USUARIO
		JLabel lblEmail = new JLabel("*Usuario: ");
		lblEmail.setFont(fuente);
		
		//CAMBIOS PARA REDUCIR TAMAÑO DEL CAMPO DE TEXTO QUE ABARCA CASI TODA LA PANTALLA
		textoUsuario = new JTextField();
		
		/*textoUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
		    @Override
		    public void keyPressed(KeyEvent e) {

		        if(e.getKeyCode() == KeyEvent.VK_ENTER){
		            try {
						login();
					} catch (InvalidUserException e1) {
						// TODO Auto-generated catch block
						
						System.out.println(e1.getMessage());
					} catch (InvalidPasswordException e1) {
						// TODO Auto-generated catch block
						System.out.println(e1.getMessage());
					} //PRESIONAMOS ENTER PARA INICIAR
		        }

		        System.out.println("Tecla presionada: " + e.getKeyChar());
		    }
		});*/
		
		textoUsuario.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyPressed(KeyEvent e) {
		        if(e.getKeyCode() == KeyEvent.VK_ENTER) {

		            loginView(); 
		        }
		    }
		});
		
		textoUsuario.setFont(fuente);
		textoUsuario.setPreferredSize(new Dimension(180, 30));
		
		/*textoUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						login();
					} catch (InvalidUserException e1) {
						// TODO Auto-generated catch block
						System.out.println(e1.getMessage());
					} catch (InvalidPasswordException e1) {
						// TODO Auto-generated catch block
						System.out.println(e1.getMessage());
					}
				}
				System.out.println("Tecla que se presiono: " + e.getKeyChar());
			}
		});*/
		
		
		
		//Validacion input en tiempo real
		textoUsuario.getDocument().addDocumentListener(new DocumentListener() {

		    public void insertUpdate(DocumentEvent e) {
		        validarUsuarioTiempoReal();
		    }

		    public void removeUpdate(DocumentEvent e) {
		        validarUsuarioTiempoReal();
		    }

		    public void changedUpdate(DocumentEvent e) {
		        validarUsuarioTiempoReal();
		    }

		});

		
        
        /*lblUsuarioCorreccion = new JLabel("*Usuario obligatorio");
        lblUsuarioCorreccion.setForeground(Color.RED);
        lblUsuarioCorreccion.setFont(fuente.deriveFont(13f));*/
        
        lblUsuarioCorreccion = new JLabel(" ");
        lblUsuarioCorreccion.setForeground(Color.RED);
        lblUsuarioCorreccion.setFont(fuente.deriveFont(13f));
     
        
        //CONTRASEÑA
        JLabel lblContrasena = new JLabel("*Contraseña: ");
        lblContrasena.setFont(fuente);
        
        contrasena = new JPasswordField();
        
        //contrasena.addActionListener(e -> loginView());
        
        contrasena.setFont(fuente);
        contrasena.setPreferredSize(new java.awt.Dimension(180, 30));
        
        //Validacion input en tiempo real
        contrasena.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                validarContrasenaTiempoReal();
            }

            public void removeUpdate(DocumentEvent e) {
                validarContrasenaTiempoReal();
            }

            public void changedUpdate(DocumentEvent e) {
                validarContrasenaTiempoReal();
            }

        });

        /*lblContrasenaCorreccion = new JLabel("*Contraseña obligatoria");
        lblContrasenaCorreccion.setForeground(Color.RED);
        lblContrasenaCorreccion.setFont(fuente.deriveFont(13f));*/
        
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
		
		//add(panelito);
		
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
	
	//Metodo que actua cuando presionamos INICIAR
	/*private void login() {
		String resultado = validarCredenciales(textoUsuario.getText(),String.valueOf(contrasena.getPassword()));
		
		//Si el metodo validarCredenciales regresa OK, es porque no hay errores y se  indica que la sesion se inicio
        if(resultado.equals("OK")) {
            //JOptionPane.showMessageDialog( this,"Se inició sesión","Sesión iniciada",JOptionPane.INFORMATION_MESSAGE);
        	System.out.println("Sesión iniciada correctamente");
        	//Limpia los campos despues de iniciar sesión correctamente
        	textoUsuario.setText("");
            contrasena.setText("");
        }

	}*/
	
	//Metodo que actua cuando presionamos INICIAR
	private void login() throws InvalidUserException, InvalidPasswordExeption {
	    //Si es true no hay errores
	
			
			if(validarCredenciales( textoUsuario.getText(),String.valueOf(contrasena.getPassword()))) {
	    	//JOptionPane.showMessageDialog( this,"Se inició sesión","Sesión iniciada",JOptionPane.INFORMATION_MESSAGE);
	    	System.out.println("Sesión iniciada correctamente");
			}
	    
	    	MainView ventana = new MainView();//SE VE VENTANA
	    	
	    	
	    	window.dispose();
	
	        //Limpia los campos despues de iniciar sesión correctamente
	        textoUsuario.setText("");
	        contrasena.setText("");
	    
	}
	
	private void registro() {
		new Formulario();
		window.dispose();
	}
	
	//METODO PARA MOSTRAR ERROR DE USUARIO
    private void usuarioError(String mensaje) {
        lblUsuarioCorreccion.setText(mensaje);
    }

    //METODO PARA MOSTRAR ERROR DE CONTRASEÑA
    private void contrasenaError(String mensaje) {
        lblContrasenaCorreccion.setText(mensaje);
    }

    //Limpia errores
    private void limpiarErrorMensaje() {
        lblUsuarioCorreccion.setText(" ");
        lblContrasenaCorreccion.setText(" ");
    }
	
    //Metodo string para validar los campos del login que son usuario y contraseña
	/*public String validarCredenciales(String usuario, String password) {
		limpiarErrorMensaje(); //Se limpian errores anteriores
	    
	    boolean hayError = false; //Con esto se evalua si ambos estan vacios, para que aparezcan ambos mensajes

	    if(usuario.trim().isEmpty()) {
	        usuarioError("*Usuario obligatorio");
	        hayError = true; 
	    }

	    if(password.trim().isEmpty()) {
	        contrasenaError("*Contraseña obligatoria");
	        hayError = true; 
	    }

	    if(hayError) {
	        return "ERROR";
	    }
	    
	    //Se devuelve OK si todo esta bien
	    return "OK";
	}*/
	
    
    //Metodo booleano para validar los campos del login que son usuario y contraseña
    public boolean validarCredenciales(String usuario, String password) throws InvalidUserException, InvalidPasswordExeption {
   
   	 	limpiarErrorMensaje(); //Se limpian errores anteriores
   	 	
	    if(usuario.trim().isEmpty()) {
	        throw new InvalidUserException("Usuario obligatorio");
	    }
	
	    if(password.trim().isEmpty()) {
	        throw new InvalidPasswordExeption("Contraseña obligatoria");
	    }
	    
	    if(password.contains(" ")) {
	        throw new InvalidPasswordExeption("La contraseña no debe tener espacios");
	    }
	
	    if((usuario.equals("dianitha@gmail.com") && password.equals("12345")) || (usuario.equals("hash@gmail.com") && password.equals("abcs"))) {
	        return true;
	    }
	
	    if(usuario.equals("dianitha@gmail.com") || usuario.equals("hash@gmail.com")) {
	        throw new InvalidPasswordExeption("Contraseña incorrecta");
	    }
	
	    throw new InvalidUserException("Usuario no existe");
    }
	
	/*private void loginView() { 
		try {
			if(validarCredenciales(
					textoUsuario.getText(),
					String.valueOf(contrasena.getPassword())
	)) {
	
		JOptionPane.showMessageDialog(
		this,
		"Se inició la sesión",
		"Sesión iniciada",
		JOptionPane.INFORMATION_MESSAGE
	);
	
		new MainView();
		window.dispose();
		
		textoUsuario.setText("");
		contrasena.setText("");
	}
	
		} catch (InvalidUserException ex) {
		usuarioError("Usuario incorrecto");
		
		} catch (InvalidPasswordException ex) {
		contrasenaError("Contraseña incorrecta");
		}
	}*/ 
	
	private void loginView() {

        limpiarErrorMensaje();

        try {
            if(validarCredenciales(
                textoUsuario.getText(),
                String.valueOf(contrasena.getPassword())
            )) {

                JOptionPane.showMessageDialog(
                    this,
                    "Se inició la sesión",
                    "Sesión iniciada",
                    JOptionPane.INFORMATION_MESSAGE
                );

                new MainView();
                window.dispose();

                textoUsuario.setText(""); //Limpia los campos
                contrasena.setText("");
            }

        } catch (InvalidUserException e) {

            //usuarioError(e.getMessage()); //imprime usuario incorrecto
            usuarioError("Datos Incorrectos");
            
        } catch (InvalidPasswordExeption e) {
            //contrasenaError(e.getMessage()); //imprime contraseña incorrecta
            contrasenaError("Datos Incorrectos");
   
        }
    }
			
	//Validacion tiempo real usuario
	private void validarUsuarioTiempoReal() {

	    if(textoUsuario.getText().trim().isEmpty()) {
	        usuarioError("*Usuario obligatorio");
	    }else {
	        lblUsuarioCorreccion.setText(" ");
	    }

	}


	//Validacion tiempo real contraseña
	private void validarContrasenaTiempoReal() {

	    if(String.valueOf(contrasena.getPassword()).trim().isEmpty()) {
	        contrasenaError("*Contraseña obligatoria");
	    }else {
	        lblContrasenaCorreccion.setText(" ");
	    }

	}

	
	
}



