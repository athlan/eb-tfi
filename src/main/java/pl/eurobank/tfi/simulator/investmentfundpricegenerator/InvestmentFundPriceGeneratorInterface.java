package pl.eurobank.tfi.simulator.investmentfundpricegenerator;

import pl.eurobank.tfi.component.investmentfund.domain.InvestmentFundInterface;
import pl.eurobank.tfi.component.money.domain.PriceInterface;

/**
 * Interface defines contract for classes that provides functionality
 * for changing fund value by providing new value that should be set for fund.
 *
 */
public interface InvestmentFundPriceGeneratorInterface {

    /**
     * Generates new value for fund.
     *
     * @param investmentFund investment fund
     * @return new value for fund
     */
    PriceInterface getFundPrice(InvestmentFundInterface investmentFund);
}
