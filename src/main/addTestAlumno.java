package main;

import java.io.IOException;
import models.Student;
import repository.StudentsRepository;

public class addTestAlumno {

    public static void main(String[] args) {
        StudentsRepository repo = new StudentsRepository();

        // Crear un alumno con todos los atributos
        Student student = new Student();
        student.setMatricula("232342424");
        student.setNombre("Luah");
        student.setApellidoPaterno("Mendivil");
        student.setApellidoMaterno("Reyes");
        student.setSexo('M');
        student.setAnio("1");
        student.setGrupo("A");
        student.setContactoEmergencia("Luca");
        student.setNumeroEmergencia("555349634");
        student.setParentesco("Padre");
        student.setDomicilio("Calle Ficus 3489");

        try {
            repo.save(student);
            System.out.println("Alumno agregado exitosamente.");
        } catch (Exception e) {
            System.err.println("Error al guardar el alumno: " + e.getMessage());
        }
    }
}