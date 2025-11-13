# Soot Fallout
> The Sunshine is just a memory.

---

## 1. Latar Belakang & Plot

**Soot Fallout** adalah Mini RPG Game dengan gaya Turn Based yang berbasis konsol/CLI yang bertemakan *post-apocalypse*.
<!-- dan berakar pada realisme ilmiah -->

Berlatar pada tahun 2277, bencana ini dikenal sebagai "The Great Dimming". Yang merupakan konsekuensi dari aktivitas industri global dan pembakaran batu bara selama ratusan tahun beroperasi secara masif tanpa mengendalikan keluaran jelaga mereka dan mengabaikan penggunaan energi alternatif/hijau terbarukan. Emisi tak terkendali (Karbon Hitam/PM2.5) ini akhirnya terakumulasi dan menciptakan "payung/kubah" partikel permanen di lapisan atmosfer, yang menghalangi sebagian besar sinar matahari. Akibatnya, proses fotosintesis global tidak lagi berjalan, membunuh semua tanaman di permukaan dan meruntuhkan rantai makanan. Udara di permukaan kini dipenuhi partikulat yang mematikan.

Manusia bertahan hidup di **Caldera Bunker**, sebuah pemukiman bawah tanah mandiri yang ditenagai oleh Inti Geotermal yang merupakan satu-satunya sumber panas dan listrik yang tersisa.

**Plot Utama:** Inti Geotermal di bunker mulai tidak berfungsi dengan semestinya. Anda, seorang `Thermo-Scavenger`, ditugaskan untuk melakukan perjalanan berbahaya ke "Stasiun Pembangkit Cadangan" yang terbengkalai untuk menjarah 4 komponen vital dan menyelamatkan bunker.

---

## 2. Fitur Gameplay

* **RPG Berbasis Konsol:** Seluruh interaksi dilakukan melalui input teks.
* **Pertarungan Turn-Based:** Sistem pertarungan klasik melawan 4 musuh kunci (2 Minion, 1 Elite, 1 Boss).
* **Alur Cerita Linear:** Alur cerita memiliki progres yang jelas (Mulai -> Misi -> Menang/Kalah).
* **Sistem Leveling:** Dapatkan EXP untuk naik level, meningkatkan HP dan Attack.
* **Inventory Fungsional:**
    * **Equip/Unequip:** Player dapat memakai (`Weapon`, `Armor`) dan melepas item.
    * **Stat Dinamis:** Status `totalAttack` dan `totalDefense` Player akan ter-update secara otomatis.
    * **Consumables:** Player dapat menggunakan item (seperti `Dirty Bandage`) untuk memulihkan HP.
* **Ending:** Permainan memiliki 2 akhir cerita (Menang/Kalah) berdasarkan keberhasilan misi, termasuk pesan moral/lingkungan di akhir.

---

## 3. Cara Menjalankan Program

Proyek ini tidak memerlukan Maven/Gradle. Cukup gunakan `javac` dan `java` dari terminal.

1.  Pastikan Anda memiliki Java (JDK) 11 atau lebih baru terinstal dan terkonfigurasi di `PATH` Anda.
2.  Buka terminal/command prompt di folder *root* proyek (`SootFallout/`).
3.  Buat direktori baru untuk menyimpan file `.class` (file yang sudah dikompilasi):
    ```bash
    mkdir bin
    ```
4.  Kompilasi semua file `.java` dari `src` ke dalam folder `bin`.
    
    *(note: Gunakan `/` untuk macOS/Linux dan `\` untuk Windows sebagai pemisah folder)*
   
    ```bash
    # Untuk macOS/Linux:
    javac -d bin src/core/*.java src/models/*.java src/models/items/*.java src/utils/*.java
    
    # Untuk Windows:
    javac -d bin src\core\*.java src\models\*.java src\models\items\*.java src\utils\*.java
    ```
5.  Jalankan program dari *root*
  
    ```bash
    java -cp bin core.GameManager
    ```

---

## 4. Struktur Proyek

Struktur proyek disederhanakan untuk kompilasi manual, namun tetap mempertahankan paket modular.

```
SootFallout/
├── bin/ (tempat hasil kompilasi)
├── docs/
│   └── DiagramClass.png
├── src/                   
│   ├── core/
│   │   └── GameManager.java
│   ├── models/
│   │   ├── items/
│   │   │   ├── Item.java (Abstract)
│   │   │   ├── Weapon.java
│   │   │   ├── Armor.java
│   │   │   ├── Consumable.java
│   │   │   └── QuestItem.java
|   |   ├── monsters/
│   │   │   ├── Monster.java
│   │   │   └── BossMonster.java
│   │   ├──Player.java
│   │   └── BossMonster.java
│   └── utils/
│       ├── InputHandler.java
│       └── Narrator.java
└── README.md
```

---

## 5. Implementasi Konsep OOP

Proyek ini dirancang secara spesifik untuk memenuhi semua persyaratan minimal OOP dari instruksi tugas mini project:

* **Class & Object:** Menggunakan 12 *class* yang saling berinteraksi (jauh di atas minimal 3+1), termasuk `GameManager`, `Player`, `Monster`, `Item`, `InputHandler`, `Narrator`, dll.

* **Inheritance (Pewarisan):**
    * `Player` dan `Monster` mewarisi (`extends`) dari `Entity`.
    * `BossMonster` mewarisi (`extends`) dari `Monster` (contoh *multilevel inheritance*).
    * `Weapon`, `Armor`, `Consumable`, dan `QuestItem` mewarisi (`extends`) dari `Item`.

* **Polymorphism (Polimorfisme):**
    * **Method Overriding:** Method `serang()` di `Entity` adalah `abstract`, dan di-implementasikan secara berbeda oleh `Player`, `Monster`, dan `BossMonster`.
    * **Tipe Objek:** `List<Item>` di `Player` dapat menampung berbagai *subclass* dari `Item` (misal: `Weapon`, `Consumable`).

* **Abstract Class:**
    * Menggunakan 2 *abstract class*: `Entity` (untuk kerangka makhluk hidup) dan `Item` (untuk kerangka item).

* **Encapsulation (Enkapsulasi):**
    * Semua atribut di *class models* (seperti `private int hp` di `Entity` atau `private int attackBonus` di `Weapon`) di-set sebagai `private`.
    * Akses ke atribut diatur secara ketat melalui *method* publik (`getter` dan `setter` publik).

* **Association (Asosiasi):**
    * `Player` "HAS-A" `List<Item>` (Inventory).
    * `GameManager` "HAS-A" `Player`.
    * `GameManager` "HAS-A" `InputHandler` dan `Narrator`.
    * `Monster` "HAS-A" `Item` (sebagai `itemDrop`).


