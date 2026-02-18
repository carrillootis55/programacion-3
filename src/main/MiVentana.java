package main;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class MiVentana extends JFrame {
	
	//Constructor
	public MiVentana() {
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(100,100);
		//setBounds(100,100,500,500);//Junta setSize y setLocation
		setResizable(false);
		setTitle("Mi Primer Ventana");
		setLocationRelativeTo(null);//La ventana se coloca en el centro de la pantalla, no importa el tipo de pantalla
		
		//Cambiar el icono de la ventana
		Toolkit tk= Toolkit.getDefaultToolkit();//Se importa
		Image icono =tk.getImage("src/img/icono.png");//Se importa
		setIconImage(icono);

		//Agregar panel
		MiPanel panelito= new MiPanel();
		add(panelito);//Se muestra el panelito en la ventana, NECESARIO USAR add PARA QUE SE MUESTRE

		
		setVisible(true); //Al estar en el constructor se debe poner hasta el final
		
		validate();//Valida que lo agregado a la ventana se ejecute
		repaint();
		
	}
	

}