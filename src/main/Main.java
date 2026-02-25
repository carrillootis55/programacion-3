package main;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

import views.Formulario;
import views.LoginWindow;
import views.MainView;

public class Main {

	public static void main(String[] args) {
		
		//MiVentana ventanita= new MiVentana();
		//LoginWindow ventanita = new LoginWindow(); //ES QUE SE USA PARA QUE SE VEA LOGIN
		Formulario formulario= new Formulario();//COMENTADO 25/02/2026
		MainView ventana = new MainView();
		showOnScreen(1, ventana);
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
