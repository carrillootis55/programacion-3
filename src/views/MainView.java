package views;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainView extends JFrame {
	 
	 public MainView() {
		 
		 setSize(500,500);
		 setTitle("SISTEMA");
		 setLocationRelativeTo(null);
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 setMenu();
		 setVisible(true);
	 }
	 
	 public void setMenu() {
		 JMenuBar mb= new JMenuBar();
		 setJMenuBar(mb);
		 
		 JMenu menu= new JMenu("Menu");
		 mb.add(menu);
		 menu.addSeparator();
		 
		 JMenuItem perfilMaestro= new JMenuItem("Perfil");
		 menu.add(perfilMaestro);
		 
		 JMenu alumnos= new JMenu("Alumnos");
		 mb.add(alumnos);
		 menu.addSeparator();
		 
		 JMenuItem registrarAlumno = new JMenuItem("Registrar Alumno");
		 alumnos.add(registrarAlumno);
		 alumnos.addSeparator();
		 
		 JMenuItem lista = new JMenuItem("Lista Alumnos");
		 alumnos.add(lista);
		 alumnos.addSeparator();
		 
		 JMenuItem calificacion= new JMenuItem("Ingresar Calificaciones");
		 alumnos.add(calificacion);
		 alumnos.addSeparator();
		 
		 
	 }

}
