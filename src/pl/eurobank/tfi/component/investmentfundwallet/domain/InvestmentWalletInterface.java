package pl.eurobank.tfi.component.investmentfundwallet.domain;

import pl.eurobank.tfi.component.money.domain.PriceInterface;

public interface InvestmentWalletInterface {

    PriceInterface getAmount();

    void setAmount(PriceInterface amount);
}
