package pl.eurobank.tfi.simulator.listener;

import com.google.common.eventbus.Subscribe;
import pl.eurobank.tfi.component.investmentfundwallet.domain.event.InvestmentTransactionEvent;

public class InvestmentTransactionEventListener {

    @Subscribe
    public void recordInvestmentSellTransaction(InvestmentTransactionEvent event) {
        throw new RuntimeException("acc");
//        System.out.println(event.getInvestmentTransaction());
    }

    @Subscribe
    public void recordAll(Object event) {
        throw new RuntimeException("abb");
    }
}
