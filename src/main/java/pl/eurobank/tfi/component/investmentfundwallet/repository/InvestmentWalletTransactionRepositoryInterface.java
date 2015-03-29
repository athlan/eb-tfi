package pl.eurobank.tfi.component.investmentfundwallet.repository;

import pl.eurobank.tfi.component.investmentfundwallet.domain.InvestmentTransactionInterface;
import pl.eurobank.tfi.component.investmentfundwallet.domain.InvestmentWallet;

public interface InvestmentWalletTransactionRepositoryInterface {

    InvestmentTransactionInterface[] findTransactionsByWallet(InvestmentWallet wallet);

    InvestmentTransactionInterface createTransaction(InvestmentTransactionInterface transaction);
}
