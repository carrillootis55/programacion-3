package controllers;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import exceptions.InvalidPasswordException;
import exceptions.InvalidUserException;
import models.Teacher;
import repository.TeacherRepository;
import utils.PasswordUtils;
import views.Form;
import views.LoginView;
import views.MainView;

public class LoginController {

	private LoginView view;

	public LoginController(LoginView view) {

		this.view = view;

		assignListeners();
	}

	//=================================================================================================================================================================

	private void assignListeners() {

        //Boton login
        view.getBtnLogin().addActionListener(e -> login());

        //Enter usuario
        view.getTxtUsername().addActionListener(e -> login());

        //Enter contraseña
        view.getTxtPassword().addActionListener(e -> login());

        //Usuario validacion tiempo real
        view.getTxtUsername().getDocument().addDocumentListener(new DocumentListener() {

            public void insertUpdate(DocumentEvent e) {
            	validateUser();
            }

            public void removeUpdate(DocumentEvent e) {
            	validateUser();
            }

            public void changedUpdate(DocumentEvent e) {
            	validateUser();
            }
        });

        //Contraseña validacion tiempo real
        view.getTxtPassword().getDocument().addDocumentListener(new DocumentListener() {

            public void insertUpdate(DocumentEvent e) {
            	validatePassword();
            }

            public void removeUpdate(DocumentEvent e) {
            	validatePassword();
            }

            public void changedUpdate(DocumentEvent e) {
            	validatePassword();
            }
        });

        view.getTxtUsername().addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {

                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    login();
                }

                System.out.println(
                		"Tecla que se presiono: "
                		+ e.getKeyChar()
                );
            }
        });

        view.getTxtPassword().addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {

                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    login();
                }

                System.out.println(
                		"Tecla que se presiono: "
                		+ e.getKeyChar()
                );
            }
        });
    }

	//=================================================================================================================================================================

	//Login
	private void login() {

	    boolean isValid = true;

	    if (!validateUser()) {
	    	isValid = false;
	    }

	    if (!validatePassword()) {
	    	isValid = false;
	    }

	    if (!isValid) {
	    	return;
	    }

	    String userEmail =
	    		view.getTxtUsername().getText();

	    String password =
	    		String.valueOf(
	    				view.getTxtPassword().getPassword()
	    		);

	    try {

	        if (validateCredentials(userEmail, password)) {

	            JOptionPane.showMessageDialog(
	            		null,
	            		"Sesión iniciada correctamente"
	            );

	            TeacherRepository repository =
	            		new TeacherRepository();

                Teacher teacher =
                		repository.getTeacherByEmail(userEmail);

	            MainView mainView =
	            		new MainView(teacher);

	            mainView.configurePermissions(teacher);

	            new HomeController(mainView, teacher);

	            mainView.showView(MainView.HOME);

	            view.getWindow().dispose();
	        }

	    } catch (InvalidUserException e) {

	        view.setUsernameError(e.getMessage());

	    } catch (InvalidPasswordException e) {

	        view.setPasswordError(e.getMessage());

	    } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Error al obtener maestro"
            );
        }
	}

	//=================================================================================================================================================================

	//Validacion usuario
    private boolean validateUser() {

        if (view.getTxtUsername().getText().trim().isEmpty()) {

            view.setUsernameError("*Usuario obligatorio");

            return false;
        }

        view.setUsernameError(" ");

        return true;
    }

    //=================================================================================================================================================================

    private void openRegister() {

        Form form = new Form();

        Teacher teacher = new Teacher();

        teacher.setYear("1");
        teacher.setGroup("A");

        //HomeController home = new HomeController(new MainView());
        new FormController(form, teacher);

        form.setLocationRelativeTo(null);
        form.setVisible(true);

        //Cerrar login
        view.getWindow().dispose();
    }

    //=================================================================================================================================================================

    //Validacion contraseña
    private boolean validatePassword() {

        if (String.valueOf(
        		view.getTxtPassword().getPassword()
        	).trim().isEmpty()) {

            view.setPasswordError("*Contraseña obligatoria");

            return false;
        }

        view.setPasswordError(" ");

        return true;
    }

    //=================================================================================================================================================================

    //Validar credenciales
    private boolean validateCredentials(
    		String email,
    		String password
    ) throws InvalidUserException,
    		 InvalidPasswordException {

        if (email.trim().isEmpty()) {

            throw new InvalidUserException(
            		"Usuario obligatorio"
            );
        }

        if (password.trim().isEmpty()) {

            throw new InvalidPasswordException(
            		"Contraseña obligatoria"
            );
        }

        if (password.contains(" ")) {

            throw new InvalidPasswordException(
            		"La contraseña no debe tener espacios"
            );
        }

        try {

            TeacherRepository repository =
            		new TeacherRepository();

            // Buscamos al maestro por su email
            Teacher teacher =
            		repository.findByEmail(email);

            if (teacher == null) {

                throw new InvalidUserException(
                		"Email no registrado"
                );
            }

            // Verificamos la contraseña
            if (!PasswordUtils.checkPassword(
            		password,
            		teacher.getPassword()
            )) {

                throw new InvalidPasswordException(
                		"Datos invalidos"
                );
            }

            System.out.println("Login exitoso:");
            System.out.println("ID: " + teacher.getId());
            System.out.println("Nombre: " + teacher.getName());
            System.out.println("Email: " + teacher.getEmail());
            System.out.println("ROL: " + teacher.getRole());

            //System.out.println("ROL RECIBIDO: -" + maestro.getRole() + "-");

            return true;

	    } catch (SQLException e) {

	        System.out.println(
	        		"Error al conectar a la BD: "
	        		+ e.getMessage()
	        );

	        throw new InvalidUserException(
	        		"Error de conexión a la BD: "
	        		+ e.getMessage()
	        );
	    }
	}
}