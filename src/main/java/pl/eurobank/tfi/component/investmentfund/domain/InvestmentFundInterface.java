package pl.eurobank.tfi.component.investmentfund.domain;

import pl.eurobank.tfi.component.money.domain.PriceInterface;

public interface InvestmentFundInterface {

    String getName();

    PriceInterface getCurrentPricing();

    void setCurrentPricing(PriceInterface currentPricing);

    InvestmentFundUnitTypeInterface[] getUnitTypes();

    void addUnitType(InvestmentFundUnitTypeInterface unitType);

    void removeUnitType(InvestmentFundUnitTypeInterface unitType);
}
