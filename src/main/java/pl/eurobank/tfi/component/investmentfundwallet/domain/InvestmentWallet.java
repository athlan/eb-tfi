package pl.eurobank.tfi.component.investmentfundwallet.domain;

import pl.eurobank.tfi.component.investmentfund.domain.InvestmentFundUnitTypeInterface;
import pl.eurobank.tfi.component.money.domain.PriceInterface;

import java.util.ArrayList;
import java.util.List;

public class InvestmentWallet implements InvestmentWalletInterface {

    private PriceInterface amount;

    private List<InvestmentWalletEntryInterface> entries;

    public InvestmentWallet(final PriceInterface amount) {
        this.amount = amount;
        this.entries = new ArrayList<>();
    }

    @Override
    public PriceInterface getAmount() {
        return amount;
    }

    @Override
    public void setAmount(PriceInterface amount) {
        this.amount = amount;
    }

    @Override
    public InvestmentWalletEntryInterface[] getEntries() {
        return entries.toArray(new InvestmentWalletEntryInterface[entries.size()]);
    }

    @Override
    public InvestmentWalletEntryInterface getEntryForInvestmentFundUnitType(InvestmentFundUnitTypeInterface unitType) {
        for (InvestmentWalletEntryInterface entry : entries) {
            if(entry.getInvestmentFundUnitType().equals(unitType)) {
                return entry;
            }
        }

        return null;
    }

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

    @Override
    public void removeEntry(InvestmentWalletEntryInterface entry) {
        entries.remove(entry);
    }
}
