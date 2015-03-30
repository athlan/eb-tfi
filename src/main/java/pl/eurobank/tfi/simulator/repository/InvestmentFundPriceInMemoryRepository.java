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

/**
 * In-memory implementation of investment fund price changes repository.
 *
 * The repository of InvestmentFundPricingChangesEvent objects. Provides access to
 * gather objects from abstract data source.
 *
 * This repository contains all information about changes of investment funds price.
 *
 */
public class InvestmentFundPriceInMemoryRepository implements InvestmentFundPriceRepositoryInterface {

    private EventBus eventBus;

    private List<InvestmentFundPricingChangesEvent> storage;

    /**
     * Creates repository.
     *
     * @param eventBus
     */
    public InvestmentFundPriceInMemoryRepository(EventBus eventBus) {
        this.eventBus = eventBus;
        storage = new ArrayList<>();
    }

    /**
     * Changes price of investment fund.
     *
     * @param investmentFund
     * @param newPrice
     * @return investment fund that price has been changed for
     */
    @Override
    public InvestmentFundInterface changePrice(InvestmentFundInterface investmentFund, PriceInterface newPrice) {
        InvestmentFundPricingChangesEvent event = new InvestmentFundPricingChangesEvent(investmentFund, newPrice, new Date());
        this.eventBus.post(event);

        storage.add(event);

        investmentFund.setCurrentPricing(newPrice);

        return investmentFund;
    }

    /**
     * Get list of investment fund price changes by given parameters.
     *
     * @param params filters, sorting options
     * @return list of investment fund price changes
     */
    @Override
    public InvestmentFundPricingChangesEvent[] getPriceChanges(Map<String, String> params) {
        return storage.toArray(new InvestmentFundPricingChangesEvent[storage.size()]);
    }
}
