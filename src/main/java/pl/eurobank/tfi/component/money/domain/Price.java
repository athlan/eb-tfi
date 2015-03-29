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
        return this.currency;
    }

    @Override
    public boolean equals(Object another) {
        if(!(another instanceof PriceInterface)) {
            return false;
        }

        PriceInterface another2 = (PriceInterface) another;

        return Double.compare(this.amount, another2.getAmount()) == 0
                && this.currency.equals(another2.getCurrency());
    }

    @Override
    public PriceInterface add(PriceInterface price) {
        if(!this.currency.equals(price.getCurrency())) {
            throw new IllegalArgumentException("Different currencies");
        }

        return new Price(this.getAmount() + price.getAmount(), this.getCurrency());
    }

    @Override
    public PriceInterface sub(PriceInterface price) {
        if(!this.currency.equals(price.getCurrency())) {
            throw new IllegalArgumentException("Different currencies");
        }

        return new Price(this.getAmount() - price.getAmount(), this.getCurrency());
    }

    @Override
    public PriceInterface multiply(double factor) {
        return new Price(this.getAmount() * factor, this.getCurrency());
    }
}
