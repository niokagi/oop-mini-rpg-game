# Soot Fallout
> The Sunshine is just a memory.

---

## Latar Belakang & Plot

**Soot Fallout** adalah Mini RPG Game dengan gaya Turn Based yang berbasis konsol/CLI yang bertemakan *post-apocalypse*.
<!-- dan berakar pada realisme ilmiah -->

Berlatar pada tahun 2277, bencana ini dikenal sebagai "The Great Dimming". Suatu bentuk konsekuensi nyata dari aktivitas industri global dan pembakaran batu bara, yang selama ratusan tahun beroperasi secara masif tanpa mengendalikan keluaran jelaga mereka dan mengabaikan penggunaan energi alternatif/hijau terbarukan. Emisi tak terkendali (Karbon Hitam/PM2.5) ini akhirnya terakumulasi dan menciptakan "payung/kubah" partikel permanen di lapisan atmosfer, yang menghalangi sebagian besar sinar matahari. Akibatnya, proses fotosintesis global tidak lagi berjalan, membunuh semua tanaman di permukaan dan meruntuhkan rantai makanan. Udara di permukaan kini dipenuhi partikulat yang mematikan.

Manusia bertahan hidup di **Caldera Bunker**, sebuah pemukiman bawah tanah mandiri yang ditenagai oleh Inti Geotermal yang merupakan satu-satunya sumber panas dan listrik yang tersisa.

**Plot Utama:** Inti Geotermal di bunker mulai tidak berfungsi dengan semestinya. Anda, seorang `Thermo-Scavenger`, ditugaskan untuk melakukan perjalanan berbahaya ke "Stasiun Pembangkit Cadangan" yang terbengkalai untuk menjarah 4 komponen vital dan menyelamatkan bunker.

---

## Fitur Gameplay

* **RPG Berbasis Konsol:** Seluruh interaksi dilakukan melalui input teks (memilih opsi).
* **Pertarungan Turn-Based:** Sistem pertarungan taktis dengan 4 opsi: `Serang`, `Gunakan Item` (Hanya *Consumable*), `Heal` (Skill dengan *cooldown*), dan `Kabur`.
* **Alur Cerita Linear:** Alur cerita memiliki progres yang jelas (Mulai => Misi => Menang/Kalah).
* **Sistem Leveling Sederhana:** Mendapat EXP untuk naik level, meningkatkan HP dan Attack.
* **Inventory Fungsional:**
    * **Equip/Unequip:** Player dapat memakai (`Weapon`, `Armor`) dan melepas item.
    * **Stat Dinamis:** Status `totalAttack` dan `totalDefense` Player akan ter-update secara otomatis.
    * **Consumables:** Player dapat menggunakan item (seperti `Dirty Bandage`) untuk memulihkan HP.
* **Ending:** Permainan memiliki 2 akhir cerita (Menang/Kalah) berdasarkan keberhasilan misi.

---

## Cara Menjalankan Program (Metode yang Direkomendasikan)

Proyek ini menggunakan Gradle untuk proses kompilasi, namun memerlukan *script* khusus untuk berjalan dengan benar (agar utilitas *clear console* dapat berfungsi).

1.  Pastikan sudah memiliki Java (JDK) >= 11.
2.  Buka terminal/command prompt di folder *root* proyek (`Soot-Fallout/`).
3.  Jalankan *script batch* kustom:

    **Untuk Windows:**
    ```bash
    .\run-game.bat
    ```

*Script* secara otomatis menggunakan Gradle Wrapper (`gradlew.bat`) untuk mengompilasi semua file `.java` (menggunakan *task* `classes`) kemudian langsung menjalankan game menggunakan perintah `java` standar, yang memungkinkan fitur *clear console* berfungsi.

---

## Struktur Proyek

```
Soot-Fallout/
├── .gradle/               
├── build/                  
├── docs/
│   └── DiagramClass.png 
├── gradle/  
│   └── wrapper/
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
│   │   ├── monsters/
│   │   │   ├── Monster.java
│   │   │   └── BossMonster.java
│   │   ├── Entity.java (Abstract)
│   │   └── Player.java
│   └── utils/
│       ├── AsciiArt.java
│       ├── ConsoleUtils.java
│       ├── InputHandler.java
│       ├── Language.java
│       └── Narrator.java
├── .gitattributes
├── .gitignore
├── build.gradle           
├── gradlew                 
├── gradlew.bat             
├── run.bat        
├── run.sh        
├── settings.gradle        
├── soot-fallout.exe        
└── README.md
```

---

## Implementasi Konsep OOP

* **Class & Object:** Menggunakan **14 *class*** yang saling berinteraksi.

* **Inheritance (Pewarisan):**
    * `Player` dan `Monster` mewarisi/**extends** dari `Entity`.
    * `BossMonster` mewarisi/**extends** dari `Monster` (*multilevel inheritance*).
    * `Weapon`, `Armor`, `Consumable`, dan `QuestItem` mewarisi/**extends** dari `Item`.

* **Polymorphism (Polimorfisme):**
    * **Method Overriding:** Method `attack()` dan `terimaDamage()` di-*override* di `Player`, `Monster`, dan `BossMonster` untuk memberikan logika dan reaksi yang unik.
    * **Tipe Objek:** `List<Item>` di *class* `Player` dapat menampung berbagai *subclass* dari `Item` (seperti `Weapon` dan `Consumable`).

* **Abstract Class:**
    * Menggunakan 2 *abstract class*: `Entity` (sebagai cetak biru makhluk hidup) dan `Item` (sebagai cetak biru semua item).

* **Encapsulation (Enkapsulasi):**
    * Semua atribut di *class model* (seperti `private int hp`, `private int attackBonus`) dideklarasikan sebagai `private`.
    * Akses dikontrol secara ketat melalui *method* publik (`getter`, `setter`, `equipItem()`, `terimaDamage()`).

* **Association (Asosiasi):**
    * `Player` "HAS-A" (memiliki) `List<Item>` (Inventory).
    * `GameManager` "HAS-A" `Player`, `InputHandler`, dan `Narrator`.
    * `Monster` "HAS-A" `Item` (sebagai `itemDrop`).


## Relasi Antar Kelas (Class Relationships)

Proyek ini menggunakan berbagai jenis relasi OOP untuk menghubungkan 14 kelas yang ada agar bekerja secara harmonis.

### A. Pewarisan (Inheritance / "is-a")
Relasi ini menunjukkan hierarki kelas, di mana kelas anak mewarisi atribut dan method dari kelas induk.

1.  **Hierarki Makhluk Hidup/Entitas (`Entity`)**
    * `Entity` (Abstract Parent)
        * `Player` (Child): Mewarisi HP dan logika serangan, menambah logika *leveling* dan *inventory*.
        * `Monster` (Child): Mewarisi HP dan logika serangan, menambah logika *drop item*.
            * `BossMonster` (Grandchild): Mewarisi dari `Monster`, menambah logika *Special Attack*.

2.  **Hierarki Barang (`Item`)**
    * `Item` (Abstract Parent)
        * `Weapon` (Child): Menambah atribut `attackBonus`.
        * `Armor` (Child): Menambah atribut `defenseBonus`.
        * `Consumable` (Child): Menambah atribut `hpBonus`.
        * `QuestItem` (Child): Item khusus penanda misi.

### B. Asosiasi & Komposisi (Association / "has-a")
Relasi ini menunjukkan kepemilikan, di mana satu kelas memiliki referensi ke kelas lain sebagai atributnya.

* **`GameManager` has-a `Player`:**
    * Kelas utama mengontrol satu objek `Player` sepanjang permainan.
* **`GameManager` has-a `InputHandler`:**
    * Kelas utama memiliki satu *handler* untuk memproses semua input *keyboard*.
* **`Player` has-a `List<Item>` (Inventory):**
    * Player memiliki daftar item (Agregasi). Satu player bisa memiliki banyak item (`Weapon`, `Armor`, dll).
* **`Monster` has-a `Item` (Drop):**
    * Setiap monster menyimpan satu objek `Item` yang akan dijatuhkan (di-*return*) saat dikalahkan.

### C. Ketergantungan (Dependency / "uses")
Relasi ini terjadi ketika sebuah kelas menggunakan kelas lain (biasanya kelas statis/utility) untuk melakukan tugas tertentu tanpa memilikinya sebagai atribut permanen.

* **`GameManager` uses `Narrator`:**
    * Untuk menampilkan teks cerita, dialog intro, dan ending.
* **`GameManager` uses `AsciiArt`:**
    * Untuk menggambar visual monster ke layar saat pertarungan dimulai.
* **`GameManager`, `Player`, `Monster` use `ConsoleUtils`:**
    * Semua kelas ini menggunakan utilitas konsol untuk membersihkan layar (`clear`) atau mewarnai teks output (Merah/Hijau).