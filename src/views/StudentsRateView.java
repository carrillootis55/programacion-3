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

import models.Student;

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

public class StudentsRateView extends JFrame{
	
	private List<JTextField> gradeFields;
	private List<JLabel> errorLabels;
	
	public JButton btnSave = new JButton("Guardar");
	
	public StudentsRateView(Student student, List<String> subjects) {

	    setTitle("Calificaciones");

	    setSize(320, 220);

	    setLayout(new BorderLayout());

	    gradeFields = new ArrayList<>();

	    errorLabels = new ArrayList<>();

	    JLabel lblStudent = new JLabel(
	            "Alumno: " + student.getName() + " " + student.getFatherLastName()
	    );

	    lblStudent.setHorizontalAlignment(SwingConstants.CENTER);

	    add(lblStudent, BorderLayout.NORTH);

	    JPanel centerPanel = new JPanel();

	    centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
	    centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

	    for (String subject : subjects) {
	        
	        JPanel rowPanel = new JPanel(new GridBagLayout());
	        
	        GridBagConstraints gbc = new GridBagConstraints();

	        JLabel lblSubject = new JLabel(subject + ": ");
	        
	        JTextField txtGrade = new JTextField(5);
	        
	        JLabel lblError = new JLabel(" ");
	        lblError.setForeground(Color.RED);

	        gradeFields.add(txtGrade);
	        errorLabels.add(lblError);

	        gbc.gridx = 0;
	        gbc.gridy = 0;
	        gbc.anchor = GridBagConstraints.LINE_END; // Alínea el texto a la derecha de su celda
	        gbc.weightx = 0.5;                        // Empuja hacia el centro
	        gbc.insets = new Insets(5, 5, 5, 15);
	        
	        rowPanel.add(lblSubject, gbc);

	        gbc.gridx = 1;
	        gbc.gridy = 0;
	        gbc.anchor = GridBagConstraints.LINE_START; // Alínea el input al inicio de su celda
	        gbc.weightx = 0.5;                          // Empuja hacia el centro
	        gbc.insets = new Insets(5, 0, 5, 5);
	        
	        rowPanel.add(txtGrade, gbc);

	        gbc.gridx = 2;
	        gbc.gridy = 0;
	        gbc.anchor = GridBagConstraints.LINE_START;
	        gbc.weightx = 0.0;
	        gbc.insets = new Insets(5, 5, 5, 5);
	        
	        rowPanel.add(lblError, gbc);

	        centerPanel.add(rowPanel);
	    }
	   
	    add(centerPanel, BorderLayout.CENTER);

	    JPanel buttonPanel = new JPanel();

	    buttonPanel.add(btnSave);

	    add(buttonPanel, BorderLayout.SOUTH);

	    setDefaultCloseOperation(
	            JFrame.DISPOSE_ON_CLOSE
	    );
	}
	
	public void loadGrades(List<Double> grades) {

        for (int i = 0; i < grades.size(); i++) {
        	gradeFields.get(i).setText(String.valueOf(grades.get(i)));
        }
    }


    public List<JTextField> getGradeFields() {
        return gradeFields;
    }
    
    public List<JLabel> getErrorLabels() {
        return errorLabels;
    }
	
    public JButton getBtnSave() {
        return btnSave;
    }
}