package pl.eurobank.tfi.component.investmentfundwallet.repository.exception;

/**
 * Exception denotes that there is no enough units
 * in user's investment wallet to process sell operation.
 *
 */
public class TransactionNoUnitsInWalletException extends IllegalStateException {

    /**
     * Creates exception
     *
     * @param message
     */
    public TransactionNoUnitsInWalletException(String message) {
        super(message);
    }
}
