package core;

import models.Player;
import models.items.*;
import models.monsters.BossMonster;
import models.monsters.Monster;
import utils.AsciiArt;
import utils.ConsoleUtils;
import utils.InputHandler;
import utils.Narrator;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private Player player;
    private InputHandler input;
    private boolean isGameRunning;

    private QuestItem item1, item2, item3, item4;
    private Monster monster1, monster2, monster3a, monster3b;
    private BossMonster boss4;

    private boolean monster1Kalah, monster2Kalah, monster3Kalah, boss4Kalah;
    private String feedbackMessage;

    public GameManager() {
        this.input = new InputHandler();
        this.isGameRunning = true;
        this.monster1Kalah = false;
        this.monster2Kalah = false;
        this.monster3Kalah = false;
        this.boss4Kalah = false;
        this.feedbackMessage = "";
    }

    // entry point
    public static void main(String[] args) {
        GameManager game = new GameManager();
        game.startGame();
    }
    //

    // start game factory / preparation
    public void startGame() {
        ConsoleUtils.clear();
        Narrator.showIntro();
        input.pressEnterToContinue();

        ConsoleUtils.clear();
        setupGame();

        while (isGameRunning) {
            showMainMenu();
        }

        input.close();
        System.out.println("Shutting down Caldera Bunker systems...");
    }

    // setup preparation
    private void setupGame() {
        String playerName = input.getString("Enter your Thermo-Scavenger's name: ");
        this.player = new Player(playerName);

        this.item1 = new QuestItem("Used Turbine Blade");
        this.item2 = new QuestItem("Pressure Valve");
        this.item3 = new QuestItem("Coolant Pump");
        this.item4 = new QuestItem("Geothermal Regulator Core");

        Weapon weapon2 = new Weapon("Scavenger's Shiv", 15);
        Armor armor3 = new Armor("Boiler Plate Vest", 20);
        // Consumable potion = new Consumable("First-Aid Kit", 100);

        this.monster1 = new Monster("Pale Crawler", 60, 10, 3, this.item1);
        this.monster2 = new Monster("Soot-lung Scavenger", 90, 14, 6, weapon2);
        this.monster3a = new Monster("Scavenger Heavy", 180, 20, 12, armor3);
        this.monster3b = new Monster("Alpha Crawler", 220, 24, 8, armor3);
        this.boss4 = new BossMonster("Station Sentinel", 350, 28, 14, 30, this.item4);
    }

    // show main menu
    private void showMainMenu() {
        ConsoleUtils.clear();

        if (this.feedbackMessage != null && !this.feedbackMessage.isEmpty()) {
            System.out.println(ConsoleUtils.YELLOW + "\nInfo: " + this.feedbackMessage + ConsoleUtils.RESET);
            this.feedbackMessage = "";
        }

        final int CONTENT_WIDTH = 48;
        String topBorder = "----------- Caldera Bunker (Main hub) --------------";
        String bottomBorder = "----------------------------------------------------";
        StringBuilder sb = new StringBuilder();

        sb.append(ConsoleUtils.CYAN + "\n" + topBorder + ConsoleUtils.RESET + "\n");
        String statusLabel = "Status: " + player.getName() + " (";
        String hpValue = player.getHp() + "/" + player.getMaxHp() + " HP";
        String statusSuffix = ")";
        String statusContent = statusLabel + ConsoleUtils.GREEN + hpValue + ConsoleUtils.RESET + statusSuffix;

        int statusVisibleLength = statusLabel.length() + hpValue.length() + statusSuffix.length();
        int statusPadding = CONTENT_WIDTH - statusVisibleLength;
        if (statusPadding < 0) {
            statusPadding = 0;
        }

        sb.append("| " + statusContent + " ".repeat(statusPadding) + " |\n");
        String missionContent = "Mission: Find the 4 Components of the Regulator.";
        int missionPadding = CONTENT_WIDTH - missionContent.length();
        sb.append("| " + missionContent + " ".repeat(missionPadding) + " |\n");

        sb.append(ConsoleUtils.CYAN + bottomBorder + ConsoleUtils.RESET + "\n");
        System.out.print(sb.toString());

        // mission progress text formatting
        System.out.println("\nMission Progress:");
        String m1 = monster1Kalah ? ConsoleUtils.GREEN + "FINISHED" + ConsoleUtils.RESET
                : ConsoleUtils.RED + "NOT YET" + ConsoleUtils.RESET;
        String m2 = monster2Kalah ? ConsoleUtils.GREEN + "FINISHED" + ConsoleUtils.RESET //
                : (monster1Kalah ? ConsoleUtils.YELLOW + "AVAILABLE" + ConsoleUtils.RESET
                        : ConsoleUtils.RED + "LOCKED" + ConsoleUtils.RESET);
        String m3 = monster3Kalah ? ConsoleUtils.GREEN + "FINISHED" + ConsoleUtils.RESET
                : (monster2Kalah ? ConsoleUtils.YELLOW + "AVAILABLE" + ConsoleUtils.RESET
                        : ConsoleUtils.RED + "LOCKED" + ConsoleUtils.RESET);
        String m4 = boss4Kalah ? ConsoleUtils.GREEN + "FINISHED"
                : (monster3Kalah ? ConsoleUtils.YELLOW + "AVAILABLE" + ConsoleUtils.RESET
                        : ConsoleUtils.RED + "LOCKED" + ConsoleUtils.RESET);

        System.out.println("1. [Service Tunnels] - " + m1);
        System.out.println("2. [Outer Power Station] - " + m2);
        System.out.println("3. [Generator Building] - " + m3);
        System.out.println("4. [Core Control Room] - " + m4);

        System.out.println("\nOptions:");
        System.out.println("1. Explore Next Mission Location");
        System.out.println("2. View Status & Inventory");
        System.out.println("3. Exit Game");

        int choice = input.getInt("Your choice (1-3): ", 1, 3);

        switch (choice) {
            case 1:
                exploreNextLocation();
                break;
            case 2:
                this.feedbackMessage = showInventoryMenu(false);
                break;
            case 3:
                System.out.println("Are you sure you want to exit?? (y/n)");
                if (input.getString("").equalsIgnoreCase("y")) {
                    isGameRunning = false;
                    ConsoleUtils.clear();
                    Narrator.showEndingB_Fail();
                }
                break;
        }
    }

    // explore next location
    private void exploreNextLocation() {
        if (!monster1Kalah) {
            Narrator.showLocation("Service Tunnels");
            input.pressEnterToContinue();
            startBattle(monster1);
            if (player.isAlive() && isGameRunning) {
                monster1Kalah = true;
                player.addItemToInventory(item1);
                boolean leveledUp = player.addExp(50);
                if (leveledUp) {
                    this.feedbackMessage = "[!] LEVEL UP! You are now Level " + player.getLevel()
                            + "! Stats increased.";
                }
            }
        } else if (!monster2Kalah) {
            Narrator.showLocation("Outer Power Station");
            input.pressEnterToContinue();
            startBattle(monster2);
            if (player.isAlive() && isGameRunning) {
                monster2Kalah = true;
                player.addItemToInventory(monster2.dropItem());
                player.addItemToInventory(item2);
                boolean leveledUp = player.addExp(75);
                if (leveledUp) {
                    this.feedbackMessage = "[!] LEVEL UP! You are now Level " + player.getLevel()
                            + "! Stats increased.";
                }
            }
        } else if (!monster3Kalah) {
            Narrator.showLocation("Generator Building");
            System.out.println("The map shows two routes:");
            System.out.println("1. Go through the Main Hall (Guarded by Scavenger Heavy)");
            System.out.println("2. Go through the Coolant Tunnels (Alpha Crawler's Nest)");
            int rute = input.getInt("Choose route (1-2): ", 1, 2);

            Monster monsterPilihan = (rute == 1) ? monster3a : monster3b;
            startBattle(monsterPilihan);

            if (player.isAlive() && isGameRunning) {
                monster3Kalah = true;
                player.addItemToInventory(monsterPilihan.dropItem());
                player.addItemToInventory(item3);
                boolean leveledUp = player.addExp(150);
                if (leveledUp) {
                    this.feedbackMessage = "[!] LEVEL UP! You are now Level " + player.getLevel()
                            + "! Stats increased.";
                }
            }
        } else if (!boss4Kalah) {
            Narrator.showLocation("Core Control Room");
            input.pressEnterToContinue();
            startBattle(boss4);
            if (player.isAlive() && isGameRunning) {
                boss4Kalah = true;
                player.addItemToInventory(boss4.dropItem());
                boolean leveledUp = player.addExp(300);
                if (leveledUp) {
                    this.feedbackMessage = "[!] LEVEL UP! You are now Level " + player.getLevel()
                            + "! Stats increased.";
                }
                winConditionCheck();
            }
        } else {
            System.out.println("All missions are complete. You have saved the Caldera Bunker!");
        }

        if (player.isAlive() && isGameRunning) {
            input.pressEnterToContinue();
        }
    }

    // information display
    // & string formatting
    private void buildStatBlock(Monster monster) {
        System.out.println();

        String pName = player.getName();
        String mName = monster.getName();
        System.out.println(String.format(" " + ConsoleUtils.YELLOW + "%-30s" + ConsoleUtils.RESET + "vs."
                + ConsoleUtils.RED + "%30s" + ConsoleUtils.RESET, pName, mName));

        String pStats = String.format(ConsoleUtils.GREEN + "%d/%d HP" + ConsoleUtils.RESET + "  %d DEF  %d ATK",
                player.getHp(), player.getMaxHp(), player.getTotalDefense(), player.getTotalAttack());

        String mStats = String.format(ConsoleUtils.GREEN + "%d/%d HP" + ConsoleUtils.RESET + "  %d DEF  %d ATK",
                monster.getHp(), monster.getMaxHp(), monster.getDefensePower(), monster.getAttackPower());

        System.out.println(String.format(" %-39s" + "   " + "%39s", pStats, mStats));
        System.out.println();
    }

    private void buildPlayerMenu() {
        // heal cooldown logic
        // w ternary opr
        String healStatus = player.getHealCooldown() > 0 ? "CD: " + player.getHealCooldown() + "" : "Ready";
        String options = String.format("| (1) Attack  (2) Use Item  (3) Heal: %-7s  (4) Flee         |", healStatus);
        String top = "+-- Your Turn! -------------------------------------------------+";
        String bottom = "+-- Choose one option: -----------------------------------------+";

        System.out.println(ConsoleUtils.RESET + top + ConsoleUtils.RESET);
        System.out.println(options);
        System.out.println(ConsoleUtils.RESET + bottom + ConsoleUtils.RESET);
    }
    //

    // start battle
    private void startBattle(Monster monster) {
        ConsoleUtils.clear();
        Narrator.showBattleIntro(monster.getName());

        // preparing monster ascii
        String monsterName = monster.getName();
        if (monsterName.equals("Pale Crawler")) {
            AsciiArt.showPaleCrawler();
        } else if (monsterName.equals("Soot-lung Scavenger")) {
            AsciiArt.showSootScavenger();
        } else if (monsterName.equals("Scavenger Heavy")) {
            AsciiArt.showScavengerHeavy();
        } else if (monsterName.equals("Alpha Crawler")) {
            AsciiArt.showAlphaCrawler();
        } else if (monsterName.equals("Station Sentinel")) {
            AsciiArt.showStationSentinel();
        }

        while (player.isAlive() && monster.isAlive() && isGameRunning) {
            ConsoleUtils.clear();

            // add monster ascii by name
            if (monsterName.equals("Pale Crawler")) {
                AsciiArt.showPaleCrawler();
            } else if (monsterName.equals("Soot-lung Scavenger")) {
                AsciiArt.showSootScavenger();
            } else if (monsterName.equals("Scavenger Heavy")) {
                AsciiArt.showScavengerHeavy();
            } else if (monsterName.equals("Alpha Crawler")) {
                AsciiArt.showAlphaCrawler();
            } else if (monsterName.equals("Station Sentinel")) {
                AsciiArt.showStationSentinel();
            }

            buildStatBlock(monster);
            buildPlayerMenu();

            int choice = input.getInt(ConsoleUtils.GRAY + "Your choice (1-4): " + ConsoleUtils.RESET, 1, 4);
            boolean turnUsed = false;

            // action's option
            if (choice == 1) {
                player.attack(monster);
                turnUsed = true;
            } else if (choice == 2) {
                String result = showInventoryMenu(true);
                if (!result.isEmpty()) {
                    turnUsed = true;
                }
            } else if (choice == 3) {
                turnUsed = player.useHealSkill();
            } else if (choice == 4) {
                System.out.println("You turn and flee from the fight...");
                isGameRunning = false;
                ConsoleUtils.clear();
                Narrator.showEndingC_Flee();
                return;
            }

            if (!player.isAlive()) {
                System.out.println("\n" + ConsoleUtils.RED + player.getName() + " has been defeated!"
                        + ConsoleUtils.RESET);
                isGameRunning = false;
                ConsoleUtils.clear();
                Narrator.showEndingB_Fail();
                break;
            }
            if (!monster.isAlive()) {
                System.out.println("\n[!] " + ConsoleUtils.GREEN + monster.getName() + " has been defeated!"
                        + ConsoleUtils.RESET);
                break;
            }

            // monster/enemy attacking
            if (turnUsed) {
                player.decrementHealCooldown();

                System.out.println(ConsoleUtils.RED + "\nEnemy Turn:" + ConsoleUtils.RESET);
                monster.attack(player);

                if (!player.isAlive()) {
                    System.out.println("\n" + ConsoleUtils.RED + player.getName() + " has been defeated!"
                            + ConsoleUtils.RESET);
                    isGameRunning = false;
                    ConsoleUtils.clear();
                    Narrator.showEndingB_Fail();
                    break;
                }
            }

            if (isGameRunning) {
                input.pressEnterToContinue();
            }
        }
    }

    // show inventory
    private String showInventoryMenu(boolean inBattle) {
        String feedback = "";

        if (inBattle) {
            System.out.println(ConsoleUtils.YELLOW + "\nOPENING BAG (CONSUMABLES ONLY):" + ConsoleUtils.RESET);
            List<Consumable> consumables = player.getConsumableItems();

            if (consumables.isEmpty()) {
                System.out.println("You have no usable items in combat.");
                return "";
            }

            for (int i = 0; i < consumables.size(); i++) {
                Consumable item = consumables.get(i);
                System.out.println((i + 1) + ". " + ConsoleUtils.GREEN + item.getName() + " (+" + item.getHpBonus()
                        + " HP)" + ConsoleUtils.RESET);
            }

            int itemChoice = input.getInt("Enter item number (0 to Cancel): ", 0, consumables.size());
            if (itemChoice == 0) {
                System.out.println("Canceled item use. Turn not used.");
                return "";
            }

            Consumable selectedItem = consumables.get(itemChoice - 1);
            feedback = player.useConsumable(selectedItem);
            return feedback;

        } else {
            ConsoleUtils.clear();
            player.showStatus();
            player.showInventory();

            System.out.println("\nINVENTORY OPTIONS:");
            System.out.println("1. Use/Equip Item");
            System.out.println("2. Unequip Item");
            System.out.println("0. Back");

            int choice = input.getInt("Your choice (0-2): ", 0, 2);

            if (choice == 0) {
                return "";
            } else if (choice == 1) {
                if (player.getInventory().isEmpty()) {
                    feedback = "Backpack is empty.";
                    System.out.println(feedback);
                    return feedback;
                }

                player.showInventory();
                int itemChoice = input.getInt("Enter item number (0 to Cancel): ", 0, player.getInventory().size());

                if (itemChoice == 0) {
                    feedback = "Canceled item selection.";
                    System.out.println(feedback);
                    return feedback;
                }

                if (itemChoice - 1 >= player.getInventory().size() || itemChoice - 1 < 0) {
                    feedback = "Invalid item number.";
                    System.out.println(feedback);
                    return feedback;
                }

                Item selectedItem = player.getInventory().get(itemChoice - 1);

                if (selectedItem instanceof Consumable) {
                    feedback = player.useConsumable((Consumable) selectedItem);
                } else if (selectedItem instanceof Weapon || selectedItem instanceof Armor) {
                    feedback = player.equipItem(selectedItem);
                } else if (selectedItem instanceof QuestItem) {
                    feedback = selectedItem.getName() + " is a quest item and cannot be used.";
                    System.out.println(feedback);
                }
                return feedback;

            } else if (choice == 2) {
                System.out.println("Unequip [1] Weapon or [2] Armor? (0 to Cancel)");
                int slotChoice = input.getInt("Your choice (0-2): ", 0, 2);
                if (slotChoice == 1) {
                    feedback = player.unequipItem("weapon");
                } else if (slotChoice == 2) {
                    feedback = player.unequipItem("armor");
                } else {
                    feedback = "Canceled unequip.";
                }
                return feedback;
            }
        }
        return "";
    }

    // check win condi
    private void winConditionCheck() {
        List<Item> inventory = new ArrayList<>();
        inventory.addAll(player.getInventory());

        if (inventory.contains(item1) &&
                inventory.contains(item2) &&
                inventory.contains(item3) &&
                inventory.contains(item4)) {
            isGameRunning = false;
            ConsoleUtils.clear();
            Narrator.showEndingA_Success();
        }
    }
}