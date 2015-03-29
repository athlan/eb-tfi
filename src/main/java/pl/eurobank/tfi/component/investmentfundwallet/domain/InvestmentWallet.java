package pl.eurobank.tfi.component.investmentfundwallet.domain;

import pl.eurobank.tfi.component.money.domain.PriceInterface;

public class InvestmentWallet implements InvestmentWalletInterface {

    private PriceInterface amount;

    public InvestmentWallet(final PriceInterface amount) {
        this.amount = amount;
    }

    @Override
    public PriceInterface getAmount() {
        return amount;
    }

    @Override
    public void setAmount(PriceInterface amount) {
        this.amount = amount;
    }
}
