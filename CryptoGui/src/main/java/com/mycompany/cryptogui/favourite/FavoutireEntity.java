package com.mycompany.cryptogui.favourite;

import lombok.Data;
import lombok.NonNull;

@Data
public class FavoutireEntity {
    private long id;
    @NonNull
    private String coinName;
    @NonNull
    private String coinId;
    @NonNull
    private String hashingAlgorithm;
    @NonNull
    private String trustScore;
}
