package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    
    private static final String URL = "jdbc:mysql://localhost:3306/sistema_primaria";
    private static final String USER = "root";
    private static final String PASSWORD = "MEMD060821";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    
    private static Connection connection = null;

    public static Connection getConnection() throws SQLException {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conexión establecida con sistema_primaria");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se encontró el conector MySQL (JAR)");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error: Credenciales incorrectas o servidor apagado");
            throw e;
        }
        return connection;
    }
}