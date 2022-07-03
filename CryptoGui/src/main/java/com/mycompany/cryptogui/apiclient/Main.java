package com.mycompany.cryptogui.apiclient;


import com.mycompany.cryptogui.apiclient.domain.Coins.CoinFullData;
import com.mycompany.cryptogui.apiclient.impl.CoinGeckoApiClientImpl;
import com.mycompany.cryptogui.apiclient.constant.Currency;

import java.util.List;

class Main {
    public static void main(String[] args) {
        CoinGeckoApiClient client = new CoinGeckoApiClientImpl();
        client.ping();
        CoinFullData coinFullData = new CoinFullData();
        coinFullData = client.getCoinById("bitcoin");

        System.out.println(coinFullData.getName());
        List<List<String>> coinData = client.getCoinOHLC("bitcoin", "usd", 1);
        for (List<String> x : coinData)
        {
            System.out.println(Double.parseDouble(x.get(2)));
        }


        client.shutdown();

    }
}