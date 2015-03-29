package pl.eurobank.tfi.simulator.domain.investmentfund;

import pl.eurobank.tfi.component.investmentfund.domain.InvestmentFundInterface;
import pl.eurobank.tfi.component.investmentfund.domain.InvestmentFundUnitType;

public class UnitTypeA extends InvestmentFundUnitType {

    public UnitTypeA(InvestmentFundInterface investmentFund) {
        super(investmentFund, "Unit type A");
    }
}
