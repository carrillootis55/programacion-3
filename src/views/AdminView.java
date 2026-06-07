package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import models.Teacher;

public class AdminView extends JPanel {

    private Teacher adminTeacher;

    public AdminView() {
        this(new Teacher());
    }

    public AdminView(Teacher adminTeacher) {

        this.adminTeacher = adminTeacher;

        initializeComponents();
    }

    private void initializeComponents() {

        setLayout(new BorderLayout());

        removeAll();

        JPanel imageContainerPanel = new JPanel(new GridBagLayout());

        imageContainerPanel.setPreferredSize(new Dimension(400, 400));

        imageContainerPanel.setBorder(BorderFactory.createEmptyBorder(0,100, 0,10));
        
        String imagePath;

        if (adminTeacher.getGender() != null && adminTeacher.getGender().equalsIgnoreCase("M")) {
            imagePath = "src/img/admin1.png";

        } else {
            imagePath = "src/img/admin2.png";
        }

        ImageIcon originalIcon = new ImageIcon(imagePath);

        Image scaledImage = originalIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);

        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));

        imageContainerPanel.add(imageLabel);
        
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        
        JLabel title = new JLabel("ADMINISTRADOR");
        
        title.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD,24));

        rightPanel.add(title, gbc);

        gbc.gridwidth = 1;
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        rightPanel.add(new JLabel("Nombre: "), gbc);
        
        gbc.gridx = 1;
        rightPanel.add(new JLabel(adminTeacher.getName() != null? adminTeacher.getName(): "No asignado"),gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        rightPanel.add(new JLabel("Edad: "), gbc);
        
        gbc.gridx = 1;
        rightPanel.add(new JLabel(String.valueOf(adminTeacher.getAge())),gbc);


        gbc.gridx = 0;
        gbc.gridy = 3;
        rightPanel.add(new JLabel("Sexo: "), gbc);
        
        gbc.gridx = 1;
        rightPanel.add(new JLabel(adminTeacher.getGender() != null? adminTeacher.getGender(): "No asignado"),gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        rightPanel.add(new JLabel("Email: "), gbc);
        
        gbc.gridx = 1;
        rightPanel.add(new JLabel(adminTeacher.getEmail() != null? adminTeacher.getEmail(): "No asignado"),gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        rightPanel.add(new JLabel("Maestría: "), gbc);
        
        gbc.gridx = 1;
        
        String masterDegreeText = adminTeacher.getMasterDegree() != null? adminTeacher.getMasterDegree(): "N/A";
        
        rightPanel.add(new JLabel(masterDegreeText), gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 6;
        rightPanel.add(new JLabel("Rol: "), gbc);
        
        gbc.gridx = 1;
        rightPanel.add(new JLabel(adminTeacher.getRole() != null? adminTeacher.getRole(): "ADMIN"),gbc);


        add(imageContainerPanel, BorderLayout.WEST);

        add(rightPanel, BorderLayout.CENTER);
        
    }


    public void updateAdmin(Teacher adminTeacher) {

        this.adminTeacher = adminTeacher;

        initializeComponents();

        revalidate();

        repaint();
    }
}