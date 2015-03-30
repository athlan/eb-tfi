package pl.eurobank.tfi.component.investmentfundwallet.domain;

import pl.eurobank.tfi.component.investmentfund.domain.InvestmentFundUnitTypeInterface;
import pl.eurobank.tfi.component.money.domain.PriceInterface;

import java.util.Date;

/**
 * Investment transaction
 *
 */
public interface InvestmentTransactionInterface {

    /**
     * Gets the wallet that transaction belongs to.
     * @return the wallet that transaction belongs to
     */
    InvestmentWalletInterface getWallet();

    /**
     * Gets investment unit type that transaction belongs to.
     * @return investment unit type that transaction belongs to
     */
    InvestmentFundUnitTypeInterface getInvestmentFundUnitType();

    /**
     * Get quantity of units.
     * @return quantity of units
     */
    Long getQuantity();

    /**
     * Get amount of transaction.
     * @return amount of transaction
     */
    PriceInterface getPrice();

    /**
     * Get absolute amount of transaction.
     * @return amount of transaction
     */
    PriceInterface getPriceValue();

    /**
     * Set amount of transaction.
     * @param price amount of transaction
     */
    void setPrice(PriceInterface price);

    /**
     * Set absolute amount of transaction.
     * @param priceValue amount of transaction
     */
    void setPriceValue(PriceInterface priceValue);

    /**
     * Get transaction date.
     * @return transaction date
     */
    Date getTransactionDate();
}
