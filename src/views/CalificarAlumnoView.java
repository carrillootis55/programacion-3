package views;

import java.awt.GridLayout;
import models.Alumno;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import lib.SpringUtilities;

public class CalificarAlumnoView extends JFrame{
	public JTextField txtArtes = new JTextField(10);
	public JTextField txtGeografia = new JTextField(10);
	public JTextField txtInformatica = new JTextField(10);
	
	public JButton btnGuardar = new JButton("Guardar");
	
	public CalificarAlumnoView(Alumno alumno) {
		//SPRING UTILITIES
		
		setTitle("Calificaciones");
		setSize(250, 200);
		setLayout(new java.awt.FlowLayout());
		
		add(new JLabel("Alumno: " + alumno.getNombre()));

		JPanel panelito= new JPanel(new SpringLayout());
		
		panelito.add(new JLabel("Artes: "));
		panelito.add(txtArtes);

		panelito.add(new JLabel("Geografia: "));
		panelito.add(txtGeografia);
		
		panelito.add(new JLabel("Informatica: "));
		panelito.add(txtInformatica);
	
		SpringUtilities.makeCompactGrid(panelito, 3, 2, 6, 6, 6, 6);
		/*	
		add(new JLabel("Artes"));
		add(new JLabel("Geografia"));
		add(new JLabel("Informatica"));
		*/
		add(panelito);
		add(btnGuardar);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
}
