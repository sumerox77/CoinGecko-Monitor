package com.mycompany.cryptogui.apiclient.domain.Coins;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mycompany.cryptogui.apiclient.domain.Coins.CoinData.*;
import com.mycompany.cryptogui.apiclient.domain.Shared.Image;

import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinHistoryById {
    @JsonProperty("id")
    private String id;
    @JsonProperty("symbol")
    private String symbol;
    @JsonProperty("name")
    private String name;
    @JsonProperty("localization")
    private Map<String, String> localization;
    @JsonProperty("image")
    private Image image;
    @JsonProperty("market_data")
    private MarketData marketData;
    @JsonProperty("community_data")
    private CommunityData communityData;
    @JsonProperty("developer_data")
    private DeveloperData developerData;
    @JsonProperty("public_interest_stats")
    private PublicInterestStats publicInterestStats;

}
