package com.mycompany.cryptogui.triggers;

import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class TriggerManager {

    private HashMap<Long, TriggerEntity> map = new HashMap<>();

    public void add(TriggerEntity te) {
        synchronized (this) {
            map.put(te.getId(), te);
        }
    }

    public TriggerEntity remove(Long id) {
        synchronized (this) {
            return map.remove(id);
        }
    }

    public Map<Long, TriggerEntity> getMap() {
        return Collections.unmodifiableMap(map);
    }
}
