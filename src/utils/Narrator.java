package utils;

public class Narrator {

        private static Language currentLanguage = Language.EN;

        public static void setLanguage(Language lang) {
                if (lang != null) {
                        currentLanguage = lang;
                }
        }

        public static Language getLanguage() {
                return currentLanguage;
        }

    private Narrator() {
    }

        public static void showIntro() {
        ConsoleUtils.clear();
        System.out.println(ConsoleUtils.RED
                + "============================================================================================="
                + ConsoleUtils.RESET);
        System.out.println(ConsoleUtils.RED + "                                 SOOT FALLOUT" + ConsoleUtils.RESET);
        System.out.println(
                ConsoleUtils.GRAY + "                           The Sun is just a memory." + ConsoleUtils.RESET);
        System.out.println(ConsoleUtils.RED
                + "============================================================================================="
                + ConsoleUtils.RESET);
        if (currentLanguage == Language.ID) {
            System.out.println("\nBerlatar pada tahun 2277, bencana ini dikenal sebagai \"The Great Dimming\".");
            System.out.println(
                    "Ini adalah konsekuensi dari industri global dan pembakaran batu bara yang, selama ratusan tahun,");
            System.out.println("berjalan masif tanpa mengendalikan keluaran jelaga.");
            System.out.println("Emisi tak terkendali ini (Black Carbon/PM2.5) akhirnya terakumulasi");
            System.out.println("dan menciptakan sebuah \"kubah\" partikel di atmosfer,");
            System.out.println("menghalangi sebagian besar cahaya matahari.");
            System.out.println("\nAkibatnya, fotosintesis global berhenti,");
            System.out.println("membunuh semua tanaman permukaan dan meruntuhkan rantai makanan.");
            System.out.println("Udara di permukaan sekarang dipenuhi partikel berbahaya.");
            System.out.println("\nAnda adalah seorang penyintas di Caldera Bunker, sebuah pemukiman bawah tanah");
            System.out.println("yang ditenagai oleh Inti Geotermal — satu-satunya sumber panas dan listrik yang tersisa.");
            System.out.println("\nInti Geotermal di bunker mulai tidak berfungsi dengan baik.");
            System.out.println("Anda, seorang Thermo-Scavenger, ditugaskan untuk menjelajah Stasiun Relay Daya yang ditinggalkan");
            System.out.println("untuk menjarah 4 komponen penting guna menyelamatkan bunker.");
        } else {
            System.out.println("\nSet in the year 2277, the disaster is known as \"The Great Dimming\".");
            System.out.println("It is the consequence of global industry and coal burning, which for hundreds of years");
            System.out.println("operated massively without controlling their soot output.");
            System.out.println("This uncontrolled emission (Black Carbon/PM2.5) finally accumulated");
            System.out.println("and created a permanent particle \"dome\" in the atmosphere,");
            System.out.println("blocking most of the sunlight.");
            System.out.println("\nAs a result, global photosynthesis has ceased,");
            System.out.println("killing all surface plant life and collapsing the food chain.");
            System.out.println("The air on the surface itself is now filled with deadly particulates.");
            System.out.println("\nYou are a survivor in the Caldera Bunker, an underground settlement");
            System.out.println("powered by a Geothermal Core—the only remaining source of heat and electricity.");
            System.out.println("\nThe Geothermal Core in the bunker is beginning to fail.");
            System.out
                    .println("You, a Thermo-Scavenger, have been tasked to venture into the abandoned Power Relay Station");
            System.out.println("to scavenge 4 vital components to save the bunker.");
        }
        System.out.println();
        System.out.println(ConsoleUtils.RED
                + "============================================================================================="
                + ConsoleUtils.RESET);
    }

    public static void showLocation(String locationName) {
                if (currentLanguage == Language.ID) {
                        System.out.println("\nMASUK LOKASI: " + ConsoleUtils.YELLOW + locationName + ConsoleUtils.RESET);
                } else {
                        System.out.println(
                                        "\nENTERING LOCATION: " + ConsoleUtils.YELLOW + locationName + ConsoleUtils.RESET);
                }
        if (locationName.equals("Service Tunnels")) {
            System.out.println("Terowongan gelap dan berbau lembab. Anda mendengar suara gesekan di depan.");
        } else if (locationName.equals("Outer Power Station")) {
            System.out.println("Jelaga turun seperti salju hitam di area terbuka ini. Sebuah bayangan melihat Anda.");
        } else if (locationName.equals("Generator Building")) {
            System.out.println(
                    "Anda telah mencapai gedung utama. Ada dua rute: Aula Utama atau Terowongan Pendingin.");
        } else if (locationName.equals("Core Control Room")) {
            System.out.println("Inilah tempatnya. Ruangan besar berdengung dengan daya sisa.");
            System.out.println("Di tengah ruangan, sebuah robot besar aktif. 'Station Sentinel'.");
        }
    }

    public static void showBattleIntro(String monsterName) {
                if (currentLanguage == Language.ID) {
                        System.out.println(ConsoleUtils.RED + "\n!!! PERTARUNGAN DIMULAI !!!" + ConsoleUtils.RESET);
                        System.out.println("Anda dihadapkan oleh: " + monsterName + "!");
                } else {
                        System.out.println(ConsoleUtils.RED + "\n!!! BATTLE START !!!" + ConsoleUtils.RESET);
                        System.out.println("You are confronted by: " + monsterName + "!");
                }
    }

    public static void showEndingA_Success() {
        System.out.println(
                ConsoleUtils.GREEN + "\n=============================================================================="
                        + ConsoleUtils.RESET);
        System.out.println(
                ConsoleUtils.GREEN + "=============================================================================="
                        + ConsoleUtils.RESET);
        if (currentLanguage == Language.ID) {
            System.out.println(
                    ConsoleUtils.GREEN + "                            ENDING: KELEMBELENGAN SEMENTARA" + ConsoleUtils.RESET);
            System.out.println("Anda kembali dengan komponen-komponen. Para teknisi memasangnya,");
            System.out.println("dan Inti Geotermal stabil kembali. Dengungan uap yang menenangkan kembali hadir.");
            System.out.println("Panas dan udara bersih kembali ke Caldera Bunker. Anda selamat.");
            System.out.println("\n...Anda melihat monitor permukaan: hanya kegelapan pekat");
            System.out.println("dan jatuhnya abu tiada henti.");
            System.out.println("Anda tidak memperbaiki dunia; Anda hanya memperbaiki penjara Anda.");
            System.out.println("Matahari tetap tersembunyi.");
            System.out.println("Jelaga di atas sana adalah pengingat abadi bahwa peradaban modern");
            System.out.println("membakar masa depannya demi energi sesaat.");
            System.out.println(ConsoleUtils.GREEN + "\nTERIMA KASIH TELAH BERMAIN." + ConsoleUtils.RESET);
        } else {
            System.out.println(
                    ConsoleUtils.GREEN + "                            ENDING: A TEMPORARY REPRIEVE" + ConsoleUtils.RESET);
            System.out.println("You return with the components. The engineers install them,");
            System.out.println("and the Geothermal Core stabilizes. The soothing hum of steam returns.");
            System.out.println("Heat and clean air return to the Caldera Bunker. You are safe.");
            System.out.println("\n...You look at the surface monitor: only thick darkness");
            System.out.println("and the endless fall of ash.");
            System.out.println("You didn't fix the world; you only fixed your prison.");
            System.out.println("The Sun remains hidden.");
            System.out.println("The soot up there is an eternal reminder that modern civilization");
            System.out.println("burned its own future for a moment's energy.");
            System.out.println(ConsoleUtils.GREEN + "\nTHANK YOU FOR PLAYING." + ConsoleUtils.RESET);
        }
        System.out.println(ConsoleUtils.GREEN + "\nTHANK YOU FOR PLAYING." + ConsoleUtils.RESET);
    }

    public static void showEndingB_Fail() {
        System.out.println(
                ConsoleUtils.RED + "\n=============================================================================="
                        + ConsoleUtils.RESET);
        System.out.println(
                ConsoleUtils.RED + "=============================================================================="
                        + ConsoleUtils.RESET);
        if (currentLanguage == Language.ID) {
            System.out.println(
                    ConsoleUtils.RED + "                             ENDING: KEMATIAN DALAM DINGIN ABADI" + ConsoleUtils.RESET);
            System.out.println("Anda terjatuh ke lantai yang dingin.");
            System.out.println("Penglihatan Anda memudar saat jelaga menutupi Anda...");
            System.out.println("\nBeberapa hari kemudian, di Caldera Bunker, lampu mulai redup.");
            System.out.println("Alarm suhu meraung, kemudian perlahan mati.");
            System.out.println("Keheningan dan dingin menusuk mengambil alih.");
            System.out.println("Caldera Bunker telah menjadi makam, sama dinginnya dengan abu di luar.");
            System.out.println(ConsoleUtils.RED + "\nGAME OVER." + ConsoleUtils.RESET);
        } else {
            System.out.println(
                    ConsoleUtils.RED + "                             ENDING: THE ETERNAL COLD" + ConsoleUtils.RESET);
            System.out.println("You fall to the cold floor.");
            System.out.println("Your vision fades as the soot slowly covers you...");
            System.out.println("\nDays later, in the Caldera Bunker, the lights begin to dim.");
            System.out.println("The temperature alarms blare loudly, then slowly die.");
            System.out.println("Silence and a piercing cold take over.");
            System.out.println("The Caldera Bunker has become a tomb, just as cold as the ashfall outside.");
            System.out.println(ConsoleUtils.RED + "\nGAME OVER." + ConsoleUtils.RESET);
        }
    }

    public static void showEndingC_Flee() {
        System.out.println(
                ConsoleUtils.GRAY + "\n=============================================================================="
                        + ConsoleUtils.RESET);
        System.out.println(
                ConsoleUtils.GRAY + "=============================================================================="
                        + ConsoleUtils.RESET);
                if (currentLanguage == Language.ID) {
                        System.out.println(ConsoleUtils.GRAY + "                             ENDING: SI PENAKUT" + ConsoleUtils.RESET);
                        System.out.println("Anda tidak menoleh.");
                        System.out.println("Suara gesekan atau tembakan memudar di belakang Anda saat Anda berlari.");
                        System.out.println("Anda berhasil kembali ke pintu masuk Caldera Bunker, sendirian dan tangan kosong.");
                        System.out.println("\nAnda tidak mengatakan bahwa Anda lari. Anda hanya bilang 'misi gagal'.");
                        System.out.println("Beberapa hari kemudian, bunker menjadi sunyi saat Inti Geotermal akhirnya mati.");
                        System.out.println("Anda selamat dari pertempuran,");
                        System.out.println("tetapi Anda membeku bersama semua orang yang Anda gagal selamatkan.");
                        System.out.println(ConsoleUtils.RED + "\nGAME OVER." + ConsoleUtils.RESET);
                } else {
                        System.out.println(ConsoleUtils.GRAY + "                             ENDING: THE COWARD" + ConsoleUtils.RESET);
                        System.out.println("You don't look back.");
                        System.out.println("The sounds of scraping or gunfire fade behind you as you run.");
                        System.out.println("You make it back to the Caldera Bunker entrance, alone and empty-handed.");
                        System.out.println("\nYou don't tell them you ran. You just say 'mission failed'.");
                        System.out.println("Days later, the bunker goes quiet as the Geothermal Core finally dies.");
                        System.out.println("You survived the fight,");
                        System.out.println("but you freeze to death along with everyone you failed.");
                        System.out.println(ConsoleUtils.RED + "\nGAME OVER." + ConsoleUtils.RESET);
                }
    }

    // ask for retry battle or not
    public static void showRevivePrompt(int chargesLeft) {
        // ConsoleUtils.clear();
                if (currentLanguage == Language.ID) {
                        System.out.println(ConsoleUtils.RED + "\nKRITIS: TANDA VITAL GAGAL." + ConsoleUtils.RESET);
                        System.out.println("Protokol Medis Darurat diinisiasi....");
                        System.out.println("Panggil Med-Bot Ekstraksi? (Sisa charge: " + chargesLeft + "/3)");
                        System.out.println("Jika Anda menolak, perjalanan Anda berakhir di sini.");
                } else {
                        System.out.println(ConsoleUtils.RED + "\nCRITICAL WARNING: VITAL SIGNS FAILING." + ConsoleUtils.RESET);
                        System.out.println("Emergency Medical Protocol initiated....");
                        System.out.println("Call for Medical Extraction Bot? (Charges remaining: " + chargesLeft + "/3)");
                        System.out.println("If you decline, your journey ends here.");
                }
    }

    public static void showReviveSuccess() {
                ConsoleUtils.clear();
                AsciiArt.showMedBot();
                if (currentLanguage == Language.ID) {
                        System.out.println(ConsoleUtils.GREEN + "\nEkstraksi dikonfirmasi. Meluncurkan Med-Bot..." + ConsoleUtils.RESET);
                        System.out.println("Anda ditarik dari medan perang tepat pada waktunya.");
                        System.out.println("...");
                        System.out.println("Anda terbangun kembali di Caldera Bunker. Luka Anda dirawat.");
                        System.out.println("Namun musuh tetap belum kalah. Anda harus mencoba lagi.");
                } else {
                        System.out.println(ConsoleUtils.GREEN + "\nExtraction confirmed. Deploying Med-Bot..." + ConsoleUtils.RESET);
                        System.out.println("You are dragged away from the battlefield just in time.");
                        System.out.println("...");
                        System.out.println("You wake up back in Caldera Bunker. Your wounds are treated.");
                        System.out.println("But the enemy remains undefeated. You must try again.");
                }
    }

    public static void showNoChargesLeft() {
                if (currentLanguage == Language.ID) {
                        System.out.println(ConsoleUtils.RED + "\nKRITIS: TANDA VITAL GAGAL." + ConsoleUtils.RESET);
                        System.out.println("Mencoba memanggil Med-Bot...");
                        System.out.println("ERROR: TIDAK ADA CHARGE TERSISA. KONEKSI DITOLAK.");
                        System.out.println("Tidak ada bantuan yang datang.");
                } else {
                        System.out.println(ConsoleUtils.RED + "\nCRITICAL WARNING: VITAL SIGNS FAILING." + ConsoleUtils.RESET);
                        System.out.println("Attempting to call Medical Bot...");
                        System.out.println("ERROR: NO CHARGES REMAINING. CONNECTION REFUSED.");
                        System.out.println("No help is coming.");
                }
    }

    public static void showRestartPrompt() {
        if (currentLanguage == Language.ID) {
            System.out.println(ConsoleUtils.YELLOW + "\nSimulasi telah berakhir, namun garis waktu tetap bisa diubah."
                    + ConsoleUtils.RESET);
            System.out.print("Apakah Anda ingin memulai ulang petualangan dan mencoba menyelamatkan Bunker lagi? (y/n)");
        } else {
            System.out.println(ConsoleUtils.YELLOW + "\nThe simulation has ended, but the timeline remains malleable."
                    + ConsoleUtils.RESET);
            System.out.print("Do you wish to restart the adventure and try to save the Bunker again? (y/n)");
        }
    }
}