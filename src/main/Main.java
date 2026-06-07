package main;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

import controllers.FormController;
import controllers.LoginController;
import views.Form;
import views.LoginView;
import views.LoginWindow;
import views.MainView;

public class Main {

	public static void main(String[] args) {
		
		//MiVentana window = new MiVentana();
		//LoginWindow window = new LoginWindow(); //SE USA PARA QUE SE VEA LOGIN
		//Form form = new Form(); //SE USA PARA QUE SE VEA EL FORMULARIO
		//new FormController(form);
		//new LoginController(view);

        //window.add(view);

        LoginWindow window = new LoginWindow();
        LoginView view = new LoginView(window);

        window.setContentPane(view);
        
        new LoginController(view);    

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
		
		//MainView window = new MainView();//SE VE VENTANA
		//showOnScreen(0, window);
	}
	
	
	
	public static void showOnScreen(int screen, JFrame frame ) {
		
	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    GraphicsDevice[] gd = ge.getScreenDevices();
	    
	    int width = 0;
	    int height = 0;
	    
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