package pl.eurobank.tfi.component.investmentfund.repository;

import pl.eurobank.tfi.component.investmentfund.domain.InvestmentFundInterface;

import java.util.Map;

/**
 * The repository of InvestmentFundInterface objects. Provides access to
 * gather objects from abstract data source.
 *
 */
public interface InvestmentFundRepositoryInterface {

    /**
     * Get investment fund by identifier.
     *
     * @param id identifier
     * @return investment fund or NULL
     */
    InvestmentFundInterface getById(Object id);

    /**
     * Get investment fund list by specified parameters
     *
     * @param params filters, sorting options
     * @return investment funds list
     */
    InvestmentFundInterface[] getList(Map<String, String> params);

    /**
     * Saves data about investment fund.
     *
     * @param investmentFund investment fund
     * @return saved investment fund object
     */
    InvestmentFundInterface save(InvestmentFundInterface investmentFund);
}
