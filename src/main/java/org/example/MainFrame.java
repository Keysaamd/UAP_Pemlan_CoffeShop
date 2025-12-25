package org.example;

import org.example.Page.Dashboard;
import org.example.Page.DataBarang;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout);

    public MainFrame() {
        setTitle("Coffee Shop UAP 2025");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        mainPanel.add(new Dashboard(this), "Dashboard");
        mainPanel.add(new DataBarang(this), "DataBarang");

        add(mainPanel);
        setVisible(true);
    }

    public void switchPage(String name) { cardLayout.show(mainPanel, name); }
    public static void main(String[] args) { new MainFrame(); }
}