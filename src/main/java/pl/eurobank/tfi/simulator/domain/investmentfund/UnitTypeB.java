package pl.eurobank.tfi.simulator.domain.investmentfund;

import pl.eurobank.tfi.component.investmentfund.domain.InvestmentFundInterface;
import pl.eurobank.tfi.component.investmentfund.domain.InvestmentFundUnitType;

public class UnitTypeB extends InvestmentFundUnitType {

    public UnitTypeB(InvestmentFundInterface investmentFund) {
        super(investmentFund, "Unit type B");
    }
}
