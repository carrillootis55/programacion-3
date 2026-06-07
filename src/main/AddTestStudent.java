package main;

import models.Student;
import repository.StudentsRepository;

public class AddTestStudent {

    public static void main(String[] args) {
    	
        StudentsRepository repo = new StudentsRepository();

        // Crear un alumno con todos los atributos
        Student student = new Student();
        
        student.setEnrollment("232342424");
        student.setName("Luah");
        student.setFatherLastName("Mendivil");
        student.setMotherLastName("Reyes");
        student.setGender('M');
        student.setYear("1");
        student.setGroup("A");
        student.setEmergencyContact("Luca");
        student.setEmergencyNumber("555349634");
        student.setRelationship("Padre");
        student.setAddress("Calle Ficus 3489");

        try {
        	
            repo.save(student);
            System.out.println("Alumno agregado exitosamente.");
            
        } catch (Exception e) {
        	
            System.err.println("Error al guardar el alumno: " + e.getMessage());
        }
    }
}