package views;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainView extends JFrame {
	 
	 public MainView() {
		 
		 setSize(500,500);
		 setTitle("PRIMARIA");
		 setLocationRelativeTo(null);
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		 setLayout(new BorderLayout());
		 
		 setMenu();
		 setVisible(true);
	 }
	 
	 public void setMenu() {
		 JMenuBar mb= new JMenuBar();
		 setJMenuBar(mb);
		 
		 // MENU ARCHIVO
		 JMenu archivo = new JMenu("ARCHIVO");
		 mb.add(archivo);

		 JMenuItem cerrarSesion = new JMenuItem("Cerrar sesión");
		 JMenuItem salir = new JMenuItem("Salir");

		 archivo.add(cerrarSesion);
		 archivo.addSeparator();
		 archivo.add(salir);
		 
		 //MAESTRO
		 JMenu maestro = new JMenu("MAESTRO");
		 mb.add(maestro);
		 
		 maestro.addSeparator();
		 
		 JMenuItem perfil = new JMenuItem("Perfil");
		 maestro.add(perfil);

		 //ALUMNOS
		 JMenu alumnos = new JMenu("ALUMNOS");
		 mb.add(alumnos);

		 JMenuItem registrar = new JMenuItem("Registrar alumno");
		 JMenuItem lista = new JMenuItem("Lista de alumnos");

		 alumnos.add(registrar);
		 alumnos.addSeparator();
		 alumnos.add(lista);

		 //CALIFICACIONES
		 JMenu calificaciones = new JMenu("CALIFICACIONES");
		 mb.add(calificaciones);
		 
		 calificaciones.addSeparator();
		 
		 JMenuItem ingresar = new JMenuItem("Ingresar calificaciones");
		 calificaciones.add(ingresar);
		 
		 
	 }

}
