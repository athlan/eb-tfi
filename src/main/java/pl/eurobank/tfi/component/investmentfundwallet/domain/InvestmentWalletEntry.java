package pl.eurobank.tfi.component.investmentfundwallet.domain;

import pl.eurobank.tfi.component.investmentfund.domain.InvestmentFundUnitTypeInterface;

public class InvestmentWalletEntry implements InvestmentWalletEntryInterface {

    private Long quantity;
    private final InvestmentFundUnitTypeInterface investmentFundUnitType;

    public InvestmentWalletEntry(final InvestmentFundUnitTypeInterface investmentFundUnitType, Long quantity) {
        this.investmentFundUnitType = investmentFundUnitType;
        this.quantity = quantity;
    }

    @Override
    public InvestmentFundUnitTypeInterface getInvestmentFundUnitType() {
        return investmentFundUnitType;
    }

    @Override
    public Long getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
