package pl.eurobank.tfi.component.investmentfundwallet.domain.event;

import pl.eurobank.tfi.component.investmentfundwallet.domain.InvestmentTransactionInterface;

import java.util.Date;

/**
 * This class represents single transaction event.
 *
 */
public class InvestmentTransactionEvent {

    /**
     * Event type. The moment of transaction.
     *
     */
    public enum Type {
        PRE,
        POST
    }

    private final Type type;

    private final InvestmentTransactionInterface investmentTransaction;

    private final Date date;

    /**
     * Creates event.
     *
     * @param type event type
     * @param investmentTransaction investment transaction that event describes
     * @param date event date
     */
    public InvestmentTransactionEvent(Type type, InvestmentTransactionInterface investmentTransaction, Date date) {
        this.type = type;
        this.investmentTransaction = investmentTransaction;
        this.date = date;
    }

    /**
     * Get event type. The moment of transaction.
     * @return event type. The moment of transaction.
     */
    public Type getType() {
        return type;
    }

    /**
     * Get investment transaction that event describes.
     * @return investment transaction that event describes
     */
    public InvestmentTransactionInterface getInvestmentTransaction() {
        return investmentTransaction;
    }

    /**
     * Get event date
     *
     * @return event date
     */
    public Date getDate() {
        return date;
    }
}
