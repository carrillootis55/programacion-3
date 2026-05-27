package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.DatabaseConnection;

public class CalificacionRepository {
	
    public void guardarCalificacion( String matricula,int materiaId,double calificacion) throws Exception {
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

         ps.setString(1, matricula);
         ps.setInt(2, materiaId);
         ps.setDouble(3, calificacion);

         ps.executeUpdate();

         ps.close();

    }
  //=================================================================================================================================================================
    public void actualizarCalificacion( String matricula, int materiaId, double calificacion) throws Exception {
    	String sql = """
                UPDATE calificacion
                SET calificacion = ?
                WHERE alumno_matricula = ?
                AND materia_id = ?
                """;

        Connection conn = DatabaseConnection.getConnection();

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setDouble(1, calificacion);
        ps.setString(2, matricula);
        ps.setInt(3, materiaId);

        ps.executeUpdate();

        ps.close();

    }
  //=================================================================================================================================================================
    public boolean existeCalificacion(String matricula, int materiaId ) {

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

            ps.setString(1, matricula);
            ps.setInt(2, materiaId);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
  //=================================================================================================================================================================
    public List<String> getMateriasPorAnio(String anio) {

        List<String> materias = new ArrayList<>();

        String sql = """
                SELECT nombre
                FROM materia
                WHERE anio_id = ?
                """;

        try (
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, anio);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                materias.add(rs.getString("nombre"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return materias;
    }
  //=================================================================================================================================================================
    public int getMateriaId( String nombreMateria, String anio) {
    	
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

            ps.setString(1, nombreMateria);
            ps.setString(2, anio);

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
    public Double obtenerCalificacion(String matricula, String nombreMateria ) {

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

             ps.setString(1, matricula);
             ps.setString(2, nombreMateria);

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
