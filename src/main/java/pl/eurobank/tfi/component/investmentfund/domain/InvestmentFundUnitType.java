package pl.eurobank.tfi.component.investmentfund.domain;

public class InvestmentFundUnitType implements InvestmentFundUnitTypeInterface {

    InvestmentFundInterface investmentFund;

    String name;

    public InvestmentFundUnitType(InvestmentFundInterface investmentFund, String name) {
        this.investmentFund = investmentFund;
        this.name = name;
    }

    @Override
    public InvestmentFundInterface getInvestmentFund() {
        return investmentFund;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object another) {
        if(another == this) {
            return true;
        }

        if(!(another instanceof InvestmentFundUnitTypeInterface)) {
            return false;
        }

        InvestmentFundUnitTypeInterface another2 = (InvestmentFundUnitTypeInterface) another;

        return another2.getName().equals(this.getName())
                && another2.getInvestmentFund().equals(this.getInvestmentFund());
    }
}
