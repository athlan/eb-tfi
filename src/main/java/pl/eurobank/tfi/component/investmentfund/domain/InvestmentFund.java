package pl.eurobank.tfi.component.investmentfund.domain;

import pl.eurobank.tfi.component.money.domain.PriceInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Investment fund.
 *
 * Investment fund is named and has a current pricing value. Current pricing value denotes
 * price that user have to pay for buy one unit of investment fund.
 *
 * Investment funds has units which can be buyed by user and placed into
 * his investment wallet to further sell.
 *
 */
public class InvestmentFund implements InvestmentFundInterface {

    private String name;

    private PriceInterface currentPricing;

    private List<InvestmentFundUnitTypeInterface> unitTypes;

    /**
     * Creates investment fund.
     *
     * @param name name of fund
     * @param currentPricing current pricing
     */
    public InvestmentFund(final String name, final PriceInterface currentPricing) {
        this.name = name;
        this.currentPricing = currentPricing;
        this.unitTypes = new ArrayList<>();
    }

    /**
     * Get name of fund.
     *
     * @return name of fund.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Set name of fund.
     *
     * @param name name of fund.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get current pricing for fund. Current pricing value denotes
     * price that user have to pay for buy one unit of investment fund.
     *
     * @return current pricing for fund
     */
    @Override
    public PriceInterface getCurrentPricing() {
        return currentPricing;
    }

    /**
     * Set current pricing for fund.
     * @param currentPricing current pricing for fund
     */
    @Override
    public void setCurrentPricing(PriceInterface currentPricing) {
        this.currentPricing = currentPricing;
    }

    /**
     * Get units of fund. Units can be buyed by user and placed into
     * his investment wallet to further sell.
     *
     * @return units of fund
     */
    @Override
    public InvestmentFundUnitTypeInterface[] getUnitTypes() {
        return unitTypes.toArray(new InvestmentFundUnitTypeInterface[unitTypes.size()]);
    }

    /**
     * Add unit of fund.
     *
     * @param unitType unit type
     */
    @Override
    public void addUnitType(InvestmentFundUnitTypeInterface unitType) {
        if(unitType.getInvestmentFund() != this) {
            throw new IllegalArgumentException("Investment fund of passed unit type is not equal to investment fund which is added to.");
        }

        unitTypes.add(unitType);
    }

    /**
     * Remove unit of fund.
     *
     * @param unitType unit type
     */
    @Override
    public void removeUnitType(InvestmentFundUnitTypeInterface unitType) {
        if(unitType.getInvestmentFund() != this) {
            throw new IllegalArgumentException("Investment fund of passed unit type is not equal to investment fund which is removed from.");
        }

        unitTypes.remove(unitType);
    }

    /**
     * Compares investment fund by identifier.
     *
     * Comparing by object reference or name is for default.
     *
     * @param another the object compare to
     * @return if funds are equal
     */
    @Override
    public boolean equals(Object another) {
        if(another == this) {
            return true;
        }

        if(null == another && !(another instanceof InvestmentFundInterface)) {
            return false;
        }

        InvestmentFundInterface another2 = (InvestmentFundInterface) another;

        return another2.getName().equals(this.getName());
    }
}
