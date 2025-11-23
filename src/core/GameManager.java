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
    private boolean monster1Defeated, monster2Defeated, monster3Defeated, boss4Defeated;
    private String feedbackMessage;
    // status flags
    private boolean hasWon;
    private boolean hasQuit;
    // revive charge
    private int reviveCharges;

    public GameManager() {
        this.input = new InputHandler();
    }

    // main / entry point
    public static void main(String[] args) {
        GameManager game = new GameManager();
        game.startGame();
    }

    // start game factory / preparation
    public void startGame() {
        ConsoleUtils.clear();
        String playerName = input.getString("Enter your Thermo-Scavenger's name: ");
        boolean keepPlaying = true;

        // if player keepPlayig / revive
        while (keepPlaying) {
            setupGame(playerName);
            ConsoleUtils.clear();
            Narrator.showIntro();
            input.pressEnterToContinue();

            while (isGameRunning) {
                showMainMenu();
            }

            if (hasWon || hasQuit) {
                keepPlaying = false;
            } else {
                Narrator.showRestartPrompt();
                String choice = input.getString("");
                if (!choice.equalsIgnoreCase("y")) {
                    keepPlaying = false;
                }
            }
        }

        input.close();
        System.out.println("Shutting down Caldera Bunker systems...");
    }

    // setup preparation (entity(player, enemy), items, states)
    private void setupGame(String playerName) {
        this.player = new Player(playerName);
        this.isGameRunning = true;

        this.monster1Defeated = false;
        this.monster2Defeated = false;
        this.monster3Defeated = false;
        this.boss4Defeated = false;
        this.hasWon = false;
        this.hasQuit = false;
        this.feedbackMessage = "";
        this.reviveCharges = 3;

        this.item1 = new QuestItem("Used Turbine Blade");
        this.item2 = new QuestItem("Pressure Valve");
        this.item3 = new QuestItem("Coolant Pump");
        this.item4 = new QuestItem("Geothermal Regulator Core");

        Weapon weapon2 = new Weapon("Scavenger's Shiv", 9);
        Armor armor3 = new Armor("Boiler Plate Vest", 10);
        // Consumable potion = new Consumable("First-Aid Kit", 100);

        this.monster1 = new Monster("Pale Crawler", 60, 10, 3, this.item1);
        this.monster2 = new Monster("Soot-lung Scavenger", 90, 14, 6, weapon2);
        this.monster3a = new Monster("Scavenger Heavy", 180, 20, 12, armor3);
        this.monster3b = new Monster("Alpha Crawler", 220, 24, 8, armor3);
        this.boss4 = new BossMonster("Station Sentinel", 400, 28, 15, 45, this.item4);
    }
    // end of setup

    // show main menu
    private void showMainMenu() {
        ConsoleUtils.clear();
        if (this.feedbackMessage != null && !this.feedbackMessage.isEmpty()) {
            System.out.println(ConsoleUtils.GRAY + "\nInfo: " + this.feedbackMessage + ConsoleUtils.RESET);
            this.feedbackMessage = "";
        }
        final int CONTENT_WIDTH = 48;

        // top border
        System.out.print(ConsoleUtils.GRAY + "\n=============" + ConsoleUtils.RESET + " Caldera Bunker (main hub) "
                + ConsoleUtils.GRAY + "=============\n\n" + ConsoleUtils.RESET);

        // stats
        String statusLabel = "Status    : " + player.getName() + " (";
        String hpValue = player.getHp() + "/" + player.getMaxHp() + " HP";
        String statusSuffix = ")";

        int statusVisibleLength = statusLabel.length() + hpValue.length() + statusSuffix.length();
        int statusPadding = CONTENT_WIDTH - statusVisibleLength;
        if (statusPadding < 0)
            statusPadding = 0;

        System.out.print(statusLabel + ConsoleUtils.GREEN + hpValue + ConsoleUtils.RESET + statusSuffix);
        System.out.print(" ".repeat(statusPadding) + "\n");

        // mission
        String missionContent = "Mission   : Find the 4 Components of the Regulator.";
        int missionPadding = CONTENT_WIDTH - missionContent.length();
        if (missionPadding < 0)
            missionPadding = 0;

        System.out.print(missionContent + " ".repeat(missionPadding) + "\n");

        // med bot / revival
        String medBotLabel = "Revival   : ";
        String medBotValue = this.reviveCharges + "/3";

        int medBotVisibleLength = medBotLabel.length() + medBotValue.length();
        int medBotPadding = CONTENT_WIDTH - medBotVisibleLength;
        if (medBotPadding < 0)
            medBotPadding = 0;

        System.out.print(medBotLabel + ConsoleUtils.RED + medBotValue + ConsoleUtils.RESET);
        System.out.print(" ".repeat(medBotPadding) + "\n");

        System.out.println(
                ConsoleUtils.GRAY + "\n=====================================================" + ConsoleUtils.RESET);
        // end of header

        System.out.println("\nMission Progress:");
        String m1 = monster1Defeated ? ConsoleUtils.GREEN + "FINISHED" + ConsoleUtils.RESET
                : ConsoleUtils.RED + "NOT YET" + ConsoleUtils.RESET;
        String m2 = monster2Defeated ? ConsoleUtils.GREEN + "FINISHED" + ConsoleUtils.RESET
                : (monster1Defeated ? ConsoleUtils.YELLOW + "AVAILABLE" + ConsoleUtils.RESET
                        : ConsoleUtils.RED + "LOCKED" + ConsoleUtils.RESET);
        String m3 = monster3Defeated ? ConsoleUtils.GREEN + "FINISHED" + ConsoleUtils.RESET
                : (monster2Defeated ? ConsoleUtils.YELLOW + "AVAILABLE" + ConsoleUtils.RESET
                        : ConsoleUtils.RED + "LOCKED" + ConsoleUtils.RESET);
        String m4 = boss4Defeated ? ConsoleUtils.GREEN + "FINISHED" + ConsoleUtils.RESET
                : (monster3Defeated ? ConsoleUtils.YELLOW + "AVAILABLE" + ConsoleUtils.RESET
                        : ConsoleUtils.RED + "LOCKED" + ConsoleUtils.RESET);

        System.out.println("1. [Service Tunnels] - " + m1);
        System.out.println("2. [Outer Power Station] - " + m2);
        System.out.println("3. [Generator Building] - " + m3);
        System.out.println("4. [Core Control Room] - " + m4);

        System.out.println("\nOptions:");
        System.out.println("1. Explore the Next Mission Location");
        System.out.println("2. View Status & Inventory");
        System.out.println("3. Exit the Game");

        int choice = input.getInt("Your choice (1-3): ", 1, 3);

        // menu choice
        switch (choice) {
            case 1:
                exploreNextLocation();
                break;
            case 2:
                this.feedbackMessage = showInventoryMenu(false);
                break;
            case 3:
                System.out.print("Are you sure you want to exit?? (y/n)");
                if (input.getString("").equalsIgnoreCase("y")) {
                    isGameRunning = false;
                    this.hasQuit = true;
                    ConsoleUtils.clear();
                    Narrator.showEndingB_Fail();
                }
                break;
        }
    }

    // explore next locs / mission with many of condi's
    private void exploreNextLocation() {
        if (!monster1Defeated) {
            Narrator.showLocation("Service Tunnels");
            input.pressEnterToContinue();
            startBattle(monster1);

            if (!monster1.isAlive() && isGameRunning) {
                monster1Defeated = true;
                player.addItemToInventory(item1);
                boolean leveledUp = player.addExp(50);
                if (leveledUp) {
                    this.feedbackMessage = " LEVEL UP! You are now Level " + player.getLevel()
                            + "! Stats increased. ";
                }
            }
        } else if (!monster2Defeated) {
            Narrator.showLocation("Outer Power Station");
            input.pressEnterToContinue();
            startBattle(monster2);
            if (!monster2.isAlive() && isGameRunning) {
                monster2Defeated = true;
                player.addItemToInventory(monster2.dropItem());
                player.addItemToInventory(item2);
                boolean leveledUp = player.addExp(75);
                if (leveledUp) {
                    this.feedbackMessage = " LEVEL UP! You are now Level " + player.getLevel()
                            + "! Stats increased. ";
                }
            }
        } else if (!monster3Defeated) {
            Narrator.showLocation("Generator Building");
            System.out.println("The map shows two routes:");
            System.out.println("1. Go through the Main Hall (Guarded by Scavenger Heavy)");
            System.out.println("2. Go through the Coolant Tunnels (Alpha Crawler's Nest)");
            int rute = input.getInt("Choose route (1-2): ", 1, 2);

            Monster monsterPilihan = (rute == 1) ? monster3a : monster3b;
            startBattle(monsterPilihan);

            if (!monsterPilihan.isAlive() && isGameRunning) {
                monster3Defeated = true;
                player.addItemToInventory(monsterPilihan.dropItem());
                player.addItemToInventory(item3);
                boolean leveledUp = player.addExp(150); // FIXED
                if (leveledUp) {
                    this.feedbackMessage = " LEVEL UP! You are now Level " + player.getLevel()
                            + "! Stats increased. ";
                }
            }
        } else if (!boss4Defeated) {
            Narrator.showLocation("Core Control Room");
            input.pressEnterToContinue();
            startBattle(boss4);
            if (!boss4.isAlive() && isGameRunning) {
                boss4Defeated = true;
                player.addItemToInventory(boss4.dropItem());
                boolean leveledUp = player.addExp(300); // FIXED
                if (leveledUp) {
                    this.feedbackMessage = " LEVEL UP! You are now Level " + player.getLevel()
                            + "! Stats increased. ";
                }
                checkWinCondition();
            }
        } else {
            System.out.println("All missions are complete. You have saved the Caldera Bunker!");
        }

        if (isGameRunning) {
            input.pressEnterToContinue();
        }
    }

    // start battle
    private void startBattle(Monster monster) {
        ConsoleUtils.clear();
        Narrator.showBattleIntro(monster.getName());

        // preparing monster ascii art
        String monsterName = monster.getName();
        if (monsterName.equals("Pale Crawler"))
            AsciiArt.showPaleCrawler();
        else if (monsterName.equals("Soot-lung Scavenger"))
            AsciiArt.showSootScavenger();
        else if (monsterName.equals("Scavenger Heavy"))
            AsciiArt.showScavengerHeavy();
        else if (monsterName.equals("Alpha Crawler"))
            AsciiArt.showAlphaCrawler();
        else if (monsterName.equals("Station Sentinel"))
            AsciiArt.showStationSentinel();

        while (player.isAlive() && monster.isAlive() && isGameRunning) {
            ConsoleUtils.clear();

            // add monster ascii by name
            if (monsterName.equals("Pale Crawler"))
                AsciiArt.showPaleCrawler();
            else if (monsterName.equals("Soot-lung Scavenger"))
                AsciiArt.showSootScavenger();
            else if (monsterName.equals("Scavenger Heavy"))
                AsciiArt.showScavengerHeavy();
            else if (monsterName.equals("Alpha Crawler"))
                AsciiArt.showAlphaCrawler();
            else if (monsterName.equals("Station Sentinel"))
                AsciiArt.showStationSentinel();

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
                player.heal(player.getMaxHp());
                monster.heal(monster.getMaxHp());
                ConsoleUtils.clear();
                // 
                
                AsciiArt.showPlayerFleeing();
                System.err.println();
                System.out.println(ConsoleUtils.YELLOW + "You chose to flee! Sprinting back to safety..." + ConsoleUtils.RESET);

                this.feedbackMessage = "You fled from " + monster.getName()
                        + ". You are rested and ready to try again.";
                return;
            }

            // gate for checkpoint here
            if (!player.isAlive()) {
                // revive check
                if (this.reviveCharges > 0) {
                    Narrator.showRevivePrompt(this.reviveCharges);
                    String reviveChoice = input.getString("Call for help? (y/n): "); // ask revive?

                    if (reviveChoice.equalsIgnoreCase("y")) {
                        this.reviveCharges--;
                        // player & monster heal
                        player.heal(player.getMaxHp());
                        monster.heal(monster.getMaxHp());
                        // ConsoleUtils.clear();

                        Narrator.showReviveSuccess();
                        this.feedbackMessage = "You were rescued by a Med-Bot. The monster remains, but you are alive.";
                        return; // respawn / return to menu
                    } else {
                        System.out.println("\n" + ConsoleUtils.RED + player.getName() + " has been defeated!"
                                + ConsoleUtils.RESET);
                        isGameRunning = false;
                        ConsoleUtils.clear();
                        Narrator.showEndingB_Fail();
                        break;
                    }
                } else {
                    Narrator.showNoChargesLeft();
                    System.out.println(
                            "\n" + ConsoleUtils.RED + player.getName() + " has been defeated!" + ConsoleUtils.RESET);
                    isGameRunning = false;
                    ConsoleUtils.clear();
                    Narrator.showEndingB_Fail();
                    break;
                }
            }
            // beating monster
            if (!monster.isAlive()) {
                System.out.println("\n[!] " + ConsoleUtils.RESET + monster.getName() + " has been"
                        + ConsoleUtils.RED + " DEFEATED!"
                        + ConsoleUtils.RESET);
                break;
            }

            if (turnUsed) {
                player.decrementHealCooldown();
                System.out.println(ConsoleUtils.RED + "\nEnemy Turn:" + ConsoleUtils.RESET);
                monster.attack(player);

                if (!player.isAlive()) {
                    if (this.reviveCharges > 0) {
                        Narrator.showRevivePrompt(this.reviveCharges);
                        String reviveChoice = input.getString("Call for help? (y/n): ");

                        if (reviveChoice.equalsIgnoreCase("y")) {
                            this.reviveCharges--;
                            player.heal(player.getMaxHp());
                            monster.heal(monster.getMaxHp());
                            // ConsoleUtils.clear();

                            Narrator.showReviveSuccess();
                            this.feedbackMessage = "You were rescued by a Med-Bot. The monster remains, but you are alive.";
                            return;
                        } else {
                            System.out.println("\n" + ConsoleUtils.RED + player.getName() + " has been defeated!"
                                    + ConsoleUtils.RESET);
                            isGameRunning = false;
                            ConsoleUtils.clear();
                            Narrator.showEndingB_Fail();
                            break;
                        }
                    } else {
                        Narrator.showNoChargesLeft();
                        System.out.println("\n" + ConsoleUtils.RED + player.getName() + " has been defeated!"
                                + ConsoleUtils.RESET);
                        isGameRunning = false;
                        ConsoleUtils.clear();
                        Narrator.showEndingB_Fail();
                        break;
                    }
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
            System.out.println(ConsoleUtils.BLUE + "\nOpening Backpack (Consumable only):" + ConsoleUtils.RESET);
            List<Consumable> consumables = player.getConsumableItems();

            if (consumables.isEmpty()) {
                System.out.println("You have no usable items in combat.");
                return "";
            }

            System.out.println(ConsoleUtils.GRAY + "--------------------------------------" + ConsoleUtils.RESET);
            for (int i = 0; i < consumables.size(); i++) {
                Consumable item = consumables.get(i);
                System.out.println((i + 1) + ". " + item.getName() + ConsoleUtils.GREEN + " (+" + item.getHpBonus()
                        + " HP)" + ConsoleUtils.RESET);
            }
            System.out.println(ConsoleUtils.GRAY + "--------------------------------------" + ConsoleUtils.RESET);

            int itemChoice = input.getInt("Enter item number (0 to Cancel): ", 0,
                    consumables.size());
            if (itemChoice == 0) {
                System.out.println("Canceled item use. Turn not used.");
                return "";
            }

            Consumable itemDipilih = consumables.get(itemChoice - 1);
            feedback = player.useConsumable(itemDipilih);
            return feedback;
        } else {
            ConsoleUtils.clear();
            player.showStatus();
            player.showInventory();

            System.out.println("\nInventory Options:");
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

                Item itemDipilih = player.getInventory().get(itemChoice - 1);

                if (itemDipilih instanceof Consumable) {
                    feedback = player.useConsumable((Consumable) itemDipilih); // FIXED
                } else if (itemDipilih instanceof Weapon || itemDipilih instanceof Armor) {
                    feedback = player.equipItem(itemDipilih);
                } else if (itemDipilih instanceof QuestItem) {
                    feedback = itemDipilih.getName() + " is a quest item and cannot be used.";
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

    // check win condi's
    private void checkWinCondition() {
        List<Item> inventory = new ArrayList<>();
        inventory.addAll(player.getInventory());

        if (inventory.contains(item1) &&
                inventory.contains(item2) &&
                inventory.contains(item3) &&
                inventory.contains(item4)) {
            isGameRunning = false;
            this.hasWon = true;
            ConsoleUtils.clear();
            Narrator.showEndingA_Success();
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

    // applying
    private void buildPlayerMenu() {
        String healStatus = player.getHealCooldown() > 0 ? "(CD: " + player.getHealCooldown() + ")" : "(Ready)";
        String options = String.format(ConsoleUtils.BLUE + "|" + ConsoleUtils.RESET
                + " (1) Attack  (2) Use Item  (3) Heal %-7s  (4) Flee          " + ConsoleUtils.BLUE + "|"
                + ConsoleUtils.RESET, healStatus);
        String top = "+-- Your Turn! -------------------------------------------------+";
        String bottom = "+-- Choose one option: -----------------------------------------+";
        System.out.println(ConsoleUtils.BLUE + top + ConsoleUtils.RESET);
        System.out.println(options);
        System.out.println(ConsoleUtils.BLUE + bottom + ConsoleUtils.RESET);
    }
}