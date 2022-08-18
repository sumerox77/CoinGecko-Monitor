package com.mycompany.cryptogui.notificationdae;

import com.mycompany.cryptogui.apiclient.CoinGeckoApiClient;
import com.mycompany.cryptogui.apiclient.constant.Currency;
import com.mycompany.cryptogui.triggers.TriggerEntity;
import com.mycompany.cryptogui.triggers.TriggerManager;
import dorkbox.notify.Notify;
import dorkbox.notify.Pos;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
@AllArgsConstructor
public class NotifierDaemon implements Runnable {


    private static final Logger log = LoggerFactory.getLogger(NotifierDaemon.class);

    private TriggerManager manager;
    private CoinGeckoApiClient client;
    private boolean active;

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(60000);
                if (active) {
                    log.info("Notification Daemon Enabled");
                    synchronized (manager) {
                        for (TriggerEntity te : manager.getMap().values()) {
                            double price = client.getPrice(te.getCoinID(), Currency.USD).get(te.getCoinID()).get(Currency.USD);
                            if (price >= te.getPriceLowerBound() && price <= te.getPriceUpperBound())
                            {
                                Notify.create()
                                        .title("Price Notification Manager:")
                                        .text(String.format("The price of %s, is within %.2f and %.2f\nPrice: %.2f USD", te.getCoinID(), te.getPriceLowerBound(), te.getPriceUpperBound(), price))
                                        .hideAfter(5000)
                                        .shake(1000, 15)
                                        .position(Pos.TOP_RIGHT)
                                        .showInformation();
                            }
                            log.info("Notification Daemon Disabled" + te);

                        }
                    }
                } else {
                    log.info("Notification Daemon Disabled");
                }
            } catch (Exception e) {
                log.error("Notification Daemon Disabled"+ e.getMessage());
            }
        }
    }
}
