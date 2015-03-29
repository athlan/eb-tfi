package pl.eurobank.tfi.simulator.repository;

import org.junit.Test;
import pl.eurobank.tfi.component.investmentfund.domain.InvestmentFund;
import pl.eurobank.tfi.component.investmentfund.domain.InvestmentFundUnitType;
import pl.eurobank.tfi.component.investmentfund.domain.InvestmentFundUnitTypeInterface;
import pl.eurobank.tfi.component.investmentfundwallet.domain.*;
import pl.eurobank.tfi.component.investmentfundwallet.repository.InvestmentWalletTransactionRepositoryInterface;
import pl.eurobank.tfi.component.money.domain.Price;
import pl.eurobank.tfi.component.money.domain.PriceInterface;

import java.util.Arrays;
import java.util.Currency;
import java.util.Date;

import static org.junit.Assert.*;

public class InvestmentWalletTransactionInMemoryRepositoryTest {

    @Test
    public void should_be_possible_make_transaction() {
        PriceInterface investmentFundPrice = new Price(100, Currency.getInstance("EUR"));
        InvestmentFund investmentFund = new InvestmentFund("Fund1", investmentFundPrice);
        InvestmentFundUnitTypeInterface unitType = new InvestmentFundUnitType(investmentFund, "Unit1");
        investmentFund.addUnitType(unitType);

        InvestmentWalletTransactionRepositoryInterface repository = new InvestmentWalletTransactionInMemoryRepository();
        PriceInterface walletAmount = new Price(1000, Currency.getInstance("EUR"));
        InvestmentWalletInterface wallet = new InvestmentWallet(walletAmount);

        InvestmentTransactionInterface transaction = new InvestmentBuyTransaction(wallet, unitType, 1L, new Date());

        repository.createTransaction(transaction);

    }

    @Test(expected = IllegalStateException.class)
    public void should_be_impossible_make_too_expensive_transaction() {
        PriceInterface investmentFundPrice = new Price(2000, Currency.getInstance("EUR"));
        InvestmentFund investmentFund = new InvestmentFund("Fund1", investmentFundPrice);
        InvestmentFundUnitTypeInterface unitType = new InvestmentFundUnitType(investmentFund, "Unit1");
        investmentFund.addUnitType(unitType);

        InvestmentWalletTransactionRepositoryInterface repository = new InvestmentWalletTransactionInMemoryRepository();
        PriceInterface walletAmount = new Price(1000, Currency.getInstance("EUR"));
        InvestmentWalletInterface wallet = new InvestmentWallet(walletAmount);

        InvestmentTransactionInterface transaction = new InvestmentBuyTransaction(wallet, unitType, 1L, new Date());

        repository.createTransaction(transaction);
    }

    @Test(expected = IllegalStateException.class)
    public void should_not_be_possible_selling_units_not_in_wallet() {
        PriceInterface investmentFundPrice = new Price(100, Currency.getInstance("EUR"));
        InvestmentFund investmentFund = new InvestmentFund("Fund1", investmentFundPrice);
        InvestmentFundUnitTypeInterface unitType = new InvestmentFundUnitType(investmentFund, "Unit1");
        investmentFund.addUnitType(unitType);
        InvestmentFundUnitTypeInterface unitType2 = new InvestmentFundUnitType(investmentFund, "Unit2");
        investmentFund.addUnitType(unitType2);

        InvestmentWalletTransactionRepositoryInterface repository = new InvestmentWalletTransactionInMemoryRepository();
        PriceInterface walletAmount = new Price(10000, Currency.getInstance("EUR"));
        InvestmentWalletInterface wallet = new InvestmentWallet(walletAmount);

        InvestmentTransactionInterface transaction = new InvestmentBuyTransaction(wallet, unitType, 2L, new Date());
        repository.createTransaction(transaction);

        InvestmentTransactionInterface transaction2 = new InvestmentSellTransaction(wallet, unitType2, 1L, new Date());
        repository.createTransaction(transaction2);
    }

    @Test
    public void wallet_amount_should_be_decreased_after_transaction() {
        PriceInterface investmentFundPrice = new Price(100, Currency.getInstance("EUR"));
        InvestmentFund investmentFund = new InvestmentFund("Fund1", investmentFundPrice);
        InvestmentFundUnitTypeInterface unitType = new InvestmentFundUnitType(investmentFund, "Unit1");
        investmentFund.addUnitType(unitType);

        InvestmentWalletTransactionRepositoryInterface repository = new InvestmentWalletTransactionInMemoryRepository();
        PriceInterface walletAmount = new Price(1000, Currency.getInstance("EUR"));
        InvestmentWalletInterface wallet = new InvestmentWallet(walletAmount);

        InvestmentTransactionInterface transaction = new InvestmentBuyTransaction(wallet, unitType, 1L, new Date());

        repository.createTransaction(transaction);

        assertEquals("Wallet amount should be decreased.",
                900, wallet.getAmount().getAmount(), 0.0);

        assertEquals("Transaction should be in repository",
                1, repository.findTransactionsByWallet(wallet, null).length);
        assertTrue("Transaction should be in repository",
                Arrays.asList(repository.findTransactionsByWallet(wallet, null)).contains(transaction));
    }

    @Test
    public void wallet_amount_summary_should_after_all_transactions() {
        PriceInterface investmentFundPrice = new Price(100, Currency.getInstance("EUR"));
        InvestmentFund investmentFund = new InvestmentFund("Fund1", investmentFundPrice);
        InvestmentFundUnitTypeInterface unitType = new InvestmentFundUnitType(investmentFund, "Unit1");
        investmentFund.addUnitType(unitType);

        InvestmentWalletTransactionRepositoryInterface repository = new InvestmentWalletTransactionInMemoryRepository();
        PriceInterface walletAmount = new Price(1000, Currency.getInstance("EUR"));
        InvestmentWalletInterface wallet = new InvestmentWallet(walletAmount);

        repository.createTransaction(new InvestmentBuyTransaction(wallet, unitType, 2L, new Date()));
        repository.createTransaction(new InvestmentBuyTransaction(wallet, unitType, 2L, new Date()));
        repository.createTransaction(new InvestmentSellTransaction(wallet, unitType, 2L, new Date()));
        repository.createTransaction(new InvestmentBuyTransaction(wallet, unitType, 2L, new Date()));

        assertEquals("Wallet amount should be decreased.",
                600, wallet.getAmount().getAmount(), 0.0);

        PriceInterface transactionsSummary = repository.getTransactionsValueByWallet(wallet, null);
        assertEquals("Wallet amount should be decreased.",
                -400, transactionsSummary.getAmount(), 0.0);
    }
}
