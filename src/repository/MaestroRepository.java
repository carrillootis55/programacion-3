package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.PasswordUtils;
import models.Maestro;
import config.DatabaseConnection;

public class MaestroRepository {
    
    private Connection connection;
    
    public MaestroRepository() throws SQLException {
        this.connection = DatabaseConnection.getConnection();
    }
    
  //=================================================================================================================================================================
    public Maestro buscarEmail(String email) throws SQLException {
        String sql = """
                SELECT
                    m.id,
                    m.nombre,
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

        try (PreparedStatement statement =connection.prepareStatement(sql)) {

            statement.setString(1, email);

            try (ResultSet resultSet =statement.executeQuery()) {

                if (resultSet.next()) {

                    Maestro maestro = new Maestro();

                    maestro.setId(resultSet.getInt("id"));

                    maestro.setNombre(resultSet.getString("nombre"));

                    maestro.setEmail(resultSet.getString("email"));

                    maestro.setPassword(resultSet.getString("password"));
                    
                    maestro.setSexo(resultSet.getString("sexo"));

                    maestro.setEdad(resultSet.getInt("edad"));

                    maestro.setMaestria(resultSet.getString("maestria"));

                    maestro.setRole(resultSet.getString("role") );

                    maestro.setAnio(resultSet.getString("anio") );

                    maestro.setGrupo(resultSet.getString("grupo"));

                    return maestro;
                }
            }
        }

        return null;
    }
  //=================================================================================================================================================================
    public boolean verificarCredenciales( String email,String password ) throws SQLException {

        String sql =  "SELECT password FROM maestro WHERE email = ?";

        try (
                Connection conn = DatabaseConnection.getConnection();

                PreparedStatement stmt =conn.prepareStatement(sql)
        ) {

            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                return PasswordUtils.checkPassword(password,rs.getString("password") );
            }
        }

        return false;
    }
    
  //=================================================================================================================================================================
    public Maestro obtenerMaestroPorEmail(String email)throws SQLException {

        String sql = """
                SELECT
                    m.id,
                    m.nombre,
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

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, email);

            try (ResultSet resultSet =statement.executeQuery()) {

                if (resultSet.next()) {

                    Maestro maestro = new Maestro();

                    maestro.setId(resultSet.getInt("id")
                    );

                    maestro.setNombre(resultSet.getString("nombre"));

                    maestro.setEmail( resultSet.getString("email"));

                    maestro.setPassword(resultSet.getString("password") );
                    
                    maestro.setSexo(resultSet.getString("sexo"));

                    maestro.setEdad(resultSet.getInt("edad"));

                    maestro.setMaestria(resultSet.getString("maestria"));

                    maestro.setRole(resultSet.getString("role"));

                    maestro.setAnio(resultSet.getString("anio"));

                    maestro.setGrupo( resultSet.getString("grupo"));

                    return maestro;
                }
            }
        }

        return null;
    }

  //=================================================================================================================================================================
    public List<Maestro> obtenerTodos()throws SQLException {

        List<Maestro> maestros = new ArrayList<>();

        String sql = """
                SELECT
                    m.id,
                    m.nombre,
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
                Statement statement =connection.createStatement();

                ResultSet resultSet =statement.executeQuery(sql)
        ) {

            while (resultSet.next()) {

                Maestro maestro = new Maestro();

                maestro.setId(resultSet.getInt("id"));

                maestro.setNombre(resultSet.getString("nombre") );

                maestro.setEmail( resultSet.getString("email") );

                maestro.setPassword(resultSet.getString("password"));
                
                maestro.setSexo(resultSet.getString("sexo"));

                maestro.setEdad(resultSet.getInt("edad"));

                maestro.setMaestria(resultSet.getString("maestria"));

                maestro.setRole(resultSet.getString("role") );

                maestro.setAnio(resultSet.getString("anio") );

                maestro.setGrupo(resultSet.getString("grupo"));

                maestros.add(maestro);
            }
        }

        return maestros;
    }
  //=================================================================================================================================================================
    public List<Maestro> obtenerSoloMaestros()throws SQLException {

        List<Maestro> maestros =
                new ArrayList<>();

        String sql = """
                SELECT
                    m.id,
                    m.nombre,
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

            PreparedStatement statement =connection.prepareStatement(sql);

            ResultSet resultSet =statement.executeQuery()

        ) {

            while (resultSet.next()) {

                Maestro maestro = new Maestro();

                maestro.setId( resultSet.getInt("id"));

                maestro.setNombre( resultSet.getString("nombre"));

                maestro.setEmail( resultSet.getString("email"));

                maestro.setPassword( resultSet.getString("password") );

                maestro.setSexo(resultSet.getString("sexo"));

                maestro.setEdad(resultSet.getInt("edad"));

                maestro.setMaestria(resultSet.getString("maestria"));

                maestro.setRole(resultSet.getString("role"));

                maestro.setAnio(resultSet.getString("anio"));

                maestro.setGrupo(resultSet.getString("grupo"));

                maestros.add(maestro);
            }
        }

        return maestros;
    }
    
  //=================================================================================================================================================================
    public int contarMaestros() throws SQLException {

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
    public boolean guardar(Maestro maestro) throws SQLException {

        String sql = """
                INSERT INTO maestro
                (
                    nombre,
                    email,
                    password,
                    sexo,
                    edad,
                    maestria,
                    anio_id,
                    grupo_id,
                    role
                )
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (PreparedStatement statement =connection.prepareStatement(sql)) {

            statement.setString(1,maestro.getNombre() );

            statement.setString(2,maestro.getEmail());

            statement.setString(3, PasswordUtils.hashPassword(maestro.getPassword()));
            
            statement.setString(4, maestro.getSexo());

            statement.setInt(5,maestro.getEdad());

            statement.setString(6,maestro.getMaestria());

            statement.setInt(7,Integer.parseInt(maestro.getAnio()));

            statement.setInt(8,maestro.getGrupo().equals("A")? 1 : 2);

            statement.setString(9, "MAESTRO");

            return statement.executeUpdate() > 0;
        }
    }
    
  //=================================================================================================================================================================
    public boolean actualizar(Maestro maestro)throws SQLException {
        String sql = """
                UPDATE maestro
                SET
                    nombre = ?,
                    email = ?,
                    password = ?,
                    sexo = ?,
                    edad = ?,
                    maestria = ?,
                    anio_id = ?,
                    grupo_id = ?
                WHERE id = ?
                """;

        try (PreparedStatement statement =connection.prepareStatement(sql)) {

            statement.setString( 1,maestro.getNombre());

            statement.setString(2,maestro.getEmail() );

            statement.setString(3,PasswordUtils.hashPassword(maestro.getPassword()));
            
            statement.setString(4, maestro.getSexo());

            statement.setInt(5,maestro.getEdad());

            statement.setString(6, maestro.getMaestria());
            
            statement.setInt(7,Integer.parseInt(maestro.getAnio()));

            statement.setInt(8, maestro.getGrupo().equals("A")? 1 : 2);

            statement.setInt( 9,maestro.getId());

            return statement.executeUpdate() > 0;
        }
    }
    
 
    /*public boolean eliminar(int id)throws SQLException {
    	if (contarMaestros() <= 6) {

            return false;
        }

        String sql =
                "DELETE FROM maestro WHERE id = ?";

        try (PreparedStatement statement =connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            return statement.executeUpdate() > 0;
        }
        
    }*/
  //=================================================================================================================================================================
    
    public boolean esAdmin(Maestro maestro) {

        return maestro.getRole().equals("ADMIN");
    }
  //=================================================================================================================================================================
    public boolean existeMaestroEnGrupo(String anio, String grupo)throws SQLException {

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

            statement.setString(1, anio);
            statement.setString(2, grupo);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                return resultSet.getInt("total") > 0;
            }
        }

        return false;
    }
    
    
    
    
}
