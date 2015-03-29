package pl.eurobank.tfi.simulator.ui.component.funds;

import org.springframework.beans.factory.annotation.Autowired;
import pl.eurobank.tfi.component.investmentfund.domain.InvestmentFundInterface;
import pl.eurobank.tfi.component.investmentfund.repository.InvestmentFundRepositoryInterface;

import javax.swing.table.AbstractTableModel;

public class FundsTableModel extends AbstractTableModel {

    @Autowired
    private InvestmentFundRepositoryInterface investmentFundRepository;

    private InvestmentFundInterface[] data;

    private String[] columnNames = new String[] {"Fund name", "Current price", "Actions"};

    public void fetchData() {
        data = investmentFundRepository.getList(null);
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
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
