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

    double getAmount();

    Currency getCurrency();

    PriceInterface add(PriceInterface price);

    PriceInterface sub(PriceInterface price);

    PriceInterface multiply(double factor);
}
