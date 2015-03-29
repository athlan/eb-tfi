package pl.eurobank.tfi.component.investmentfund.repository;

import pl.eurobank.tfi.component.investmentfund.domain.InvestmentFundInterface;

import java.util.Map;

public interface InvestmentFundRepositoryInterface {

    InvestmentFundInterface getById(Object id);

    InvestmentFundInterface[] getList(Map<String, String> params);

    InvestmentFundInterface save(InvestmentFundInterface investmentFund);
}
