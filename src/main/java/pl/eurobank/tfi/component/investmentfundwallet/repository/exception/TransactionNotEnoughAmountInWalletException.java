package pl.eurobank.tfi.component.investmentfundwallet.repository.exception;

public class TransactionNotEnoughAmountInWalletException extends IllegalStateException {

    public TransactionNotEnoughAmountInWalletException(String message) {
        super(message);
    }
}
