package org.example.Model;

import java.time.LocalDate;

public class Transaksi {
    private String nama;
    private int qty;
    private double total;
    private LocalDate tanggal;

    public Transaksi(String nama, int qty, double total) {
        this.nama = nama; this.qty = qty; this.total = total; this.tanggal = LocalDate.now();
    }
    public String toCSV() { return tanggal + "," + nama + "," + qty + "," + total; }
    public double getTotalHarga() { return total; }
    public Object[] toArray() { return new Object[]{tanggal, nama, qty, total}; }
}
