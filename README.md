# Sistem Kasir Coffee Shop - UAP Pemrograman Lanjut

Aplikasi sistem kasir digital bertema Coffee Shop yang dibangun menggunakan **Java Swing**. Program ini dirancang dengan arsitektur **Multi-Page** dan menerapkan konsep **Object-Oriented Programming (OOP)** serta manajemen data berbasis **Flat-File Database** (file `.txt`).

## Fitur Utama

1. **Sistem Autentikasi (Login)**: Keamanan akses masuk menggunakan kredensial Admin.
2. **Dashboard Navigasi**: Menu utama yang menghubungkan seluruh modul aplikasi menggunakan `CardLayout`.
3. **Manajemen Data Barang (CRUD & Sorting)**:
* Mengelola menu kopi (Tambah & Hapus).
* **Fitur Sorting**: Daftar menu otomatis diurutkan berdasarkan **Harga Termurah** menggunakan *Comparator*.


4. **Sistem Kasir (Pemesanan)**:
* Input pesanan melalui dropdown menu yang terintegrasi.
* Perhitungan subtotal dan total harga secara otomatis.
* **Auto-Reset**: Dropdown dan kolom jumlah otomatis kembali kosong setelah item ditambahkan ke keranjang.


5. **Laporan Riwayat Penjualan (Sorting & ID Unik)**:
* Pencatatan setiap transaksi menggunakan **ID Transaksi Unik** (berbasis *timestamp*).
* **Fitur Sorting**: Laporan diurutkan berdasarkan **Waktu Terbaru** (detik demi detik).



## Struktur Proyek (Package)

Pemisahan kelas dilakukan berdasarkan fungsinya (Separation of Concerns):

* **`org.example`**: Berisi `MainFrame.java` sebagai kontainer utama aplikasi.
* **`org.example.Page`**: Berisi semua halaman UI (`Login`, `Dashboard`, `DataBarang`, `Pemesanan`, `Riwayat`).
* **`org.example.Model`**: Berisi blueprint data (`Produk.java` dan `Transaksi.java`).
* **`org.example.Data`**: Berisi `FileHandler.java` yang menangani baca/tulis ke file teks.

## Persistensi Data

Aplikasi menggunakan dua file basis data lokal:

* `produk.txt`: Menyimpan daftar menu (ID, Nama, Harga).
* `riwayat.txt`: Menyimpan log transaksi (ID Transaksi, Waktu, Nama Menu, Qty, Total).

## Cara Menjalankan Program

1. **Prasyarat**: Pastikan Anda telah menginstal **JDK 11** atau versi lebih baru.
2. **Clone/Download**: Unduh kode sumber dari repositori ini.
3. **Buka di IDE**: Masukkan folder proyek ke dalam **IntelliJ IDEA** atau IDE Java lainnya.
4. **Build Project**: Biarkan Maven atau IDE memproses dependensi (jika ada).
5. **Run**: Jalankan file `src/main/java/org/example/MainFrame.java`.
6. **Login Default**:
* *Username*: `admin`
* *Password*: `123`



---

**Disusun Oleh:** Keysa & Novi

**Status Proyek:** Selesai (Final UAP Pemlan 2025)

