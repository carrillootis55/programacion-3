package repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import models.Alumno;
/*
 * BufferedWriter = ALMACENA CARACTERES EN EL BUFER - ESCRIBE TEXTOS DENTRO DE LA LISTA
 * BufferedReader = ALMACENA DATOS EN LA MEMORIA - LEE EL TEXTO DE LA LISTA
 * FileReader = Extiende InputStreamReader y se utiliza comúnmente para leer archivos carácter por carácter
 * FileWriter =  Extiende OutputStreamWriter y se utiliza para escribir archivos linea por linea
 * REMOVE = En este caso utilizamos alumnos pero la estructura de este metodo es:
 * lista.remove(objeto) elimina la primera ocurrencia
 */
public class AlumnoRepository {
	
	//Ruta del archivo donde se guardan los datos de los alumnos en formato CSV
	//private final String FILE = "src/assets/files/alumnos.csv";
	
	//Ruta del archivo donde se guardan los datos de los alumnos en formato JSON
	private final String FILE = "src/assets/files/alumnos.json";
	
	private final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    //Guardar csv
    /*public void save(Alumno alumno) throws IOException {

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FILE, true), StandardCharsets.UTF_8))) {
        	
        	//Se convierte a formato CSV y se escribe en el archivo
            writer.write(alumno.toCsv());
            //Salto de linea
            writer.newLine();
        }
    }*/
    
    //Guardar json
    public void save(Alumno alumno) throws IOException {
        List<Alumno> alumnos = getAlumnos();
        alumnos.add(alumno);
        updateAll(alumnos);
    }
    //Reescribe archivo csv
    /*public void updateAll(List<Alumno> alumnos) throws IOException{
    	try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE))){
    		for(Alumno alumno : alumnos) {
    			writer.write(alumno.toCsv());
    			writer.newLine();
    		}
    	}
    }*/
    
    //Reescribe archivo json
    public void updateAll(List<Alumno> alumnos) throws IOException {
        mapper.writeValue(new File(FILE), alumnos);
    }

    
    //Editar alumno
    public void update(int index, Alumno alumnoActualizado) throws IOException{
    	List<Alumno> alumnos = getAlumnos();
    	alumnos.set(index, alumnoActualizado);
    	updateAll(alumnos);
    }
    
    //Eliminar alumno
    public void delete(int index) throws IOException {
    	List<Alumno> alumnos = getAlumnos();
    	alumnos.remove(index);
    	updateAll(alumnos);
    }
    
    //Se obtienen y almacenan los alumnos csv
    /*public List<Alumno> getAlumnos() throws IOException {

        List<Alumno> alumnos = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE))) {

            String line;

            while ((line = reader.readLine()) != null) {
                Alumno alumno = Alumno.fromCsv(line);
                alumnos.add(alumno);
            }
        }

        return alumnos;
    }*/
    
    //Se obtienen y almacenan los alumnos json
    public List<Alumno> getAlumnos() throws IOException {

        File file = new File(FILE);

        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }

        return mapper.readValue(
                file,
                new TypeReference<List<Alumno>>() {}
        );
    }
}