package pl.eurobank.tfi.simulator.repository;

import com.google.common.eventbus.EventBus;
import pl.eurobank.tfi.component.investmentfund.domain.InvestmentFundInterface;
import pl.eurobank.tfi.component.investmentfund.domain.event.InvestmentFundPricingChangesEvent;
import pl.eurobank.tfi.component.investmentfund.repository.InvestmentFundPriceRepositoryInterface;
import pl.eurobank.tfi.component.investmentfund.repository.InvestmentFundRepositoryInterface;
import pl.eurobank.tfi.component.money.domain.PriceInterface;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class InvestmentFundPriceInMemoryRepository implements InvestmentFundPriceRepositoryInterface {

    private EventBus eventBus;

    private List<InvestmentFundPricingChangesEvent> storage;

    public InvestmentFundPriceInMemoryRepository(EventBus eventBus) {
        this.eventBus = eventBus;
        storage = new ArrayList<>();
    }

    @Override
    public InvestmentFundInterface changePrice(InvestmentFundInterface investmentFund, PriceInterface newPrice) {
        InvestmentFundPricingChangesEvent event = new InvestmentFundPricingChangesEvent(investmentFund, newPrice, new Date());
        this.eventBus.post(event);

        storage.add(event);

        investmentFund.setCurrentPricing(newPrice);

        return investmentFund;
    }

    @Override
    public InvestmentFundPricingChangesEvent[] getPriceChanges(Map<String, String> params) {
        return storage.toArray(new InvestmentFundPricingChangesEvent[storage.size()]);
    }
}
