package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

        ps.setString(1, student.getEnrollment());
        ps.setString(2, student.getName());
        ps.setString(3, student.getFatherLastName());
        ps.setString(4, student.getMotherLastName());
        ps.setString(5, student.getBirthDate()); 
        ps.setString(6, String.valueOf(student.getGender()));
        ps.setInt(7, Integer.parseInt(student.getYear()));
        ps.setInt(8, student.getGroup().equals("A") ? 1 : 2);
        ps.setString(9, student.getEmergencyContact());
        ps.setString(10, student.getEmergencyNumber());
        ps.setString(11, student.getRelationship());
        ps.setString(12, student.getAddress());

        ps.executeUpdate();
        ps.close();
    }
	
	//=================================================================================================================================================================
	public void updateStudent(Student student) throws Exception {
		
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

        ps.setString(1, student.getName());
        ps.setString(2, student.getFatherLastName());
        ps.setString(3, student.getMotherLastName());
        ps.setString(4, student.getBirthDate()); 
        ps.setString(5, String.valueOf(student.getGender()));
        ps.setInt(6, Integer.parseInt(student.getYear()));
        ps.setInt(7, student.getGroup().equals("A") ? 1 : 2);
        ps.setString(8, student.getEmergencyContact());
        ps.setString(9, student.getEmergencyNumber());
        ps.setString(10, student.getRelationship());
        ps.setString(11, student.getAddress());
        ps.setString(12, student.getEnrollment());

        ps.executeUpdate();
        ps.close();
    }
	
	//=================================================================================================================================================================
    //Eliminar alumno
	public void delete(String enrollment) throws Exception {

        String sql = "DELETE FROM alumno WHERE matricula=?";

        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, enrollment);

        ps.executeUpdate();
        ps.close();
    }
    
	//=================================================================================================================================================================
	public List<Student> getStudents() {

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
	public List<Student> getStudentsByGroup(String year, String group) {

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

            ps.setString(1, year);
            ps.setString(2, group);
            
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
	public boolean enrollmentExists(String enrollment) {

	    String sql = "SELECT matricula FROM alumno WHERE matricula = ?";

	    try (
	        Connection conn = DatabaseConnection.getConnection();
	        PreparedStatement ps = conn.prepareStatement(sql)
	    ) {

	        ps.setString(1, enrollment);
	        
	        ResultSet rs = ps.executeQuery();
	        
	        return rs.next();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return false;
	}
	
	//=================================================================================================================================================================
	public int countStudentsByGroup(String year, String group) {
		
        return getStudentsByGroup(year, group).size();
    }
}