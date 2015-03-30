package pl.eurobank.tfi.component.investmentfundwallet.repository.exception;

/**
 * Exception denotes that there is no enough units quantity
 * in user's investment wallet to process sell operation.
 *
 */
public class TransactionNotEnoughUnitsQuantityInWalletException extends IllegalStateException {

    /**
     * Creates exception
     *
     * @param message exception message
     */
    public TransactionNotEnoughUnitsQuantityInWalletException(String message) {
        super(message);
    }
}
