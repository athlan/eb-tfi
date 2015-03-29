package pl.eurobank.tfi.component.investmentfund.domain;

import pl.eurobank.tfi.component.money.domain.PriceInterface;

import java.util.ArrayList;
import java.util.List;

public class InvestmentFund implements InvestmentFundInterface {

    private String name;

    private PriceInterface currentPricing;

    private List<InvestmentFundUnitTypeInterface> unitTypes;

    public InvestmentFund(final String name, final PriceInterface currentPricing) {
        this.name = name;
        this.currentPricing = currentPricing;
        this.unitTypes = new ArrayList<>();
    }

    @Override
    public String getName() {
        return null;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public PriceInterface getCurrentPricing() {
        return null;
    }

    public void setCurrentPricing(PriceInterface currentPricing) {
        this.currentPricing = currentPricing;
    }

    @Override
    public InvestmentFundUnitTypeInterface[] getUnitTypes() {
        return unitTypes.toArray(new InvestmentFundUnitTypeInterface[unitTypes.size()]);
    }

    public void addUnitType(InvestmentFundUnitTypeInterface unitType) {
        if(unitType.getInvestmentFund() != this) {
            throw new IllegalArgumentException("Investment fund of passed unit type is not equal to investment fund which is added to.");
        }

        unitTypes.add(unitType);
    }

    public void removeUnitType(InvestmentFundUnitTypeInterface unitType) {
        if(unitType.getInvestmentFund() != this) {
            throw new IllegalArgumentException("Investment fund of passed unit type is not equal to investment fund which is removed from.");
        }

        unitTypes.remove(unitType);
    }
}
