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
    

    public Maestro buscarEmail(String email) throws SQLException {
        String sql = "SELECT id, nombre, email, password, role FROM maestro WHERE email = ? LIMIT 1";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Maestro(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("role")
                    );
                }
            }
        }
        return null;
    }
    

    public boolean verificarCredenciales(String email, String password) throws SQLException {
        String sql = "SELECT password FROM maestro WHERE email = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
            	return PasswordUtils.checkPassword(password, rs.getString("password"));
            	}
        }
        return false;
    }
    public Maestro obtenerMaestroPorEmail(String email) throws SQLException {
        //String sql = "SELECT id, nombre, email, password, edad, maestria, genero FROM maestros WHERE email = ? LIMIT 1";
        String sql = """
        		SELECT id, nombre, email, password,
        		edad, maestria, genero, anio, grupo
        		FROM maestro
        		WHERE email = ?
        		LIMIT 1
        		""";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Maestro maestro = new Maestro();
                    maestro.setId(resultSet.getInt("id"));
                    maestro.setNombre(resultSet.getString("nombre"));
                    maestro.setEmail(resultSet.getString("email"));
                    maestro.setPassword(resultSet.getString("password"));
                    maestro.setEdad(resultSet.getInt("edad"));
                    maestro.setMaestria(resultSet.getString("maestria"));
                    maestro.setGenero(resultSet.getString("genero").charAt(0));
                    maestro.setAnio(resultSet.getString("anio"));
                    maestro.setGrupo(resultSet.getString("grupo"));
                    
                    
                    return maestro;
                }
            }
        }
        return null;
    }
    

    public List<Maestro> obtenerTodos() throws SQLException {
        List<Maestro> maestros = new ArrayList<>();
        //String sql = "SELECT id, nombre, email, password, edad, maestria, genero FROM maestros";
        String sql = """
        		SELECT id, nombre, email, password,
        		edad, maestria, genero, anio, grupo
        		FROM maestro
        		""";
        
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            
            while (resultSet.next()) {
                Maestro maestro = new Maestro();
                maestro.setId(resultSet.getInt("id"));
                maestro.setNombre(resultSet.getString("nombre"));
                maestro.setEmail(resultSet.getString("email"));
                maestro.setPassword(resultSet.getString("password"));
                maestro.setEdad(resultSet.getInt("edad"));
                maestro.setMaestria(resultSet.getString("maestria"));
                maestro.setGenero(resultSet.getString("genero").charAt(0));
                maestro.setAnio(resultSet.getString("anio"));
                maestro.setGrupo(resultSet.getString("grupo"));
                
                maestros.add(maestro);
            }
        }
        return maestros;
    }
    
 
    public boolean guardar(Maestro maestro) throws SQLException {

        String sql = """
            INSERT INTO maestro
            (nombre, email, password, edad, maestria, anio_id, grupo_id, role)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            """;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, maestro.getNombre());
            statement.setString(2, maestro.getEmail());
            statement.setString(3,PasswordUtils.hashPassword(maestro.getPassword()));
            statement.setInt(4, maestro.getEdad());
            statement.setString(5, maestro.getMaestria());
            statement.setInt(6, Integer.parseInt(maestro.getAnio()));
            statement.setInt(7,
                    maestro.getGrupo().equals("A") ? 1 : 2);

            statement.setString(8, "MAESTRO");

            return statement.executeUpdate() > 0;
        }
    }
    public boolean actualizar(Maestro maestro) throws SQLException {
        String sql = "UPDATE maestro SET nombre = ?, email = ?, password = ?, edad = ?, maestria = ?, genero = ?, anio = ?, grupo = ? WHERE id = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, maestro.getNombre());
            statement.setString(2, maestro.getEmail());
            statement.setString(3, PasswordUtils.hashPassword(maestro.getPassword()));
            statement.setInt(4, maestro.getEdad());
            statement.setString(5, maestro.getMaestria());
            statement.setString(6, String.valueOf(maestro.getGenero()));
            statement.setString(7, maestro.getAnio());
            statement.setString(8, maestro.getGrupo());
            statement.setInt(9, maestro.getId());
            
            return statement.executeUpdate() > 0;
        }
    }
    
 
    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM maestro WHERE id = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        }
    }
}
