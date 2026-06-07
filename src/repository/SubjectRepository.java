package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.DatabaseConnection;
import models.Subject;

public class SubjectRepository {
	
	public List<Subject> getSubjectsByYear(String year) {

        List<Subject> subjects = new ArrayList<>();

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

            ps.setString(1, year);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Subject subject = new Subject(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("anio")
                );

                subjects.add(subject);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return subjects;
    }

}