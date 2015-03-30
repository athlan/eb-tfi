package pl.eurobank.tfi.component.investmentfundwallet.domain;

import pl.eurobank.tfi.component.investmentfund.domain.InvestmentFundUnitTypeInterface;
import pl.eurobank.tfi.component.money.domain.PriceInterface;

import java.util.Date;

public interface InvestmentTransactionInterface {

    InvestmentWalletInterface getWallet();

    InvestmentFundUnitTypeInterface getInvestmentFundUnitType();

    Long getQuantity();

    PriceInterface getPrice();

    PriceInterface getPriceValue();

    void setPrice(PriceInterface price);

    void setPriceValue(PriceInterface priceValue);

    Date getTransactionDate();
}
