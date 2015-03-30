package pl.eurobank.tfi.component.investmentfundwallet.domain;

import pl.eurobank.tfi.component.investmentfund.domain.InvestmentFundUnitTypeInterface;

/**
 * Investment wallet entry denotes investment fund unit and quantity.
 *
 */
public interface InvestmentWalletEntryInterface {

    /**
     * Get investment fund unit.
     *
     * @return investment fund unit
     */
    InvestmentFundUnitTypeInterface getInvestmentFundUnitType();

    /**
     * Get quantity of investment fund unit.
     * @return quantity of investment fund unit
     */
    Long getQuantity();

    /**
     * Set quantity of investment fund unit.
     * @param quantity quantity of investment fund unit
     */
    void setQuantity(Long quantity);

}
