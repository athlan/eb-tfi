package pl.eurobank.tfi.component.investmentfund.domain;

import pl.eurobank.tfi.component.money.domain.PriceInterface;

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
public interface InvestmentFundInterface {

    /**
     * Get name of fund.
     *
     * @return name of fund.
     */
    String getName();

    /**
     * Get current pricing for fund. Current pricing value denotes
     * price that user have to pay for buy one unit of investment fund.
     *
     * @return current pricing for fund
     */
    PriceInterface getCurrentPricing();

    /**
     * Set current pricing for fund. Current pricing value denotes
     * price that user have to pay for buy one unit of investment fund.
     *
     * @param currentPricing current pricing for fund
     */
    void setCurrentPricing(PriceInterface currentPricing);

    /**
     * Get units of fund. Units can be buyed by user and placed into
     * his investment wallet to further sell.
     *
     * @return units of fund
     */
    InvestmentFundUnitTypeInterface[] getUnitTypes();

    /**
     * Add unit of fund.
     *
     * @param unitType unit type
     */
    void addUnitType(InvestmentFundUnitTypeInterface unitType);

    /**
     * Remove unit of fund.
     *
     * @param unitType unit type
     */
    void removeUnitType(InvestmentFundUnitTypeInterface unitType);
}
