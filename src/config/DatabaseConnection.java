package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static Connection connection = null;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            Properties props = new Properties();
            
            try (FileInputStream fis = new FileInputStream("db.properties")) {
                props.load(fis);
                
                String url = props.getProperty("db.url");
                String user = props.getProperty("db.user");
                String pass = props.getProperty("db.password");
                String driver = props.getProperty("db.driver");

                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, pass);
                System.out.println("Conexión establecida exitosamente");
                
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error: No se pudo leer el archivo db.properties o no existe.");
                e.printStackTrace();
                throw new SQLException("Error de configuración local.");
            }
        }
        return connection;
    }
}