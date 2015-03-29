package pl.eurobank.tfi.component.investmentfundwallet.domain;

import pl.eurobank.tfi.component.investmentfund.domain.InvestmentFundUnitTypeInterface;
import pl.eurobank.tfi.component.money.domain.PriceInterface;

public interface InvestmentWalletInterface {

    PriceInterface getAmount();

    void setAmount(PriceInterface amount);

    InvestmentWalletEntryInterface[] getEntries();

    InvestmentWalletEntryInterface getEntryForInvestmentFundUnitType(InvestmentFundUnitTypeInterface unitType);

    void addEntry(InvestmentWalletEntryInterface entry);

    void removeEntry(InvestmentWalletEntryInterface entry);
}
