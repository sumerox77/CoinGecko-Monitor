package com.mycompany.cryptogui.favourite;

import com.mycompany.cryptogui.dbconnector.DatabaseConnector;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
public class FavouriteManager {

    DatabaseConnector databaseConnector = new DatabaseConnector();


    public void add(FavoutireEntity favoutireEntity) {
        synchronized (this) {
            try {
                databaseConnector.createFavourite(favoutireEntity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void remove(Long id) {
        synchronized (this) {
            try {
                databaseConnector.deleteFavourite(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Map<Long, FavoutireEntity> getMap() {
        try {
            return databaseConnector.selectAllFavourite();
        } catch (Exception e) {
            e.printStackTrace();
        }
    return null;
    }
}
