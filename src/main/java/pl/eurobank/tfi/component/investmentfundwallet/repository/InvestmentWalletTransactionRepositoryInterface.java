package pl.eurobank.tfi.component.investmentfundwallet.repository;

import pl.eurobank.tfi.component.investmentfundwallet.domain.InvestmentTransactionInterface;
import pl.eurobank.tfi.component.investmentfundwallet.domain.InvestmentWalletInterface;
import pl.eurobank.tfi.component.money.domain.PriceInterface;

import java.util.Map;

public interface InvestmentWalletTransactionRepositoryInterface {

    InvestmentTransactionInterface[] findTransactionsByWallet(InvestmentWalletInterface wallet, Map<String, String> params);

    PriceInterface getTransactionsValueByWallet(InvestmentWalletInterface wallet, Map<String, String> params);

    InvestmentTransactionInterface createTransaction(InvestmentTransactionInterface transaction);
}
