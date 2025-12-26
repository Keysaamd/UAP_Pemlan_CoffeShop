package org.example.Page;

import org.example.Data.FileHandler;
import org.example.MainFrame;
import org.example.Model.Produk;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Comparator;

public class DataBarang extends JPanel {
    private JTable table;
    private DefaultTableModel model;
    private ArrayList<Produk> list;
    private JTextField txtSearch;

    public DataBarang(MainFrame frame) {
        setBackground(new Color(51, 36, 33));
        setLayout(new BorderLayout(15, 15));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        list = FileHandler.bacaProduk();

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setOpaque(false);
        JLabel lblS = new JLabel("Cari Nama Menu: "); lblS.setForeground(Color.WHITE);
        txtSearch = new JTextField(20);
        topPanel.add(lblS); topPanel.add(txtSearch);
        add(topPanel, BorderLayout.NORTH);

        model = new DefaultTableModel(new String[]{"ID", "Nama", "Harga (Rp)"}, 0);
        table = new JTable(model);
        table.setRowHeight(25);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel southPanel = new JPanel(new GridBagLayout());
        southPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();

        JPanel form = new JPanel(new GridBagLayout());
        form.setOpaque(false);
        form.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), "Manajemen Menu", 0, 0, new Font("Segoe UI", Font.BOLD, 12), Color.WHITE));

        GridBagConstraints fGbc = new GridBagConstraints();
        fGbc.insets = new Insets(8, 15, 8, 15);

        JLabel lbN = new JLabel("Nama:"); lbN.setForeground(Color.WHITE);
        JLabel lbH = new JLabel("Harga:"); lbH.setForeground(Color.WHITE);
        JTextField tNama = new JTextField(); tNama.setPreferredSize(new Dimension(200, 28));
        JTextField tHarga = new JTextField(); tHarga.setPreferredSize(new Dimension(200, 28));

        fGbc.gridx = 0; fGbc.gridy = 0; fGbc.anchor = GridBagConstraints.WEST; form.add(lbN, fGbc);
        fGbc.gridx = 1; fGbc.weightx = 1.0; fGbc.fill = 2; form.add(tNama, fGbc);
        fGbc.gridx = 0; fGbc.gridy = 1; fGbc.weightx = 0; fGbc.fill = 0; form.add(lbH, fGbc);
        fGbc.gridx = 1; fGbc.weightx = 1.0; fGbc.fill = 2; form.add(tHarga, fGbc);

        JPanel btnP = new JPanel(new FlowLayout(1, 15, 0));
        btnP.setOpaque(false);

        JButton bAdd = createBtn("SIMPAN", new Color(46, 125, 50), Color.WHITE);
        JButton bDel = createBtn("HAPUS", new Color(211, 47, 47), Color.WHITE);
        JButton bBack = createBtn("KEMBALI", new Color(100, 100, 100), Color.WHITE);

        btnP.add(bAdd); btnP.add(bDel); btnP.add(bBack);

        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1.0; gbc.fill = 2; southPanel.add(form, gbc);
        gbc.gridy = 1; gbc.insets = new Insets(15, 0, 0, 0); gbc.fill = 0; southPanel.add(btnP, gbc);
        add(southPanel, BorderLayout.SOUTH);

        txtSearch.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) { refreshTable(txtSearch.getText()); }
        });

        bAdd.addActionListener(e -> {
            try {
                if(tNama.getText().isEmpty()) return;
                double h = Double.parseDouble(tHarga.getText());
                list.add(new Produk("P" + (list.size() + 1), tNama.getText(), h));
                FileHandler.simpanProduk(list);
                refreshTable(""); tNama.setText(""); tHarga.setText("");
            } catch (Exception ex) { JOptionPane.showMessageDialog(this, "Input harga salah!"); }
        });

        bDel.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                int confirm = JOptionPane.showConfirmDialog(this, "Hapus menu ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    String namaDiTabel = model.getValueAt(row, 1).toString();
                    list.removeIf(p -> p.getNama().equals(namaDiTabel));
                    FileHandler.simpanProduk(list);
                    refreshTable("");
                }
            }
        });

        bBack.addActionListener(e -> frame.switchPage("Dashboard"));
        refreshTable("");
    }

    private JButton createBtn(String t, Color bg, Color fg) {
        JButton b = new JButton(t); b.setPreferredSize(new Dimension(110, 30));
        b.setBackground(bg); b.setForeground(fg); b.setFont(new Font("Segoe UI", Font.BOLD, 12));
        b.setFocusPainted(false);
        return b;
    }

    private void refreshTable(String query) {
        model.setRowCount(0);

        list.sort((p1, p2) -> Double.compare(p1.getHarga(), p2.getHarga()));

        for (Produk p : list) {
            if (p.getNama().toLowerCase().contains(query.toLowerCase())) {
                model.addRow(new Object[]{p.getId(), p.getNama(), p.getHarga()});
            }
        }
    }
}