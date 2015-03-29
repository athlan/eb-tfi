package pl.eurobank.tfi.component.investmentfundwallet.repository.exception;

public class TransactionNotEnoughUnitsQuantityInWalletException extends IllegalStateException {

    public TransactionNotEnoughUnitsQuantityInWalletException(String message) {
        super(message);
    }
}
