package org.example.Page;

import org.example.Data.FileHandler;
import org.example.MainFrame;
import org.example.Model.Produk;
import org.example.Model.Transaksi;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class Pemesanan extends JPanel {
    private JComboBox<String> cbProduk;
    private JTextField txtQty;
    private JTable table;
    private DefaultTableModel model;
    private JLabel lblTotal;
    private ArrayList<Produk> listProduk;
    private double totalHarga = 0;

    public Pemesanan(MainFrame frame) {
        setBackground(new Color(51, 36, 33));
        setLayout(new BorderLayout(15, 15));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel lblTitle = new JLabel("CASHIER SYSTEM");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(Color.WHITE);
        add(lblTitle, BorderLayout.NORTH);

        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 15);

        cbProduk = new JComboBox<>();
        cbProduk.setPreferredSize(new Dimension(180, 30));

        txtQty = new JTextField();
        txtQty.setPreferredSize(new Dimension(180, 30));

        JButton btnAdd = createLoginStyleButton("TAMBAH");
        JButton btnDelete = createLoginStyleButton("HAPUS ITEM");
        JButton btnFinish = createLoginStyleButton("SELESAI TRANSAKSI");

        gbc.gridx = 0; gbc.gridy = 0; leftPanel.add(new JLabel("Menu:") {{ setForeground(Color.LIGHT_GRAY); }}, gbc);
        gbc.gridy = 1; leftPanel.add(cbProduk, gbc);
        gbc.gridy = 2; leftPanel.add(new JLabel("Kuantitas:") {{ setForeground(Color.LIGHT_GRAY); }}, gbc);
        gbc.gridy = 3; leftPanel.add(txtQty, gbc);

        gbc.insets = new Insets(20, 0, 5, 15);
        gbc.gridy = 4; leftPanel.add(btnAdd, gbc);

        gbc.insets = new Insets(5, 0, 5, 15);
        gbc.gridy = 5; leftPanel.add(btnDelete, gbc);
        gbc.gridy = 6; leftPanel.add(btnFinish, gbc);
        add(leftPanel, BorderLayout.WEST);

        model = new DefaultTableModel(new String[]{"ITEM", "HARGA", "QTY", "SUBTOTAL"}, 0);
        table = new JTable(model);
        table.setRowHeight(25);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);
        lblTotal = new JLabel("TOTAL: Rp 0");
        lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTotal.setForeground(new Color(255, 204, 102));

        JButton btnBack = new JButton("KEMBALI");
        btnBack.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnBack.setBackground(new Color(183, 28, 28));
        btnBack.setForeground(Color.WHITE);
        btnBack.setFocusPainted(false);
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBack.setPreferredSize(new Dimension(120, 35));
        btnBack.addActionListener(e -> frame.switchPage("Dashboard"));

        bottomPanel.add(lblTotal, BorderLayout.WEST);
        bottomPanel.add(btnBack, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);

        btnAdd.addActionListener(e -> tambahItem());
        btnDelete.addActionListener(e -> hapusItem());
        btnFinish.addActionListener(e -> selesaiTransaksi());
    }


    private JButton createLoginStyleButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setBackground(new Color(245, 245, 235));
        btn.setForeground(Color.BLACK);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(180, 40));
        return btn;
    }

    private void tambahItem() {
        try {
            int idx = cbProduk.getSelectedIndex();
            if (idx <= 0 || txtQty.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Pilih menu dan isi kuantitas!");
                return;
            }

            int qty = Integer.parseInt(txtQty.getText());
            Produk p = listProduk.get(idx - 1);
            double sub = p.getHarga() * qty;

            model.addRow(new Object[]{p.getNama(), p.getHarga(), qty, sub});
            totalHarga += sub;
            lblTotal.setText("TOTAL: Rp " + (int)totalHarga);

            cbProduk.setSelectedIndex(0);
            txtQty.setText("");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Input Qty harus angka!");
        }
    }

    private void hapusItem() {
        int r = table.getSelectedRow();
        if (r != -1) {
            totalHarga -= Double.parseDouble(model.getValueAt(r, 3).toString());
            lblTotal.setText("TOTAL: Rp " + (int)totalHarga);
            model.removeRow(r);
        } else {
            JOptionPane.showMessageDialog(this, "Pilih item di tabel untuk dihapus!");
        }
    }

    private void selesaiTransaksi() {
        if (model.getRowCount() > 0) {
            String idOrder = "TR-" + (System.currentTimeMillis() / 1000);

            for (int i = 0; i < model.getRowCount(); i++) {
                String nama = model.getValueAt(i, 0).toString();
                int qty = Integer.parseInt(model.getValueAt(i, 2).toString());
                double sub = Double.parseDouble(model.getValueAt(i, 3).toString());

                org.example.Model.Transaksi t = new org.example.Model.Transaksi(idOrder, nama, qty, sub);
                FileHandler.simpanRiwayat(t.toCSV());
            }

            JOptionPane.showMessageDialog(this, "Transaksi Berhasill!\nID: " + idOrder);

            model.setRowCount(0);
            totalHarga = 0;
            lblTotal.setText("TOTAL: Rp 0");
            cbProduk.setSelectedIndex(0);
            txtQty.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Keranjang kosong!");
        }
    }

    public void refreshComboBox() {
        listProduk = FileHandler.bacaProduk();
        cbProduk.removeAllItems();
        cbProduk.addItem("-- Pilih Menu --");
        for (Produk p : listProduk) {
            cbProduk.addItem(p.getNama() + " (Rp " + (int)p.getHarga() + ")");
        }
        cbProduk.setSelectedIndex(0);
    }
}