package pl.eurobank.tfi.component.investmentfundwallet.domain;

import org.junit.Test;
import pl.eurobank.tfi.component.investmentfund.domain.InvestmentFund;
import pl.eurobank.tfi.component.investmentfund.domain.InvestmentFundUnitType;
import pl.eurobank.tfi.component.investmentfund.domain.InvestmentFundUnitTypeInterface;
import pl.eurobank.tfi.component.money.domain.Price;
import pl.eurobank.tfi.component.money.domain.PriceInterface;

import java.util.Currency;
import java.util.Date;

import static org.junit.Assert.*;

public class InvestmentBuyTransactionTest {

    @Test
    public void price_should_be_correct() {
        PriceInterface investmentFundPrice = new Price(100, Currency.getInstance("EUR"));
        InvestmentFund investmentFund = new InvestmentFund("Fund1", investmentFundPrice);
        InvestmentFundUnitTypeInterface unitType = new InvestmentFundUnitType(investmentFund, "Unit1");
        investmentFund.addUnitType(unitType);

        PriceInterface walletAmount = new Price(10000, Currency.getInstance("EUR"));
        InvestmentWalletInterface wallet = new InvestmentWallet(walletAmount);

        InvestmentTransactionInterface transaction = new InvestmentBuyTransaction(wallet, unitType, 3L, new Date());

        assertTrue("Price should be negative for buying transaction.",
                transaction.getPrice().getAmount() <= 0);

        assertEquals("Price should be equals.",
                -300.0, transaction.getPrice().getAmount(), 0.0);
        assertEquals("Currency should be equals.",
                "EUR", transaction.getPrice().getCurrency().getCurrencyCode());
    }
}
