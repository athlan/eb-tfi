package pl.eurobank.tfi.component.money.domain;

import org.junit.Test;
import pl.eurobank.tfi.component.money.domain.Price;
import pl.eurobank.tfi.component.money.domain.PriceInterface;

import java.util.Currency;

import static org.junit.Assert.*;

public class PriceTest {

    @Test
    public void should_be_equals() {
        PriceInterface price1 = new Price(123, Currency.getInstance("PLN"));
        PriceInterface price2 = new Price(123, Currency.getInstance("PLN"));

        assertTrue(price1.equals(price2));
        assertTrue(price2.equals(price1));
    }

    @Test
    public void should_not_be_equals_when_different_currencies() {
        PriceInterface price1 = new Price(123, Currency.getInstance("PLN"));
        PriceInterface price2 = new Price(123, Currency.getInstance("EUR"));

        assertFalse(price1.equals(price2));
        assertFalse(price2.equals(price1));
    }

    @Test
    public void should_not_be_equals_when_different_amounts() {
        PriceInterface price1 = new Price(123, Currency.getInstance("PLN"));
        PriceInterface price2 = new Price(456, Currency.getInstance("PLN"));

        assertFalse(price1.equals(price2));
        assertFalse(price2.equals(price1));
    }
}
