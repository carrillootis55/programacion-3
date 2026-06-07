package main;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class MyWindow extends JFrame {
	
	//Constructor
	public MyWindow() {
		
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(100,100);
		
		//setBounds(100,100,500,500);//Junta setSize y setLocation
		
		setResizable(false);
		setTitle("Mi Primer Ventana");
		
		//La ventana se coloca en el centro de la pantalla, no importa el tipo de pantalla
		setLocationRelativeTo(null);
		
		//Cambiar el icono de la ventana
		Toolkit toolkit = Toolkit.getDefaultToolkit();//Se importa
		
		Image icon = toolkit.getImage("src/img/icono.png");//Se importa
		
		setIconImage(icon);

		//Agregar panel
		MyPanel panel = new MyPanel();
		
		//Se muestra el panelito en la ventana, NECESARIO USAR add PARA QUE SE MUESTRE
		add(panel);

		//Al estar en el constructor se debe poner hasta el final
		setVisible(true);
		
		//Valida que lo agregado a la ventana se ejecute
		validate();
		repaint();
	}
}