package pl.eurobank.tfi.component.investmentfund.domain;

/**
 * Investment fund unit.
 *
 * Investment funds units can be buyed by user and placed into
 * his investment wallet to further sell.
 *
 */
public class InvestmentFundUnitType implements InvestmentFundUnitTypeInterface {

    private InvestmentFundInterface investmentFund;

    private String name;

    /**
     * Creates investment fund unit
     *
     * @param investmentFund investment fund that unit belongs to
     * @param name unit name
     */
    public InvestmentFundUnitType(InvestmentFundInterface investmentFund, String name) {
        this.investmentFund = investmentFund;
        this.name = name;
    }

    /**
     * Get investment fund that unit belongs to.
     * @return investment fund that unit belongs to
     */
    @Override
    public InvestmentFundInterface getInvestmentFund() {
        return investmentFund;
    }

    /**
     * Get unit name.
     *
     * @return unit name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Compares investment fund unit by identifier.
     *
     * Comparing by object reference or name is for default.
     *
     * @param another the object compare to
     * @return if funds units are equal
     */
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
