package utils;

public class Narrator {

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
        System.out.println();
        System.out.println(ConsoleUtils.RED
                + "============================================================================================="
                + ConsoleUtils.RESET);
    }

    public static void showLocation(String locationName) {
        System.out.println(
                "\nENTERING LOCATION: " + ConsoleUtils.YELLOW + locationName + ConsoleUtils.RESET);
        if (locationName.equals("Service Tunnels")) {
            System.out.println("The tunnel is dark and smells of damp. You hear scraping sounds ahead.");
        } else if (locationName.equals("Outer Power Station")) {
            System.out.println("Soot falls like black snow in this open area. A shadow spots you.");
        } else if (locationName.equals("Generator Building")) {
            System.out.println(
                    "You've reached the main building. There are two routes: The Main Hall or the Coolant Tunnels.");
        } else if (locationName.equals("Core Control Room")) {
            System.out.println("This is it. The large room hums with residual power.");
            System.out.println("In the center of the room, a large robot activates. 'Station Sentinel'.");
        }
    }

    public static void showBattleIntro(String monsterName) {
        System.out.println(ConsoleUtils.RED + "\n!!! BATTLE START !!!" + ConsoleUtils.RESET);
        System.out.println("You are confronted by: " + monsterName + "!");
    }

    public static void showEndingA_Success() {
        System.out.println(
                ConsoleUtils.GREEN + "\n=============================================================================="
                        + ConsoleUtils.RESET);
        System.out.println(
                ConsoleUtils.GREEN + "                            ENDING: A TEMPORARY REPRIEVE" + ConsoleUtils.RESET);
        System.out.println(
                ConsoleUtils.GREEN + "=============================================================================="
                        + ConsoleUtils.RESET);
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

    public static void showEndingB_Fail() {
        System.out.println(
                ConsoleUtils.RED + "\n=============================================================================="
                        + ConsoleUtils.RESET);
        System.out.println(
                ConsoleUtils.RED + "                             ENDING: THE ETERNAL COLD" + ConsoleUtils.RESET);
        System.out.println(
                ConsoleUtils.RED + "=============================================================================="
                        + ConsoleUtils.RESET);
        System.out.println("You fall to the cold floor.");
        System.out.println("Your vision fades as the soot slowly covers you...");
        System.out.println("\nDays later, in the Caldera Bunker, the lights begin to dim.");
        System.out.println("The temperature alarms blare loudly, then slowly die.");
        System.out.println("Silence and a piercing cold take over.");
        System.out.println("The Caldera Bunker has become a tomb, just as cold as the ashfall outside.");
        System.out.println(ConsoleUtils.RED + "\nGAME OVER." + ConsoleUtils.RESET);
    }

    public static void showEndingC_Flee() {
        System.out.println(
                ConsoleUtils.GRAY + "\n=============================================================================="
                        + ConsoleUtils.RESET);
        System.out.println(ConsoleUtils.GRAY + "                             ENDING: THE COWARD" + ConsoleUtils.RESET);
        System.out.println(
                ConsoleUtils.GRAY + "=============================================================================="
                        + ConsoleUtils.RESET);
        System.out.println("You don't look back.");
        System.out.println("The sounds of scraping or gunfire fade behind you as you run.");
        System.out.println("You make it back to the Caldera Bunker entrance, alone and empty-handed.");
        System.out.println("\nYou don't tell them you ran. You just say 'mission failed'.");
        System.out.println("Days later, the bunker goes quiet as the Geothermal Core finally dies.");
        System.out.println("You survived the fight,");
        System.out.println("but you freeze to death along with everyone you failed.");
        System.out.println(ConsoleUtils.RED + "\nGAME OVER." + ConsoleUtils.RESET);
    }

    // ask for retry battle or not
    public static void showRevivePrompt(int chargesLeft) {
        // ConsoleUtils.clear();
        System.out.println(ConsoleUtils.RED + "\nCRITICAL WARNING: VITAL SIGNS FAILING." + ConsoleUtils.RESET);
        System.out.println("Emergency Medical Protocol initiated....");
        System.out.println("Call for Medical Extraction Bot? (Charges remaining: " + chargesLeft + "/3)");
        System.out.println("If you decline, your journey ends here.");
    }

    public static void showReviveSuccess() {
        ConsoleUtils.clear();
        AsciiArt.showMedBot();
        System.out.println(ConsoleUtils.GREEN + "\nExtraction confirmed. Deploying Med-Bot..." + ConsoleUtils.RESET);
        System.out.println("You are dragged away from the battlefield just in time.");
        System.out.println("...");
        System.out.println("You wake up back in Caldera Bunker. Your wounds are treated.");
        System.out.println("But the enemy remains undefeated. You must try again.");
    }

    public static void showNoChargesLeft() {
        System.out.println(ConsoleUtils.RED + "\nCRITICAL WARNING: VITAL SIGNS FAILING." + ConsoleUtils.RESET);
        System.out.println("Attempting to call Medical Bot...");
        System.out.println("ERROR: NO CHARGES REMAINING. CONNECTION REFUSED.");
        System.out.println("No help is coming.");
    }

    public static void showRestartPrompt() {
        System.out.println(ConsoleUtils.YELLOW + "\nThe simulation has ended, but the timeline remains malleable."
                + ConsoleUtils.RESET);
        System.out.print("Do you wish to restart the adventure and try to save the Bunker again? (y/n)");
    }
}