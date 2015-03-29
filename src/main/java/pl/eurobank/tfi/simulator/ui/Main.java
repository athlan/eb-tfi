package pl.eurobank.tfi.simulator.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import pl.eurobank.tfi.component.investmentfundwallet.domain.InvestmentWalletInterface;
import pl.eurobank.tfi.simulator.ui.component.funds.FundsTableFactory;

import javax.swing.*;
import java.awt.*;

@Component
public class Main {

    @Autowired
    InvestmentWalletInterface investmentWallet;

    @Autowired
    FundsTableFactory fundsTableFactory;

    public void run() {
        JFrame frame = new JFrame("CFI Simulator");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel emptyLabel = new JLabel("a");

        frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);

        JTable fundsTable = fundsTableFactory.createTable();
        fundsTableFactory.reloadData();
        frame.getContentPane().add(fundsTable);

        frame.pack();

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfiguration.class);
        Main app = context.getBean(Main.class);

        app.run();

        context.close();
    }
}
