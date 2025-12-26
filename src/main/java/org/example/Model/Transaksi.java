package org.example.Model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaksi {
    private String idTransaksi;
    private String nama;
    private int qty;
    private double total;
    private String waktu;

    public Transaksi(String idTransaksi, String nama, int qty, double total) {
        this.idTransaksi = idTransaksi;
        this.nama = nama;
        this.qty = qty;
        this.total = total;
        this.waktu = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String toCSV() {
        return idTransaksi + ";" + waktu + ";" + nama + ";" + qty + ";" + total;
    }
}