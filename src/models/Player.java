package models;

import models.items.*;
import utils.ConsoleUtils;
import utils.Narrator;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Player extends Entity {
    private int level;
    private int exp;
    private int expToNextLevel;
    private int baseAttack;
    private int baseDefense;
    private int totalAttack;
    private int totalDefense;
    private List<Item> inventory;
    private Weapon equippedWeapon;
    private Armor equippedArmor;
    private int healCooldown;

    // begin of inits
    public Player(String name) {
        super(name, 100);
        this.level = 1;
        this.exp = 0;
        this.expToNextLevel = 50;
        this.baseAttack = 5;
        this.baseDefense = 1;
        this.inventory = new ArrayList<>();
        this.healCooldown = 0;

        Weapon starterWeapon = new Weapon("Lead Pipe", 5);
        Armor starterArmor = new Armor("Tattered Rags", 2);
        this.inventory.add(starterWeapon);
        this.inventory.add(starterArmor);
        this.inventory.add(new Consumable("Dirty Bandage", 25));

        equipItem(starterWeapon);
        equipItem(starterArmor);
    }

    // update stats
    private void updateStats() {
        this.totalAttack = this.baseAttack + (equippedWeapon != null ? equippedWeapon.getAttackBonus() : 0);
        this.totalDefense = this.baseDefense + (equippedArmor != null ? equippedArmor.getDefenseBonus() : 0);
    }

    // method from entity
    @Override
    public void attack(Entity target) {
        System.out.println();
        if (Narrator.getLanguage() == utils.Language.ID) {
            System.out.println(this.name + " menyerang " + target.getName() + " menggunakan "
                    + (equippedWeapon != null ? equippedWeapon.getName() : "tangan kosong") + "!");
        } else {
            System.out.println(this.name + " attacks " + target.getName() + " with "
                    + (equippedWeapon != null ? equippedWeapon.getName() : "bare hands") + "!");
        }
        target.tookDamage(this.totalAttack);
    }

    @Override
    public void tookDamage(int damage) {
        // formula
        int damageDiterima = damage - this.totalDefense;
        if (damageDiterima < 1) {
            damageDiterima = 1;
        }

        super.tookDamage(damageDiterima);
        String[] reactions;
        if (Narrator.getLanguage() == utils.Language.ID) {
            reactions = new String[]{"Aduh!", "Argh!", "Sakit juga!", "Sialan!"};
        } else {
            reactions = new String[]{"Ugh!", "Gah!", "That stings!", "Damn it!"};
        }
        String reaction = reactions[ThreadLocalRandom.current().nextInt(reactions.length)];
        String dmgReceivedText = ConsoleUtils.RED + damageDiterima + " damage: ";
        System.out.println(ConsoleUtils.GRAY + this.name + " receives " + dmgReceivedText + "\"" + reaction
                + "\"" + ConsoleUtils.RESET);
    }

    // + exp
    public boolean addExp(int amount) {
        this.exp += amount;
        if (Narrator.getLanguage() == utils.Language.ID) {
            System.out.println("[!] " + this.name + " memperoleh " + ConsoleUtils.CYAN + amount + " EXP." + ConsoleUtils.RESET);
        } else {
            System.out.println("[!] " + this.name + " gained " + ConsoleUtils.CYAN + amount + " EXP." + ConsoleUtils.RESET);
        }
        // check exp constraint
        if (this.exp >= this.expToNextLevel) {
            levelUp();
            return true;
        }
        return false;
    }

    // lvl up
    private void levelUp() {
        this.level++;
        // scaling exp
        this.exp -= this.expToNextLevel;
        this.expToNextLevel *= 1.5;

        this.maxHp += 20;
        this.hp = this.maxHp; // full heal/recovery when level up
        this.baseAttack += 3;
        this.baseDefense += 1;

        updateStats();
    }

    // add item into inventory
    // after beating monster/enemy
    public void addItemToInventory(Item item) {
        this.inventory.add(item);
        if (Narrator.getLanguage() == utils.Language.ID) {
            System.out.println("[!] \"" + item.getName() + "\" telah ditambahkan ke ransel Anda." + ConsoleUtils.RESET);
        } else {
            System.out.println("[!] \"" + item.getName() + "\" was added to your inventory." + ConsoleUtils.RESET);
        }
    }

    // item equipment logic
    public String equipItem(Item itemToEquip) {
        String feedback = "";
        // if item == weapon
        if (itemToEquip instanceof Weapon) {
            // auto disposing of old items
            if (this.equippedWeapon != null) {
                this.inventory.add(this.equippedWeapon);
            }
            this.equippedWeapon = (Weapon) itemToEquip;
            this.inventory.remove(itemToEquip);
            if (Narrator.getLanguage() == utils.Language.ID) {
                feedback = itemToEquip.getName() + " sekarang dipasang sebagai senjata Anda.";
            } else {
                feedback = itemToEquip.getName() + " is now equipped as your weapon.";
            }
            System.out.println(ConsoleUtils.YELLOW + feedback + ConsoleUtils.RESET);
            // armor
        } else if (itemToEquip instanceof Armor) {
            // disposing
            if (this.equippedArmor != null) {
                this.inventory.add(this.equippedArmor);
            }
            this.equippedArmor = (Armor) itemToEquip;
            this.inventory.remove(itemToEquip);
            if (Narrator.getLanguage() == utils.Language.ID) {
                feedback = itemToEquip.getName() + " sekarang dipasang sebagai armor Anda.";
            } else {
                feedback = itemToEquip.getName() + " is now equipped as your armor.";
            }
            System.out.println(ConsoleUtils.YELLOW + feedback + ConsoleUtils.RESET);
        } else {
            if (Narrator.getLanguage() == utils.Language.ID) {
                feedback = itemToEquip.getName() + " tidak bisa dipasang.";
            } else {
                feedback = itemToEquip.getName() + " cannot be equipped.";
            }
            System.out.println(feedback);
        }

        updateStats();
        return feedback;
    }

    // unequip
    public String unequipItem(String slot) {
        String feedback = "";
        if (slot.equalsIgnoreCase("weapon") && this.equippedWeapon != null) {
            if (Narrator.getLanguage() == utils.Language.ID) {
                feedback = this.equippedWeapon.getName() + " telah dilepas.";
            } else {
                feedback = this.equippedWeapon.getName() + " has been unequipped.";
            }
            System.out.println(ConsoleUtils.YELLOW + feedback + ConsoleUtils.RESET);
            this.inventory.add(this.equippedWeapon);
            this.equippedWeapon = null;
        } else if (slot.equalsIgnoreCase("armor") && this.equippedArmor != null) {
            if (Narrator.getLanguage() == utils.Language.ID) {
                feedback = this.equippedArmor.getName() + " telah dilepas.";
            } else {
                feedback = this.equippedArmor.getName() + " has been unequipped.";
            }
            System.out.println(ConsoleUtils.YELLOW + feedback + ConsoleUtils.RESET);
            this.inventory.add(this.equippedArmor);
            this.equippedArmor = null;
        } else {
            if (Narrator.getLanguage() == utils.Language.ID) {
                feedback = "Slot tersebut kosong.";
            } else {
                feedback = "This slot is empty.";
            }
            System.out.println(feedback);
        }

        updateStats();
        return feedback;
    }

    public String useConsumable(Consumable item) {
        heal(item.getHpBonus());
        this.inventory.remove(item);
        String feedback;
        if (Narrator.getLanguage() == utils.Language.ID) {
            feedback = ConsoleUtils.GRAY + item.getName() + " telah digunakan.";
        } else {
            feedback = ConsoleUtils.GRAY + item.getName() + " has been used.";
        }
        System.out.println(feedback);
        return feedback;
    }

    //
    public void showStatus() {
        final int CONTENT_WIDTH = 45;
        String horizontalLine = "+" + "-".repeat(CONTENT_WIDTH + 2) + "+\n";
        String emptyLine = "| " + " ".repeat(CONTENT_WIDTH) + " |\n";

        StringBuilder sb = new StringBuilder();
        sb.append(horizontalLine);

        // status header
        String title = (Narrator.getLanguage() == utils.Language.ID) ? "Status" : "Status";
        int titlePadding = CONTENT_WIDTH - title.length();
        sb.append("| " + ConsoleUtils.BLUE + title + ConsoleUtils.RESET
                + " ".repeat(titlePadding) + " |\n");
        // name
        String nameContent = (Narrator.getLanguage() == utils.Language.ID) ? "Nama : " + this.name : "Name : " + this.name;
        int namePadding = CONTENT_WIDTH - nameContent.length();
        sb.append("| " + nameContent + " ".repeat(namePadding) + " |\n");
        // lvl
        String levelContent = (Narrator.getLanguage() == utils.Language.ID) ? "Level : " + this.level + " (EXP: " + this.exp + "/" + this.expToNextLevel + ")" : "Level : " + this.level + " (EXP: " + this.exp + "/" + this.expToNextLevel + ")";
        int levelPadding = CONTENT_WIDTH - levelContent.length();
        sb.append("| " + levelContent + " ".repeat(levelPadding) + " |\n");
        // hp
        String hpLabel = (Narrator.getLanguage() == utils.Language.ID) ? "HP : " : "HP : ";
        String hpValue = this.hp + "/" + this.maxHp;
        String hpContent = hpLabel + ConsoleUtils.GREEN + hpValue + ConsoleUtils.RESET;
        int hpVisibleLength = hpLabel.length() + hpValue.length();
        int hpPadding = CONTENT_WIDTH - hpVisibleLength;
        sb.append("| " + hpContent + " ".repeat(hpPadding) + " |\n");
        // att
        String attackLabel = (Narrator.getLanguage() == utils.Language.ID) ? "Serangan: " : "Attack: ";
        String attackValue = " (Base: " + this.baseAttack + ")";
        String attackContent = attackLabel + ConsoleUtils.RED + this.totalAttack + ConsoleUtils.RESET + attackValue;
        int attackVisibleLength = attackLabel.length() + String.valueOf(this.totalAttack).length()
                + attackValue.length();
        int attackPadding = CONTENT_WIDTH - attackVisibleLength;
        sb.append("| " + attackContent + " ".repeat(attackPadding) + " |\n");
        // def
        String defenseLabel = (Narrator.getLanguage() == utils.Language.ID) ? "Pertahanan: " : "Defense: ";
        String defenseValue = " (Base: " + this.baseDefense + ")";
        String defenseContent = defenseLabel + ConsoleUtils.BLUE + this.totalDefense + ConsoleUtils.RESET
                + defenseValue;
        int defenseVisibleLength = defenseLabel.length() + String.valueOf(this.totalDefense).length()
                + defenseValue.length();
        int defensePadding = CONTENT_WIDTH - defenseVisibleLength;
        sb.append("| " + defenseContent + " ".repeat(defensePadding) + " |\n");
        // separator
        sb.append(emptyLine);

        // equipment header
        String eqTitle = (Narrator.getLanguage() == utils.Language.ID) ? "Perlengkapan" : "Equipment";
        int eqTitlePadding = CONTENT_WIDTH - eqTitle.length();
        sb.append("| " + ConsoleUtils.BLUE + eqTitle + ConsoleUtils.RESET
                + " ".repeat(eqTitlePadding) + " |\n");
        //
            String weaponContent;
        int weaponVisibleLength;
        if (equippedWeapon != null) {
            String weaponLabel = "Weapon: " + equippedWeapon.getName();
            String weaponBonus = " (+" + equippedWeapon.getAttackBonus() + " Atk)";
            weaponContent = weaponLabel + " (" + ConsoleUtils.RED + "+" + equippedWeapon.getAttackBonus() + " Atk"
                    + ConsoleUtils.RESET + ")";
            weaponVisibleLength = weaponLabel.length() + weaponBonus.length();
        } else {
            weaponContent = (Narrator.getLanguage() == utils.Language.ID) ? "Senjata: Kosong" : "Weapon: Empty";
            weaponVisibleLength = weaponContent.length();
        }
        int weaponPadding = CONTENT_WIDTH - weaponVisibleLength;
        sb.append("| " + weaponContent + " ".repeat(weaponPadding) + " |\n");
        //
        String armorContent;
        int armorVisibleLength;
        if (equippedArmor != null) {
            String armorLabel = "Armor: " + equippedArmor.getName();
            String armorBonus = " (+" + equippedArmor.getDefenseBonus() + " Def)";
            armorContent = armorLabel + " (" + ConsoleUtils.BLUE + "+" + equippedArmor.getDefenseBonus() + " Def"
                    + ConsoleUtils.RESET + ")";
            armorVisibleLength = armorLabel.length() + armorBonus.length();
        } else {
            armorContent = (Narrator.getLanguage() == utils.Language.ID) ? "Armor: Kosong" : "Armor: Empty";
            armorVisibleLength = armorContent.length();
        }
        int armorPadding = CONTENT_WIDTH - armorVisibleLength;
        sb.append("| " + armorContent + " ".repeat(armorPadding) + " |\n");

        // separator
        sb.append(emptyLine);

        // Inventory Header
        String invTitle = (Narrator.getLanguage() == utils.Language.ID) ? "Isi Ransel" : "Backpack Contents";
        int invTitlePadding = CONTENT_WIDTH - invTitle.length();
        sb.append("| " + ConsoleUtils.BLUE + invTitle + ConsoleUtils.RESET
                + " ".repeat(invTitlePadding) + " |\n");

        if (inventory.isEmpty()) {
            String emptyMsg = (Narrator.getLanguage() == utils.Language.ID) ? "Ransel kosong." : "Backpack is Empty.";
            int emptyPadding = CONTENT_WIDTH - emptyMsg.length();
            sb.append("| " + emptyMsg + " ".repeat(emptyPadding) + " |\n");
        } else {
            for (int i = 0; i < inventory.size(); i++) {
                Item item = inventory.get(i);
                String stats = "";
                String color = ConsoleUtils.RESET;

                if (item instanceof Weapon) {
                    stats = "(+" + ((Weapon) item).getAttackBonus() + " Atk)";
                    color = ConsoleUtils.RED;
                } else if (item instanceof Armor) {
                    stats = "(+" + ((Armor) item).getDefenseBonus() + " Def)";
                    color = ConsoleUtils.BLUE;
                } else if (item instanceof Consumable) {
                    stats = "(+" + ((Consumable) item).getHpBonus() + " HP)";
                    color = ConsoleUtils.GREEN;
                } else if (item instanceof QuestItem) {
                    stats = "(Quest Item)";
                    color = ConsoleUtils.YELLOW;
                }

                // Format: "1. ItemName (Stats)"
                String itemLabel = (i + 1) + ". " + item.getName();
                String itemDisplay = itemLabel + " " + stats; // Plain text for length calc

                // Truncate if too long (simple safety)
                if (itemDisplay.length() > CONTENT_WIDTH) {
                    // This is a basic truncation, might cut off stats, but keeps box intact
                    // Ideally we'd wrap, but keeping it simple for now as per request
                }

                int itemPadding = CONTENT_WIDTH - itemDisplay.length();
                if (itemPadding < 0)
                    itemPadding = 0;

                // Reconstruct with colors
                String coloredLine = itemLabel + color + " " + stats + ConsoleUtils.RESET;

                sb.append("| " + coloredLine + " ".repeat(itemPadding) + " |\n");
            }
        }

        // Final bottom line
        sb.append(horizontalLine);
        System.out.print(sb.toString());
    }

    public void showInventory() {
        System.out.println(ConsoleUtils.BLUE + "\n" + ((Narrator.getLanguage() == utils.Language.ID) ? "Isi Ransel (Inventory): " : "Backpack Contents (Inventory): ") + ConsoleUtils.RESET);
        if (inventory.isEmpty()) {
            System.out.println((Narrator.getLanguage() == utils.Language.ID) ? "Ransel kosong." : "Backpack is Empty.");
            return;
        }
        for (int i = 0; i < inventory.size(); i++) {
            Item item = inventory.get(i);
            String stats = "";
            String color = ConsoleUtils.RESET;

            // coloring by item types
            if (item instanceof Weapon) {
                stats = "(+" + ((Weapon) item).getAttackBonus() + " Atk)";
                color = ConsoleUtils.RED;
            } else if (item instanceof Armor) {
                stats = "(+" + ((Armor) item).getDefenseBonus() + " Def)";
                color = ConsoleUtils.BLUE;
            } else if (item instanceof Consumable) {
                stats = "(+" + ((Consumable) item).getHpBonus() + " HP)";
                color = ConsoleUtils.GREEN;
            } else if (item instanceof QuestItem) {
                stats = "(Quest Item)";
                color = ConsoleUtils.YELLOW;
            }
            System.out.println((i + 1) + ". " + item.getName() + color + " " + stats + ConsoleUtils.RESET);
        }
    }

    // getters
    // att & dmg
    public int getTotalAttack() {
        return this.totalAttack;
    }

    public int getTotalDefense() {
        return this.totalDefense;
    }

    // inventory
    public List<Item> getInventory() {
        return inventory;
    }

    // show consumable items
    public List<Consumable> getConsumableItems() {
        List<Consumable> consumables = new ArrayList<>();
        for (Item item : this.inventory) {
            if (item instanceof Consumable) {
                consumables.add((Consumable) item);
            }
        }
        return consumables;
    }

    // lvl
    public int getLevel() {
        return this.level;
    }

    // healing cd / cooldown
    public int getHealCooldown() {
        return this.healCooldown;
    }

    // decrement healing cd logic
    public void decrementHealCooldown() {
        if (this.healCooldown > 0) {
            this.healCooldown--;
        }
    }
    // end of getters

    // use heal method
    public boolean useHealSkill() {
        if (this.healCooldown > 0) {
            if (Narrator.getLanguage() == utils.Language.ID) {
                System.out.println(ConsoleUtils.RED + "Skill Healer sedang cooldown! (" + this.healCooldown
                        + " giliran lagi)" + ConsoleUtils.RESET);
            } else {
                System.out.println(ConsoleUtils.RED + "Heal skill is on Cooldown! (" + this.healCooldown
                        + " more turns)" + ConsoleUtils.RESET);
            }
            return false;
        }

        // heal calc
        int healAmount = this.maxHp / 4;
        this.heal(healAmount);
        this.healCooldown = 3;
        if (Narrator.getLanguage() == utils.Language.ID) {
            System.out.println(ConsoleUtils.GRAY + "Skill Heal sekarang dalam cooldown (3 giliran)." );
        } else {
            System.out.println(ConsoleUtils.GRAY + "Heal Skill is now on cooldown (3 turns).");
        }
        return true;
    }
}