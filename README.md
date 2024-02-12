# Tugas Kecil Strategi Algoritma IF2211_01

## Daftar Isi

- [Tugas Kecil Strategi Algoritma IF2211_01](#tugas-kecil-strategi-algoritma-if2211_01)
  - [Daftar Isi](#daftar-isi)
  - [Cyberpunk 2077 Breach Protocol](#cyberpunk-2077-breach-protocol)
  - [Penjelasan Permasalahan](#penjelasan-permasalahan)
  - [Jenis Algoritma](#jenis-algoritma)
  - [Struktur Program](#struktur-program)
  - [Cara Jalan Program](#cara-jalan-program)
  - [Libraries Used](#libraries-used)

## Cyberpunk 2077 Breach Protocol

Dibuat oleh Marvin Scifo Yehezkiel Hutahaean (13522110) sebagai pemenuhan komponen Tugas Kecil 1 dari Strategi Algoritma IF2211


## Deskripsi Permasalahan

Cyberpunk 2077 Breach Protocol adalah sebuah minigame yang melibatkan 1 matriks dan beberapa sekuens token yang berisi poin. Pemain harus memilih token-token tertentu agar terdapat kecocokan dengan sekuens-sekuens token yang ada. Pertama, pemain akan memilih token dari baris yang paling atas lalu pemain akan disuruh memilih token kedua dari kolom dari token yang pemain pilih dan seterusnya. Jika token yang dipilih oleh pemain ada kecocokan dengan sekuens-sekuens token, pemain akan mendapatkan poin sesuai dengan poin yang dimiliki oleh sekuens-sekuens token tersebut.

Untuk mempermudah permainan, sebuah program dibuat untuk memberikan solusi paling optimal untuk menyelesaikan permaslahan dari Breach Protocol ini dengan menggunakan Algoritma Brute Force.

## Algoritma yang digunakan

Pencarian solusi Breach protocol menggunakan Algoritma Brute Force. Ini artinya semua elemen akan ditelusuri untuk dicari kombinasi solusinya. Beginilah gambaran dari Brute Force-nya


1. Program akan mulai dengan memproses elemen yang paling awal lalu ditambahkan ke list dalam bentuk koordinat. Lalu program akan memproses elemen selanjutnya secara vertikal. Elemen tersebut akan dimasukkan ke list berisi koordinat lalu program akan memproses elemen selanjutnya secara horizontal. Perlu diketahui bahwa jika terdapat koordinat yang sama dengan yang ada list, elemen tersebut akan
dihindari dan program akan lanjut melakukan iterasi ke elemen yang lain.
2. Jika list sudah penuh dan elemen terakhir sudah mencapai ambang matriks (misalnya elemen 6,6), program akan menghapus elemen terakhir tersebut dan jika masih ada elemen terakhir yang mencapai ambang, program akan menghapusnya lagi. Setelah itu, program akan melakukan backtrack untuk mencari kombinasi lain dan iterasi yang baru akan dimulai.
3. Semua kemungkinan list yang ada akan dimasukkan ke dalam sebuah list (list berisi list). Setelah semuanya selesai, list berisi list tersebut akan berisi semua kemungkinan list koordinat yang akan dikonversi menjadi token di matriks (koordinatnya (1,1) maka akan dikonversi menjadi token elemen (0,0)).
4. List akan diproses dan dicari solusi yang paling optimal untuk diberi informasinya ke terminal.

## Struktur Program

```
│ .gitignore
│ README.md
├─── bin
│       │ Driver.class
│       │ FileProcess.class
│       │ ListDyn.class
│       │ ListListDyn.class
│       │ ListString.class
│       │ Matrix.class
│       │ Point.class
│
├─── doc
│       │ Tucil1_K1_135522110_MarvinScifoYHutahaean.pdf
│
├─── src
│       │ Driver.java
│       │ FileProcess.java
│       │ ListDyn.java
│       │ ListListDyn.java
│       │ ListString.java
│       │ Matrix.java
│       │ Point.java
│
├─── test
        │ output-output dan file input juga ada untuk pemeriksaan
```

## Menjalankan Program

1. Buka *root directory* (Directory Tucil Stima)
2. Masukkan *cd bin*
3. Masukkan *java Driver* pada directory bin tersebut

Contoh:

D:\file\file2\Tucil_Stima_IF2211_01> cd bin

D:\file\file2\Tucil_Stime_IF2211_01\bin> java Driver

## Libraries Used

1. java.util.* (Java)
2. java.io.* (Java)
