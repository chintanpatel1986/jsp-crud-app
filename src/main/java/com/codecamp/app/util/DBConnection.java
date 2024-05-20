package com.codecamp.app.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String USER = "chintanpatel";
    private static final String PASSWORD = "root";
    private static final String URL = "jdbc:postgresql://localhost:5432/jsp";
    private static final String DRIVER = "org.postgresql.Driver";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
                if (connection != null) {
                    System.out.println("Database connection established");
                } else {
                    System.out.println("Database connection not established");
                }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
