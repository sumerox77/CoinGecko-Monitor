package com.mycompany.cryptogui.triggers;

import com.mycompany.cryptogui.dbconnector.DatabaseConnector;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
public class TriggerManager {

    DatabaseConnector databaseConnector = new DatabaseConnector();

    public void add(TriggerEntity te) {
        synchronized (this) {
            try {
                databaseConnector.createTrigger(te);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void remove(Long id) {
        synchronized (this) {
            try {
                databaseConnector.deleteTrigger(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Map<Long, TriggerEntity> getMap() {
        try {
            return databaseConnector.selectAllTriggers();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
