package org.example.Page;

import org.example.Data.FileHandler;
import org.example.MainFrame;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class Riwayat extends JPanel {
    private JTable table;
    private DefaultTableModel model;

    public Riwayat(MainFrame frame) {
        setBackground(new Color(51, 36, 33));
        setLayout(new BorderLayout(15, 15));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel lblTitle = new JLabel("LAPORAN RIWAYAT PENJUALAN", JLabel.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(Color.WHITE);
        add(lblTitle, BorderLayout.NORTH);

        model = new DefaultTableModel(new String[]{"ID Transaksi", "Waktu", "Menu", "Qty", "Total (Rp)"}, 0);
        table = new JTable(model);
        table.setRowHeight(25);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton btnBack = new JButton("KEMBALI KE DASHBOARD");
        btnBack.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnBack.setBackground(new Color(183, 28, 28));
        btnBack.setForeground(Color.WHITE);
        btnBack.setFocusPainted(false);
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBack.setPreferredSize(new Dimension(200, 35));
        btnBack.addActionListener(e -> frame.switchPage("Dashboard"));
        add(btnBack, BorderLayout.SOUTH);
    }

    public void refreshTable() {
        model.setRowCount(0);
        ArrayList<String[]> dataRiwayat = FileHandler.bacaRiwayat();
        if (dataRiwayat != null) {
            dataRiwayat.sort((a, b) -> b[1].compareTo(a[1]));
            for (String[] baris : dataRiwayat) {
                if (baris.length >= 5) {
                    double total = Double.parseDouble(baris[4]);
                    baris[4] = String.valueOf((int) total);
                }
                model.addRow(baris);
            }
        }
    }
}

