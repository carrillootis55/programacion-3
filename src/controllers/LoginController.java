package controllers;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import views.Formulario;
import exceptions.InvalidPasswordException;
import exceptions.InvalidUserException;
import views.LoginView;
import views.MainView;

public class LoginController {

	private LoginView view;
	
	public LoginController(LoginView view) {
		this.view = view;
		assignListeners();
		
	}
	
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
        
  
        //Boton registrar
        view.getBtnRegister().addActionListener(e -> abrirRegistro());
    }
	
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

	            //new MainView();
	            MainView main = new MainView();
	            new HomeController(main);
	            
	            view.getWindow().dispose();
	        }

	    } catch (InvalidUserException e) {
	        view.setErrorUsuario(e.getMessage());

	    } catch (InvalidPasswordException e) {
	        view.setErrorContrasena(e.getMessage());
	    }
	}
	
	
	//Validacion usuario
    private boolean validarUsuario() {
        if (view.getTextoUsuario().getText().trim().isEmpty()) {
            view.setErrorUsuario("*Usuario obligatorio");
            return false;
        }
        view.setErrorUsuario(" ");
        
        return true;
    }
    
    private void abrirRegistro() {

        Formulario formulario = new Formulario();
        
        //HomeController home = new HomeController(new MainView());
        new FormularioController(formulario);
        
        formulario.setLocationRelativeTo(null);
        formulario.setVisible(true);

        //Cerrar login
        view.getWindow().dispose();
    }
    
    //Validacion contraseña
    private boolean validarContrasena() {
        if (String.valueOf(view.getContrasena().getPassword()).trim().isEmpty()) {
            view.setErrorContrasena("*Contraseña obligatoria");
            return false;
        }
        view.setErrorContrasena(" ");
        
        return true;

    }
    
    //Validar credenciales
    private boolean validarCredenciales(String usuario, String password)
            throws InvalidUserException, InvalidPasswordException {

        if (usuario.trim().isEmpty()) {
            throw new InvalidUserException("Usuario obligatorio");
        }

        if (password.trim().isEmpty()) {
            throw new InvalidPasswordException("Contraseña obligatoria");
        }

        if (password.contains(" ")) {
            throw new InvalidPasswordException("La contraseña no debe tener espacios");
        }

        if ((usuario.equals("dianitha@gmail.com") && password.equals("12345")) ||
            (usuario.equals("hash@gmail.com") && password.equals("abcs"))||
            (usuario.equals("dmedinam2108@gmail.com") && password.equals("2127"))
            ) {
            return true;
        }

        if (usuario.equals("dianitha@gmail.com") || usuario.equals("hash@gmail.com") ||           
        (usuario.equals("dmedinam2108@gmail.com"))){
            throw new InvalidPasswordException("Contraseña incorrecta");
        }

        throw new InvalidUserException("Usuario no existe");
    }

    
	
}
