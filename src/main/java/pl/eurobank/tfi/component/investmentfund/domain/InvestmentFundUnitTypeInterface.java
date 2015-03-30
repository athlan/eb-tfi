package pl.eurobank.tfi.component.investmentfund.domain;

/**
 * Investment fund unit.
 *
 * Investment funds units can be buyed by user and placed into
 * his investment wallet to further sell.
 *
 */
public interface InvestmentFundUnitTypeInterface {

    /**
     * Get investment fund that unit belongs to.
     * @return investment fund that unit belongs to
     */
    InvestmentFundInterface getInvestmentFund();

    /**
     * Get unit name.
     *
     * @return unit name
     */
    String getName();
}
