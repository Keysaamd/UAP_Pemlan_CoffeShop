package org.example;

import org.example.Page.*;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout);
    private Pemesanan pagePemesanan;
    private Riwayat pageRiwayat;

    public MainFrame() {
        setTitle("Coffee Shop UAP 2025");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        pagePemesanan = new Pemesanan(this);
        pageRiwayat = new Riwayat(this);

        mainPanel.add(new Login(this), "Login");
        mainPanel.add(new Dashboard(this), "Dashboard");
        mainPanel.add(new DataBarang(this), "DataBarang");
        mainPanel.add(pagePemesanan, "Pemesanan");
        mainPanel.add(pageRiwayat, "Riwayat");

        add(mainPanel);
        setVisible(true);
    }

    public void switchPage(String name) {
        if (name.equals("Pemesanan")) {
            pagePemesanan.refreshComboBox();
        } else if (name.equals("Riwayat")) {
            pageRiwayat.refreshTable();
        }
        cardLayout.show(mainPanel, name);
    }

    public static void main(String[] args) { new MainFrame(); }
}