package com.mycompany.cryptogui.triggers;

import lombok.Data;

import java.util.HashMap;

@Data
public class TriggerManager {
    public HashMap<Long, TriggerEntity> map = new HashMap<>();

}
