package pl.eurobank.tfi.component.investmentfund.repository;

import pl.eurobank.tfi.component.money.domain.PriceInterface;
import pl.eurobank.tfi.component.investmentfund.domain.InvestmentFundInterface;
import pl.eurobank.tfi.component.investmentfund.domain.event.InvestmentFundPricingChangesEvent;

import java.util.Map;

public interface InvestmentFundPriceRepositoryInterface {

    InvestmentFundPricingChangesEvent[] getPriceChanges(Map<String, String> params);

    InvestmentFundInterface changePrice(InvestmentFundInterface investmentFund, PriceInterface newPrice);
}
