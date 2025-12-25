package org.example.Page;

import org.example.MainFrame;
import javax.swing.*;
import java.awt.*;

public class Dashboard extends JPanel {
    public Dashboard(MainFrame frame) {
        setLayout(new GridBagLayout());
        setBackground(new Color(51, 36, 33));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitle = new JLabel("COFFEE SHOP SYSTEM", JLabel.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(Color.WHITE);
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 30, 0);
        add(lblTitle, gbc);

        Color warnaKrem = new Color(240, 240, 240);
        Color warnaTeks = new Color(51, 36, 33);
        gbc.insets = new Insets(5, 0, 5, 0);

        JButton btnBarang = createStyledButton("Kelola Data Barang", warnaKrem, warnaTeks);
        JButton btnPesan = createStyledButton("Transaksi Pemesanan", warnaKrem, warnaTeks);
        JButton btnRiwayat = createStyledButton("Laporan Riwayat", warnaKrem, warnaTeks);
        JButton btnLogout = createStyledButton("Logout", new Color(183, 28, 28), Color.WHITE);

        btnBarang.addActionListener(e -> frame.switchPage("DataBarang"));
        btnPesan.addActionListener(e -> frame.switchPage("Pemesanan"));
        btnRiwayat.addActionListener(e -> frame.switchPage("Riwayat"));
        btnLogout.addActionListener(e -> {
            int n = JOptionPane.showConfirmDialog(this, "Yakin Logout?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if(n == 0) frame.switchPage("Login");
        });

        gbc.gridy = 1; add(btnBarang, gbc);
        gbc.gridy = 2; add(btnPesan, gbc);
        gbc.gridy = 3; add(btnRiwayat, gbc);
        gbc.insets = new Insets(20, 0, 5, 0);
        gbc.gridy = 4; add(btnLogout, gbc);
    }

    private JButton createStyledButton(String text, Color bg, Color fg) {
        JButton btn = new JButton(text);
        btn.setPreferredSize(new Dimension(300, 45));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFocusPainted(false);
        return btn;
    }
}