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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Formulario extends JFrame{
	
	public Formulario() {
		setSize(500, 500); //lo reemplazo por pack
		
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
		
		JLabel lblTitulo = new JLabel("REGISTRO DE ALUMNO");
		add(lblTitulo, BorderLayout.NORTH);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);


		JPanel panelComponentes = new JPanel();
		panelComponentes.setLayout(new BoxLayout(panelComponentes, BoxLayout.Y_AXIS));
		panelComponentes.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		//JCheckBox chkAceptoCondiciones = new JCheckBox("", true);
		//(chkAceptoCondiciones);
		
		JRadioButton rbMujer = new JRadioButton("Mujer");
		add(rbMujer);
		JRadioButton rbHombre = new JRadioButton("Hombre");
		add(rbHombre);
		
		ButtonGroup bgSexo = new ButtonGroup();
		bgSexo.add(rbHombre);
		bgSexo.add(rbMujer);
		
		JLabel matricula= new JLabel("Matricula:");
		panelComponentes.add(matricula);
		JTextField txt1 = new JTextField(10);
		panelComponentes.add(txt1);
		
		//AGREGAR TURNO, FECHA DE NACIMIENTO, GENERO, DEJAR POR DEFECTO TURNO MATUTINO
		
		JLabel apellidoPaterno= new JLabel("Apellido Paterno:");
		panelComponentes.add(apellidoPaterno);
		JTextField txt2 = new JTextField(10);
		panelComponentes.add(txt2);
		
		JLabel apellidoMaterno= new JLabel("Apellido Materno:");
		panelComponentes.add(apellidoMaterno);
		JTextField txt3 = new JTextField(10);
		panelComponentes.add(txt3);
		
		JLabel alumno= new JLabel("Nombre del alumno:");
		panelComponentes.add(alumno);
		JTextField txt = new JTextField(10);
		panelComponentes.add(txt);
		
		JLabel grupo= new JLabel("Grupo:");//OPCION
		panelComponentes.add(grupo);
		JTextField txt6 = new JTextField(10);
		panelComponentes.add(txt6);
		
		
		JLabel nombreContacto= new JLabel("Nombre del Contacto de Emergencia:");
		panelComponentes.add(nombreContacto);
		JTextField txt5 = new JTextField(10);
		panelComponentes.add(txt5);
		
		JLabel contacto= new JLabel("Numero del Contacto de Emergencia:");
		panelComponentes.add(contacto);
		JTextField txt4 = new JTextField(10);
		panelComponentes.add(txt4);
		
		JLabel direccion= new JLabel("Domicilio (calle y numero):");
		panelComponentes.add(direccion);
		JTextField txt7 = new JTextField(10);
		panelComponentes.add(txt7);
		  
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
