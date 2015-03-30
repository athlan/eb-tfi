package pl.eurobank.tfi.component.investmentfundwallet.repository.exception;

/**
 * Exception denotes that there is no money to process buy operation.
 *
 */
public class TransactionNotEnoughAmountInWalletException extends IllegalStateException {

    /**
     * Creates exception
     *
     * @param message exception message
     */
    public TransactionNotEnoughAmountInWalletException(String message) {
        super(message);
    }
}
