package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Panel;
import models.Teacher;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TeacherView extends JPanel{
	
	private Teacher teacher;
	
	public TeacherView() {
		this(new Teacher());
	}
	
	public TeacherView(Teacher teacher) {
		this.teacher = teacher;
		initializeComponents();
	}

	private void initializeComponents() {
		setLayout(new BorderLayout());
		removeAll();
		
		JPanel imageContainerPanel = new JPanel(new GridBagLayout());
        imageContainerPanel.setPreferredSize(new Dimension(400, 400));
        imageContainerPanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 10));

       
        String imagePath;

        if(teacher.getGender() != null && teacher.getGender().equalsIgnoreCase("M")) {
            imagePath = "src/img/maestra.png";

        } else {

            imagePath = "src/img/maestro.png";
        }


        ImageIcon originalIcon = new ImageIcon(imagePath);

        Image scaledImage = originalIcon.getImage()
                .getScaledInstance(300, 300, Image.SCALE_SMOOTH);

        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
        
	    imageContainerPanel.add(imageLabel);
	    
	    
		JPanel rightPanel = new JPanel(new GridBagLayout());
		rightPanel.setOpaque(false);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5,5,5,5); //ESPACIO ENTRE LINEAS DE TEXTO
		gbc.anchor = GridBagConstraints.WEST;
		
		
		gbc.gridx = 0; gbc.gridy = 0;
		rightPanel.add(new JLabel("Nombre: "), gbc);
		gbc.gridx = 1;
		rightPanel.add(new JLabel(teacher.getName() != null ? teacher.getName() : "No asignado"), gbc);
		
		gbc.gridx = 0; gbc.gridy = 1;
		rightPanel.add(new JLabel("Edad: "), gbc);
		gbc.gridx = 1;
		rightPanel.add(new JLabel(String.valueOf(teacher.getAge())), gbc);
		
		
		gbc.gridx = 0; gbc.gridy = 3;
		rightPanel.add(new JLabel("Maestria: "), gbc);
		gbc.gridx = 1;
		String masterDegreeText = teacher.getMasterDegree() != null ? teacher.getMasterDegree() : "N/A";
		rightPanel.add(new JLabel(masterDegreeText), gbc);
				
		gbc.gridx = 0;
        gbc.gridy = 5;
        rightPanel.add(new JLabel("Año: "), gbc);
        gbc.gridx = 1;
        rightPanel.add(new JLabel(teacher.getYear() != null? teacher.getYear(): "No asignado"),gbc);

        
        gbc.gridx = 0;
        gbc.gridy = 6;
        rightPanel.add(new JLabel("Grupo: "), gbc);
        gbc.gridx = 1;
        rightPanel.add(new JLabel(teacher.getGroup() != null? teacher.getGroup(): "No asignado"), gbc);

		
        add(imageContainerPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
		
		
	}
	
	public void updateTeacher(Teacher teacher) {
		this.teacher = teacher;
		initializeComponents();
		revalidate();
		repaint();
	}

}