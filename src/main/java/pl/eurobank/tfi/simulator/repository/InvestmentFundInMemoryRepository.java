package pl.eurobank.tfi.simulator.repository;

import pl.eurobank.tfi.component.investmentfund.domain.InvestmentFundInterface;
import pl.eurobank.tfi.component.investmentfund.repository.InvestmentFundRepositoryInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * In-memory implementation of investment fund repository.
 *
 * The repository of InvestmentFundInterface objects. Provides access to
 * gather objects from abstract data source.
 *
 */
public class InvestmentFundInMemoryRepository implements InvestmentFundRepositoryInterface {

    private List<InvestmentFundInterface> storage;

    /**
     * Creates the repository.
     *
     */
    public InvestmentFundInMemoryRepository() {
        this.storage = new ArrayList<>();
    }

    /**
     * Get investment fund by identifier.
     *
     * @param id
     * @return investment fund or NULL
     */
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

    /**
     * Get investment fund list by specified parameters
     *
     * @param params filters, sorting options
     * @return investment funds list
     */
    @Override
    public InvestmentFundInterface[] getList(Map<String, String> params) {
        return storage.toArray(new InvestmentFundInterface[storage.size()]);
    }

    /**
     * Saves data about investment fund.
     *
     * @param investmentFund
     * @return saved investment fund object
     */
    @Override
    public InvestmentFundInterface save(InvestmentFundInterface investmentFund) {
        if(!storage.contains(investmentFund)) {
            storage.add(investmentFund);
        }

        return investmentFund;
    }
}
