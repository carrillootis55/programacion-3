package views;
 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class MainView extends JFrame {
	private Font fuente = new Font("Times New Roman", Font.ITALIC, 14);
	
	public MainView() {
		setTitle("PRIMARIA");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		setLayout(new BorderLayout());
		 
		setMenu();
		setPanelInicio();
		
		pack(); //Ajusta la ventana al contenido
		setLocationRelativeTo(null); // Centra en pantalla
		setResizable(false);
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				System.out.println("La ventana se abrio");
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				int opcion = JOptionPane.showConfirmDialog(null, "quieres salir?", "Cerrar sistema", JOptionPane.YES_OPTION);
				if(opcion == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});
		
	}
	
	public void setMenu() {
		JMenuBar mb= new JMenuBar();
		setJMenuBar(mb);
		
		// MENU ARCHIVO
		JMenu archivo = new JMenu("SESIÓN");
		archivo.setFont(fuente);
		mb.add(archivo);

		JMenuItem cerrarSesion = new JMenuItem("Cerrar sesión");
		cerrarSesion.setFont(fuente);
		
		JMenuItem salir = new JMenuItem("Salir");
		salir.setFont(fuente);
		
		//Regresa al loginView
		salir.addActionListener(e -> {
			int opcion = JOptionPane.showConfirmDialog( this,"¿Desea salir del sistema?","Confirmar salida",JOptionPane.YES_NO_OPTION );
			
		    if(opcion == JOptionPane.YES_OPTION){
		        new LoginWindow(); // abre la ventana de login
		        dispose();         // cierra MainView

		    }

		});


		archivo.add(cerrarSesion);
		archivo.addSeparator();
		archivo.add(salir);
		 
		//MAESTRO
		JMenu maestro = new JMenu("MAESTRO");
		maestro.setFont(fuente);
		mb.add(maestro);
		 
		maestro.addSeparator();
		 
		JMenuItem perfil = new JMenuItem("Perfil");
		perfil.setFont(fuente);
		maestro.add(perfil);

		//ALUMNOS
		JMenu alumnos = new JMenu("ALUMNOS");
		alumnos.setFont(fuente);
		mb.add(alumnos);

		JMenuItem registrar = new JMenuItem("Registrar alumno");
		registrar.setFont(fuente);
		
		//Abrir formulario
		registrar.addActionListener(e -> {
            new Formulario(); 
        });
		
		JMenuItem lista = new JMenuItem("Lista de alumnos");
		lista.setFont(fuente);

		alumnos.add(registrar);
		alumnos.addSeparator();
		alumnos.add(lista);

		//CALIFICACIONES
		JMenu calificaciones = new JMenu("CALIFICACIONES");
		calificaciones.setFont(fuente);
		mb.add(calificaciones);
		 
		calificaciones.addSeparator();
		 
		JMenuItem ingresar = new JMenuItem("Ingresar calificaciones");
		ingresar.setFont(fuente);
		calificaciones.add(ingresar);
		 
	}

	private void setPanelInicio() {

	    JPanel panel = new JPanel();
	    panel.setBackground(Color.WHITE);
	    panel.setLayout(new GridBagLayout()); //centra vertical y horizontal
	    
	    panel.setPreferredSize(new Dimension(500, 500));//tamaño de la ventana

	    JPanel contenido = new JPanel();
	    contenido.setBackground(Color.WHITE);
	    contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));

	    JLabel titulo = new JLabel("¡Bienvenido al Sistema de Calificaciones!");
	    titulo.setAlignmentX(JLabel.CENTER_ALIGNMENT);
	    titulo.setFont(new Font("Times New Roman", Font.BOLD, 24));

	    JLabel mensaje = new JLabel("Seleccione una opción del menú superior para continuar");
	    mensaje.setAlignmentX(JLabel.CENTER_ALIGNMENT);
	    mensaje.setFont(new Font("Times New Roman", Font.ITALIC, 15));

	    contenido.add(titulo);
	    //contenido.add(Box.createVerticalStrut(20)); // espacio entre texto
	    contenido.add(mensaje);

	    panel.add(contenido);

	    add(panel, BorderLayout.CENTER);
	}
	

}
