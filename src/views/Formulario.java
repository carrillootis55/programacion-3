package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class Formulario extends JFrame{
	
	public Formulario() {
		setSize(500, 600); //lo reemplazo por pack
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(100,100);
		setResizable(false);
		setTitle("LISTA DE ALUMNOS");
		setLocationRelativeTo(null);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image icono = tk.getImage("src/img/icono.png");
		setIconImage(icono);
		
		inicializarComponentes();
		crearBotones();
		setVisible(true);
	}
	
	
	public void inicializarComponentes() {
		
		JLabel lblTitulo = new JLabel("REGISTRO DEL ALUMNO");
		add(lblTitulo, BorderLayout.NORTH);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);


		JPanel panelComponentes = new JPanel();
		panelComponentes.setLayout(new BoxLayout(panelComponentes, BoxLayout.Y_AXIS));
		panelComponentes.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		//JCheckBox chkAceptoCondiciones = new JCheckBox("", true);
		//(chkAceptoCondiciones);
		JLabel matricula = new JLabel("Matricula:");
		matricula.setAlignmentX(LEFT_ALIGNMENT);
		panelComponentes.add(matricula);

		JTextField txt1 = new JTextField(20);
		txt1.setMaximumSize(txt1.getPreferredSize()); 
		txt1.setAlignmentX(LEFT_ALIGNMENT);
		panelComponentes.add(txt1);
		
		//AGREGAR TURNO, FECHA DE NACIMIENTO, GENERO, DEJAR POR DEFECTO TURNO MATUTINO
		
		JLabel apellidoPaterno= new JLabel("Apellido Paterno:");
		apellidoPaterno.setAlignmentX(LEFT_ALIGNMENT);
		panelComponentes.add(apellidoPaterno);
		
		JTextField txt2 = new JTextField(20);
		txt2.setMaximumSize(txt2.getPreferredSize()); 
		txt2.setAlignmentX(LEFT_ALIGNMENT);
		panelComponentes.add(txt2);
		
		JLabel apellidoMaterno= new JLabel("Apellido Materno:");
		apellidoMaterno.setAlignmentX(LEFT_ALIGNMENT);
		panelComponentes.add(apellidoMaterno);
		
		JTextField txt3 = new JTextField(20);
		txt3.setMaximumSize(txt3.getPreferredSize()); 
		txt3.setAlignmentX(LEFT_ALIGNMENT);
		panelComponentes.add(txt3);
		
		JLabel alumno= new JLabel("Nombre del alumno:");
		alumno.setAlignmentX(LEFT_ALIGNMENT);
		panelComponentes.add(alumno);
		
		JTextField txt = new JTextField(20);
		txt.setMaximumSize(txt.getPreferredSize()); 
		txt.setAlignmentX(LEFT_ALIGNMENT);
		panelComponentes.add(txt);
		
		JLabel sexo = new JLabel("Sexo");
		panelComponentes.add(sexo);
	
		JRadioButton rbMujer = new JRadioButton("Mujer");
		add(rbMujer);
		panelComponentes.add(rbMujer);
		
		JRadioButton rbHombre = new JRadioButton("Hombre");
		add(rbHombre);
		panelComponentes.add(rbHombre);
		
		ButtonGroup bgSexo = new ButtonGroup();
		bgSexo.add(rbHombre);
		bgSexo.add(rbMujer);
		
		
		JLabel grupo= new JLabel("Grupo:");//OPCION
		panelComponentes.add(grupo);
		
		JRadioButton rbA = new JRadioButton("A");
		add(rbA);
		panelComponentes.add(rbA);
		
		JRadioButton rbB = new JRadioButton("B");
		add(rbB);
		panelComponentes.add(rbB);
		
		ButtonGroup bgGrupo = new ButtonGroup();
		bgGrupo.add(rbA);
		bgGrupo.add(rbB);

		
		JLabel nombreContacto= new JLabel("Nombre del Contacto de Emergencia:");
		nombreContacto.setAlignmentX(LEFT_ALIGNMENT);
		panelComponentes.add(nombreContacto);
		
		JTextField txt5 = new JTextField(5);
		txt5.setMaximumSize(txt5.getPreferredSize()); 
		txt5.setAlignmentX(LEFT_ALIGNMENT);
		panelComponentes.add(txt5);
		
		JLabel contacto= new JLabel("Numero del Contacto de Emergencia:");
		contacto.setAlignmentX(LEFT_ALIGNMENT);
		panelComponentes.add(contacto);
		
		JTextField txt4 = new JTextField(5);
		panelComponentes.add(txt4);
		
		JLabel parentesco= new JLabel("Parentesco con el alumno:");
		panelComponentes.add(parentesco);
		
		String[] opcionesParentesco = {"Padre","Madre","Hermana/Hermano",
				"Tio/Tia", "Abuelo/Abuela"};
		
		JComboBox<String> parentescoAlumno = new JComboBox<String>(opcionesParentesco);
		parentescoAlumno.setSelectedIndex(2);
		panelComponentes.add(parentescoAlumno);
		
		JLabel direccion= new JLabel("Domicilio (calle y numero):");
		panelComponentes.add(direccion);
		
		String[] opcionesDomicilio = {"Calle Altamirano Allende 1398","Calle Gomez Farias 4367","Calle Rosa Maria 2345",
				"Calle Diana Laura 2365", "Calle Ficus 3489", "Calle Aquiles Serdan 1993", "Calle Francisco y Madero 3740", "Calle Colosio 2521",
				"Calle 3 de Mayo 9757"};
		
		JComboBox<String> domicilio = new JComboBox<String>(opcionesDomicilio);
		domicilio.setSelectedIndex(2);
		panelComponentes.add(domicilio);
		
		
		
		//ESTAS OPCIONES SON PARA VER CUAL SE VE MEJOR
		/*JList lista = new JList(opcionesDomicilio);
		JScrollPane lista2 = new JScrollPane(lista);
		panelComponentes.add(lista2);
		
		
		/*JTextArea
		 * 
		JTextArea domicilio = new JTextArea(5, 30);	
		
		JScrollPane scrollPane = new JScrollPane(domicilio);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panelComponentes.add(scrollPane);
		
		*/
		
		JScrollPane scroll = new JScrollPane(panelComponentes);
		scroll.setHorizontalScrollBar(null);
		
		add(scroll);
		
	}
	
	private void crearBotones() {
	    JPanel panelBoton = new JPanel();
	    panelBoton.setBackground(Color.WHITE);
	    
	    JButton boton = new JButton("REGISTRAR ALUMNO");
	    boton.setBackground(Color.WHITE);
	    boton.setForeground(Color.BLACK);

	    panelBoton.add(boton);

	    add(panelBoton, BorderLayout.SOUTH);
	}

	
	
}
