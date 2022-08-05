package com.mycompany.cryptogui;

import com.mycompany.cryptogui.favourite.FavoutireEntity;
import com.mycompany.cryptogui.triggers.TriggerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

public class DatabaseConnector {
    private static final Logger log = LoggerFactory.getLogger(DatabaseConnector.class);

    Connection connection;

    public DatabaseConnector() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");


            Statement statement = connection.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS TRIGGERS_INFO (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, PRICE_LOW_BOUND INT NOT NULL, PRICE_UP_BOUND INT NOT NULL, COIN_ID CHAR(50))";
            statement.executeUpdate(sql);
            statement.close();

            String sqlTableFavourite = "CREATE TABLE IF NOT EXISTS FAVOURITE_INFO (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, COIN_NAME VARCHAR(100), COIN_ID VARCHAR(100), HASHING_ALG VARCHAR(100), TRUST_SCORE VARCHAR(100))";
            statement.executeUpdate(sqlTableFavourite);
            statement.close();


        } catch ( Exception e ) {
            log.error("Error while creating db! " + e.getMessage());
            System.exit(0);
        }
        log.info("Table created!");
    }

    public long createFavourite(FavoutireEntity favoutireEntity) throws Exception {
        try(PreparedStatement statement = connection.prepareStatement("INSERT INTO FAVOURITE_INFO (COIN_NAME, COIN_ID, HASHING_ALG, TRUST_SCORE) VALUES  (?, ?, ?, ?)");){
            statement.setString(1, favoutireEntity.getCoinName());
            statement.setString(2, favoutireEntity.getCoinId());
            statement.setString(3, favoutireEntity.getHashingAlgorithm());
            statement.setString(4, favoutireEntity.getTrustScore());
            statement.execute();

            try(ResultSet resultSet = connection.createStatement().executeQuery("SELECT last_insert_rowid()");){
                resultSet.next();
                long id = resultSet.getLong(1);
                favoutireEntity.setId(id);
                return id;
            }

        }
    }

    public long createTrigger(TriggerEntity te) throws Exception {
        try(PreparedStatement statement = connection.prepareStatement("INSERT INTO TRIGGERS_INFO (PRICE_LOW_BOUND, PRICE_UP_BOUND, COIN_ID) VALUES (?, ?, ?)");){
            statement.setDouble(1, te.getPriceLowerBound());
            statement.setDouble(2, te.getPriceUpperBound());
            statement.setString(3, te.getCoinID());
            statement.execute();

            try(ResultSet resultSet = connection.createStatement().executeQuery("SELECT last_insert_rowid()");){
                resultSet.next();
                long id = resultSet.getLong(1);
                te.setId(id);
                return id;
            }
        }
    }

    public void deleteFavourite(Long id) throws Exception {
        try(PreparedStatement statement = connection.prepareStatement("DELETE FROM FAVOURITE_INFO WHERE ID = ?");) {
            statement.setLong(1, id);
            statement.execute();
        }
    }

    public void deleteTrigger(Long id) throws Exception {
        try(PreparedStatement statement = connection.prepareStatement("DELETE FROM TRIGGERS_INFO WHERE ID = ?");) {
            statement.setLong(1, id);
            statement.execute();
        }
    }

    public HashMap<Long, FavoutireEntity> selectAllFavourite() throws Exception {
        try(PreparedStatement statement = connection.prepareStatement("SELECT ID, COIN_NAME, COIN_ID, HASHING_ALG, TRUST_SCORE FROM FAVOURITE_INFO");) {
            ResultSet resultSet =  statement.executeQuery();
            HashMap<Long, FavoutireEntity> tes = new HashMap<>();
            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                String coinName = resultSet.getString(2);
                String coinID = resultSet.getString(3);
                String hashingAlgorithm = resultSet.getString(4);
                String trustScore = resultSet.getString(5);
                FavoutireEntity temp = new FavoutireEntity(coinName, coinID, hashingAlgorithm, trustScore);
                temp.setId(id);
                tes.put(temp.getId(), temp);
            }
            return tes;
        }
    }

    public HashMap<Long, TriggerEntity> selectAllTriggers() throws Exception {
        try(PreparedStatement statement = connection.prepareStatement("SELECT ID, PRICE_LOW_BOUND, PRICE_UP_BOUND, COIN_ID FROM TRIGGERS_INFO");) {
            ResultSet resultSet =  statement.executeQuery();
            HashMap<Long, TriggerEntity> tes = new HashMap<>();
            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                double priceLowBound = resultSet.getDouble(2);
                double priceUpBound = resultSet.getDouble(3);
                String coinID = resultSet.getString(4);
                TriggerEntity temp = new TriggerEntity(priceLowBound, priceUpBound, coinID);
                temp.setId(id);
                tes.put(temp.getId(), temp);
            }
            return tes;
        }
    }

    public void deleteAllFavourite() throws Exception {
        try(PreparedStatement statement = connection.prepareStatement("DELETE FROM FAVOURITE_INFO");) {
            statement.execute();
        }
    }

    public void deleteAllTriggers() throws Exception {
        try(PreparedStatement statement = connection.prepareStatement("DELETE FROM TRIGGERS_INFO");) {
            statement.execute();
        }
    }


    public static void main(String[] args) throws Exception {
        DatabaseConnector databaseConnector = new DatabaseConnector();
        databaseConnector.createTrigger(new TriggerEntity(50.0, 1439820.0, "bitcoin"));
        databaseConnector.createTrigger(new TriggerEntity(624.0, 104321.0, "ethereum"));
        databaseConnector.createTrigger(new TriggerEntity(7504.0, 15492.0, "dogecoin"));
        databaseConnector.createTrigger(new TriggerEntity(833.0, 13313.0, "litecoin"));
        databaseConnector.deleteTrigger(1L);
        databaseConnector.deleteAllTriggers();

        databaseConnector.createFavourite(new FavoutireEntity("bitcoin", "btc", "123vc", "green"));
        databaseConnector.createFavourite(new FavoutireEntity("eth", "eth", "1223vc", "red"));
        databaseConnector.createFavourite(new FavoutireEntity("dogecoin", "dogecoin", "122133vc", "green"));
        databaseConnector.createFavourite(new FavoutireEntity("litecoin", "litecoin", "12vkc3vc", "red"));
        databaseConnector.deleteFavourite(1L);
        databaseConnector.deleteAllFavourite();

    }

}
