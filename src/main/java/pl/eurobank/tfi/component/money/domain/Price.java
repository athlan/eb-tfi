package pl.eurobank.tfi.component.money.domain;

import java.util.Currency;

/**
 * This class represents immutable objects of price. Price have amount and currency.
 *
 * @author Piotr Pelczar
 */
public class Price implements PriceInterface {

    private final double amount;

    private final Currency currency;

    public Price(final double amount, final Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    @Override
    public double getAmount() {
        return this.amount;
    }

    @Override
    public Currency getCurrency() {
        return this.getCurrency();
    }
}
