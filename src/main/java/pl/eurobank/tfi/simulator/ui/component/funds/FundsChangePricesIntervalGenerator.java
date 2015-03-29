package pl.eurobank.tfi.simulator.ui.component.funds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.eurobank.tfi.component.investmentfund.domain.InvestmentFundInterface;
import pl.eurobank.tfi.component.investmentfund.repository.InvestmentFundPriceRepositoryInterface;
import pl.eurobank.tfi.component.investmentfund.repository.InvestmentFundRepositoryInterface;
import pl.eurobank.tfi.component.money.domain.PriceInterface;
import pl.eurobank.tfi.simulator.investmentfundpricegenerator.InvestmentFundPriceGeneratorInterface;

import java.util.HashMap;
import java.util.Map;

public class FundsChangePricesIntervalGenerator extends Thread {

    protected InvestmentFundPriceRepositoryInterface investmentFundPriceRepository;

    protected long interval;

    public Map<InvestmentFundInterface, InvestmentFundPriceGeneratorInterface> generators;

    public FundsChangePricesIntervalGenerator(long interval, InvestmentFundPriceRepositoryInterface investmentFundPriceRepository) {
        generators = new HashMap<>();
        this.interval = interval;
        this.investmentFundPriceRepository = investmentFundPriceRepository;
    }

    public long getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public void updateAll() {
        for (Map.Entry<InvestmentFundInterface, InvestmentFundPriceGeneratorInterface> entry : generators.entrySet()) {
            InvestmentFundInterface fund = entry.getKey();
            InvestmentFundPriceGeneratorInterface generator = entry.getValue();

            PriceInterface new_price = generator.getFundPrice(fund);
            investmentFundPriceRepository.changePrice(fund, new_price);
        }
    }

    @Override
    public void run() {
        while(true) {
            updateAll();

            if (interval <= 0) {
                break;
            }
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
            }
        }
    }
}
