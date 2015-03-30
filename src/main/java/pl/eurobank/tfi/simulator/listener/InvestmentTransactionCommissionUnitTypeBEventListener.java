package pl.eurobank.tfi.simulator.listener;

import com.google.common.eventbus.Subscribe;
import pl.eurobank.tfi.component.investmentfundwallet.domain.InvestmentSellTransaction;
import pl.eurobank.tfi.component.investmentfundwallet.domain.InvestmentTransactionInterface;
import pl.eurobank.tfi.component.investmentfundwallet.domain.event.InvestmentTransactionEvent;
import pl.eurobank.tfi.simulator.domain.investmentfund.UnitTypeB;

public class InvestmentTransactionCommissionUnitTypeBEventListener {

    @Subscribe
    public void recordInvestmentTransactionEvent(InvestmentTransactionEvent event) {
        if (event.getType() != InvestmentTransactionEvent.Type.PRE) {
            return;
        }

        InvestmentTransactionInterface transaction = event.getInvestmentTransaction();

        // accept UnitA type only
        if (!(transaction.getInvestmentFundUnitType() instanceof UnitTypeB)) {
            return;
        }

        // accept sell transactions only
        if(!(transaction instanceof InvestmentSellTransaction)) {
            return;
        }

        double commission = 0.02d;
        double priceValueFactor = 1.0 - commission;
        transaction.setPrice(transaction.getPrice().multiply(priceValueFactor));
        transaction.setPriceValue(transaction.getPrice().multiply(priceValueFactor));
    }
}
