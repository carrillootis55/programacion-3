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
import models.Teacher;

public class MainView extends JFrame {
	private Font font = new Font("Times New Roman", Font.ITALIC, 14);
	
	public JMenuItem logoutItem;
    public JMenuItem studentsListItem;
    public JMenuItem profileItem;
    public JMenuItem qualificationsItem;
    public JMenu homeMenu;
    
    public JMenu teacherMenu;
    public JMenu studentsMenu;
    public JMenu qualificationsMenu;
    
    public JMenu adminMenu;
    public JMenuItem adminProfileItem;
    public JMenuItem teachersListItem;
    

    public static final String HOME = "HOME";
    public static final String STUDENTS = "STUDENTS";
    public static final String TEACHER = "TEACHER";
    public static final String QUALIFICATIONS = "QUALIFICATIONS";
    public static final String ADMIN_PROFILE = "ADMIN_PROFILE";
	public static final String TEACHERS_LIST = "TEACHERS_LIST";

    private CardLayout cardLayout;
    private JPanel containerPanel;

    public StudentsView studentsPanel;
	public TeacherView teacherPanel;
	public QualificationsView qualificationsPanel;
	
	public AdminView adminPanel;
	public TeacherListView teachersListPanel;
	
	private Teacher teacher;
	
	public MainView(Teacher teacher) {
		this.teacher = teacher;
		
		setTitle("SISTEMA DE CALIFICACIONES DE SECUNDARIA");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		 
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
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		// MENU ARCHIVO
		JMenu sessionMenu = new JMenu("SESIÓN");
		sessionMenu.setFont(font);
		menuBar.add(sessionMenu);

		
		logoutItem = new JMenuItem("Cerrar sesión");
		logoutItem.setFont(font);

		sessionMenu.add(logoutItem);
		
		//Inicio
		homeMenu = new JMenu("INICIO");
		homeMenu.setFont(font);
		menuBar.add(homeMenu);
		 
		//MAESTRO
		/*JMenu maestro = new JMenu("MAESTRO");
		maestro.setFont(fuente);
		mb.add(maestro);
		 		 
		mItemPerfil = new JMenuItem("Perfil");
		mItemPerfil.setFont(fuente);
		maestro.add(mItemPerfil);*/
		
		teacherMenu = new JMenu("MAESTRO");
        teacherMenu.setFont(font);
        menuBar.add(teacherMenu);
        profileItem = new JMenuItem("Perfil");
        profileItem.setFont(font);
        teacherMenu.add(profileItem);

		//ALUMNOS
		/*JMenu alumnos = new JMenu("ALUMNOS");
		alumnos.setFont(fuente);
		mb.add(alumnos);
	
        mItemListaAlumnos = new JMenuItem("Lista de alumnos");
        mItemListaAlumnos.setFont(fuente);
        alumnos.add(mItemListaAlumnos);*/
        
        studentsMenu = new JMenu("ALUMNOS");
        studentsMenu.setFont(font);
        menuBar.add(studentsMenu);
        studentsListItem = new JMenuItem("Lista de alumnos");
        studentsListItem.setFont(font);
        studentsMenu.add(studentsListItem);

		//CALIFICACIONES
        /*JMenu menuCalificaciones = new JMenu("CALIFICACIONES");
        menuCalificaciones.setFont(fuente);
        mb.add(menuCalificaciones);
		 		 
		mItemCalificaciones = new JMenuItem("Calificaciones");
		mItemCalificaciones.setFont(fuente);
		menuCalificaciones.add(mItemCalificaciones);*/
        
        qualificationsMenu = new JMenu("CALIFICACIONES");
        qualificationsMenu.setFont(font);
        menuBar.add(qualificationsMenu);
        qualificationsItem = new JMenuItem("Calificaciones");
        qualificationsItem.setFont(font);
        qualificationsMenu.add(qualificationsItem);
		
		//ADMIN
		adminMenu = new JMenu("ADMIN");
		adminMenu.setFont(font);
		menuBar.add(adminMenu);
		adminProfileItem = new JMenuItem("Perfil");
		adminProfileItem.setFont(font);
		adminMenu.add(adminProfileItem);
		teachersListItem = new JMenuItem("Lista Maestros");
		teachersListItem.setFont(font);
		adminMenu.add(teachersListItem);
		
		adminMenu.setVisible(false);
		 
	}
	
	/*public void configurarPermisos(Maestro maestro) {

		//Si es admin
		 if(maestro.getRole() != null &&maestro.getRole().equalsIgnoreCase("ADMIN")) {
            admin.setVisible(true);;
            menuAlumnos.setVisible(false);
            menuCalificaciones.setVisible(false);

		}else {
			//Ocultar admin
            admin.setVisible(false);

            //Mostrar menus normales
            menuMaestro.setVisible(true);
            menuAlumnos.setVisible(true);
            menuCalificaciones.setVisible(true);
		}
	}*/
	
	public void configurePermissions(Teacher teacher) {

	    //Obtener rol seguro
	    String role = teacher.getRole();

	    //Verificar si es ADMIN
	    if (role != null && role.trim().equalsIgnoreCase("ADMIN")) {

	        adminMenu.setVisible(true);
	        teacherMenu.setVisible(false);
	        studentsMenu.setVisible(false);
	        qualificationsMenu.setVisible(false);

	    } else {
	        adminMenu.setVisible(false);
	        teacherMenu.setVisible(true);
	        studentsMenu.setVisible(true);
	        qualificationsMenu.setVisible(true);
	    }
	}
	
    public int confirmExit() {
        return JOptionPane.showConfirmDialog(
            this,
            "¿Desea cerrar la sesion del sistema?",
            "Confirmar salida",
            JOptionPane.YES_NO_OPTION
        );
    }

	private JPanel setHomePanel() {

	    JPanel panel = new JPanel();
	    panel.setBackground(Color.WHITE);
	    panel.setLayout(new GridBagLayout()); //centra vertical y horizontal
	    
	    panel.setPreferredSize(new Dimension(800, 500));//tamaño de la ventana

	    JPanel contentPanel = new JPanel();
	    contentPanel.setBackground(Color.WHITE);
	    contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

	    JLabel titleLabel = new JLabel("¡Bienvenido al Sistema de Calificaciones!");
	    titleLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
	    titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));

	    JLabel messageLabel = new JLabel("Seleccione una opción del menú superior para continuar");
	    messageLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
	    messageLabel.setFont(new Font("Times New Roman", Font.ITALIC, 15));

	    contentPanel.add(titleLabel);
	    //contenido.add(Box.createVerticalStrut(20)); // espacio entre texto
	    contentPanel.add(messageLabel);

	    panel.add(contentPanel);

	    //add(panel, BorderLayout.CENTER);
	    
	    return panel;
	}
	
	private void createViews() {

	    cardLayout = new CardLayout();
	    containerPanel = new JPanel(cardLayout);

	    JPanel homePanel = setHomePanel();

	    studentsPanel = new StudentsView();
	    //maestroPanel = new MaestroView(new Maestro());
	    teacherPanel = new TeacherView(teacher);
	    qualificationsPanel = new QualificationsView();
	    
	    //adminPanel = new AdminView(new Maestro());s
	    adminPanel = new AdminView(teacher);

		teachersListPanel = new TeacherListView();
	    
	    containerPanel.add(homePanel, HOME);
	    containerPanel.add(studentsPanel, STUDENTS);
	    containerPanel.add(teacherPanel, TEACHER);
	    containerPanel.add(qualificationsPanel, QUALIFICATIONS);
	    
	    containerPanel.add(adminPanel, ADMIN_PROFILE);

		containerPanel.add(teachersListPanel, TEACHERS_LIST);
	    
	    add(containerPanel, BorderLayout.CENTER);
	}
	
	
	public void showView(String view) {
	    cardLayout.show(containerPanel, view);
	    //Vista completa para alumnos y calificaciones
	    if(view.equals(STUDENTS)|| view.equals(QUALIFICATIONS) || view.equals(TEACHERS_LIST)) {
	        setExtendedState(JFrame.MAXIMIZED_BOTH);
	    }else {

	        //Ventana normal para otras vistas
	        setExtendedState(JFrame.NORMAL);
	    }
	    
	}
		
	

}