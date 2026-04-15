package repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import models.Alumno;

public class AlumnoRepository {
	
	//Ruta del archivo donde se guardan los datos de los alumnos en formato CSV
	private final String FILE = "src/assets/files/alumnos.csv";

    //Guardar
    public void save(Alumno alumno) throws IOException {

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FILE, true), StandardCharsets.UTF_8))) {
        	
        	//Se convierte a formato CSV y se escribe en el archivo
            writer.write(alumno.toCsv());
            //Salto de linea
            writer.newLine();
        }
    }
    
    //Se obtienen y almacenan los alumnos
    public List<Alumno> getAlumnos() throws IOException {

        List<Alumno> alumnos = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE))) {

            String line;

            while ((line = reader.readLine()) != null) {
                Alumno alumno = Alumno.fromCsv(line);
                alumnos.add(alumno);
            }
        }

        return alumnos;
    }
}