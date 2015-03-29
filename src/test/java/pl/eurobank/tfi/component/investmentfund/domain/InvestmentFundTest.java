package pl.eurobank.tfi.component.investmentfund.domain;

import org.junit.Test;
import pl.eurobank.tfi.component.money.domain.Price;
import pl.eurobank.tfi.component.money.domain.PriceInterface;

import java.util.Arrays;
import java.util.Currency;

import static org.junit.Assert.*;

public class InvestmentFundTest {

    @Test
    public void unit_type_should_be_added() {
        PriceInterface investmentFundPrice = new Price(100, Currency.getInstance("EUR"));
        InvestmentFund investmentFund = new InvestmentFund("Fund1", investmentFundPrice);

        InvestmentFundUnitTypeInterface unitType1 = new InvestmentFundUnitType(investmentFund, "Unit1");
        InvestmentFundUnitTypeInterface unitType2 = new InvestmentFundUnitType(investmentFund, "Unit2");

        assertEquals("Number of unit types should be not changed before add.",
                0, investmentFund.getUnitTypes().length);

        investmentFund.addUnitType(unitType1);
        assertEquals("Number of unit types should increase after add.",
                1, investmentFund.getUnitTypes().length);

        investmentFund.addUnitType(unitType2);
        assertEquals("Number of unit types should increase after add.",
                2, investmentFund.getUnitTypes().length);

        assertTrue(
                "Unit type should be on list after add.",
                Arrays.asList(investmentFund.getUnitTypes()).contains(unitType1));
        assertTrue(
                "Unit type should be on list after add.",
                Arrays.asList(investmentFund.getUnitTypes()).contains(unitType2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void unit_type_should_not_be_added_to_another_fund() {
        PriceInterface investmentFundPrice = new Price(100, Currency.getInstance("EUR"));
        InvestmentFund investmentFund = new InvestmentFund("Fund1", investmentFundPrice);
        InvestmentFund investmentFund2 = new InvestmentFund("Fund2", investmentFundPrice);

        InvestmentFundUnitTypeInterface unitType = new InvestmentFundUnitType(investmentFund2, "Unit1");

        assertEquals("Number of unit types should be not changed before add.",
                0, investmentFund.getUnitTypes().length);

        investmentFund.addUnitType(unitType);
    }

    @Test
    public void unit_type_should_be_removed() {
        PriceInterface investmentFundPrice = new Price(100, Currency.getInstance("EUR"));
        InvestmentFund investmentFund = new InvestmentFund("Fund1", investmentFundPrice);

        InvestmentFundUnitTypeInterface unitType1 = new InvestmentFundUnitType(investmentFund, "Unit1");
        investmentFund.addUnitType(unitType1);

        InvestmentFundUnitTypeInterface unitType2 = new InvestmentFundUnitType(investmentFund, "Unit1");
        investmentFund.addUnitType(unitType2);

        investmentFund.removeUnitType(unitType1);

        assertEquals("Number of unit types should decrease after remove.",
                1, investmentFund.getUnitTypes().length);

        assertFalse(
                "Unit type should not exists after removal of another.",
                Arrays.asList(investmentFund.getUnitTypes()).contains(unitType1));

        assertTrue(
                "Unit type should remain after removal of another.",
                Arrays.asList(investmentFund.getUnitTypes()).contains(unitType2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void unit_type_should_be_removed_from_another_fund() {
        PriceInterface investmentFundPrice = new Price(100, Currency.getInstance("EUR"));
        InvestmentFund investmentFund = new InvestmentFund("Fund1", investmentFundPrice);
        InvestmentFund investmentFund2 = new InvestmentFund("Fund2", investmentFundPrice);

        InvestmentFundUnitTypeInterface unitType = new InvestmentFundUnitType(investmentFund2, "Unit1");

        investmentFund.removeUnitType(unitType);
    }

}
