package pl.eurobank.tfi.component.investmentfundwallet.domain;

import pl.eurobank.tfi.component.investmentfund.domain.InvestmentFundUnitTypeInterface;
import pl.eurobank.tfi.component.money.domain.Price;
import pl.eurobank.tfi.component.money.domain.PriceInterface;

import java.util.Date;

public class AbstractInvestmentTransaction implements InvestmentTransactionInterface {

    InvestmentWalletInterface wallet;

    InvestmentFundUnitTypeInterface investmentFundUnitType;

    Long quantity;

    PriceInterface price;

    PriceInterface priceValue;

    Date transactionDate;

    public AbstractInvestmentTransaction(InvestmentWalletInterface wallet, InvestmentFundUnitTypeInterface investmentFundUnitType, Long quantity, Date transactionDate) {
        this.wallet = wallet;
        this.investmentFundUnitType = investmentFundUnitType;
        this.quantity = quantity;
        this.transactionDate = transactionDate;

        PriceInterface currentPricing = investmentFundUnitType.getInvestmentFund().getCurrentPricing();

        if(!currentPricing.getCurrency().equals(wallet.getAmount().getCurrency())) {
            throw new IllegalArgumentException("You cannot make transactions in different currency than is wallet.");
        }

        this.price = new Price(quantity * currentPricing.getAmount(), currentPricing.getCurrency());
        this.priceValue = new Price(quantity * currentPricing.getAmount(), currentPricing.getCurrency());
    }

    @Override
    public InvestmentWalletInterface getWallet() {
        return wallet;
    }

    @Override
    public InvestmentFundUnitTypeInterface getInvestmentFundUnitType() {
        return investmentFundUnitType;
    }

    @Override
    public Long getQuantity() {
        return quantity;
    }

    @Override
    public Date getTransactionDate() {
        return transactionDate;
    }

    @Override
    public PriceInterface getPrice() {
        return price;
    }

    @Override
    public PriceInterface getPriceValue() {
        return priceValue;
    }
}
