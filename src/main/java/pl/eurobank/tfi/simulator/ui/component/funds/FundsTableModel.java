package pl.eurobank.tfi.simulator.ui.component.funds;

import org.springframework.beans.factory.annotation.Autowired;
import pl.eurobank.tfi.component.investmentfund.domain.InvestmentFundInterface;
import pl.eurobank.tfi.component.investmentfund.repository.InvestmentFundRepositoryInterface;

import javax.swing.table.AbstractTableModel;

public class FundsTableModel extends AbstractTableModel {

    @Autowired
    private InvestmentFundRepositoryInterface investmentFundRepository;

    private InvestmentFundInterface[] data;

    public void fetchData() {
        data = investmentFundRepository.getList(null);
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(0 == columnIndex) {
            return data[rowIndex].getName();
        }
        if(1 == columnIndex) {
            return data[rowIndex].getCurrentPricing();
        }

        return null;
    }
}
