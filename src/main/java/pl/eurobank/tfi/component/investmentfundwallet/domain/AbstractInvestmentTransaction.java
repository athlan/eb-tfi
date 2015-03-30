package pl.eurobank.tfi.component.investmentfundwallet.domain;

import pl.eurobank.tfi.component.investmentfund.domain.InvestmentFundUnitTypeInterface;
import pl.eurobank.tfi.component.money.domain.Price;
import pl.eurobank.tfi.component.money.domain.PriceInterface;

import java.util.Date;

/**
 * Investment transaction
 *
 */
public class AbstractInvestmentTransaction implements InvestmentTransactionInterface {

    private InvestmentWalletInterface wallet;

    private InvestmentFundUnitTypeInterface investmentFundUnitType;

    private Long quantity;

    private PriceInterface price;

    private PriceInterface priceValue;

    private Date transactionDate;

    /**
     * Creates transaction.
     *
     * @param wallet the wallet that transaction belongs to
     * @param investmentFundUnitType investment unit type that transaction belongs to
     * @param quantity quantity of units
     * @param transactionDate transaction date
     */
    public AbstractInvestmentTransaction(InvestmentWalletInterface wallet, InvestmentFundUnitTypeInterface investmentFundUnitType, Long quantity, Date transactionDate) {
        this.wallet = wallet;
        this.investmentFundUnitType = investmentFundUnitType;
        this.quantity = quantity;
        this.transactionDate = transactionDate;

        PriceInterface currentPricing = investmentFundUnitType.getInvestmentFund().getCurrentPricing();

        if(!currentPricing.getCurrency().equals(wallet.getAmount().getCurrency())) {
            throw new IllegalArgumentException("You cannot make transactions in different currency than is wallet.");
        }

        this.price = new Price(quantity * currentPricing.getAmount(), currentPricing.getCurrency());
        this.priceValue = new Price(quantity * currentPricing.getAmount(), currentPricing.getCurrency());
    }

    /**
     * Gets the wallet that transaction belongs to.
     * @return the wallet that transaction belongs to
     */
    @Override
    public InvestmentWalletInterface getWallet() {
        return wallet;
    }

    /**
     * Gets investment unit type that transaction belongs to.
     * @return investment unit type that transaction belongs to
     */
    @Override
    public InvestmentFundUnitTypeInterface getInvestmentFundUnitType() {
        return investmentFundUnitType;
    }

    /**
     * Get quantity of units.
     * @return quantity of units
     */
    @Override
    public Long getQuantity() {
        return quantity;
    }

    /**
     * Get transaction date.
     * @return transaction date
     */
    @Override
    public Date getTransactionDate() {
        return transactionDate;
    }

    /**
     * Get amount of transaction.
     * @return amount of transaction
     */
    @Override
    public PriceInterface getPrice() {
        return price;
    }

    /**
     * Get absolute amount of transaction.
     * @return amount of transaction
     */
    @Override
    public PriceInterface getPriceValue() {
        return priceValue;
    }

    /**
     * Set amount of transaction.
     * @param price amount of transaction
     */
    @Override
    public void setPrice(PriceInterface price) {
        this.price = price;
    }

    /**
     * Set absolute amount of transaction.
     * @param priceValue amount of transaction
     */
    @Override
    public void setPriceValue(PriceInterface priceValue) {
        this.priceValue = priceValue;
    }
}
