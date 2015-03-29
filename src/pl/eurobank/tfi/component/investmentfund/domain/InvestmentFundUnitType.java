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
}
