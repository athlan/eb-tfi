package pl.eurobank.tfi.component.investmentfundwallet.domain;

import pl.eurobank.tfi.component.investmentfund.domain.InvestmentFundUnitTypeInterface;
import pl.eurobank.tfi.component.money.domain.PriceInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * User's investment wallet.
 *
 * Represents user's investment wallet in certain currency. In the investment wallet
 * user has units of investment fund.
 *
 */
public class InvestmentWallet implements InvestmentWalletInterface {

    private PriceInterface amount;

    private List<InvestmentWalletEntryInterface> entries;

    /**
     * Creates investment wallet in certain currency.
     *
     * @param amount
     */
    public InvestmentWallet(final PriceInterface amount) {
        this.amount = amount;
        this.entries = new ArrayList<>();
    }

    /**
     * Get wallet amount.
     *
     * @return wallet amount.
     */
    @Override
    public PriceInterface getAmount() {
        return amount;
    }

    /**
     * Set wallet amount.
     *
     * @param amount wallet amount.
     */
    @Override
    public void setAmount(PriceInterface amount) {
        this.amount = amount;
    }

    /**
     * Get buyed investment funds units.
     *
     * @return investment funds units
     */
    @Override
    public InvestmentWalletEntryInterface[] getEntries() {
        return entries.toArray(new InvestmentWalletEntryInterface[entries.size()]);
    }

    /**
     * Get buyed investment funds units entry for certain unit type.
     *
     * @return investment funds units
     */
    @Override
    public InvestmentWalletEntryInterface getEntryForInvestmentFundUnitType(InvestmentFundUnitTypeInterface unitType) {
        for (InvestmentWalletEntryInterface entry : entries) {
            if(entry.getInvestmentFundUnitType().equals(unitType)) {
                return entry;
            }
        }

        return null;
    }

    /**
     * Add buyed investment funds units.
     *
     */
    @Override
    public void addEntry(InvestmentWalletEntryInterface entry) {
        InvestmentWalletEntryInterface existing = getEntryForInvestmentFundUnitType(entry.getInvestmentFundUnitType());

        if(null != existing) {
            existing.setQuantity(existing.getQuantity() + entry.getQuantity());
        }
        else {
            entries.add(entry);
        }
    }

    /**
     * Remove buyed investment funds units.
     *
     */
    @Override
    public void removeEntry(InvestmentWalletEntryInterface entry) {
        entries.remove(entry);
    }
}
