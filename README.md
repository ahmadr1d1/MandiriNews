# 📱 MandiriNews - Aplikasi Berita Sederhana

MandiriNews adalah aplikasi Android sederhana yang menampilkan daftar berita terkini secara real-time menggunakan API dari [NewsAPI](https://newsapi.org). Aplikasi ini dibuat sebagai bagian dari **Virtual Internship Experience Mobile Apps Developer - Bank Mandiri x Rakamin Academy**.

---

## ✨ Fitur
- Menampilkan daftar berita terbaru secara real-time
- Tampilan daftar berita menggunakan `RecyclerView` dan `ListAdapter`
- Arsitektur bersih menggunakan pola MVVM
- Integrasi API dengan Retrofit
- Format waktu rilis berita yang rapi
- Ukuran ringan dan mudah dijalankan

---

## 🔧 Teknologi yang Digunakan
- **Bahasa**: Kotlin
- **Arsitektur**: MVVM (Model-View-ViewModel)
- **Library**:
  - Retrofit2 + Gson
  - LiveData + ViewModel
  - RecyclerView + ListAdapter
  - View Binding

---

## 📷 Screenshot
| Tampilan Utama | Pesan Fitur Search |
|----------------|---------------|
| *![Screenshot 2025-05-21 224521](https://github.com/user-attachments/assets/e19863b1-ed87-41f8-ac0b-65d6c8b2e1d5)* | *![Screenshot 2025-05-21 224602](https://github.com/user-attachments/assets/b38f72ee-cb5e-4ea2-bd4a-2ae65621b5f4)* |

> 🔍 **Catatan Mengenai Fitur Pencarian**
>
> Untuk fitur pencarian tidak saya lanjutkan karena pada awalnya saya berharap agar user dapat melakukan pencarian terhadap data yang berhasil muncul pada halaman, namun karena endpoint `top-headlines` dan `everything` dari NewsAPI tidak menyediakan mekanisme pencarian lokal dari hasil data yang sudah ditampilkan, dan API ini hanya mendukung pencarian melalui parameter `q` saat permintaan awal (bukan pada data yang telah diambil sebelumnya), maka fitur ini tidak saya lanjutkan. Hal ini saya putuskan agar tetap menjaga kesederhanaan dan efisiensi aplikasi.


---


## 🚀 Cara Menjalankan Aplikasi

1. Clone repositori ini:
```bash
git clone https://github.com/ahmadr1d1/MandiriNews.git
```
2. Buka project dengan Android Studio
3. Dapatkan API key gratis dari [NewsAPI](https://newsapi.org)
4. Tambahkan API key ke dalam file ApiConfig.kt atau melalui file build.gradle root Module :app
5. Jalankan aplikasi di emulator atau physical device android

---


## 📹 Demo Aplikasi 
Tonton demo aplikasi lengkap di sini:
📎 [Link Google Drive](https://drive.google.com/file/d/196Aenz0YNphlQCHDWLVEE6qWOrv2gWtT/view?usp=drive_link)



---
## Terimakasih telah melihat project ini !
