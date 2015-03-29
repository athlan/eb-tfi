package pl.eurobank.tfi.component.investmentfundwallet.domain;

import pl.eurobank.tfi.component.investmentfund.domain.InvestmentFundUnitTypeInterface;

import java.util.Date;

public class InvestmentBuyTransaction extends AbstractInvestmentTransaction {

    public InvestmentBuyTransaction(InvestmentWalletInterface wallet, InvestmentFundUnitTypeInterface investmentFundUnitType, Long quantity, Date transactionDate) {
        super(wallet, investmentFundUnitType, quantity, transactionDate);
    }
}
