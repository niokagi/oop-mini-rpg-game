package utils;

import java.util.HashMap;
import java.util.Map;

public class LanguageManager {
    private static Language currentLanguage = Language.ENGLISH;
    private static final Map<String, Map<Language, String>> texts = new HashMap<>();

    static {
        // Language selection
        addText("lang_select_title", "SELECT LANGUAGE / PILIH BAHASA", "SELECT LANGUAGE / PILIH BAHASA");
        addText("lang_select_option1", "1. English", "1. English");
        addText("lang_select_option2", "2. Bahasa Indonesia", "2. Bahasa Indonesia");
        addText("lang_select_prompt", "Your choice (1-2): ", "Pilihan Anda (1-2): ");
        
        // Game intro
        addText("game_title", "SOOT FALLOUT", "SOOT FALLOUT");
        addText("game_subtitle", "The Sun is just a memory.", "Matahari hanyalah kenangan.");
        addText("intro_line1", "Set in the year 2277, the disaster is known as \"The Great Dimming\".", 
                "Pada tahun 2277, bencana ini dikenal sebagai \"The Great Dimming\".");
        addText("intro_line2", "It is the consequence of global industry and coal burning, which for hundreds of years", 
                "Ini adalah akibat dari industri global dan pembakaran batubara, yang selama ratusan tahun");
        addText("intro_line3", "operated massively without controlling their soot output.", 
                "beroperasi secara masif tanpa mengendalikan emisi jelaga mereka.");
        addText("intro_line4", "This uncontrolled emission (Black Carbon/PM2.5) finally accumulated", 
                "Emisi tak terkendali ini (Black Carbon/PM2.5) akhirnya terakumulasi");
        addText("intro_line5", "and created a permanent particle \"dome\" in the atmosphere,", 
                "dan menciptakan \"kubah\" partikel permanen di atmosfer,");
        addText("intro_line6", "blocking most of the sunlight.", 
                "menghalangi sebagian besar sinar matahari.");
        addText("intro_line7", "As a result, global photosynthesis has ceased,", 
                "Akibatnya, fotosintesis global telah berhenti,");
        addText("intro_line8", "killing all surface plant life and collapsing the food chain.", 
                "membunuh semua kehidupan tumbuhan di permukaan dan meruntuhkan rantai makanan.");
        addText("intro_line9", "The air on the surface itself is now filled with deadly particulates.", 
                "Udara di permukaan kini dipenuhi dengan partikel mematikan.");
        addText("intro_line10", "You are a survivor in the Caldera Bunker, an underground settlement", 
                "Anda adalah penyintas di Bunker Caldera, sebuah pemukiman bawah tanah");
        addText("intro_line11", "powered by a Geothermal Core—the only remaining source of heat and electricity.", 
                "yang ditenagai oleh Inti Geotermal—satu-satunya sumber panas dan listrik yang tersisa.");
        addText("intro_line12", "The Geothermal Core in the bunker is beginning to fail.", 
                "Inti Geotermal di bunker mulai gagal.");
        addText("intro_line13", "You, a Thermo-Scavenger, have been tasked to venture into the abandoned Power Relay Station", 
                "Anda, seorang Thermo-Scavenger, ditugaskan untuk masuk ke Stasiun Relay Tenaga yang ditinggalkan");
        addText("intro_line14", "to scavenge 4 vital components to save the bunker.", 
                "untuk mengumpulkan 4 komponen vital untuk menyelamatkan bunker.");
        
        addText("press_enter", "[Press ENTER to continue...]", "[Tekan ENTER untuk melanjutkan...]");
        addText("enter_name", "Enter your Thermo-Scavenger's name: ", "Masukkan nama Thermo-Scavenger Anda: ");
        
        // Main menu
        addText("main_hub_title", "Caldera Bunker (Main hub)", "Bunker Caldera (Markas Utama)");
        addText("status_label", "Status: ", "Status: ");
        addText("mission_label", "Mission: Find the 4 Components of the Regulator.", 
                "Misi: Temukan 4 Komponen Regulator.");
        addText("mission_progress", "Mission Progress:", "Progres Misi:");
        addText("info_label", "Info: ", "Info: ");
        
        // Mission status
        addText("status_finished", "FINISHED", "SELESAI");
        addText("status_not_yet", "NOT YET", "BELUM");
        addText("status_available", "AVAILABLE", "TERSEDIA");
        addText("status_locked", "LOCKED", "TERKUNCI");
        
        // Locations
        addText("location_service_tunnels", "Service Tunnels", "Terowongan Servis");
        addText("location_outer_power", "Outer Power Station", "Stasiun Tenaga Luar");
        addText("location_generator", "Generator Building", "Gedung Generator");
        addText("location_core_control", "Core Control Room", "Ruang Kontrol Inti");
        
        // Location descriptions
        addText("desc_service_tunnels", "The tunnel is dark and smells of damp. You hear scraping sounds ahead.", 
                "Terowongan gelap dan berbau lembap. Anda mendengar suara goresan di depan.");
        addText("desc_outer_power", "Soot falls like black snow in this open area. A shadow spots you.", 
                "Jelaga jatuh seperti salju hitam di area terbuka ini. Sebuah bayangan melihat Anda.");
        addText("desc_generator", "You've reached the main building. There are two routes: The Main Hall or the Coolant Tunnels.", 
                "Anda telah mencapai gedung utama. Ada dua rute: Aula Utama atau Terowongan Pendingin.");
        addText("desc_core_control", "This is it. The large room hums with residual power.", 
                "Inilah dia. Ruangan besar berdengung dengan sisa daya.");
        addText("desc_core_control2", "In the center of the room, a large robot activates. 'Station Sentinel'.", 
                "Di tengah ruangan, robot besar diaktifkan. 'Station Sentinel'.");
        
        // Menu options
        addText("menu_option1", "1. Explore Next Mission Location", "1. Jelajahi Lokasi Misi Berikutnya");
        addText("menu_option2", "2. View Status & Inventory", "2. Lihat Status & Inventori");
        addText("menu_option3", "3. Exit Game", "3. Keluar Game");
        addText("choice_prompt", "Your choice (1-3): ", "Pilihan Anda (1-3): ");
        
        addText("exit_confirm", "Are you sure you want to exit?? (y/n)", "Apakah Anda yakin ingin keluar?? (y/n)");
        addText("shutting_down", "Shutting down Caldera Bunker systems...", "Mematikan sistem Bunker Caldera...");
        
        // Battle
        addText("battle_start", "!!! BATTLE START !!!", "!!! PERTARUNGAN DIMULAI !!!");
        addText("confronted_by", "You are confronted by: ", "Anda berhadapan dengan: ");
        addText("entering_location", "ENTERING LOCATION: ", "MEMASUKI LOKASI: ");
        
        addText("player_turn", "Your Turn!", "Giliran Anda!");
        addText("enemy_turn", "Enemy Turn:", "Giliran Musuh:");
        addText("battle_option1", "(1) Attack", "(1) Serang");
        addText("battle_option2", "(2) Use Item", "(2) Gunakan Item");
        addText("battle_option3", "(3) Heal", "(3) Sembuh");
        addText("battle_option4", "(4) Flee", "(4) Kabur");
        addText("choose_option", "Choose one option: ", "Pilih satu opsi: ");
        addText("battle_choice_prompt", "Your choice (1-4): ", "Pilihan Anda (1-4): ");
        
        addText("flee_message", "You turn and flee from the fight...", "Anda berbalik dan kabur dari pertarungan...");
        addText("defeated_message", " has been defeated!", " telah dikalahkan!");
        
        // Route selection
        addText("route_map", "The map shows two routes:", "Peta menunjukkan dua rute:");
        addText("route_option1", "1. Go through the Main Hall (Guarded by Scavenger Heavy)", 
                "1. Lewat Aula Utama (Dijaga oleh Scavenger Heavy)");
        addText("route_option2", "2. Go through the Coolant Tunnels (Alpha Crawler's Nest)", 
                "2. Lewat Terowongan Pendingin (Sarang Alpha Crawler)");
        addText("route_prompt", "Choose route (1-2): ", "Pilih rute (1-2): ");
        
        // Level up
        addText("level_up", "[!] LEVEL UP! You are now Level ", "[!] NAIK LEVEL! Anda sekarang Level ");
        addText("stats_increased", "! Stats increased.", "! Statistik meningkat.");
        
        // Inventory
        addText("opening_bag", "--- OPENING BAG (CONSUMABLES ONLY) ---", "--- MEMBUKA TAS (HANYA BARANG HABIS PAKAI) ---");
        addText("no_usable_items", "You have no usable items in combat.", "Anda tidak memiliki item yang bisa digunakan dalam pertempuran.");
        addText("enter_item_number", "Enter item number (0 to Cancel): ", "Masukkan nomor item (0 untuk Batal): ");
        addText("canceled_item", "Canceled item use. Turn not used.", "Penggunaan item dibatalkan. Giliran tidak terpakai.");
        
        addText("inventory_options", "INVENTORY OPTIONS:", "OPSI INVENTORI:");
        addText("inv_option1", "1. Use/Equip Item", "1. Gunakan/Pakai Item");
        addText("inv_option2", "2. Unequip Item", "2. Lepas Item");
        addText("inv_option0", "0. Back", "0. Kembali");
        addText("inv_choice_prompt", "Your choice (0-2): ", "Pilihan Anda (0-2): ");
        
        addText("backpack_empty", "Backpack is empty.", "Ransel kosong.");
        addText("canceled_selection", "Canceled item selection.", "Pemilihan item dibatalkan.");
        addText("invalid_item", "Invalid item number.", "Nomor item tidak valid.");
        addText("quest_item_msg", " is a quest item and cannot be used.", " adalah item quest dan tidak bisa digunakan.");
        
        addText("unequip_prompt", "Unequip [1] Weapon or [2] Armor? (0 to Cancel)", 
                "Lepas [1] Senjata atau [2] Armor? (0 untuk Batal)");
        addText("canceled_unequip", "Canceled unequip.", "Melepas item dibatalkan.");
        
        // Win condition
        addText("missions_complete", "All missions are complete. You have saved the Caldera Bunker!", 
                "Semua misi selesai. Anda telah menyelamatkan Bunker Caldera!");
        
        // Endings
        addText("ending_a_title", "ENDING: A TEMPORARY REPRIEVE", "ENDING: PENANGGUHAN SEMENTARA");
        addText("ending_a_line1", "You return with the components. The engineers install them,", 
                "Anda kembali dengan komponen tersebut. Para insinyur memasangnya,");
        addText("ending_a_line2", "and the Geothermal Core stabilizes. The soothing hum of steam returns.", 
                "dan Inti Geotermal stabil. Dengung uap yang menenangkan kembali.");
        addText("ending_a_line3", "Heat and clean air return to the Caldera Bunker. You are safe.", 
                "Panas dan udara bersih kembali ke Bunker Caldera. Anda selamat.");
        addText("ending_a_line4", "...You look at the surface monitor: only thick darkness", 
                "...Anda melihat monitor permukaan: hanya kegelapan tebal");
        addText("ending_a_line5", "and the endless fall of ash.", 
                "dan jatuhnya abu yang tak berujung.");
        addText("ending_a_line6", "You didn't fix the world; you only fixed your prison.", 
                "Anda tidak memperbaiki dunia; Anda hanya memperbaiki penjara Anda.");
        addText("ending_a_line7", "The Sun remains hidden.", 
                "Matahari tetap tersembunyi.");
        addText("ending_a_line8", "The soot up there is an eternal reminder that modern civilization", 
                "Jelaga di sana adalah pengingat abadi bahwa peradaban modern");
        addText("ending_a_line9", "burned its own future for a moment's energy.", 
                "membakar masa depannya sendiri demi energi sesaat.");
        addText("thank_you", "THANK YOU FOR PLAYING.", "TERIMA KASIH TELAH BERMAIN.");
        
        addText("ending_b_title", "ENDING: THE ETERNAL COLD", "ENDING: DINGIN ABADI");
        addText("ending_b_line1", "You fall to the cold floor.", 
                "Anda jatuh ke lantai yang dingin.");
        addText("ending_b_line2", "Your vision fades as the soot slowly covers you...", 
                "Penglihatan Anda memudar saat jelaga perlahan menutupi Anda...");
        addText("ending_b_line3", "Days later, in the Caldera Bunker, the lights begin to dim.", 
                "Beberapa hari kemudian, di Bunker Caldera, lampu mulai redup.");
        addText("ending_b_line4", "The temperature alarms blare loudly, then slowly die.", 
                "Alarm suhu berbunyi keras, lalu perlahan mati.");
        addText("ending_b_line5", "Silence and a piercing cold take over.", 
                "Keheningan dan dingin yang menusuk mengambil alih.");
        addText("ending_b_line6", "The Caldera Bunker has become a tomb, just as cold as the ashfall outside.", 
                "Bunker Caldera telah menjadi makam, sedingin hujan abu di luar.");
        addText("game_over", "GAME OVER.", "GAME OVER.");
        
        addText("ending_c_title", "ENDING: THE COWARD", "ENDING: SI PENGECUT");
        addText("ending_c_line1", "You don't look back.", 
                "Anda tidak menoleh ke belakang.");
        addText("ending_c_line2", "The sounds of scraping or gunfire fade behind you as you run.", 
                "Suara goresan atau tembakan memudar di belakang Anda saat Anda berlari.");
        addText("ending_c_line3", "You make it back to the Caldera Bunker entrance, alone and empty-handed.", 
                "Anda berhasil kembali ke pintu masuk Bunker Caldera, sendirian dan dengan tangan kosong.");
        addText("ending_c_line4", "You don't tell them you ran. You just say 'mission failed'.", 
                "Anda tidak memberi tahu mereka bahwa Anda kabur. Anda hanya berkata 'misi gagal'.");
        addText("ending_c_line5", "Days later, the bunker goes quiet as the Geothermal Core finally dies.", 
                "Beberapa hari kemudian, bunker menjadi sunyi saat Inti Geotermal akhirnya mati.");
        addText("ending_c_line6", "You survived the fight,", 
                "Anda selamat dari pertarungan,");
        addText("ending_c_line7", "but you freeze to death along with everyone you failed.", 
                "tapi Anda membeku sampai mati bersama semua orang yang Anda gagalkan.");
        
        // Error messages
        addText("invalid_choice", "Invalid choice. Enter a number between ", "Pilihan tidak valid. Masukkan angka antara ");
        addText("and", " and ", " dan ");
        addText("incorrect_input", "Incorrect input. Please enter a valid number.", 
                "Input salah. Silakan masukkan angka yang valid.");
        
        // Cooldown
        addText("cd", "CD: ", "CD: ");
        addText("ready", "Ready", "Siap");
    }

    private static void addText(String key, String englishText, String indonesianText) {
        Map<Language, String> translations = new HashMap<>();
        translations.put(Language.ENGLISH, englishText);
        translations.put(Language.INDONESIAN, indonesianText);
        texts.put(key, translations);
    }

    public static void setLanguage(Language language) {
        currentLanguage = language;
    }

    public static Language getCurrentLanguage() {
        return currentLanguage;
    }

    public static String getText(String key) {
        Map<Language, String> translations = texts.get(key);
        if (translations != null) {
            return translations.get(currentLanguage);
        }
        return key; // Return key if translation not found
    }
}
