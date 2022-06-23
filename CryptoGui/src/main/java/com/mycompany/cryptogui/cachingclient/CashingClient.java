package com.mycompany.cryptogui.cachingclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
public class CashingClient {

    private static final Logger log = LoggerFactory.getLogger(CashingClient.class);

    public static void main(String[] args) throws SQLException {
        Connection connection;
        Statement statement;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");

            statement = connection.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS TRIGGERS_INFO (ID INT PRIMARY KEY NOT NULL, PRICE_LOW_BOUND INT NOT NULL, PRICE_UP_BOUND INT NOT NULL, COIN_ID CHAR(50))";
            statement.executeUpdate(sql);
//            statement.execute("INSERT INTO COMPANY VALUES (3, \"JohnTest\", 5, \"Blank Address\", 10);");

            statement.close();
            connection.close();
        } catch ( Exception e ) {
            log.error("Error while creating db!" + e.getMessage());
            System.exit(0);
        }
        log.warn("Table created!");
    }
}
