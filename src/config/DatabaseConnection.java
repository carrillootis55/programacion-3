package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static Connection connection = null;

    /*DriverManager es una clase, su trabajo es trabajar como el "jefe" y coordinador de todas las conexiones de bd
     *En lugar de escribir la contrasena directamente en el codigo usamos un archivo de texto 
     *db.properties que lee cuatro variables la dirección (url), el usuario (user), la contraseña (pass) y el nombre del driver (driver)*/
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            Properties props = new Properties();
            
            try (FileInputStream fis = new FileInputStream("db.properties")) {
                props.load(fis);
                
                String url = props.getProperty("db.url");
                String user = props.getProperty("db.user");
                String pass = props.getProperty("db.password");
                String driver = props.getProperty("db.driver");
                //AQUI carga la memoria ram en el driver basicamente le dice java al driver despierta porque hablaremos mysql
                Class.forName(driver);
                
                connection = DriverManager.getConnection(url, user, pass);
                System.out.println("Conexión establecida exitosamente");
                
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error: No se pudo leer el archivo db.properties o no existe");
                e.printStackTrace();
                throw new SQLException("Error de configuración local");
            }
        }
        return connection;
    }
}