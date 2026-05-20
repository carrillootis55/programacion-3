package repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
//import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;

import config.DatabaseConnection;
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
	
	// Ruta del archivo donde se guardan los datos de los alumnos en formato CSV
	//private final String FILE = "src/assets/files/alumnos.csv";
	
	//Ruta del archivo donde se guardan los datos de los alumnos en formato JSON
	//private final String FILE = "src/assets/files/alumnos.json";
	
	//private final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

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
	
	public void save(Alumno alumno) throws Exception {
        String sql = """
            INSERT INTO alumno(
                matricula,
                nombre,
                apellido_paterno,
                apellido_materno,
                sexo,
                anio_id,
                grupo_id,
                contacto_emergencia,
                numero_emergencia,
                parentesco,
                domicilio
            )
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;

        Connection conn = DatabaseConnection.getConnection();

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, alumno.getMatricula());

        ps.setString(2, alumno.getNombre());

        ps.setString(3, alumno.getApellidoPaterno());

        ps.setString(4, alumno.getApellidoMaterno());

        ps.setString(5, String.valueOf(alumno.getSexo()));

        ps.setInt(6, Integer.parseInt(alumno.getAnio()));

        ps.setInt(7, alumno.getGrupo().equals("A") ? 1 : 2);

        ps.setString(8, alumno.getContactoEmergencia());

        ps.setString(9, alumno.getNumeroEmergencia());

        ps.setString(10, alumno.getParentesco());

        ps.setString(11, alumno.getDomicilio());

        ps.executeUpdate();

        ps.close();
    }
    	
    	/*List<Alumno> alumnos = getAlumnos();
        alumnos.add(alumno);
        updateAll(alumnos);
        */
    
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
    /*public void updateAll(List<Alumno> alumnos) throws IOException {
        mapper.writeValue(new File(FILE), alumnos);
    }
*/
    
	public void updateAlumno(Alumno alumno) throws Exception {

        String sql = """
            UPDATE alumno
            SET
                nombre = ?,
                apellido_paterno = ?,
                apellido_materno = ?,
                sexo = ?,
                anio_id = ?,
                grupo_id = ?,
                contacto_emergencia = ?,
                numero_emergencia = ?,
                parentesco = ?,
                domicilio = ?
            WHERE matricula = ?
            """;

        Connection conn = DatabaseConnection.getConnection();

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, alumno.getNombre());

        ps.setString(2, alumno.getApellidoPaterno());

        ps.setString(3, alumno.getApellidoMaterno());

        ps.setString(4, String.valueOf(alumno.getSexo()));

        ps.setInt(5, Integer.parseInt(alumno.getAnio()));

        ps.setInt(6, alumno.getGrupo().equals("A") ? 1 : 2);

        ps.setString(7, alumno.getContactoEmergencia());

        ps.setString(8, alumno.getNumeroEmergencia());

        ps.setString(9, alumno.getParentesco());

        ps.setString(10, alumno.getDomicilio());

        ps.setString(11, alumno.getMatricula());

        ps.executeUpdate();

        ps.close();
    }
	 
    //Eliminar alumno
	public void delete(String matricula) throws Exception {

        String sql = "DELETE FROM alumno WHERE matricula=?";

        Connection conn = DatabaseConnection.getConnection();

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, matricula);

        ps.executeUpdate();

        ps.close();
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
	public List<Alumno> getAlumnos() {

        System.out.println("Conectado");

        List<Alumno> alumnos = new ArrayList<>();

        String sql = """
            SELECT
                a.*,
                an.tipo AS anio,
                g.tipoGrupo AS grupo
            FROM alumno a
            INNER JOIN anio an
                ON a.anio_id = an.id
            INNER JOIN grupo g
                ON a.grupo_id = g.id
            """;

        try (

            Connection conn = DatabaseConnection.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery()

        ) {

            while (rs.next()) {

                System.out.println(rs.getString("nombre"));

                Alumno alumno = new Alumno(

                    rs.getString("matricula"),

                    rs.getString("nombre"),

                    rs.getString("apellido_paterno"),

                    rs.getString("apellido_materno"),

                    rs.getString("sexo").charAt(0),

                    rs.getString("anio"),

                    rs.getString("grupo"),

                    rs.getString("contacto_emergencia"),

                    rs.getString("numero_emergencia"),

                    rs.getString("parentesco"),

                    rs.getString("domicilio")
                );

                alumnos.add(alumno);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return alumnos;
    }

  
	 public List<Alumno> getAlumnosPorGrupo(String anio, String grupo) {

	        List<Alumno> alumnos = new ArrayList<>();

	        String sql = """
	            SELECT
	                a.*,
	                an.tipo AS anio,
	                g.tipoGrupo AS grupo
	            FROM alumno a
	            INNER JOIN anio an
	                ON a.anio_id = an.id
	            INNER JOIN grupo g
	                ON a.grupo_id = g.id
	            WHERE an.tipo = ?
	            AND g.tipoGrupo = ?
	            """;

	        try (

	            Connection conn = DatabaseConnection.getConnection();

	            PreparedStatement ps = conn.prepareStatement(sql)

	        ) {

	            ps.setString(1, anio);

	            ps.setString(2, grupo);

	            ResultSet rs = ps.executeQuery();

	            while (rs.next()) {

	                Alumno alumno = new Alumno(

	                    rs.getString("matricula"),

	                    rs.getString("nombre"),

	                    rs.getString("apellido_paterno"),

	                    rs.getString("apellido_materno"),

	                    rs.getString("sexo").charAt(0),

	                    rs.getString("anio"),

	                    rs.getString("grupo"),

	                    rs.getString("contacto_emergencia"),

	                    rs.getString("numero_emergencia"),

	                    rs.getString("parentesco"),

	                    rs.getString("domicilio")
	                );

	                alumnos.add(alumno);
	            }

	        } catch (Exception e) {

	            e.printStackTrace();
	        }

	        return alumnos;
	    }
    
  
    
    
}
