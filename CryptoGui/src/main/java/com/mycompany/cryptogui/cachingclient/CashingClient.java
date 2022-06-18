package com.mycompany.cryptogui.cachingclient;


import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.sql.*;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;

public class CashingClient {

    private static final Logger logger = LogManager.getLogger(CashingClient.class);

    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");
            logger.info("Opened database successfully");

            statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS COMPANY " +
                    "(ID INT PRIMARY KEY     NOT NULL," +
                    " NAME           TEXT    NOT NULL, " +
                    " AGE            INT     NOT NULL, " +
                    " ADDRESS        CHAR(50), " +
                    " SALARY         REAL)";
            statement.executeUpdate(sql);
            statement.execute("SELECT * FROM COMPANY");
            statement.close();
            connection.close();
        } catch ( Exception e ) {
            logger.error(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        logger.info("Table created successfully");
    }
}
