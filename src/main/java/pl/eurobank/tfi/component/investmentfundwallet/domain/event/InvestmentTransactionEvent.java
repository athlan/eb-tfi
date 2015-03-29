package pl.eurobank.tfi.component.investmentfundwallet.domain.event;

import pl.eurobank.tfi.component.investmentfundwallet.domain.InvestmentTransactionInterface;

import java.util.Date;

public class InvestmentTransactionEvent {

    private final InvestmentTransactionInterface investmentTransaction;

    private final Date date;

    public InvestmentTransactionEvent(InvestmentTransactionInterface investmentTransaction, Date date) {
        this.investmentTransaction = investmentTransaction;
        this.date = date;
    }

    public InvestmentTransactionInterface getInvestmentTransaction() {
        return investmentTransaction;
    }

    public Date getDate() {
        return date;
    }
}
