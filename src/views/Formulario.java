package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Formulario extends JFrame{
	
	public Formulario() {
		setSize(300, 500); //lo reemplazo por pack
		
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
		
		JLabel lblTitulo = new JLabel("GRUPO A");
		add(lblTitulo, BorderLayout.NORTH);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		
		
	
		
		
		JPanel panelComponentes = new JPanel();
		panelComponentes.setLayout(new BoxLayout(panelComponentes, BoxLayout.Y_AXIS));
		panelComponentes.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		JLabel matricula= new JLabel("Matricula:");
		panelComponentes.add(matricula);
		JTextField txt1 = new JTextField(10);
		panelComponentes.add(txt1);
		
		
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
		
		JLabel contacto= new JLabel("Contacto de Emergencia:");
		panelComponentes.add(contacto);
		JTextField txt4 = new JTextField(10);
		panelComponentes.add(txt4);
		
		
		
		
		
		
		
		
		/*for(int i = 1; i < 10; i++) {
			JLabel lbl = new JLabel("Nombre " + i);
			panelComponentes.add(lbl);
			JTextField txt = new JTextField(10);
			panelComponentes.add(txt);
		}*/
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
