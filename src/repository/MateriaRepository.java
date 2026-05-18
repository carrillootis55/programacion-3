package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.DatabaseConnection;
import models.Materia;

public class MateriaRepository {
	
	public List<Materia> getMateriasPorAnio(String anio) {

        List<Materia> materias = new ArrayList<>();

        String sql = """
                SELECT *
                FROM materias
                WHERE anio = ?
                """;

        try (
            Connection conn =
                    DatabaseConnection.getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(sql)
        ) {

            ps.setString(1, anio);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Materia materia = new Materia(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("anio")
                );

                materias.add(materia);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return materias;
    }

}
