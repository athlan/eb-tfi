package pl.eurobank.tfi.component.investmentfundwallet.domain;

import pl.eurobank.tfi.component.investmentfund.domain.InvestmentFundUnitTypeInterface;

import java.util.Date;

/**
 * Sell investment transaction
 *
 */
public class InvestmentSellTransaction extends AbstractInvestmentTransaction {

    /**
     * Creates transaction.
     *
     * @param wallet the wallet that transaction belongs to
     * @param investmentFundUnitType investment unit type that transaction belongs to
     * @param quantity quantity of units
     * @param transactionDate transaction date
     */
    public InvestmentSellTransaction(InvestmentWalletInterface wallet, InvestmentFundUnitTypeInterface investmentFundUnitType, Long quantity, Date transactionDate) {
        super(wallet, investmentFundUnitType, quantity, transactionDate);
    }
}
