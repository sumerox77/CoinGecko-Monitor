package com.mycompany.cryptogui.cachingclient;


import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.sql.*;

public class CashingClient {

    private static final Logger logger = LogManager.getLogger(CashingClient.class);

    public static void main(String[] args) throws SQLException {
        Connection connection;
        Statement statement;

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
                    " SALARY         INT)";
            statement.executeUpdate(sql);
            statement.execute("INSERT INTO COMPANY VALUES (3, \"JohnTest\", 5, \"Blank Address\", 10);");
            statement.close();
            connection.close();
        } catch ( Exception e ) {
            logger.error(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        logger.info("Table created successfully");
    }
}
