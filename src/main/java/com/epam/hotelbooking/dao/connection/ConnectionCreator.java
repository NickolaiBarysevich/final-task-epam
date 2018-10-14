package com.epam.hotelbooking.dao.connection;

import com.epam.hotelbooking.exception.ConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionCreator {

    private static final String URL = "jdbc:mysql://localhost:3306/hotelbooking?useUnicode=true" +
            "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC" +
            "&autoReconnect=true&useSSL=false";
    private static final String ROOT = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() throws ConnectionException {
        try {
            return DriverManager.getConnection(URL, ROOT, PASSWORD);
        } catch (SQLException e) {
            throw new ConnectionException(e.getMessage(), e);
        }

    }
}
