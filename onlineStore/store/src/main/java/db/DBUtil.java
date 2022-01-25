package db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private final String DB_DRIVER = "org.h2.Driver";
    private final String DB_URL = "jdbc:h2:~/store";
    private final String DB_USER = "admin";
    private final String DB_PASSWORD = "12345";

    public Connection get_connection () {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Connection OK");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Connection ERROR");
        }
        return connection;
    }
}
