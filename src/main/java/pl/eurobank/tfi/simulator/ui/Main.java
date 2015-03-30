package pl.eurobank.tfi.simulator.ui;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import pl.eurobank.tfi.component.investmentfund.domain.event.InvestmentFundPricingChangesEvent;
import pl.eurobank.tfi.component.investmentfundwallet.domain.InvestmentWalletInterface;
import pl.eurobank.tfi.simulator.ui.component.funds.FundsChangePricesIntervalGenerator;
import pl.eurobank.tfi.simulator.ui.component.funds.FundsTableFactory;

import javax.swing.*;
import java.awt.*;

@Component
public class Main {

    @Autowired
    EventBus eventBus;

    @Autowired
    InvestmentWalletInterface investmentWallet;

    @Autowired
    FundsTableFactory fundsTableFactory;

    @Autowired
    FundsChangePricesIntervalGenerator fundsChangePricesIntervalGenerator;

    public void run() {
        eventBus.register(this);

        JFrame frame = new JFrame("CFI Simulator");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel emptyLabel = new JLabel("a");



        frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);

        JTable fundsTable = fundsTableFactory.createTable();
        fundsTableFactory.reloadData();
        frame.getContentPane().add(fundsTable.getTableHeader(), BorderLayout.PAGE_START);
        frame.getContentPane().add(fundsTable);

        frame.pack();

        frame.setVisible(true);

        fundsChangePricesIntervalGenerator.setInterval(10000);
        fundsChangePricesIntervalGenerator.setInterval(1000);
        fundsChangePricesIntervalGenerator.start();

    }

    @Subscribe
    public void recordFundPriceChange(InvestmentFundPricingChangesEvent event) {
        fundsTableFactory.reloadData();
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfiguration.class);
        Main app = context.getBean(Main.class);

        app.run();

        context.close();
    }
}
