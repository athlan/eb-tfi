package pl.eurobank.tfi.component.investmentfund.domain.event;

import pl.eurobank.tfi.component.money.domain.PriceInterface;
import pl.eurobank.tfi.component.investmentfund.domain.InvestmentFundInterface;

import java.util.Date;

public class InvestmentFundPricingChangesEvent {

    private final InvestmentFundInterface investmentFund;

    private final PriceInterface price;

    private final Date date;

    public InvestmentFundPricingChangesEvent(InvestmentFundInterface investmentFund, PriceInterface price, Date date) {
        this.investmentFund = investmentFund;
        this.price = price;
        this.date = date;
    }

    public InvestmentFundInterface getInvestmentFund() {
        return investmentFund;
    }

    public PriceInterface getPrice() {
        return price;
    }

    public Date getDate() {
        return date;
    }
}
