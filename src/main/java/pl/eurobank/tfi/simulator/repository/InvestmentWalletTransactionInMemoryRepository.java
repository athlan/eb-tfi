package pl.eurobank.tfi.simulator.repository;

import com.google.common.eventbus.EventBus;
import pl.eurobank.tfi.component.investmentfundwallet.domain.*;
import pl.eurobank.tfi.component.investmentfundwallet.domain.event.InvestmentTransactionEvent;
import pl.eurobank.tfi.component.investmentfundwallet.repository.InvestmentWalletTransactionRepositoryInterface;
import pl.eurobank.tfi.component.investmentfundwallet.repository.exception.TransactionNoUnitsInWalletException;
import pl.eurobank.tfi.component.investmentfundwallet.repository.exception.TransactionNotEnoughAmountInWalletException;
import pl.eurobank.tfi.component.investmentfundwallet.repository.exception.TransactionNotEnoughUnitsQuantityInWalletException;
import pl.eurobank.tfi.component.money.domain.Price;
import pl.eurobank.tfi.component.money.domain.PriceInterface;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class InvestmentWalletTransactionInMemoryRepository implements InvestmentWalletTransactionRepositoryInterface {

    EventBus eventBus;

    private List<InvestmentTransactionInterface> storage;

    public InvestmentWalletTransactionInMemoryRepository(EventBus eventBus) {
        this.storage = new ArrayList<>();
        this.eventBus = eventBus;
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
        InvestmentWalletInterface wallet = transaction.getWallet();

        if(wallet.getAmount().getAmount() < transaction.getPriceValue().getAmount()) {
            throw new TransactionNotEnoughAmountInWalletException("No enough money in wallet to do this transaction");
        }

        if(transaction instanceof InvestmentSellTransaction) {
            InvestmentWalletEntryInterface walletEntry = wallet.getEntryForInvestmentFundUnitType(transaction.getInvestmentFundUnitType());

            if(null == walletEntry) {
                throw new TransactionNoUnitsInWalletException("No wallet entry to sell this unit type.");
            }
            if(walletEntry.getQuantity() < transaction.getQuantity()) {
                throw new TransactionNotEnoughUnitsQuantityInWalletException("No enough quantity in wallet to sell this unit type.");
            }
        }

        storage.add(transaction);

        PriceInterface price = wallet.getAmount().add(transaction.getPrice());
        wallet.setAmount(price);

        wallet.addEntry(new InvestmentWalletEntry(transaction.getInvestmentFundUnitType(), transaction.getQuantity()));

        InvestmentTransactionEvent event = new InvestmentTransactionEvent(transaction, new Date());
        eventBus.post(event);

        return transaction;
    }
}
