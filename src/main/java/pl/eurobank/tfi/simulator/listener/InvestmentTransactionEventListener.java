package pl.eurobank.tfi.simulator.listener;

import com.google.common.eventbus.Subscribe;
import pl.eurobank.tfi.component.investmentfundwallet.domain.event.InvestmentTransactionEvent;

public class InvestmentTransactionEventListener {

    @Subscribe
    public void recordInvestmentTransactionEvent(InvestmentTransactionEvent event) {
//        System.out.println(event.getInvestmentTransaction());
    }
}
