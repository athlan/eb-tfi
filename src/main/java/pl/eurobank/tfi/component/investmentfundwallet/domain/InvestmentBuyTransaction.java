package pl.eurobank.tfi.component.investmentfundwallet.domain;

import pl.eurobank.tfi.component.investmentfund.domain.InvestmentFundUnitTypeInterface;
import pl.eurobank.tfi.component.money.domain.Price;

import java.util.Date;

/**
 * Buy investment transaction
 *
 */
public class InvestmentBuyTransaction extends AbstractInvestmentTransaction {

    /**
     * Creates transaction.
     *
     * @param wallet the wallet that transaction belongs to
     * @param investmentFundUnitType investment unit type that transaction belongs to
     * @param quantity quantity of units
     * @param transactionDate transaction date
     */
    public InvestmentBuyTransaction(InvestmentWalletInterface wallet, InvestmentFundUnitTypeInterface investmentFundUnitType, Long quantity, Date transactionDate) {
        super(wallet, investmentFundUnitType, quantity, transactionDate);

        // inverse price
        setPrice(super.getPrice().multiply(-1.0));
    }
}
