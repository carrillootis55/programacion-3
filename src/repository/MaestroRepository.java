package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Maestro;
import config.DatabaseConnection;

public class MaestroRepository {
    
    private Connection connection;
    
    public MaestroRepository() throws SQLException {
        this.connection = DatabaseConnection.getConnection();
    }
    
    public Maestro login(String email, String password) throws SQLException {
        // Consulta para obtener los datos 
        //String sql = "SELECT id, nombre, email FROM maestros WHERE email = ? AND password = ?";
        String sql = """
        		SELECT id, nombre, email, anio, grupo
        		FROM maestros
        		WHERE email = ? AND password = ?
        		""";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, email);
            stmt.setString(2, password);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Creamos el objeto Maestro con los datos de la base de datos
                    Maestro maestro = new Maestro();
                    maestro.setId(rs.getInt("id"));
                    maestro.setNombre(rs.getString("nombre"));
                    maestro.setEmail(rs.getString("email"));
                    maestro.setAnio(rs.getString("anio"));
                    maestro.setGrupo(rs.getString("grupo"));
                    
                    return maestro; // Login exitoso
                }
            }
        }
        return null; // Login fallido
    }

    public Maestro buscarEmail(String email) throws SQLException {
        String sql = "SELECT id, nombre, email, password FROM maestros WHERE email = ? LIMIT 1";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Maestro(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                    );
                }
            }
        }
        return null;
    }
    

    public boolean verificarCredenciales(String email, String password) throws SQLException {
        String sql = "SELECT password FROM maestros WHERE email = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getString("password").equals(password);
            }
        }
        return false;
    }
    public Maestro obtenerMaestroPorEmail(String email) throws SQLException {
        //String sql = "SELECT id, nombre, email, password, edad, maestria, genero FROM maestros WHERE email = ? LIMIT 1";
        String sql = """
        		SELECT id, nombre, email, password,
        		edad, maestria, genero, anio, grupo
        		FROM maestros
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
        		FROM maestros
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
        String sql = "INSERT INTO maestros (nombre, email, password, edad, maestria, genero, anio, grupo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, maestro.getNombre());
            statement.setString(2, maestro.getEmail());
            statement.setString(3, maestro.getPassword());
            statement.setInt(4, maestro.getEdad());
            statement.setString(5, maestro.getMaestria());
            statement.setString(6, String.valueOf(maestro.getGenero()));
            statement.setString(7, maestro.getAnio());
            statement.setString(8, maestro.getGrupo());
            
            return statement.executeUpdate() > 0;
        }
    }
    
 
    public boolean actualizar(Maestro maestro) throws SQLException {
        String sql = "UPDATE maestros SET nombre = ?, email = ?, password = ?, edad = ?, maestria = ?, genero = ?, anio = ?, grupo = ? WHERE id = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, maestro.getNombre());
            statement.setString(2, maestro.getEmail());
            statement.setString(3, maestro.getPassword());
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
        String sql = "DELETE FROM maestros WHERE id = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        }
    }
}
