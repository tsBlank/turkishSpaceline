package fr.esiee.turkishspacelines.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    private static final String DATABASE_URL = "jdbc:mysql://mysql-tsblank.alwaysdata.net:3306/tsblank_turkishspaceline?serverTimezone=UTC";
    private static final String DATABASE_USER = "tsblank";
    private static final String DATABASE_PASSWORD = "Root_95*";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
    }
}