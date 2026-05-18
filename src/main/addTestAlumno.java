package main;

import java.io.IOException;
import models.Alumno;
import repository.AlumnoRepository;

public class addTestAlumno {

    public static void main(String[] args) {
        AlumnoRepository repo = new AlumnoRepository();

        // Crear un alumno con todos los atributos
        Alumno alumno = new Alumno();
        alumno.setMatricula("232342424");
        alumno.setNombre("Luah");
        alumno.setApellidoPaterno("Mendivil");
        alumno.setApellidoMaterno("Reyes");
        alumno.setSexo('M');
        alumno.setAnio("1");
        alumno.setGrupo("A");
        alumno.setContactoEmergencia("Luca");
        alumno.setNumeroEmergencia("555349634");
        alumno.setParentesco("Padre");
        alumno.setDomicilio("Calle Ficus 3489");

        try {
            repo.save(alumno);
            System.out.println("Alumno agregado exitosamente.");
        } catch (Exception e) {
            System.err.println("Error al guardar el alumno: " + e.getMessage());
        }
    }
}