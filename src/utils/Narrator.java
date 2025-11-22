package utils;

public class Narrator {

    private Narrator() {
    }

    public static void showIntro() {
        ConsoleUtils.clear();
        System.out.println(ConsoleUtils.RED + "=============================================================================================" + ConsoleUtils.RESET);
        System.out.println(ConsoleUtils.RED + "                                 " + LanguageManager.getText("game_title") + ConsoleUtils.RESET);
        System.out.println(ConsoleUtils.GRAY + "                           " + LanguageManager.getText("game_subtitle") + ConsoleUtils.RESET);
        System.out.println(ConsoleUtils.RED + "=============================================================================================" + ConsoleUtils.RESET);
        System.out.println("\n" + LanguageManager.getText("intro_line1"));
        System.out.println(LanguageManager.getText("intro_line2"));
        System.out.println(LanguageManager.getText("intro_line3"));
        System.out.println(LanguageManager.getText("intro_line4"));
        System.out.println(LanguageManager.getText("intro_line5"));
        System.out.println(LanguageManager.getText("intro_line6"));
        System.out.println("\n" + LanguageManager.getText("intro_line7"));
        System.out.println(LanguageManager.getText("intro_line8"));
        System.out.println(LanguageManager.getText("intro_line9"));
        System.out.println("\n" + LanguageManager.getText("intro_line10"));
        System.out.println(LanguageManager.getText("intro_line11"));
        System.out.println("\n" + LanguageManager.getText("intro_line12"));
        System.out.println(LanguageManager.getText("intro_line13"));
        System.out.println(LanguageManager.getText("intro_line14"));
        System.out.println();
        System.out.println(ConsoleUtils.RED + "=============================================================================================" + ConsoleUtils.RESET);
    }

    public static void showLocation(String locationName) {
        System.out.println(
                "\n" + LanguageManager.getText("entering_location") + ConsoleUtils.YELLOW + LanguageManager.getText("location_" + locationName.toLowerCase().replace(" ", "_")) + ConsoleUtils.RESET);
        if (locationName.equals("Service Tunnels")) {
            System.out.println(LanguageManager.getText("desc_service_tunnels"));
        } else if (locationName.equals("Outer Power Station")) {
            System.out.println(LanguageManager.getText("desc_outer_power"));
        } else if (locationName.equals("Generator Building")) {
            System.out.println(LanguageManager.getText("desc_generator"));
        } else if (locationName.equals("Core Control Room")) {
            System.out.println(LanguageManager.getText("desc_core_control"));
            System.out.println(LanguageManager.getText("desc_core_control2"));
        }
    }

    public static void showBattleIntro(String monsterName) {
        System.out.println(ConsoleUtils.RED + "\n" + LanguageManager.getText("battle_start") + ConsoleUtils.RESET);
        System.out.println(LanguageManager.getText("confronted_by") + monsterName + "!");
    }

    public static void showEndingA_Success() {
        System.out.println(ConsoleUtils.GREEN + "\n=============================================================================="+ ConsoleUtils.RESET);
        System.out.println(ConsoleUtils.GREEN + "                            " + LanguageManager.getText("ending_a_title") + ConsoleUtils.RESET);
        System.out.println(ConsoleUtils.GREEN + "==============================================================================" + ConsoleUtils.RESET);
        System.out.println(LanguageManager.getText("ending_a_line1"));
        System.out.println(LanguageManager.getText("ending_a_line2"));
        System.out.println(LanguageManager.getText("ending_a_line3"));
        System.out.println("\n" + LanguageManager.getText("ending_a_line4"));
        System.out.println(LanguageManager.getText("ending_a_line5"));
        System.out.println(LanguageManager.getText("ending_a_line6"));
        System.out.println(LanguageManager.getText("ending_a_line7"));
        System.out.println(LanguageManager.getText("ending_a_line8"));
        System.out.println(LanguageManager.getText("ending_a_line9"));
        System.out.println(ConsoleUtils.GREEN + "\n" + LanguageManager.getText("thank_you") + ConsoleUtils.RESET);
    }

    public static void showEndingB_Fail() {
        System.out.println(ConsoleUtils.RED + "\n==============================================================================" + ConsoleUtils.RESET);
        System.out.println(ConsoleUtils.RED + "                             " + LanguageManager.getText("ending_b_title") + ConsoleUtils.RESET);
        System.out.println(ConsoleUtils.RED + "==============================================================================" + ConsoleUtils.RESET);
        System.out.println(LanguageManager.getText("ending_b_line1"));
        System.out.println(LanguageManager.getText("ending_b_line2"));
        System.out.println("\n" + LanguageManager.getText("ending_b_line3"));
        System.out.println(LanguageManager.getText("ending_b_line4"));
        System.out.println(LanguageManager.getText("ending_b_line5"));
        System.out.println(LanguageManager.getText("ending_b_line6"));
        System.out.println(ConsoleUtils.RED + "\n" + LanguageManager.getText("game_over") + ConsoleUtils.RESET);
    }

    public static void showEndingC_Flee() {
        System.out.println(ConsoleUtils.GRAY + "\n=============================================================================="+ ConsoleUtils.RESET);
        System.out.println(ConsoleUtils.GRAY + "                             " + LanguageManager.getText("ending_c_title") + ConsoleUtils.RESET);
        System.out.println(ConsoleUtils.GRAY + "=============================================================================="+ ConsoleUtils.RESET);
        System.out.println(LanguageManager.getText("ending_c_line1"));
        System.out.println(LanguageManager.getText("ending_c_line2"));
        System.out.println(LanguageManager.getText("ending_c_line3"));
        System.out.println("\n" + LanguageManager.getText("ending_c_line4"));
        System.out.println(LanguageManager.getText("ending_c_line5"));
        System.out.println(LanguageManager.getText("ending_c_line6"));
        System.out.println(LanguageManager.getText("ending_c_line7"));
        System.out.println(ConsoleUtils.RED + "\n" + LanguageManager.getText("game_over") + ConsoleUtils.RESET);
    }
}