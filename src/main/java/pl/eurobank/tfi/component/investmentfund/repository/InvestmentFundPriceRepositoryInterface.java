package pl.eurobank.tfi.component.investmentfund.repository;

import pl.eurobank.tfi.component.money.domain.PriceInterface;
import pl.eurobank.tfi.component.investmentfund.domain.InvestmentFundInterface;
import pl.eurobank.tfi.component.investmentfund.domain.event.InvestmentFundPricingChangesEvent;

import java.util.Map;

/**
 * The repository of InvestmentFundPricingChangesEvent objects. Provides access to
 * gather objects from abstract data source.
 *
 * This repository contains all information about changes of investment funds price.
 *
 */
public interface InvestmentFundPriceRepositoryInterface {

    /**
     * Get list of investment fund price changes by given parameters.
     *
     * @param params filters, sorting options
     * @return list of investment fund price changes
     */
    InvestmentFundPricingChangesEvent[] getPriceChanges(Map<String, String> params);

    /**
     * Changes price of investment fund.
     *
     * @param investmentFund
     * @param newPrice
     * @return investment fund that price has been changed for
     */
    InvestmentFundInterface changePrice(InvestmentFundInterface investmentFund, PriceInterface newPrice);
}
