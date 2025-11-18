package utils;

// simple monster art  
public class AsciiArt {

    private AsciiArt() {
    }

    public static void showPaleCrawler() {
        System.out.println(ConsoleUtils.GRAY);
        System.out.println("     / \\.-./ \\");
        System.out.println("     |  @ @  |");
        System.out.println("    /  (---)  \\");
        System.out.println("   / ` |   | ` \\");
        System.out.println("  ( (  |   |  ) )");
        System.out.println("__ `\\\"-'   `\\\"-'`________________________________________________");
        System.out.println(ConsoleUtils.RESET);
    }

    public static void showSootScavenger() {
        System.out.println(ConsoleUtils.GRAY);
        System.out.println("        ...");
        System.out.println("       /   \\");
        System.out.println("      |<>_<>|");
        System.out.println("     .'      `.");
        System.out.println("    / /'|  | \\ \\");
        System.out.println("   | |  |  |  ' |");
        System.out.println("   | |  |  |  , |");
        System.out.println("___\\_\\__|__|_/_/_________________________________________________");
        System.out.println(ConsoleUtils.RESET);
    }

    public static void showScavengerHeavy() {
        System.out.println(ConsoleUtils.GRAY);
        System.out.println("       [---]");
        System.out.println("       |O_O|");
        System.out.println("      .'   `.");
        System.out.println("     / /\" \"\\ \\");
        System.out.println("    | |=====| |");
        System.out.println("    |=| | | |=|");
        System.out.println("    | |_| |_| |");
        System.out.println("____\\__)\\_/(__/__________________________________________________");
        System.out.println(ConsoleUtils.RESET);
    }

    public static void showAlphaCrawler() {
        System.out.println(ConsoleUtils.GRAY);
        System.out.println("     .--./ \\.--.");
        System.out.println("    /  @ > < @  \\");
        System.out.println("   |   (-----)   |");
        System.out.println("  / `  |     |  ` \\");
        System.out.println(" / /   |     |   \\ \\                       _");
        System.out.println("( (    |_____|    ) )                     / \\");
        System.out.println(" `\"----'     `----'\"`_______/\\_______/\\__/___\\___________________");
        System.out.println(ConsoleUtils.RESET);
    }

    public static void showStationSentinel() {
        System.out.println(ConsoleUtils.GRAY);
        System.out.println("      ._______.");
        System.out.println("     / __   __ \\");
        System.out.println("    | |  | |  | |");
        System.out.println("    | |_[O_O]_| |");
        System.out.println("    |           |");
        System.out.println("   [|           |]");
        System.out.println("    |  |-----|  |");
        System.out.println("    / ||     || \\");
        System.out.println("   / /||     ||\\ \\");
        System.out.println("  |__|||     |||__|");
        System.out.println("__(T T)______(T  T)_____________________________________________");

        System.out.println(ConsoleUtils.RESET);
    }
}