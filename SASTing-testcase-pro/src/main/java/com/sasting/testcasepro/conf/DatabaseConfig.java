package com.sasting.testcasepro.conf;

import lombok.NonNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConfig {
    public static final String JDBC_URL = "jdbc:mysql://localhost:3306/sastingdb";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "123456";

    @NonNull
    public static Statement getStatement() {
        Statement statement;
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return statement;
    }
}
