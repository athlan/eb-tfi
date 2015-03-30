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

/**
 * The repository of InvestmentTransactionInterface objects. Provides access to
 * gather objects from abstract data source.
 *
 * This repository contains all information about investment transactions.
 *
 */
public class InvestmentWalletTransactionInMemoryRepository implements InvestmentWalletTransactionRepositoryInterface {

    private EventBus eventBus;

    private List<InvestmentTransactionInterface> storage;

    /**
     * Creates repository
     *
     * @param eventBus event bus for propagate event
     */
    public InvestmentWalletTransactionInMemoryRepository(EventBus eventBus) {
        this.storage = new ArrayList<>();
        this.eventBus = eventBus;
    }

    /**
     * Get list of investment transactions for given wallet and parameters.
     *
     * @param wallet investment wallet
     * @param params filters, sorting options
     * @return list of investment transactions
     */
    @Override
    public InvestmentTransactionInterface[] findTransactionsByWallet(InvestmentWalletInterface wallet, Map<String, String> params) {
        List<InvestmentTransactionInterface> tmp = new ArrayList<>();

        for (InvestmentTransactionInterface element : storage) {
            tmp.add(element);
        }

        tmp.sort((o1, o2) -> o1.getTransactionDate().compareTo(o2.getTransactionDate()) );

        return tmp.toArray(new InvestmentTransactionInterface[tmp.size()]);
    }

    /**
     * Get summary price of investment transactions for given wallet and parameters.
     *
     * @param wallet investment wallet
     * @param params filters, sorting options
     * @return list of investment transactions
     */
    @Override
    public PriceInterface getTransactionsValueByWallet(InvestmentWalletInterface wallet, Map<String, String> params) {
        double amount = 0.0;

        for (InvestmentTransactionInterface element : storage) {
            amount += element.getPrice().getAmount();
        }

        return new Price(amount, wallet.getAmount().getCurrency());
    }

    /**
     * Creates investment transaction.
     *
     * @param transaction transaction to make
     * @throws TransactionNotEnoughAmountInWalletException Exception is thrown when there is no enough money in wallet to process buy transaction
     * @throws TransactionNoUnitsInWalletException Exception is thrown when there is no units in wallet to process sell transaction
     * @throws TransactionNotEnoughUnitsQuantityInWalletException Exception is thrown when there is no enough unit in wallet to process sell transaction
     * @return transaction has been made
     */
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

        InvestmentTransactionEvent event;
        event = new InvestmentTransactionEvent(InvestmentTransactionEvent.Type.PRE, transaction, new Date());
        eventBus.post(event);

        storage.add(transaction);

        PriceInterface price = wallet.getAmount().add(transaction.getPrice());
        wallet.setAmount(price);

        wallet.addEntry(new InvestmentWalletEntry(transaction.getInvestmentFundUnitType(), transaction.getQuantity()));

        event = new InvestmentTransactionEvent(InvestmentTransactionEvent.Type.POST, transaction, new Date());
        eventBus.post(event);

        return transaction;
    }
}
