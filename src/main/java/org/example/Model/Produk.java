package org.example.Model;

public class Produk {
    private String id, nama;
    private double harga;

    public Produk(String id, String nama, double harga) {
        this.id = id; this.nama = nama; this.harga = harga;
    }
    public String getId() { return id; }
    public String getNama() { return nama; }
    public double getHarga() { return harga; }
    public String toCSV() { return id + "," + nama + "," + harga; }
}
