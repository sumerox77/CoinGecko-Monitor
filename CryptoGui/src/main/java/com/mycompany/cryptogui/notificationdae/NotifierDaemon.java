package com.mycompany.cryptogui.notificationdae;

import com.mycompany.cryptogui.apiclient.CoinGeckoApiClient;
import com.mycompany.cryptogui.apiclient.constant.Currency;
import com.mycompany.cryptogui.triggers.TriggerEntity;
import com.mycompany.cryptogui.triggers.TriggerManager;
import dorkbox.notify.Notify;
import dorkbox.notify.Pos;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotifierDaemon implements Runnable {

    private TriggerManager manager;
    private CoinGeckoApiClient client;
    private boolean active;

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10000);
                if (active) {
                    System.out.println("me enabled");
                    synchronized (manager) {
                        for (TriggerEntity te : manager.getMap().values()) {
                            double price = client.getPrice(te.getCoinID(), Currency.USD).get(te.getCoinID()).get(Currency.USD);
                            if (price >= te.getPriceLowerBound() && price <= te.getPriceUpperBound())
                            {
                                Notify.create()
                                        .title("COING-ECKO PRICE NOTIFICATION")
                                        .text(String.format("The price of %s, is within %.2f and %.2f\nPrice: %.2f USD", te.getCoinID(), te.getPriceLowerBound(), te.getPriceUpperBound(), price))
                                        .hideAfter(5000)
                                        .shake(1000, 15)
                                        .position(Pos.TOP_RIGHT)
                                        .showInformation();
                            }


                            System.out.println("Checking " + te);
                            // TODO
                        }
                    }
                } else {
                    System.out.println("me disabled");
                }
            } catch (Exception e) {
                System.out.println("Exception");
                e.printStackTrace();
            }
        }
    }
}
