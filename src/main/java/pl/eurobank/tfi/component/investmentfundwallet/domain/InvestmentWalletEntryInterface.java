package pl.eurobank.tfi.component.investmentfundwallet.domain;

import pl.eurobank.tfi.component.investmentfund.domain.InvestmentFundUnitTypeInterface;

public interface InvestmentWalletEntryInterface {

    InvestmentFundUnitTypeInterface getInvestmentFundUnitType();

    Long getQuantity();

    void setQuantity(Long quantity);

}
