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

	private Font font;

	private JTextField txtUsername;

	private JPasswordField txtPassword;

	private JLabel lblUsernameError;

	private JLabel lblPasswordError;

	private Color defaultButtonColor;

	private JButton btnLogin;

	public LoginView(LoginWindow window) {

		this.window = window;
		font = new Font("Times New Roman", Font.ITALIC, 17);

		setBackground(Color.WHITE);
		setOpaque(true);
		setLayout(new BorderLayout());

		initializeComponents();

	}

	private void initializeComponents() {

		createTitle();
		createForm();
		createButtons();

	}

	//Getters
	public JPasswordField getTxtPassword() {
		return txtPassword;
	}

	public LoginWindow getWindow() {
		return window;
	}

	public JTextField getTxtUsername() {
		return txtUsername;
	}

	public JLabel getLblUsernameError() {
		return lblUsernameError;
	}

	public JLabel getLblPasswordError() {
		return lblPasswordError;
	}

	public JButton getBtnLogin() {
		return btnLogin;
	}

	//METODO PARA CENTRAR INICIO DE SESION
	private void createTitle() {

		JLabel lblGreeting = new JLabel("Iniciar sesión", JLabel.CENTER);
		lblGreeting.setFont(font);
		lblGreeting.setHorizontalAlignment(JLabel.CENTER);
		//Se centra el texto

		add(lblGreeting, BorderLayout.NORTH);
	}

	//BOTON
	private void createButtons() {

		JPanel pnlButton = new JPanel();
		pnlButton.setBackground(Color.WHITE);
		pnlButton.setOpaque(true);

		btnLogin = new JButton("Iniciar");
		defaultButtonColor = btnLogin.getBackground();
		btnLogin.setBackground(Color.WHITE);
		btnLogin.setForeground(Color.BLACK);
		btnLogin.setFont(font);
		btnLogin.setToolTipText("Inicio de sesion");

		//Uso de mouse para cambiar color el boton
		btnLogin.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				btnLogin.setForeground(Color.BLUE);
			}

			public void mouseExited(MouseEvent e) {
				btnLogin.setForeground(Color.BLACK);
			}
		});

		setButtonIcon(btnLogin, "../img/icono.png");

		pnlButton.add(btnLogin);
		add(pnlButton, BorderLayout.SOUTH);

	}

	//Formulario
	private void createForm() {

		JPanel pnlForm = new JPanel(new SpringLayout());
		pnlForm.setBackground(Color.WHITE);
		pnlForm.setOpaque(true);

		//USUARIO
		JLabel lblUsername = new JLabel("*Usuario: ");
		lblUsername.setFont(font);

		//CAMBIOS PARA REDUCIR TAMAÑO DEL CAMPO DE TEXTO QUE ABARCA CASI TODA LA PANTALLA
		txtUsername = new JTextField();
		txtUsername.setFont(font);
		txtUsername.setPreferredSize(new Dimension(180, 30));

		lblUsernameError = new JLabel(" ");
		lblUsernameError.setForeground(Color.RED);
		lblUsernameError.setFont(font.deriveFont(13f));

		//CONTRASEÑA
		JLabel lblPassword = new JLabel("*Contraseña: ");
		lblPassword.setFont(font);

		txtPassword = new JPasswordField();
		txtPassword.setFont(font);
		txtPassword.setPreferredSize(new java.awt.Dimension(180, 30));

		lblPasswordError = new JLabel(" ");
		lblPasswordError.setForeground(Color.RED);
		lblPasswordError.setFont(font.deriveFont(13f));

		//SE AGREGA LO CREADO
		pnlForm.add(lblUsername);
		pnlForm.add(txtUsername);

		//Se agrega debajo del correo
		pnlForm.add(new JLabel());
		pnlForm.add(lblUsernameError);

		pnlForm.add(lblPassword);
		pnlForm.add(txtPassword);

		//Se agrega debajo de la contraseña
		pnlForm.add(new JLabel());
		pnlForm.add(lblPasswordError);

		//Iguala el ancho de todas las columnas al componente mas grande
		SpringUtilities.makeCompactGrid(pnlForm, 4, 2, 20, 10, 15, 15);

		add(pnlForm, BorderLayout.CENTER);

	}

	//icono boton
	private void setButtonIcon(JButton button, String path) {

		try {

			Image icon = ImageIO.read(getClass().getResource(path));
			icon = icon.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

			button.setIcon(new ImageIcon(icon));

		} catch (Exception ex) {

			System.out.println("No está la imagen del ícono");
		}
	}

	//Limpia errores
	public void clearErrors() {

		lblUsernameError.setText(" ");
		lblPasswordError.setText(" ");
	}

	public void setUsernameError(String message) {

		lblUsernameError.setText(message);
	}

	public void setPasswordError(String message) {

		lblPasswordError.setText(message);
	}

}