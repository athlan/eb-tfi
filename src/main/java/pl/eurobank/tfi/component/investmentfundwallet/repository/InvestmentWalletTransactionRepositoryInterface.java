package pl.eurobank.tfi.component.investmentfundwallet.repository;

import pl.eurobank.tfi.component.investmentfundwallet.domain.InvestmentTransactionInterface;
import pl.eurobank.tfi.component.investmentfundwallet.domain.InvestmentWalletInterface;
import pl.eurobank.tfi.component.investmentfundwallet.repository.exception.TransactionNoUnitsInWalletException;
import pl.eurobank.tfi.component.investmentfundwallet.repository.exception.TransactionNotEnoughAmountInWalletException;
import pl.eurobank.tfi.component.investmentfundwallet.repository.exception.TransactionNotEnoughUnitsQuantityInWalletException;
import pl.eurobank.tfi.component.money.domain.PriceInterface;

import java.util.Map;

/**
 * The repository of InvestmentTransactionInterface objects. Provides access to
 * gather objects from abstract data source.
 *
 * This repository contains all information about investment transactions.
 *
 */
public interface InvestmentWalletTransactionRepositoryInterface {

    /**
     * Get list of investment transactions for given wallet and parameters.
     *
     * @param wallet investment wallet
     * @param params filters, sorting options
     * @return list of investment transactions
     */
    InvestmentTransactionInterface[] findTransactionsByWallet(InvestmentWalletInterface wallet, Map<String, String> params);

    /**
     * Get summary price of investment transactions for given wallet and parameters.
     *
     * @param wallet investment wallet
     * @param params filters, sorting options
     * @return list of investment transactions
     */
    PriceInterface getTransactionsValueByWallet(InvestmentWalletInterface wallet, Map<String, String> params);

    /**
     * Creates investment transaction.
     *
     * @param transaction transaction to make
     * @throws TransactionNotEnoughAmountInWalletException Exception is thrown when there is no enough money in wallet to process buy transaction
     * @throws TransactionNoUnitsInWalletException Exception is thrown when there is no units in wallet to process sell transaction
     * @throws TransactionNotEnoughUnitsQuantityInWalletException Exception is thrown when there is no enough unit in wallet to process sell transaction
     * @return transaction has been made
     */
    InvestmentTransactionInterface createTransaction(InvestmentTransactionInterface transaction);
}
