package pl.eurobank.tfi.component.investmentfundwallet.domain;

import pl.eurobank.tfi.component.investmentfund.domain.InvestmentFundUnitTypeInterface;
import pl.eurobank.tfi.component.money.domain.PriceInterface;

import java.util.Date;

public interface InvestmentTransactionInterface {

    InvestmentWalletInterface getWallet();

    InvestmentFundUnitTypeInterface getInvestmentFundUnit();

    Long getQuantity();

    PriceInterface getPrice();

    Date getTransactionDate();
}
