package pl.eurobank.tfi.component.investmentfundwallet.domain;

import pl.eurobank.tfi.component.investmentfund.domain.InvestmentFundUnitTypeInterface;

/**
 * Investment wallet entry denotes investment fund unit and quantity.
 *
 */
public class InvestmentWalletEntry implements InvestmentWalletEntryInterface {

    private Long quantity;
    private final InvestmentFundUnitTypeInterface investmentFundUnitType;

    /**
     * Creates investment wallet entry.
     *
     * @param investmentFundUnitType investment fund unit type
     * @param quantity investment fund unit quantity
     */
    public InvestmentWalletEntry(final InvestmentFundUnitTypeInterface investmentFundUnitType, Long quantity) {
        this.investmentFundUnitType = investmentFundUnitType;
        this.quantity = quantity;
    }

    /**
     * Get investment fund unit.
     *
     * @return investment fund unit
     */
    @Override
    public InvestmentFundUnitTypeInterface getInvestmentFundUnitType() {
        return investmentFundUnitType;
    }

    /**
     * Get quantity of investment fund unit.
     * @return quantity of investment fund unit
     */
    @Override
    public Long getQuantity() {
        return this.quantity;
    }

    /**
     * Set quantity of investment fund unit.
     * @param quantity quantity of investment fund unit
     */
    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
