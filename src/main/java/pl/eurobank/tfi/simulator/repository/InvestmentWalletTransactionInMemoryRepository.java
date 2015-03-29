package pl.eurobank.tfi.simulator.repository;

import pl.eurobank.tfi.component.investmentfundwallet.domain.InvestmentTransactionInterface;
import pl.eurobank.tfi.component.investmentfundwallet.domain.InvestmentWalletInterface;
import pl.eurobank.tfi.component.investmentfundwallet.repository.InvestmentWalletTransactionRepositoryInterface;
import pl.eurobank.tfi.component.money.domain.Price;
import pl.eurobank.tfi.component.money.domain.PriceInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InvestmentWalletTransactionInMemoryRepository implements InvestmentWalletTransactionRepositoryInterface {

    private List<InvestmentTransactionInterface> storage;

    public InvestmentWalletTransactionInMemoryRepository() {
        this.storage = new ArrayList<>();
    }

    @Override
    public InvestmentTransactionInterface[] findTransactionsByWallet(InvestmentWalletInterface wallet, Map<String, String> params) {
        List<InvestmentTransactionInterface> tmp = new ArrayList<>();

        for (InvestmentTransactionInterface element : storage) {
            tmp.add(element);
        }

        tmp.sort((o1, o2) -> o1.getTransactionDate().compareTo(o2.getTransactionDate()) );

        return tmp.toArray(new InvestmentTransactionInterface[tmp.size()]);
    }

    @Override
    public PriceInterface getTransactionsValueByWallet(InvestmentWalletInterface wallet, Map<String, String> params) {
        double amount = 0.0;

        for (InvestmentTransactionInterface element : storage) {
            amount += element.getPrice().getAmount();
        }

        return new Price(amount, wallet.getAmount().getCurrency());
    }

    @Override
    public InvestmentTransactionInterface createTransaction(InvestmentTransactionInterface transaction) {
        if(transaction.getWallet().getAmount().getAmount() < transaction.getPriceValue().getAmount()) {
            throw new IllegalStateException("No enough money in wallet to do this transaction");
        }

        storage.add(transaction);

        PriceInterface price = transaction.getWallet().getAmount().add(transaction.getPrice());
        transaction.getWallet().setAmount(price);

        return transaction;
    }
}
