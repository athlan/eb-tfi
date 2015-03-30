package pl.eurobank.tfi.simulator.ui;

import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.eurobank.tfi.component.investmentfund.domain.InvestmentFund;
import pl.eurobank.tfi.component.investmentfund.domain.InvestmentFundInterface;
import pl.eurobank.tfi.component.investmentfund.repository.InvestmentFundPriceRepositoryInterface;
import pl.eurobank.tfi.component.investmentfund.repository.InvestmentFundRepositoryInterface;
import pl.eurobank.tfi.component.investmentfundwallet.domain.InvestmentWallet;
import pl.eurobank.tfi.component.investmentfundwallet.domain.InvestmentWalletInterface;
import pl.eurobank.tfi.component.investmentfundwallet.repository.InvestmentWalletTransactionRepositoryInterface;
import pl.eurobank.tfi.component.money.domain.Price;
import pl.eurobank.tfi.component.money.domain.PriceInterface;
import pl.eurobank.tfi.simulator.investmentfundpricegenerator.RangeButNotUnderZeroInvestmentFundPriceGenerator;
import pl.eurobank.tfi.simulator.repository.InvestmentFundInMemoryRepository;
import pl.eurobank.tfi.simulator.repository.InvestmentFundPriceInMemoryRepository;
import pl.eurobank.tfi.simulator.repository.InvestmentWalletTransactionInMemoryRepository;
import pl.eurobank.tfi.simulator.ui.component.funds.FundsChangePricesIntervalGenerator;
import pl.eurobank.tfi.simulator.ui.component.funds.FundsTableFactory;
import pl.eurobank.tfi.simulator.ui.component.funds.FundsTableModel;

import java.util.Currency;

@Configuration
@ComponentScan(value={"pl.eurobank.tfi.simulator.ui"})
public class MainConfiguration {

    @Autowired
    EventBus eventBus;

    @Autowired
    InvestmentFundRepositoryInterface investmentFundRepository;

    @Autowired
    InvestmentFundPriceRepositoryInterface investmentFundPriceRepository;

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
        entity = new InvestmentFund("Fundusz rynku pienieznego", price);
        repository.save(entity);

        price = new Price(100, Currency.getInstance("PLN"));
        entity = new InvestmentFund("Fundusz obligacji", price);
        repository.save(entity);

        price = new Price(100, Currency.getInstance("PLN"));
        entity = new InvestmentFund("Fundusz stabilnego wzrostu", price);
        repository.save(entity);

        price = new Price(100, Currency.getInstance("PLN"));
        entity = new InvestmentFund("Fundusz zrownowazony", price);
        repository.save(entity);

        price = new Price(100, Currency.getInstance("PLN"));
        entity = new InvestmentFund("Fundusz akcji", price);
        repository.save(entity);

        return repository;
    }

    @Bean
    public InvestmentFundPriceRepositoryInterface getInvestmentFundPriceRepository() {
        InvestmentFundPriceRepositoryInterface repository = new InvestmentFundPriceInMemoryRepository(eventBus);
        return repository;
    }

    @Bean
    public FundsChangePricesIntervalGenerator getFundsChangePricesIntervalGenerator() {
        long interval = 10000;
        FundsChangePricesIntervalGenerator generator = new FundsChangePricesIntervalGenerator(interval, investmentFundPriceRepository);

        InvestmentFundInterface entity;

        entity = investmentFundRepository.getById("Fundusz rynku pienieznego");if(null != entity) {
            generator.generators.put(entity,
                    new RangeButNotUnderZeroInvestmentFundPriceGenerator(
                            new Price(-0.05, Currency.getInstance("PLN")),
                            new Price(0.40, Currency.getInstance("PLN"))
                    ));
        }

        entity = investmentFundRepository.getById("Fundusz obligacji");
        if(null != entity) {
            generator.generators.put(entity,
                    new RangeButNotUnderZeroInvestmentFundPriceGenerator(
                            new Price(-0.15, Currency.getInstance("PLN")),
                            new Price(0.60, Currency.getInstance("PLN"))
                    ));
        }

        entity = investmentFundRepository.getById("Fundusz stabilnego wzrostu");
        if(null != entity) {
            generator.generators.put(entity,
                    new RangeButNotUnderZeroInvestmentFundPriceGenerator(
                            new Price(-0.65, Currency.getInstance("PLN")),
                            new Price(0.85, Currency.getInstance("PLN"))
                    ));
        }

        entity = investmentFundRepository.getById("Fundusz zrownowazony");
        if(null != entity) {
            generator.generators.put(entity,
                    new RangeButNotUnderZeroInvestmentFundPriceGenerator(
                            new Price(-0.80, Currency.getInstance("PLN")),
                            new Price(0.96, Currency.getInstance("PLN"))
                    ));
        }

        entity = investmentFundRepository.getById("Fundusz akcji");
        if(null != entity) {
            generator.generators.put(entity,
                    new RangeButNotUnderZeroInvestmentFundPriceGenerator(
                            new Price(-1.00, Currency.getInstance("PLN")),
                            new Price(1.10, Currency.getInstance("PLN"))
                    ));
        }

        return generator;
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
