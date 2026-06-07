package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import utils.PasswordUtils;
import models.Teacher;
import config.DatabaseConnection;

public class TeacherRepository {
    
    private Connection connection;
    
    public TeacherRepository() throws SQLException {
        this.connection = DatabaseConnection.getConnection();
    }
    
  //=================================================================================================================================================================
    public Teacher findByEmail(String email) throws SQLException {
    	
        String sql = """
                SELECT
                    m.id,
                    m.nombre,
                    m.fecha_nacimiento,
                    m.email,
                    m.password,
                    m.sexo,
                    m.edad,
                    m.maestria,
                    m.role,

                    a.tipo AS anio,
                    g.tipoGrupo AS grupo

                FROM maestro m

                LEFT JOIN anio a
                	ON m.anio_id = a.id

                LEFT JOIN grupo g
                    ON m.grupo_id = g.id

                WHERE m.email = ?
                LIMIT 1
                """;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, email);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    Teacher teacher = new Teacher();

                    teacher.setId(resultSet.getInt("id"));

                    teacher.setName(resultSet.getString("nombre"));
                    
                    teacher.setBirthDate(resultSet.getString("fecha_nacimiento"));

                    teacher.setEmail(resultSet.getString("email"));

                    teacher.setPassword(resultSet.getString("password"));
                    
                    teacher.setGender(resultSet.getString("sexo"));

                    teacher.setAge(resultSet.getInt("edad"));

                    teacher.setMasterDegree(resultSet.getString("maestria"));

                    teacher.setRole(resultSet.getString("role"));

                    teacher.setYear(resultSet.getString("anio"));

                    teacher.setGroup(resultSet.getString("grupo"));

                    return teacher;
                }
            }
        }

        return null;
    }
    
  //=================================================================================================================================================================
    public boolean verifyCredentials(String email, String password) throws SQLException {

        String sql = "SELECT password FROM maestro WHERE email = ?";

        try (
                Connection conn = DatabaseConnection.getConnection();

                PreparedStatement statement = conn.prepareStatement(sql)
        ) {

            statement.setString(1, email);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {

                return PasswordUtils.checkPassword(
                        password,
                        rs.getString("password")
                );
            }
        }

        return false;
    }
    
  //=================================================================================================================================================================
    public Teacher getTeacherByEmail(String email) throws SQLException {

        String sql = """
                SELECT
                    m.id,
                    m.nombre,
                    m.fecha_nacimiento,
                    m.email,
                    m.password,
                    m.sexo,
                    m.edad,
                    m.maestria,
                    m.role,

                    a.tipo AS anio,
                    g.tipoGrupo AS grupo

                FROM maestro m

                LEFT JOIN anio a
                    ON m.anio_id = a.id

                LEFT JOIN grupo g
                    ON m.grupo_id = g.id

                WHERE m.email = ?
                LIMIT 1
                """;

        try (
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, email);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    Teacher teacher = new Teacher();

                    teacher.setId(resultSet.getInt("id"));

                    teacher.setName(resultSet.getString("nombre"));
                    
                    teacher.setBirthDate(resultSet.getString("fecha_nacimiento"));

                    teacher.setEmail(resultSet.getString("email"));

                    teacher.setPassword(resultSet.getString("password"));
                    
                    teacher.setGender(resultSet.getString("sexo"));

                    teacher.setAge(resultSet.getInt("edad"));

                    teacher.setMasterDegree(resultSet.getString("maestria"));

                    teacher.setRole(resultSet.getString("role"));

                    teacher.setYear(resultSet.getString("anio"));

                    teacher.setGroup(resultSet.getString("grupo"));

                    return teacher;
                }
            }
        }

        return null;
    }

  //=================================================================================================================================================================
    public List<Teacher> getAll() throws SQLException {

        List<Teacher> teachers = new ArrayList<>();

        String sql = """
                SELECT
                    m.id,
                    m.nombre,
                    m.fecha_nacimiento,
                    m.email,
                    m.password,
                    m.sexo,
                    m.edad,
                    m.maestria,
                    m.role,

                    a.tipo AS anio,
                    g.tipoGrupo AS grupo

                FROM maestro m

                LEFT JOIN anio a
                    ON m.anio_id = a.id

                LEFT JOIN grupo g
                    ON m.grupo_id = g.id
                """;

        try (
                Statement statement = connection.createStatement();

                ResultSet resultSet = statement.executeQuery(sql)
        ) {

            while (resultSet.next()) {

            	Teacher teacher = new Teacher();

                teacher.setId(resultSet.getInt("id"));

                teacher.setName(resultSet.getString("nombre"));
                
                teacher.setBirthDate(resultSet.getString("fecha_nacimiento"));

                teacher.setEmail(resultSet.getString("email"));

                teacher.setPassword(resultSet.getString("password"));
                
                teacher.setGender(resultSet.getString("sexo"));

                teacher.setAge(resultSet.getInt("edad"));

                teacher.setMasterDegree(resultSet.getString("maestria"));

                teacher.setRole(resultSet.getString("role"));

                teacher.setYear(resultSet.getString("anio"));

                teacher.setGroup(resultSet.getString("grupo"));

                teachers.add(teacher);
            }
        }

        return teachers;
    }
    
  //=================================================================================================================================================================
    public List<Teacher> getOnlyTeachers() throws SQLException {

        List<Teacher> teachers = new ArrayList<>();

        String sql = """
                SELECT
                    m.id,
                    m.nombre,
                    m.fecha_nacimiento,
                    m.email,
                    m.password,
                    m.sexo,
                    m.edad,
                    m.maestria,
                    m.role,

                    a.tipo AS anio,
                    g.tipoGrupo AS grupo

                FROM maestro m

                LEFT JOIN anio a
                    ON m.anio_id = a.id

                LEFT JOIN grupo g
                    ON m.grupo_id = g.id

                WHERE m.role = 'MAESTRO'
                """;

        try (

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery()

        ) {

            while (resultSet.next()) {

            	Teacher teacher = new Teacher();

                teacher.setId(resultSet.getInt("id"));

                teacher.setName(resultSet.getString("nombre"));
                
                teacher.setBirthDate(resultSet.getString("fecha_nacimiento"));

                teacher.setEmail(resultSet.getString("email"));

                teacher.setPassword(resultSet.getString("password"));

                teacher.setGender(resultSet.getString("sexo"));

                teacher.setAge(resultSet.getInt("edad"));

                teacher.setMasterDegree(resultSet.getString("maestria"));

                teacher.setRole(resultSet.getString("role"));

                teacher.setYear(resultSet.getString("anio"));

                teacher.setGroup(resultSet.getString("grupo"));

                teachers.add(teacher);
            }
        }

        return teachers;
    }
    
  //=================================================================================================================================================================
    public int countTeachers() throws SQLException {

        String sql = """
                SELECT COUNT(*) total
                FROM maestro
                WHERE role = 'MAESTRO'
                """;

        try (

            PreparedStatement statement =
                connection.prepareStatement(sql);

            ResultSet resultSet =
                statement.executeQuery()

        ) {

            if (resultSet.next()) {

                return resultSet.getInt("total");
            }
        }

        return 0;
    }
    
  //=================================================================================================================================================================
    public boolean save(Teacher teacher) throws SQLException {

        String sql = """
                INSERT INTO maestro
                (
                    nombre,
                    fecha_nacimiento,
                    email,
                    password,
                    sexo,
                    edad,
                    maestria,
                    anio_id,
                    grupo_id,
                    role
                )
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, teacher.getName());
            
            statement.setString(2, teacher.getBirthDate());

            statement.setString(3, teacher.getEmail());

            statement.setString(4, PasswordUtils.hashPassword(teacher.getPassword()));
            
            statement.setString(5, teacher.getGender());

            statement.setInt(6, teacher.getAge());

            statement.setString(7, teacher.getMasterDegree());

            statement.setInt(8, Integer.parseInt(teacher.getYear()));

            statement.setInt(9, teacher.getGroup().equals("A") ? 1 : 2);

            statement.setString(10, "MAESTRO");

            return statement.executeUpdate() > 0;
        }
    }
    
  //=================================================================================================================================================================
    public boolean update(Teacher teacher) throws SQLException {
    	
        String sql = """
                UPDATE maestro
                SET
                    nombre = ?,
                    fecha_nacimiento = ?,
                    email = ?,
                    password = ?,
                    sexo = ?,
                    edad = ?,
                    maestria = ?,
                    anio_id = ?,
                    grupo_id = ?
                WHERE id = ?
                """;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, teacher.getName());
            
            statement.setString(2, teacher.getBirthDate());

            statement.setString(3, teacher.getEmail());

            statement.setString(4, PasswordUtils.hashPassword(teacher.getPassword()));
            
            statement.setString(5, teacher.getGender());

            statement.setInt(6, teacher.getAge());

            statement.setString(7, teacher.getMasterDegree());
            
            statement.setInt(8, Integer.parseInt(teacher.getYear()));

            statement.setInt(9, teacher.getGroup().equals("A") ? 1 : 2);

            statement.setInt(10, teacher.getId());

            return statement.executeUpdate() > 0;
        }
    }
    
 
    public boolean delete(int id) throws SQLException {
    	
        String sql = "DELETE FROM maestro WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            return statement.executeUpdate() > 0;
        }
        
    }
    
  //=================================================================================================================================================================
    public boolean isAdmin(Teacher teacher) {

        return teacher.getRole().equals("ADMIN");
    }
    
  //=================================================================================================================================================================
    public boolean teacherExistsInGroup(String year, String group) throws SQLException {

        String sql = """
                SELECT COUNT(*) total
                FROM maestro m
                INNER JOIN anio a
                    ON m.anio_id = a.id
                INNER JOIN grupo g
                    ON m.grupo_id = g.id
                WHERE a.tipo = ?
                AND g.tipoGrupo = ?
                AND m.role = 'MAESTRO'
                """;

        try (
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, year);
            statement.setString(2, group);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                return resultSet.getInt("total") > 0;
            }
        }

        return false;
    }
    
  //=================================================================================================================================================================
    public boolean emailExists(String email) throws SQLException {

        String sql = "SELECT COUNT(*) total FROM maestro WHERE email = ?";

        try (
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, email);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return rs.getInt("total") > 0;
            }
        }

        return false;
    }
    
}