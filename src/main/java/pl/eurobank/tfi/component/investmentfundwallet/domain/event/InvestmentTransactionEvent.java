package pl.eurobank.tfi.component.investmentfundwallet.domain.event;

import pl.eurobank.tfi.component.investmentfundwallet.domain.InvestmentTransactionInterface;

import java.util.Date;

public class InvestmentTransactionEvent {

    public enum Type {
        PRE,
        POST
    }

    private final Type type;

    private final InvestmentTransactionInterface investmentTransaction;

    private final Date date;

    public InvestmentTransactionEvent(Type type, InvestmentTransactionInterface investmentTransaction, Date date) {
        this.type = type;
        this.investmentTransaction = investmentTransaction;
        this.date = date;
    }

    public Type getType() {
        return type;
    }

    public InvestmentTransactionInterface getInvestmentTransaction() {
        return investmentTransaction;
    }

    public Date getDate() {
        return date;
    }
}
