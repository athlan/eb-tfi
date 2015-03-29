package pl.eurobank.tfi.simulator.investmentfundpricegenerator;

import pl.eurobank.tfi.component.investmentfund.domain.InvestmentFundInterface;
import pl.eurobank.tfi.component.money.domain.Price;
import pl.eurobank.tfi.component.money.domain.PriceInterface;

public class RangeButNotUnderZeroInvestmentFundPriceGenerator implements InvestmentFundPriceGeneratorInterface {

    protected final PriceInterface min;
    protected final PriceInterface max;

    protected double range_min;
    protected double range;

    public RangeButNotUnderZeroInvestmentFundPriceGenerator(final PriceInterface min, final PriceInterface max) {
        if(!min.getCurrency().equals(max.getCurrency())) {
            throw new IllegalArgumentException("min and max price should be in the same currency");
        }
        if(!(min.getAmount() <= max.getAmount())) {
            throw new IllegalArgumentException("min should be less of equal max");
        }

        this.min = min;
        this.max = max;

        this.range_min = min.getAmount();
        this.range = max.getAmount() - min.getAmount();
    }

    @Override
    public PriceInterface getFundPrice(InvestmentFundInterface investmentFund) {
        PriceInterface current = investmentFund.getCurrentPricing();

        // cannot generate new fund price when different currencies
        // so return current instead
        if(!current.getCurrency().equals(min.getCurrency())) {
            return current;
        }

        double new_amount_delta = Math.random() * range + range_min;

        return new Price(current.getAmount() + new_amount_delta, min.getCurrency());
    }
}
