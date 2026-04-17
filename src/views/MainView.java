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


public class MainView extends JFrame {
	private Font fuente = new Font("Times New Roman", Font.ITALIC, 14);
	
	public JMenuItem salir;
    public JMenuItem mItemRegistrar;
    public JMenuItem mItemListaAlumnos;
    public JMenu inicio;
    

    public static final String HOME = "HOME";
    public static final String ALUMNOS = "ALUMNOS";

    private CardLayout cardLayout;
    private JPanel container;

    public AlumnosView alumnosPanel;
	
	public MainView() {
		setTitle("PRIMARIA");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		setLayout(new BorderLayout());
		 
		setMenu();
		//setPanelInicio();
		
		createViews(); 
		
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

		JMenuItem cerrarSesion = new JMenuItem("Cerrar sesión");
		cerrarSesion.setFont(fuente);
		
		salir = new JMenuItem("Salir");
		salir.setFont(fuente);

		archivo.add(cerrarSesion);
		archivo.addSeparator();
		archivo.add(salir);
		
		//Inicio
		inicio = new JMenu("INICIO");
		inicio.setFont(fuente);
		mb.add(inicio);
		 
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
		
		mItemRegistrar = new JMenuItem("Registrar alumno");
        mItemRegistrar.setFont(fuente);

        mItemListaAlumnos = new JMenuItem("Lista de alumnos");
        mItemListaAlumnos.setFont(fuente);

        alumnos.add(mItemRegistrar);
        alumnos.addSeparator();
        alumnos.add(mItemListaAlumnos);

		//CALIFICACIONES
		JMenu calificaciones = new JMenu("CALIFICACIONES");
		calificaciones.setFont(fuente);
		mb.add(calificaciones);
		 
		calificaciones.addSeparator();
		 
		JMenuItem ingresar = new JMenuItem("Ingresar calificaciones");
		ingresar.setFont(fuente);
		calificaciones.add(ingresar);
		 
	}
	
    public int confirmExit() {
        return JOptionPane.showConfirmDialog(
            this,
            "¿Desea salir del sistema?",
            "Confirmar salida",
            JOptionPane.YES_NO_OPTION
        );
    }

	private JPanel setPanelInicio() {

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

	    //add(panel, BorderLayout.CENTER);
	    
	    return panel;
	}
	
	private void createViews() {

	    cardLayout = new CardLayout();
	    container = new JPanel(cardLayout);

	    JPanel homePanel = setPanelInicio();

	    alumnosPanel = new AlumnosView();

	    container.add(homePanel, HOME);
	    container.add(alumnosPanel, ALUMNOS);

	    add(container, BorderLayout.CENTER);
	}
	
	
	public void showView(String view) {
	    cardLayout.show(container, view);
	}
	
		
	

}
