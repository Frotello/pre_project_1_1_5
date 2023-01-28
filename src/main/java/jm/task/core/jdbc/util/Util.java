package jm.task.core.jdbc.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root1234";
    private static Connection connection = null;

    public static Connection getConnection(){

        try  {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if (!connection.isClosed()) {
                System.out.println("Соединение с БД установлено");
            }
            return connection;
        } catch (SQLException e) {
            System.out.println("Соединение с БД не установлено");
        }
            return connection;

    }
}
