package pl.eurobank.tfi.component.investmentfund.domain.event;

import pl.eurobank.tfi.component.money.domain.PriceInterface;
import pl.eurobank.tfi.component.investmentfund.domain.InvestmentFundInterface;

import java.util.Date;

/**
 * Event denotes change of fund price.
 *
 */
public class InvestmentFundPricingChangesEvent {

    private final InvestmentFundInterface investmentFund;

    private final PriceInterface price;

    private final Date date;

    /**
     * Creates event
     *
     * @param investmentFund investment fund that event describes
     * @param price new price
     * @param date event date
     */
    public InvestmentFundPricingChangesEvent(InvestmentFundInterface investmentFund, PriceInterface price, Date date) {
        this.investmentFund = investmentFund;
        this.price = price;
        this.date = date;
    }

    /**
     * Get investment fund that event describes.
     *
     * @return investment fund that event describes
     */
    public InvestmentFundInterface getInvestmentFund() {
        return investmentFund;
    }

    /**
     * Get new price
     *
     * @return new price
     */
    public PriceInterface getPrice() {
        return price;
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
