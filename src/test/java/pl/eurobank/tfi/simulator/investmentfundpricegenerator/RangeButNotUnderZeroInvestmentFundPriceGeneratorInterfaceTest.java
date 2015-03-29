package pl.eurobank.tfi.simulator.investmentfundpricegenerator;

import org.junit.Test;
import pl.eurobank.tfi.component.investmentfund.domain.InvestmentFund;
import pl.eurobank.tfi.component.investmentfund.domain.InvestmentFundInterface;
import pl.eurobank.tfi.component.money.domain.Price;
import pl.eurobank.tfi.component.money.domain.PriceInterface;

import java.util.Currency;

import static org.junit.Assert.*;

public class RangeButNotUnderZeroInvestmentFundPriceGeneratorInterfaceTest {

    @Test(expected = IllegalArgumentException.class)
    public void should_not_be_initialized_with_invalid_minmax() {
        PriceInterface min = new Price(2.0, Currency.getInstance("EUR"));
        PriceInterface max = new Price(1.0, Currency.getInstance("EUR"));
        InvestmentFundPriceGeneratorInterface generator = new RangeButNotUnderZeroInvestmentFundPriceGenerator(min, max);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_be_initialized_with_minmax_different_currencies() {
        PriceInterface min = new Price(1.0, Currency.getInstance("EUR"));
        PriceInterface max = new Price(1.5, Currency.getInstance("PLN"));
        InvestmentFundPriceGeneratorInterface generator = new RangeButNotUnderZeroInvestmentFundPriceGenerator(min, max);
    }

    @Test
    public void should_be_in_same_currency() {
        PriceInterface min = new Price(1.0, Currency.getInstance("EUR"));
        PriceInterface max = new Price(2.0, Currency.getInstance("EUR"));
        InvestmentFundPriceGeneratorInterface generator = new RangeButNotUnderZeroInvestmentFundPriceGenerator(min, max);

        PriceInterface investmentFundPrice = new Price(100.0, Currency.getInstance("EUR"));
        InvestmentFundInterface investmentFund = new InvestmentFund("Fund1", investmentFundPrice);

        PriceInterface new_price = generator.getFundPrice(investmentFund);

        assertTrue("Generated value should be in same currency",
                new_price.getCurrency().equals(min.getCurrency()));
    }

    @Test
    public void should_be_from_range() {
        PriceInterface min = new Price(-1.0, Currency.getInstance("EUR"));
        PriceInterface max = new Price(2.0, Currency.getInstance("EUR"));
        InvestmentFundPriceGeneratorInterface generator = new RangeButNotUnderZeroInvestmentFundPriceGenerator(min, max);

        PriceInterface investmentFundPrice = new Price(100.0, Currency.getInstance("EUR"));
        InvestmentFundInterface investmentFund = new InvestmentFund("Fund1", investmentFundPrice);

        PriceInterface new_price = generator.getFundPrice(investmentFund);

        assertTrue(String.format("Generated value should be less or equal than min value. %f returned.", new_price.getAmount()),
                99.0 <= new_price.getAmount());
        assertTrue(String.format("Generated value should be greater or equal than min value. %f returned.", new_price.getAmount()),
                102.0 >= new_price.getAmount());
    }

    @Test
    public void should_not_be_under_zero() {
        // force invest under 0
        PriceInterface min = new Price(-200.0, Currency.getInstance("EUR"));
        PriceInterface max = new Price(-100.0, Currency.getInstance("EUR"));
        InvestmentFundPriceGeneratorInterface generator = new RangeButNotUnderZeroInvestmentFundPriceGenerator(min, max);

        PriceInterface investmentFundPrice = new Price(10.0, Currency.getInstance("EUR"));
        InvestmentFundInterface investmentFund = new InvestmentFund("Fund1", investmentFundPrice);

        PriceInterface new_price = generator.getFundPrice(investmentFund);

        assertTrue("Generated value should not be under 0",
                0 >= new_price.getAmount());
    }

}
