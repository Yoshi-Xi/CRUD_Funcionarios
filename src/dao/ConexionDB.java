package dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionDB {
    private static final String URL = "jdbc:mysql://localhost:3306/gestion_rrhh?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static String PASSWORD;

    static {
        try (InputStream input = ConexionDB.class.getClassLoader().getResourceAsStream("config.properties")) {
            Properties prop = new Properties();
            if (input == null) {
                System.out.println("Lo siento, no se pudo encontrar config.properties");
                PASSWORD = ""; 
            } else {
                prop.load(input);
                PASSWORD = prop.getProperty("db.password");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: No se encontró el driver de MySQL.");
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}