package pl.eurobank.tfi.simulator.repository;

import pl.eurobank.tfi.component.investmentfundwallet.domain.InvestmentTransactionInterface;
import pl.eurobank.tfi.component.investmentfundwallet.domain.InvestmentWallet;
import pl.eurobank.tfi.component.investmentfundwallet.repository.InvestmentWalletTransactionRepositoryInterface;

import java.util.ArrayList;
import java.util.List;

public class InvestmentWalletTransactionInMemoryRepository implements InvestmentWalletTransactionRepositoryInterface {

    private List<InvestmentTransactionInterface> storage;

    public InvestmentWalletTransactionInMemoryRepository() {
        this.storage = new ArrayList<>();
    }

    @Override
    public InvestmentTransactionInterface[] findTransactionsByWallet(InvestmentWallet wallet) {
        List<InvestmentTransactionInterface> tmp = new ArrayList<>();

        for (InvestmentTransactionInterface element : storage) {
            tmp.add(element);
        }

        tmp.sort((o1, o2) -> o1.getTransactionDate().compareTo(o2.getTransactionDate()) );

        return tmp.toArray(new InvestmentTransactionInterface[tmp.size()]);
    }

    @Override
    public InvestmentTransactionInterface createTransaction(InvestmentTransactionInterface transaction) {
        if(transaction.getWallet().getAmount().getAmount() < transaction.getPrice().getAmount()) {
            throw new IllegalStateException("No enough money in wallet to do this transaction");
        }

        storage.add(transaction);

        return transaction;
    }
}
