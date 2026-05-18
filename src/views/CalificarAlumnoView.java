package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import models.Alumno;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import lib.SpringUtilities;

public class CalificarAlumnoView extends JFrame{
	
	private List<JTextField> camposCalificaciones;
	private List<JLabel> labelsError;
	
	public JButton btnGuardar = new JButton("Guardar");
	
	public CalificarAlumnoView(Alumno alumno,List<String> materias) {

	    setTitle("Calificaciones");

	    setSize(320, 220);

	    setLayout(new BorderLayout());

	    camposCalificaciones = new ArrayList<>();

	    labelsError = new ArrayList<>();

	    JLabel lblAlumno =new JLabel( "Alumno: " + alumno.getNombre() + " " + alumno.getApellidoPaterno() );

	    lblAlumno.setHorizontalAlignment(JLabel.CENTER );

	    add(lblAlumno, BorderLayout.NORTH);

	    JPanel panelCentro = new JPanel();

	    panelCentro.setLayout(new BoxLayout( panelCentro,BoxLayout.Y_AXIS));
	    panelCentro.setBorder(javax.swing.BorderFactory .createEmptyBorder(5, 10, 5, 10) );

	    for (String materia : materias) {

	    	JPanel fila = new JPanel( new FlowLayout( FlowLayout.LEFT, 5, 2 ));

	        JLabel lblMateria = new JLabel(materia + ": ");

	        JTextField txtCalificacion =new JTextField(5);

	        JLabel lblError =new JLabel(" ");

	        lblError.setForeground(Color.RED);

	        camposCalificaciones.add(txtCalificacion );

	        labelsError.add(lblError);

	        fila.add(lblMateria);
	        fila.add(txtCalificacion);
	        fila.add(lblError);

	        panelCentro.add(fila);
	    }

	    add(panelCentro, BorderLayout.CENTER);

	    JPanel panelBoton = new JPanel();

	    panelBoton.add(btnGuardar);

	    add(panelBoton, BorderLayout.SOUTH);

	    setDefaultCloseOperation(
	            JFrame.DISPOSE_ON_CLOSE
	    );
	}


    public List<JTextField> getCamposCalificaciones() {
        return camposCalificaciones;
    }
    
    public List<JLabel> getLabelsError() {
        return labelsError;
    }
	
}
