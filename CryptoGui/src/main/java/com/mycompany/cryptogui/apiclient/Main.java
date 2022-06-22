package com.mycompany.cryptogui.apiclient;


import com.mycompany.cryptogui.apiclient.domain.Coins.CoinFullData;
import com.mycompany.cryptogui.apiclient.impl.CoinGeckoApiClientImpl;
import com.mycompany.cryptogui.apiclient.constant.Currency;

class Main {
    public static void main(String[] args) {
        CoinGeckoApiClient client = new CoinGeckoApiClientImpl();
        client.ping();
        CoinFullData coinFullData = new CoinFullData();
        coinFullData = client.getCoinById("bitcoin");
        System.out.println(coinFullData.getImage().getLarge());
        System.out.println(client.getPrice("bitcoin",Currency.USD).get("bitcoin").get(Currency.USD));
        client.shutdown();

    }
}