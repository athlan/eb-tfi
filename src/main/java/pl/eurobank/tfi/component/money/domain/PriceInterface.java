package pl.eurobank.tfi.component.money.domain;

import java.util.Currency;

/**
 * This interface represents describes objects of price. Price have amount and currency.
 *
 * Price objects should be immutable.
 *
 * @author Piotr Pelczar
 */
public interface PriceInterface {

    /**
     * Get amount of money.
     *
     * @return amount of money.
     */
    double getAmount();

    /**
     * Get currency of money.
     *
     * @return currency of money
     */
    Currency getCurrency();

    /**
     * Creates new immutable object of the same currency with
     * money amount greater with price is adding.
     *
     * @param price price to add
     * @throws IllegalArgumentException if prices are from different currencies
     * @return
     */
    PriceInterface add(PriceInterface price);

    /**
     * Creates new immutable object of the same currency with
     * money amount lower with price is substracting.
     *
     * @param price price to substract
     * @throws IllegalArgumentException if prices are from different currencies
     * @return
     */
    PriceInterface sub(PriceInterface price);

    /**
     * Creates new immutable object of the same currency with
     * money amount multiplied by given factor.
     *
     * @param factor factor to multiply
     * @return
     */
    PriceInterface multiply(double factor);
}
