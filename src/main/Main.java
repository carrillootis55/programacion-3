package main;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

import controllers.FormularioController;
import controllers.LoginController;
import views.Formulario;
import views.LoginView;
import views.LoginWindow;
import views.MainView;

public class Main {

	public static void main(String[] args) {
		
		//MiVentana ventanita= new MiVentana();
		//LoginWindow ventanita = new LoginWindow(); //SE USA PARA QUE SE VEA LOGIN
		//Formulario formulario = new Formulario(); //SE USA PARA QUE SE VEA EL FORMULARIO
		//new FormularioController(formulario);
		//new LoginController(view);

        //ventana.add(vista);

        LoginWindow ventana = new LoginWindow();
        LoginView vista = new LoginView(ventana);

        ventana.setContentPane(vista);
        new LoginController(vista);    

        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
		
		//MainView ventana = new MainView();//SE VE VENTANA
		//showOnScreen(0, ventana);
	}
	
	
	
	public static void showOnScreen(int screen, JFrame frame ) {
	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    GraphicsDevice[] gd = ge.getScreenDevices();
	    int width = 0, height = 0;
	    
	    if( screen > -1 && screen < gd.length ) {
	        width = gd[screen].getDefaultConfiguration().getBounds().width;
	        height = gd[screen].getDefaultConfiguration().getBounds().height;
	        frame.setLocation(
	            ((width / 2) - (frame.getSize().width / 2)) + gd[screen].getDefaultConfiguration().getBounds().x, 
	            ((height / 2) - (frame.getSize().height / 2)) + gd[screen].getDefaultConfiguration().getBounds().y
	        );
	    } else {
	        throw new RuntimeException( "No se encontro la pantalla" );
	    }
	}
	

}
