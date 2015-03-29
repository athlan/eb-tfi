package pl.eurobank.tfi.simulator.investmentfundpricegenerator;

import pl.eurobank.tfi.component.investmentfund.domain.InvestmentFundInterface;
import pl.eurobank.tfi.component.money.domain.PriceInterface;

public interface InvestmentFundPriceGeneratorInterface {

    PriceInterface getFundPrice(InvestmentFundInterface investmentFund);
}
