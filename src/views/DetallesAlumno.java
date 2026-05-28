package views;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import models.Alumno;

public class DetallesAlumno extends JFrame {

    public DetallesAlumno(Alumno alumno) {

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
                new JLabel("Matrícula: " + alumno.getMatricula());

        JLabel lblNombre =
                new JLabel("Nombre: " + alumno.getNombre());

        JLabel lblApellidoP =
                new JLabel("Apellido Paterno: " +
                        alumno.getApellidoPaterno());

        JLabel lblApellidoM =
                new JLabel("Apellido Materno: " +
                        alumno.getApellidoMaterno());

        JLabel lblSexo =
                new JLabel("Sexo: " + alumno.getSexo());

        JLabel lblGrupo =
                new JLabel("Grupo: " + alumno.getGrupo());

        JLabel lblAnio =
                new JLabel("Año: " + alumno.getAnio());

        JLabel lblContacto =
                new JLabel("Contacto Emergencia: " +
                        alumno.getContactoEmergencia());

        JLabel lblNumero =
                new JLabel("Número Emergencia: " +
                        alumno.getNumeroEmergencia());

        JLabel lblParentesco =
                new JLabel("Parentesco: " +
                        alumno.getParentesco());

        JLabel lblDomicilio =
                new JLabel("Domicilio: " +
                        alumno.getDomicilio());

        lblMatricula.setFont(font);
        lblNombre.setFont(font);
        lblApellidoP.setFont(font);
        lblApellidoM.setFont(font);
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