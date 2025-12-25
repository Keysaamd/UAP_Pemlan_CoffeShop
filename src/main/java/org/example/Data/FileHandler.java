package org.example.Data;

import java.io.*;
import java.util.ArrayList;

public class FileHandler {
    private static final String FILE_PRODUK = "produk.txt";
    private static final String FILE_RIWAYAT = "riwayat.txt";

    public static void simpanProduk(ArrayList<org.example.Model.Produk> list) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_PRODUK))) {
            for (org.example.Model.Produk p : list) pw.println(p.getId() + ";" + p.getNama() + ";" + p.getHarga());
        } catch (IOException e) { e.printStackTrace(); }
    }

    public static ArrayList<org.example.Model.Produk> bacaProduk() {
        ArrayList<org.example.Model.Produk> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PRODUK))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(";");
                list.add(new org.example.Model.Produk(d[0], d[1], Double.parseDouble(d[2])));
            }
        } catch (Exception e) {}
        return list;
    }

    // FUNGSI BARU: Simpan Riwayat
    public static void simpanRiwayat(String data) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_RIWAYAT, true))) {
            pw.println(data);
        } catch (IOException e) { e.printStackTrace(); }
    }

    // FUNGSI BARU: Baca Riwayat
    public static ArrayList<String[]> bacaRiwayat() {
        ArrayList<String[]> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_RIWAYAT))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line.split(";"));
            }
        } catch (Exception e) {}
        return list;
    }
}