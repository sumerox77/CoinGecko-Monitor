package com.mycompany.cryptogui.apiclient.domain.Coins.CoinData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CodeAdditionsDeletions4Weeks {
    @JsonProperty("additions")
    private long additions;
    @JsonProperty("deletions")
    private long deletions;

}
