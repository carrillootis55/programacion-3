package repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.DatabaseConnection;
import models.Student;

public class StudentsRepository {
	
	//=================================================================================================================================================================
	public void save(Student student) throws Exception {
        String sql = """
            INSERT INTO alumno(
                matricula,
                nombre,
                apellido_paterno,
                apellido_materno,
                fecha_nacimiento,
                sexo,
                anio_id,
                grupo_id,
                contacto_emergencia,
                numero_emergencia,
                parentesco,
                domicilio
            )
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;

        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, student.getMatricula());
        ps.setString(2, student.getNombre());
        ps.setString(3, student.getApellidoPaterno());
        ps.setString(4, student.getApellidoMaterno());
        ps.setString(5, student.getFechaNacimiento()); 
        ps.setString(6, String.valueOf(student.getSexo()));
        ps.setInt(7, Integer.parseInt(student.getAnio()));
        ps.setInt(8, student.getGrupo().equals("A") ? 1 : 2);
        ps.setString(9, student.getContactoEmergencia());
        ps.setString(10, student.getNumeroEmergencia());
        ps.setString(11, student.getParentesco());
        ps.setString(12, student.getDomicilio());

        ps.executeUpdate();
        ps.close();
    }
	
	//=================================================================================================================================================================
	public void updateAlumno(Student student) throws Exception {
        String sql = """
            UPDATE alumno
            SET
                nombre = ?,
                apellido_paterno = ?,
                apellido_materno = ?,
                fecha_nacimiento = ?,
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

        ps.setString(1, student.getNombre());
        ps.setString(2, student.getApellidoPaterno());
        ps.setString(3, student.getApellidoMaterno());
        ps.setString(4, student.getFechaNacimiento()); 
        ps.setString(5, String.valueOf(student.getSexo()));
        ps.setInt(6, Integer.parseInt(student.getAnio()));
        ps.setInt(7, student.getGrupo().equals("A") ? 1 : 2);
        ps.setString(8, student.getContactoEmergencia());
        ps.setString(9, student.getNumeroEmergencia());
        ps.setString(10, student.getParentesco());
        ps.setString(11, student.getDomicilio());
        ps.setString(12, student.getMatricula()); // 

        ps.executeUpdate();
        ps.close();
    }
	
	//=================================================================================================================================================================
    //Eliminar alumno
	public void delete(String matricula) throws Exception {

        String sql = "DELETE FROM alumno WHERE matricula=?";

        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, matricula);

        ps.executeUpdate();
        ps.close();
    }
    
	//=================================================================================================================================================================
	public List<Student> getAlumnos() {

        System.out.println("Conectado");
        List<Student> students = new ArrayList<>();
        
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
                Student student = new Student(
                    rs.getString("matricula"),
                    rs.getString("nombre"),
                    rs.getString("apellido_paterno"),
                    rs.getString("apellido_materno"),
                    rs.getString("fecha_nacimiento"), 
                    rs.getString("sexo").charAt(0),
                    rs.getString("anio"),
                    rs.getString("grupo"),
                    rs.getString("contacto_emergencia"),
                    rs.getString("numero_emergencia"),
                    rs.getString("parentesco"),
                    rs.getString("domicilio")
                );

                students.add(student);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return students;
    }

	//=================================================================================================================================================================
	public List<Student> getAlumnosPorGrupo(String anio, String grupo) {

        List<Student> students = new ArrayList<>();

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
                Student student = new Student(
                    rs.getString("matricula"),
                    rs.getString("nombre"),
                    rs.getString("apellido_paterno"),
                    rs.getString("apellido_materno"),
                    rs.getString("fecha_nacimiento"), 
                    rs.getString("sexo").charAt(0),
                    rs.getString("anio"),
                    rs.getString("grupo"),
                    rs.getString("contacto_emergencia"),
                    rs.getString("numero_emergencia"),
                    rs.getString("parentesco"),
                    rs.getString("domicilio")
                );

                students.add(student);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return students;
    }
	 
	//=================================================================================================================================================================
	public boolean existeMatricula(String matricula) {

	    String sql = "SELECT matricula FROM alumno WHERE matricula = ?";

	    try (
	        Connection conn = DatabaseConnection.getConnection();
	        PreparedStatement ps = conn.prepareStatement(sql)
	    ) {

	        ps.setString(1, matricula);
	        ResultSet rs = ps.executeQuery();
	        return rs.next();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return false;
	}
	public int contarAlumnosPorGrupo(String anio, String grupo) {
        return getAlumnosPorGrupo(anio, grupo).size();
    }
}