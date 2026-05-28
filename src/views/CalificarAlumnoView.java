package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import models.Alumno;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

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

	    lblAlumno.setHorizontalAlignment(SwingConstants.CENTER );

	    add(lblAlumno, BorderLayout.NORTH);

	    JPanel panelCentro = new JPanel();

	    panelCentro.setLayout(new BoxLayout( panelCentro,BoxLayout.Y_AXIS));
	    panelCentro.setBorder(BorderFactory .createEmptyBorder(10, 10, 10, 10) );

	    for (String materia : materias) {
	        JPanel fila = new JPanel(new GridBagLayout());
	        GridBagConstraints gbc = new GridBagConstraints();

	        JLabel lblMateria = new JLabel(materia + ": ");
	        JTextField txtCalificacion = new JTextField(5);
	        
	        JLabel lblError = new JLabel(" ");
	        lblError.setForeground(Color.RED);

	        camposCalificaciones.add(txtCalificacion);
	        labelsError.add(lblError);

	        gbc.gridx = 0;
	        gbc.gridy = 0;
	        gbc.anchor = GridBagConstraints.LINE_END; // Alínea el texto a la derecha de su celda
	        gbc.weightx = 0.5;                        // Empuja hacia el centro
	        gbc.insets = new Insets(5, 5, 5, 15);     
	        fila.add(lblMateria, gbc);

	        gbc.gridx = 1;
	        gbc.gridy = 0;
	        gbc.anchor = GridBagConstraints.LINE_START; // Alínea el input al inicio de su celda
	        gbc.weightx = 0.5;                          // Empuja hacia el centro
	        gbc.insets = new Insets(5, 0, 5, 5);
	        fila.add(txtCalificacion, gbc);

	        gbc.gridx = 2;
	        gbc.gridy = 0;
	        gbc.anchor = GridBagConstraints.LINE_START;
	        gbc.weightx = 0.0; 
	        gbc.insets = new Insets(5, 5, 5, 5);
	        fila.add(lblError, gbc);

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
	 public void cargarCalificaciones(List<Double> calificaciones) {

	        for (int i = 0; i < calificaciones.size(); i++) {
	        	camposCalificaciones.get(i).setText(String.valueOf(calificaciones.get(i)));
	        }
	    }


    public List<JTextField> getCamposCalificaciones() {
        return camposCalificaciones;
    }
    
    public List<JLabel> getLabelsError() {
        return labelsError;
    }
	
}
