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

        JLabel titulo = new JLabel("INFORMACIÓN DEL ALUMNO");
        titulo.setFont(new Font("Times New Roman", Font.BOLD, 18));

        panel.add(titulo);

        JLabel lblMatricula =
                new JLabel("Matrícula: " + student.getMatricula());

        JLabel lblNombre =
                new JLabel("Nombre: " + student.getNombre());

        JLabel lblApellidoP =
                new JLabel("Apellido Paterno: " +
                        student.getApellidoPaterno());

        JLabel lblApellidoM =
                new JLabel("Apellido Materno: " +
                        student.getApellidoMaterno());
        
        String fechaFormateada = student.getFechaNacimiento();

        try {
            LocalDate fecha =
                    LocalDate.parse(fechaFormateada);

            fechaFormateada =
                    fecha.format(
                            DateTimeFormatter.ofPattern("dd/MM/yyyy")
                    );
        } catch (Exception e) {
          
        }

        JLabel lblFechaNacimiento =
                new JLabel("Fecha de Nacimiento: " + fechaFormateada);

        JLabel lblSexo =
                new JLabel("Sexo: " + student.getSexo());

        JLabel lblGrupo =
                new JLabel("Grupo: " + student.getGrupo());

        JLabel lblAnio =
                new JLabel("Año: " + student.getAnio());

        JLabel lblContacto =
                new JLabel("Contacto Emergencia: " +
                        student.getContactoEmergencia());

        JLabel lblNumero =
                new JLabel("Número Emergencia: " +
                        student.getNumeroEmergencia());

        JLabel lblParentesco =
                new JLabel("Parentesco: " +
                        student.getParentesco());

        JLabel lblDomicilio =
                new JLabel("Domicilio: " +
                        student.getDomicilio());

        lblMatricula.setFont(font);
        lblNombre.setFont(font);
        lblApellidoP.setFont(font);
        lblApellidoM.setFont(font);
        lblFechaNacimiento.setFont(font);
        lblSexo.setFont(font);
        lblGrupo.setFont(font);
        lblAnio.setFont(font);
        lblContacto.setFont(font);
        lblNumero.setFont(font);
        lblParentesco.setFont(font);
        lblDomicilio.setFont(font);

        panel.add(lblMatricula);
        panel.add(lblNombre);
        panel.add(lblApellidoP);
        panel.add(lblApellidoM);
        panel.add(lblFechaNacimiento);
        panel.add(lblSexo);
        panel.add(lblGrupo);
        panel.add(lblAnio);
        panel.add(lblContacto);
        panel.add(lblNumero);
        panel.add(lblParentesco);
        panel.add(lblDomicilio);

        add(panel, BorderLayout.CENTER);
    }
}