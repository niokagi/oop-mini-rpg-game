# Soot Fallout
> The Sunshine is just a memory.

---

## Latar Belakang & Plot

**Soot Fallout** adalah Mini RPG Game dengan gaya Turn Based yang berbasis konsol/CLI yang bertemakan *post-apocalypse*.
<!-- dan berakar pada realisme ilmiah -->

Berlatar pada tahun 2277, bencana ini dikenal sebagai "The Great Dimming". Yang merupakan konsekuensi dari aktivitas industri global dan pembakaran batu bara selama ratusan tahun beroperasi secara masif tanpa mengendalikan keluaran jelaga mereka dan mengabaikan penggunaan energi alternatif/hijau terbarukan. Emisi tak terkendali (Karbon Hitam/PM2.5) ini akhirnya terakumulasi dan menciptakan "payung/kubah" partikel permanen di lapisan atmosfer, yang menghalangi sebagian besar sinar matahari. Akibatnya, proses fotosintesis global tidak lagi berjalan, membunuh semua tanaman di permukaan dan meruntuhkan rantai makanan. Udara di permukaan kini dipenuhi partikulat yang mematikan.

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

## 3. Cara Menjalankan Program (Metode yang Direkomendasikan)

Proyek ini menggunakan Gradle untuk proses kompilasi, namun memerlukan *script* khusus untuk berjalan dengan benar (agar utilitas *clear console* berfungsi).

1.  Pastikan sudah memiliki Java (JDK) 11 atau lebih baru.
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
│       └── Narrator.java
├── .gitattributes
├── .gitignore
├── build.gradle           
├── gradlew                 
├── gradlew.bat             
├── run.bat        
├── settings.gradle        
└── README.md
```

---

## Implementasi Konsep OOP

* **Class & Object:** Menggunakan **14 *class*** yang saling berinteraksi, termasuk `GameManager`, `Player`, `Monster`, `Item`, `InputHandler`, `Narrator`, dan `ConsoleUtils`.

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
    * Akses ke atribut tersebut diatur secara ketat melalui *method* publik (`getter` dan `setter` publik).

* **Association (Asosiasi):**
    * `Player` "has-a" `List<Item>` (Inventory).
    * `GameManager` "has-a" `Player`.
    * `GameManager` "has-a" `InputHandler` dan `Narrator`.
    * `Monster` "has-a" `Item` (sebagai `itemDrop`).

