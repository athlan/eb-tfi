package pl.eurobank.tfi.component.investmentfundwallet.domain;

import pl.eurobank.tfi.component.investmentfund.domain.InvestmentFundUnitTypeInterface;
import pl.eurobank.tfi.component.money.domain.PriceInterface;

/**
 * User's investment wallet.
 *
 * Represents user's investment wallet in certain currency. In the investment wallet
 * user has units of investment fund.
 *
 */
public interface InvestmentWalletInterface {

    /**
     * Get wallet amount.
     *
     * @return wallet amount.
     */
    PriceInterface getAmount();

    /**
     * Set wallet amount.
     *
     * @param amount wallet amount.
     */
    void setAmount(PriceInterface amount);

    /**
     * Get buyed investment funds units.
     *
     * @return investment funds units
     */
    InvestmentWalletEntryInterface[] getEntries();

    /**
     * Get buyed investment funds units entry for certain unit type.
     *
     * @return investment funds units
     */
    InvestmentWalletEntryInterface getEntryForInvestmentFundUnitType(InvestmentFundUnitTypeInterface unitType);

    /**
     * Add buyed investment funds units.
     *
     */
    void addEntry(InvestmentWalletEntryInterface entry);

    /**
     * Remove buyed investment funds units.
     *
     */
    void removeEntry(InvestmentWalletEntryInterface entry);
}
