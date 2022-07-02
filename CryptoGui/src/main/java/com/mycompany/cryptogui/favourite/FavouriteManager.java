package com.mycompany.cryptogui.favourite;

import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class FavouriteManager {

    private HashMap<Long, FavoutireEntity> map = new HashMap<>();

    public void add(FavoutireEntity favoutireEntity) {
        synchronized (this) {
            map.put(favoutireEntity.getId(), favoutireEntity);
        }
    }

    public FavoutireEntity remove(Long id) {
        synchronized (this) {
            return map.remove(id);
        }
    }

    public Map<Long, FavoutireEntity> getMap() {
        return Collections.unmodifiableMap(map);
    }
}
