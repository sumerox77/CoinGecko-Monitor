package com.mycompany.cryptogui.triggers;

import lombok.Data;
import lombok.NonNull;

@Data
public class TriggerEntity {
    private Long id;
    @NonNull
    private Double priceLowerBound;
    @NonNull
    private Double priceUpperBound;
    @NonNull
    private String coinID;
}
