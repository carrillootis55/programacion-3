package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.DatabaseConnection;

public class QualificationRepository {
	
    public void saveQualification(String enrollment, int subjectId, double qualification) throws Exception {
    	
    	 String sql = """
                 INSERT INTO calificacion(
                     alumno_matricula,
                     materia_id,
                     calificacion
                 )
                 VALUES (?, ?, ?)
                 """;

         Connection conn = DatabaseConnection.getConnection();

         PreparedStatement ps = conn.prepareStatement(sql);

         ps.setString(1, enrollment);
         ps.setInt(2, subjectId);
         ps.setDouble(3, qualification);

         ps.executeUpdate();

         ps.close();

    }
  //=================================================================================================================================================================
    public void updateQualification(String enrollment, int subjectId, double qualification) throws Exception {
    	
    	String sql = """
                UPDATE calificacion
                SET calificacion = ?
                WHERE alumno_matricula = ?
                AND materia_id = ?
                """;

        Connection conn = DatabaseConnection.getConnection();

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setDouble(1, qualification);
        ps.setString(2, enrollment);
        ps.setInt(3, subjectId);

        ps.executeUpdate();

        ps.close();

    }
  //=================================================================================================================================================================
    public boolean qualificationExists(String enrollment, int subjectId ) {

    	String sql = """
                SELECT *
                FROM calificacion
                WHERE alumno_matricula = ?
                AND materia_id = ?
                """;

        try (
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, enrollment);
            ps.setInt(2, subjectId);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
  //=================================================================================================================================================================
    public List<String> getSubjectsByYear(String year) {

        List<String> subjects = new ArrayList<>();

        String sql = """
                SELECT nombre
                FROM materia
                WHERE anio_id = ?
                """;

        try (
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, year);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                subjects.add(rs.getString("nombre"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return subjects;
    }
  //=================================================================================================================================================================
    public int getSubjectId(String subjectName, String year) {
    	
    	String sql = """
                SELECT id
                FROM materia
                WHERE nombre = ?
                AND anio_id = ?
                LIMIT 1
                """;

        try (
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, subjectName);
            ps.setString(2, year);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }
  //=================================================================================================================================================================
    public Double getQualification(String enrollment, String subjectName ) {

    	 String sql = """
                 SELECT c.calificacion
                 FROM calificacion c
                 INNER JOIN materia m
                 ON c.materia_id = m.id
                 WHERE c.alumno_matricula = ?
                 AND m.nombre = ?
                 """;

         try (
             Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)
         ) {

             ps.setString(1, enrollment);
             ps.setString(2, subjectName);

             ResultSet rs = ps.executeQuery();

             if (rs.next()) {

                 return rs.getDouble("calificacion");
             }

         } catch (Exception e) {
             e.printStackTrace();
         }

         return null;
    }

}