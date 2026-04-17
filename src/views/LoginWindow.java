package views;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

import controllers.LoginController;

public class LoginWindow extends JFrame {
	public LoginWindow() {
		
		//setSize(400, 400); lo reemplazo por pack
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(100,100);
		//setBounds(100,100,500,500);
		setResizable(false);
		setTitle("SISTEMA DE CALIFICACIONES");
		setLocationRelativeTo(null);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image icono = tk.getImage("src/img/icono.png");
		setIconImage(icono);
		
		LoginView panelito = new LoginView(this);
		add(panelito);
		
		new LoginController(panelito);
		
		pack();//Se usa pack para que se ajuste al tamaño real del texto que se tiene
		setMinimumSize(new Dimension(450, getHeight())); //Para que el titulo de SISTEMA se vea completo
		setLocationRelativeTo(null);//centra la ventana en la pantalla
		setVisible(true);
		
		//validate();
		//repaint();
	}

}