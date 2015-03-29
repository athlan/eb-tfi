package pl.eurobank.tfi.simulator.ui;

import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.eurobank.tfi.component.investmentfund.domain.InvestmentFund;
import pl.eurobank.tfi.component.investmentfund.domain.InvestmentFundInterface;
import pl.eurobank.tfi.component.investmentfund.repository.InvestmentFundRepositoryInterface;
import pl.eurobank.tfi.component.investmentfundwallet.domain.InvestmentWallet;
import pl.eurobank.tfi.component.investmentfundwallet.domain.InvestmentWalletInterface;
import pl.eurobank.tfi.component.investmentfundwallet.repository.InvestmentWalletTransactionRepositoryInterface;
import pl.eurobank.tfi.component.money.domain.Price;
import pl.eurobank.tfi.component.money.domain.PriceInterface;
import pl.eurobank.tfi.simulator.repository.InvestmentFundInMemoryRepository;
import pl.eurobank.tfi.simulator.repository.InvestmentWalletTransactionInMemoryRepository;
import pl.eurobank.tfi.simulator.ui.component.funds.FundsTableFactory;
import pl.eurobank.tfi.simulator.ui.component.funds.FundsTableModel;

import java.util.Currency;

@Configuration
@ComponentScan(value={"pl.eurobank.tfi.simulator.ui"})
public class MainConfiguration {

    @Autowired
    EventBus eventBus;

    @Bean
    public EventBus getEventBus(){
        return new EventBus();
    }

    @Bean
    public InvestmentWalletInterface getInvestmentWallet() {
        PriceInterface walletAmount = new Price(100.0, Currency.getInstance("PLN"));
        return new InvestmentWallet(walletAmount);
    }

    @Bean
    public InvestmentWalletTransactionRepositoryInterface getInvestmentWalletTransactionRepository() {
        return new InvestmentWalletTransactionInMemoryRepository(eventBus);
    }

    @Bean
    public InvestmentFundRepositoryInterface getInvestmentFundRepository() {
        InvestmentFundRepositoryInterface repository = new InvestmentFundInMemoryRepository();

        PriceInterface price;
        InvestmentFundInterface entity;

        price = new Price(100, Currency.getInstance("PLN"));
        entity = new InvestmentFund("Fund 1", price);
        repository.save(entity);

        price = new Price(100, Currency.getInstance("PLN"));
        entity = new InvestmentFund("Fund 2", price);
        repository.save(entity);

        return repository;
    }

    @Bean
    public FundsTableFactory getFundsTableFactory() {
        return new FundsTableFactory();
    }

    @Bean
    public FundsTableModel getFundsTableModel() {
        return new FundsTableModel();
    }
}
