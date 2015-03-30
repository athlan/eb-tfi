package pl.eurobank.tfi.simulator.listener;

import com.google.common.eventbus.EventBus;
import org.junit.Before;
import org.junit.Test;
import pl.eurobank.tfi.component.investmentfund.domain.InvestmentFund;
import pl.eurobank.tfi.component.investmentfund.domain.InvestmentFundUnitType;
import pl.eurobank.tfi.component.investmentfund.domain.InvestmentFundUnitTypeInterface;
import pl.eurobank.tfi.component.investmentfundwallet.domain.*;
import pl.eurobank.tfi.component.investmentfundwallet.repository.InvestmentWalletTransactionRepositoryInterface;
import pl.eurobank.tfi.component.money.domain.Price;
import pl.eurobank.tfi.component.money.domain.PriceInterface;
import pl.eurobank.tfi.simulator.domain.investmentfund.UnitTypeB;
import pl.eurobank.tfi.simulator.repository.InvestmentWalletTransactionInMemoryRepository;

import java.util.Currency;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class InvestmentTransactionCommissionUnitTypeBEventListenerTest {

    private EventBus eventBus;

    @Before
    public void setup() {
        eventBus = new EventBus();

        eventBus.register(new InvestmentTransactionCommissionUnitTypeBEventListener());
    }

    @Test
    public void should_be_commission_on_sell_valid_type() {
        PriceInterface investmentFundPrice = new Price(100, Currency.getInstance("EUR"));
        InvestmentFund investmentFund = new InvestmentFund("Fund1", investmentFundPrice);
        InvestmentFundUnitTypeInterface unitType = new UnitTypeB(investmentFund);
        investmentFund.addUnitType(unitType);

        InvestmentWalletTransactionRepositoryInterface repository = new InvestmentWalletTransactionInMemoryRepository(eventBus);
        PriceInterface walletAmount = new Price(1000, Currency.getInstance("EUR"));
        InvestmentWalletInterface wallet = new InvestmentWallet(walletAmount);

        InvestmentTransactionInterface transaction = new InvestmentBuyTransaction(wallet, unitType, 2L, new Date());
        repository.createTransaction(transaction);

        InvestmentTransactionInterface transaction2 = new InvestmentSellTransaction(wallet, unitType, 2L, new Date());
        repository.createTransaction(transaction2);

        assertEquals("Transaction should be charged by commission",
                196, transaction2.getPrice().getAmount(), 0.0);

        assertEquals("Wallet should be charged by commission",
                1000 - 200 + 196, wallet.getAmount().getAmount(), 0.0);
    }

    @Test
    public void should_not_be_commission_on_sell_invalid_type() {
        PriceInterface investmentFundPrice = new Price(100, Currency.getInstance("EUR"));
        InvestmentFund investmentFund = new InvestmentFund("Fund1", investmentFundPrice);
        InvestmentFundUnitTypeInterface unitType = new InvestmentFundUnitType(investmentFund, "Unit1");
        investmentFund.addUnitType(unitType);

        InvestmentWalletTransactionRepositoryInterface repository = new InvestmentWalletTransactionInMemoryRepository(eventBus);
        PriceInterface walletAmount = new Price(1000, Currency.getInstance("EUR"));
        InvestmentWalletInterface wallet = new InvestmentWallet(walletAmount);

        InvestmentTransactionInterface transaction = new InvestmentBuyTransaction(wallet, unitType, 2L, new Date());
        repository.createTransaction(transaction);

        InvestmentTransactionInterface transaction2 = new InvestmentSellTransaction(wallet, unitType, 2L, new Date());
        repository.createTransaction(transaction2);

        assertEquals("Transaction should not be charged by commission",
                200, transaction2.getPrice().getAmount(), 0.0);

        assertEquals("Wallet should not be charged by commission",
                1000 - 200 + 200, wallet.getAmount().getAmount(), 0.0);
    }

    @Test
    public void should_not_be_commission_on_buy_valid_type() {
        PriceInterface investmentFundPrice = new Price(100, Currency.getInstance("EUR"));
        InvestmentFund investmentFund = new InvestmentFund("Fund1", investmentFundPrice);
        InvestmentFundUnitTypeInterface unitType = new UnitTypeB(investmentFund);
        investmentFund.addUnitType(unitType);

        InvestmentWalletTransactionRepositoryInterface repository = new InvestmentWalletTransactionInMemoryRepository(eventBus);
        PriceInterface walletAmount = new Price(1000, Currency.getInstance("EUR"));
        InvestmentWalletInterface wallet = new InvestmentWallet(walletAmount);

        InvestmentTransactionInterface transaction = new InvestmentBuyTransaction(wallet, unitType, 2L, new Date());
        repository.createTransaction(transaction);

        assertEquals("Transaction not should be charged by commission",
                -200, transaction.getPrice().getAmount(), 0.0);

        assertEquals("Wallet should not be charged by commission",
                1000 - 200, wallet.getAmount().getAmount(), 0.0);
    }
}
