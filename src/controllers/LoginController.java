package controllers;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import utils.PasswordUtils;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import views.Formulario;
import exceptions.InvalidPasswordException;
import exceptions.InvalidUserException;
import models.Maestro;
import views.LoginView;
import views.MainView;
import java.sql.SQLException;
import repository.MaestroRepository;


public class LoginController {

	private LoginView view;
	
	public LoginController(LoginView view) {
		this.view = view;
		assignListeners();
		
	}
	//=================================================================================================================================================================
	private void assignListeners() {

        //Boton login
        view.getBotonLogin().addActionListener(e -> login());

        //Enter usuario
        view.getTextoUsuario().addActionListener(e -> login());

        //Enter contraseña
        view.getContrasena().addActionListener(e -> login());

        //Usuario validacion tiempo real
        view.getTextoUsuario().getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { validarUsuario(); }
            public void removeUpdate(DocumentEvent e) { validarUsuario(); }
            public void changedUpdate(DocumentEvent e) { validarUsuario(); }
        });

        //Contraseña validacion tiempo real
        view.getContrasena().getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { validarContrasena(); }
            public void removeUpdate(DocumentEvent e) { validarContrasena(); }
            public void changedUpdate(DocumentEvent e) { validarContrasena(); }
        });
      
        view.getTextoUsuario().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    login(); 
                }

                System.out.println("Tecla que se presiono: " + e.getKeyChar());
            }
        });

        view.getContrasena().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    login();
                }

                System.out.println("Tecla que se presiono: " + e.getKeyChar());
            }
        });
        
  
    }
	//=================================================================================================================================================================
	//Login
	private void login() {

	    boolean valido = true;

	    if (!validarUsuario()) {
	    	valido = false;
	    }
	    if (!validarContrasena()) {
	    	valido = false;
	    }

	    if (!valido) {
	    	return;
	    }

	    String usuario = view.getTextoUsuario().getText();
	    String password = String.valueOf(view.getContrasena().getPassword());

	    try {

	        if (validarCredenciales(usuario, password)) {

	            JOptionPane.showMessageDialog(null, "Sesión iniciada correctamente");
	            
	            MaestroRepository repository = new MaestroRepository();

                Maestro maestro =  repository.obtenerMaestroPorEmail(usuario);
	            //new MainView();
                
	            MainView main = new MainView(maestro);
	            
	            main.configurarPermisos(maestro);
	            
	            new HomeController(main,maestro);
	       
	            main.showView(MainView.HOME);
	            
	            view.getWindow().dispose();
	        }

	    } catch (InvalidUserException e) {
	        view.setErrorUsuario(e.getMessage());

	    } catch (InvalidPasswordException e) {
	        view.setErrorContrasena(e.getMessage());
	    }catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Error al obtener maestro"
            );
        }
	    
	    
	}
	
	//=================================================================================================================================================================
	//Validacion usuario
    private boolean validarUsuario() {
        if (view.getTextoUsuario().getText().trim().isEmpty()) {
            view.setErrorUsuario("*Usuario obligatorio");
            return false;
        }
        view.setErrorUsuario(" ");
        
        return true;
    }
  //=================================================================================================================================================================
    private void abrirRegistro() {

        Formulario formulario = new Formulario();
        
        Maestro maestro = new Maestro();

        maestro.setAnio("1");
        maestro.setGrupo("A");
        
        //HomeController home = new HomeController(new MainView());
        new FormularioController(formulario, maestro);
        
        formulario.setLocationRelativeTo(null);
        formulario.setVisible(true);

        //Cerrar login
        view.getWindow().dispose();
    }
  //=================================================================================================================================================================
    //Validacion contraseña
    private boolean validarContrasena() {
        if (String.valueOf(view.getContrasena().getPassword()).trim().isEmpty()) {
            view.setErrorContrasena("*Contraseña obligatoria");
            return false;
        }
        view.setErrorContrasena(" ");
        
        return true;

    }
  //=================================================================================================================================================================
    //Validar credenciales
    private boolean validarCredenciales(String email, String password)
            throws InvalidUserException, InvalidPasswordException {

        if (email.trim().isEmpty()) {
            throw new InvalidUserException("Usuario obligatorio");
        }

        if (password.trim().isEmpty()) {
            throw new InvalidPasswordException("Contraseña obligatoria");
        }

        if (password.contains(" ")) {
            throw new InvalidPasswordException("La contraseña no debe tener espacios");
        }

        try {
            MaestroRepository repository = new MaestroRepository();
            
            // Buscamos al maestro por su email
            models.Maestro maestro = repository.buscarEmail(email);
            
            if (maestro == null) {
                throw new InvalidUserException("Email no registrado");
            }

            // Verificamos la contraseña
            if (!PasswordUtils.checkPassword(password, maestro.getPassword())) {
                throw new InvalidPasswordException("Contraseña incorrecta");
            }
            System.out.println("Login exitoso:");
            System.out.println("ID: " + maestro.getId());
            System.out.println("Nombre: " + maestro.getNombre());
            System.out.println("Email: " + maestro.getEmail());
            System.out.println("ROL: " + maestro.getRole());
            //System.out.println("ROL RECIBIDO: -" + maestro.getRole() + "-");
            return true;
	        } catch (SQLException e) {
	        System.out.println("Error al conectar a la BD: " + e.getMessage());
	        throw new InvalidUserException("Error de conexión a la BD: " + e.getMessage());
	    }
	}



    
	
}
