package pl.eurobank.tfi.simulator.repository;

import pl.eurobank.tfi.component.investmentfund.domain.InvestmentFundInterface;
import pl.eurobank.tfi.component.investmentfund.repository.InvestmentFundRepositoryInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InvestmentFundInMemoryRepository implements InvestmentFundRepositoryInterface {

    private List<InvestmentFundInterface> storage;

    public InvestmentFundInMemoryRepository() {
        this.storage = new ArrayList<>();
    }

    @Override
    public InvestmentFundInterface getById(Object id) {
        if(!(id instanceof String)) {
            return null;
        }

        for (InvestmentFundInterface item : storage) {
            if(item.getName().equals(id)) {
                return item;
            }
        }

        return null;
    }

    @Override
    public InvestmentFundInterface[] getList(Map<String, String> params) {
        return storage.toArray(new InvestmentFundInterface[storage.size()]);
    }

    @Override
    public InvestmentFundInterface save(InvestmentFundInterface investmentFund) {
        if(!storage.contains(investmentFund)) {
            storage.add(investmentFund);
        }

        return investmentFund;
    }
}
