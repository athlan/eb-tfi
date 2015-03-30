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

    /**
     * Creates immutable object.
     *
     * @param amount amount of money
     * @param currency currency
     */
    public Price(final double amount, final Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    /**
     * Get amount of money.
     *
     * @return amount of money.
     */
    @Override
    public double getAmount() {
        return this.amount;
    }

    /**
     * Get currency of money.
     *
     * @return currency of money
     */
    @Override
    public Currency getCurrency() {
        return this.currency;
    }

    /**
     * String representation of price object.
     *
     * @return String representation of price object.
     */
    @Override
    public String toString() {
        return amount + " " + currency.getCurrencyCode();
    }

    /**
     * Compares two price objects. Two prices is equal only if there are both same currency
     * and have same amount of money.
     *
     * @param another
     * @return
     */
    @Override
    public boolean equals(Object another) {
        if(another == this) {
            return true;
        }

        if(!(another instanceof PriceInterface)) {
            return false;
        }

        PriceInterface another2 = (PriceInterface) another;

        return Double.compare(this.amount, another2.getAmount()) == 0
                && this.currency.equals(another2.getCurrency());
    }

    /**
     * Creates new immutable object of the same currency with
     * money amount greater with price is adding.
     *
     * @param price price to add
     * @throws IllegalArgumentException if prices are from different currencies
     * @return
     */
    @Override
    public PriceInterface add(PriceInterface price) {
        if(!this.currency.equals(price.getCurrency())) {
            throw new IllegalArgumentException("Different currencies");
        }

        return new Price(this.getAmount() + price.getAmount(), this.getCurrency());
    }

    /**
     * Creates new immutable object of the same currency with
     * money amount lower with price is substracting.
     *
     * @param price price to substract
     * @throws IllegalArgumentException if prices are from different currencies
     * @return
     */
    @Override
    public PriceInterface sub(PriceInterface price) {
        if(!this.currency.equals(price.getCurrency())) {
            throw new IllegalArgumentException("Different currencies");
        }

        return new Price(this.getAmount() - price.getAmount(), this.getCurrency());
    }

    /**
     * Creates new immutable object of the same currency with
     * money amount multiplied by given factor.
     *
     * @param factor factor to multiply
     * @return
     */
    @Override
    public PriceInterface multiply(double factor) {
        return new Price(this.getAmount() * factor, this.getCurrency());
    }
}
