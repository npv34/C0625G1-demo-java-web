package com.codegym.myapp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    private static DBConnect instance;
    private Connection connection;

    public DBConnect() {

    }

    public Connection getConnection() {
        if (connection != null) {
            return connection;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String jdbcURL = "jdbc:mysql://localhost:3306/app_db?useSSL=false";
            String jdbcUsername = "root";
            String jdbcPassword = "123456@Abc";
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            System.out.println("Connected to database");
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Connection failed");
            throw new RuntimeException(e);
        }
    }

    public static DBConnect getInstance() {
        if (instance == null) {
            instance = new DBConnect();
        }
        return instance;
    }

}
