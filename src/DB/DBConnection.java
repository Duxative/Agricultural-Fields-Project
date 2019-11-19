package DB;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    static Connection connection = null;

    public static Connection getConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/agricultural_field";
            connection = DriverManager.getConnection(url, "root", "");

        } catch (Exception e) {
            System.out.println("no conectado");
        }

        return connection;
    }
}