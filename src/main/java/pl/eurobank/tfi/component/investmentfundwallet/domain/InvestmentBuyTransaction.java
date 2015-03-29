package pl.eurobank.tfi.component.investmentfundwallet.domain;

import pl.eurobank.tfi.component.investmentfund.domain.InvestmentFundUnitTypeInterface;
import pl.eurobank.tfi.component.money.domain.Price;

import java.util.Date;

public class InvestmentBuyTransaction extends AbstractInvestmentTransaction {

    public InvestmentBuyTransaction(InvestmentWalletInterface wallet, InvestmentFundUnitTypeInterface investmentFundUnitType, Long quantity, Date transactionDate) {
        super(wallet, investmentFundUnitType, quantity, transactionDate);

        // inverse price
        this.price = new Price(super.getPrice().getAmount() * -1, super.getPrice().getCurrency());
    }
}
