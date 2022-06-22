package com.mycompany.cryptogui.apiclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class CoinGeckoApiError {
    @JsonProperty("code")
    private int code;
    @JsonProperty("error")
    private String message;

}
