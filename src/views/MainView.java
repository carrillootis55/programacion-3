package views;
 
import java.awt.BorderLayout;
import java.awt.CardLayout;
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
import models.Maestro;

public class MainView extends JFrame {
	private Font fuente = new Font("Times New Roman", Font.ITALIC, 14);
	
	public JMenuItem salir;
    public JMenuItem mItemListaAlumnos;
    public JMenuItem mItemPerfil;
    public JMenuItem mItemCalificaciones;
    public JMenu inicio;
    

    public static final String HOME = "HOME";
    public static final String ALUMNOS = "ALUMNOS";
    public static final String MAESTRO = "MAESTRO";
    public static final String CALIFICACIONES = "CALIFICACIONES";

    
    private CardLayout cardLayout;
    private JPanel container;

    public AlumnosView alumnosPanel;
	public MaestroView maestroPanel;
	public CalificacionesView calificacionesPanel;
	public MainView() {
		setTitle("SISTEMA DE PRIMARIA");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		 
		setLayout(new BorderLayout());
		 
		setMenu();
		//setPanelInicio();
		
		createViews(); 
		//CAMBIOS 15 MAYO
		pack(); //Ajusta la ventana al contenido
		setLocationRelativeTo(null); // Centra en pantalla
		setResizable(true);//Aqui se podra ajustar el tamaño de la pantalla para que se aprecien mejor los datos de la tabla
		

		setVisible(true);
		
	}
	
	public void setMenu() {
		JMenuBar mb= new JMenuBar();
		setJMenuBar(mb);
		
		// MENU ARCHIVO
		JMenu archivo = new JMenu("SESIÓN");
		archivo.setFont(fuente);
		mb.add(archivo);

		
		salir = new JMenuItem("Cerrar sesión");
		salir.setFont(fuente);

		archivo.add(salir);
		
		//Inicio
		inicio = new JMenu("INICIO");
		inicio.setFont(fuente);
		mb.add(inicio);
		 
		//MAESTRO
		JMenu maestro = new JMenu("MAESTRO");
		maestro.setFont(fuente);
		mb.add(maestro);
		 		 
		mItemPerfil = new JMenuItem("Perfil");
		mItemPerfil.setFont(fuente);
		maestro.add(mItemPerfil);

		//ALUMNOS
		JMenu alumnos = new JMenu("ALUMNOS");
		alumnos.setFont(fuente);
		mb.add(alumnos);
	
        mItemListaAlumnos = new JMenuItem("Lista de alumnos");
        mItemListaAlumnos.setFont(fuente);

        alumnos.add(mItemListaAlumnos);

		//CALIFICACIONES
        JMenu menuCalificaciones = new JMenu("CALIFICACIONES");
        menuCalificaciones.setFont(fuente);
        mb.add(menuCalificaciones);
		 		 
		mItemCalificaciones = new JMenuItem("Calificaciones");
		mItemCalificaciones.setFont(fuente);
		menuCalificaciones.add(mItemCalificaciones);
		 
	}
	
    public int confirmExit() {
        return JOptionPane.showConfirmDialog(
            this,
            "¿Desea cerrar la sesion del sistema?",
            "Confirmar salida",
            JOptionPane.YES_NO_OPTION
        );
    }

	private JPanel setPanelInicio() {

	    JPanel panel = new JPanel();
	    panel.setBackground(Color.WHITE);
	    panel.setLayout(new GridBagLayout()); //centra vertical y horizontal
	    
	    panel.setPreferredSize(new Dimension(800, 500));//tamaño de la ventana

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

	    //add(panel, BorderLayout.CENTER);
	    
	    return panel;
	}
	
	private void createViews() {

	    cardLayout = new CardLayout();
	    container = new JPanel(cardLayout);

	    JPanel homePanel = setPanelInicio();

	    alumnosPanel = new AlumnosView();
	    maestroPanel = new MaestroView(new Maestro());
	    calificacionesPanel = new CalificacionesView();
	    
	    container.add(homePanel, HOME);
	    container.add(alumnosPanel, ALUMNOS);
	    container.add(maestroPanel, MAESTRO);
	    container.add(calificacionesPanel, CALIFICACIONES);
	    
	    add(container, BorderLayout.CENTER);
	}
	
	
	public void showView(String view) {
	    cardLayout.show(container, view);
	    //CAMBIOS 15 MAYO
	    //Vista completa para alumnos y calificaciones
	    if(view.equals(ALUMNOS)|| view.equals(CALIFICACIONES)) {
	        setExtendedState(JFrame.MAXIMIZED_BOTH);
	    }else {

	        //Ventana normal para otras vistas
	        setExtendedState(JFrame.NORMAL);
	    }
	}
	
		
	

}
