package pl.eurobank.tfi.simulator.ui.component.funds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class FundsTableFactory {

    @Autowired
    private FundsTableModel tableModel;

    public JTable createTable() {
        JTable table = new JTable(tableModel);

        return table;
    }

    public void reloadData() {
        tableModel.fetchData();
    }
}
