package views;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import models.Student;

public class StudentDetails extends JFrame {

    public StudentDetails(Student student) {

        setTitle("Detalles del Alumno");

        setSize(400, 500);

        setLocationRelativeTo(null);

        setResizable(false);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(0, 1, 5, 5));

        panel.setBorder(
                BorderFactory.createEmptyBorder(15,15,15,15));

        Font font = new Font("Times New Roman", Font.PLAIN, 16);

        JLabel title = new JLabel("INFORMACIÓN DEL ALUMNO");
        title.setFont(new Font("Times New Roman", Font.BOLD, 18));

        panel.add(title);

        JLabel lblEnrollment =
                new JLabel("Matrícula: " + student.getEnrollment());

        JLabel lblName =
                new JLabel("Nombre: " + student.getName());

        JLabel lblLastNameP =
                new JLabel("Apellido Paterno: " +
                        student.getFatherLastName());

        JLabel lblLastNameM =
                new JLabel("Apellido Materno: " +
                        student.getMotherLastName());
        
        String formattedDate = student.getBirthDate();

        try {
            
            LocalDate date =
                    LocalDate.parse(formattedDate);

            formattedDate =
                    date.format(
                            DateTimeFormatter.ofPattern("dd/MM/yyyy")
                    );
                    
        } catch (Exception e) {
          
        }

        JLabel lblBirthDate =
                new JLabel("Fecha de Nacimiento: " + formattedDate);

        JLabel lblGender =
                new JLabel("Sexo: " + student.getGender());

        JLabel lblGroup =
                new JLabel("Grupo: " + student.getGroup());

        JLabel lblYear =
                new JLabel("Año: " + student.getYear());

        JLabel lblEmergencyContact =
                new JLabel("Contacto Emergencia: " +
                        student.getEmergencyContact());

        JLabel lblEmergencyNumber =
                new JLabel("Número Emergencia: " +
                        student.getEmergencyNumber());

        JLabel lblRelationship =
                new JLabel("Parentesco: " +
                        student.getRelationship());

        JLabel lblAddress =
                new JLabel("Domicilio: " +
                        student.getAddress());

        lblEnrollment.setFont(font);
        lblName.setFont(font);
        lblLastNameP.setFont(font);
        lblLastNameM.setFont(font);
        lblBirthDate.setFont(font);
        lblGender.setFont(font);
        lblGroup.setFont(font);
        lblYear.setFont(font);
        lblEmergencyContact.setFont(font);
        lblEmergencyNumber.setFont(font);
        lblRelationship.setFont(font);
        lblAddress.setFont(font);

        panel.add(lblEnrollment);
        panel.add(lblName);
        panel.add(lblLastNameP);
        panel.add(lblLastNameM);
        panel.add(lblBirthDate);
        panel.add(lblGender);
        panel.add(lblGroup);
        panel.add(lblYear);
        panel.add(lblEmergencyContact);
        panel.add(lblEmergencyNumber);
        panel.add(lblRelationship);
        panel.add(lblAddress);

        add(panel, BorderLayout.CENTER);
    }
}