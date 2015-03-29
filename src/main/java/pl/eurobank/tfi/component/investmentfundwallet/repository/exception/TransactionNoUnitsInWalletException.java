package pl.eurobank.tfi.component.investmentfundwallet.repository.exception;

public class TransactionNoUnitsInWalletException extends IllegalStateException {

    public TransactionNoUnitsInWalletException(String message) {
        super(message);
    }
}
