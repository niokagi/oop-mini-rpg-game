package models;

import models.items.*;
import utils.ConsoleUtils;
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

    private void updateStats() {
        this.totalAttack = this.baseAttack + (equippedWeapon != null ? equippedWeapon.getAttackBonus() : 0);
        this.totalDefense = this.baseDefense + (equippedArmor != null ? equippedArmor.getDefenseBonus() : 0);
    }

    // method from entity
    @Override
    public void attack(Entity target) {
        System.out.println();
        System.out.println(this.name + " attacks " + target.getName() + " with "
                + (equippedWeapon != null ? equippedWeapon.getName() : "bare hands") + "!");
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
        String[] reactions = { "Ugh!", "Gah!", "That stings!", "Damn it!" }; // also add text effect when attacking, for
                                                                             // next (!)
        String reaction = reactions[ThreadLocalRandom.current().nextInt(reactions.length)];
        String dmgReceivedText = ConsoleUtils.RED + damageDiterima + " damage: ";
        System.out.println(this.name + " receives " + dmgReceivedText + "\"" + reaction
                + "\"" + ConsoleUtils.RESET);
    }
    //

    // + exp
    public boolean addExp(int amount) {
        this.exp += amount;
        System.out.println(ConsoleUtils.CYAN + this.name + " gained " + amount + " EXP." + ConsoleUtils.RESET);
        if (this.exp >= this.expToNextLevel) {
            levelUp();
            return true;
        }
        return false;
    }

    // lvl up
    private void levelUp() {
        this.level++;
        this.exp -= this.expToNextLevel;
        this.expToNextLevel *= 1.5;

        this.maxHp += 20;
        this.hp = this.maxHp;
        this.baseAttack += 3;
        this.baseDefense += 1;

        updateStats();
    }

    // add item into inventory
    public void addItemToInventory(Item item) {
        this.inventory.add(item);
        System.out.println(ConsoleUtils.GREEN + item.getName() + " was added to your inventory." + ConsoleUtils.RESET);
    }

    // equip
    public String equipItem(Item itemToEquip) {
        String feedback = "";
        if (itemToEquip instanceof Weapon) {
            if (this.equippedWeapon != null) {
                this.inventory.add(this.equippedWeapon);
            }
            this.equippedWeapon = (Weapon) itemToEquip;
            this.inventory.remove(itemToEquip);
            feedback = itemToEquip.getName() + " is now equipped as your weapon.";
            System.out.println(ConsoleUtils.YELLOW + feedback + ConsoleUtils.RESET);
        } else if (itemToEquip instanceof Armor) {
            if (this.equippedArmor != null) {
                this.inventory.add(this.equippedArmor);
            }
            this.equippedArmor = (Armor) itemToEquip;
            this.inventory.remove(itemToEquip);
            feedback = itemToEquip.getName() + " is now equipped as your armor.";
            System.out.println(ConsoleUtils.YELLOW + feedback + ConsoleUtils.RESET);
        } else {
            feedback = itemToEquip.getName() + " cannot be equipped.";
            System.out.println(feedback);
        }

        updateStats();
        return feedback;
    }

    // unequip
    public String unequipItem(String slot) {
        String feedback = "";
        if (slot.equalsIgnoreCase("weapon") && this.equippedWeapon != null) {
            feedback = this.equippedWeapon.getName() + " has been unequipped.";
            System.out.println(ConsoleUtils.YELLOW + feedback + ConsoleUtils.RESET);
            this.inventory.add(this.equippedWeapon);
            this.equippedWeapon = null;
        } else if (slot.equalsIgnoreCase("armor") && this.equippedArmor != null) {
            feedback = this.equippedArmor.getName() + " has been unequipped.";
            System.out.println(ConsoleUtils.YELLOW + feedback + ConsoleUtils.RESET);
            this.inventory.add(this.equippedArmor);
            this.equippedArmor = null;
        } else {
            feedback = "This slot is empty.";
            System.out.println(feedback);
        }

        updateStats();
        return feedback;
    }

    public String useConsumable(Consumable item) {
        heal(item.getHpBonus());
        this.inventory.remove(item);
        String feedback = item.getName() + " has been used.";
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

        String title = "STATUS";
        int titlePadding = CONTENT_WIDTH - title.length();
        sb.append("| " + ConsoleUtils.CYAN + title + ConsoleUtils.RESET
                + " ".repeat(titlePadding) + " |\n");
        // name
        String nameContent = "Name : " + this.name;
        int namePadding = CONTENT_WIDTH - nameContent.length();
        sb.append("| " + nameContent + " ".repeat(namePadding) + " |\n");
        // lvl
        String levelContent = "Level : " + this.level + " (EXP: " + this.exp + "/" + this.expToNextLevel + ")";
        int levelPadding = CONTENT_WIDTH - levelContent.length();
        sb.append("| " + levelContent + " ".repeat(levelPadding) + " |\n");
        // hp
        String hpLabel = "HP : ";
        String hpValue = this.hp + "/" + this.maxHp;
        String hpContent = hpLabel + ConsoleUtils.GREEN + hpValue + ConsoleUtils.RESET;
        int hpVisibleLength = hpLabel.length() + hpValue.length();
        int hpPadding = CONTENT_WIDTH - hpVisibleLength;
        sb.append("| " + hpContent + " ".repeat(hpPadding) + " |\n");
        // att
        String attackLabel = "Attack: ";
        String attackValue = " (Base: " + this.baseAttack + ")";
        String attackContent = attackLabel + ConsoleUtils.RED + this.totalAttack + ConsoleUtils.RESET + attackValue;
        int attackVisibleLength = attackLabel.length() + String.valueOf(this.totalAttack).length()
                + attackValue.length();
        int attackPadding = CONTENT_WIDTH - attackVisibleLength;
        sb.append("| " + attackContent + " ".repeat(attackPadding) + " |\n");
        // def
        String defenseLabel = "Defense: ";
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
        String eqTitle = "EQUIPMENT";
        int eqTitlePadding = CONTENT_WIDTH - eqTitle.length();
        sb.append("| " + ConsoleUtils.CYAN + eqTitle + ConsoleUtils.RESET
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
            weaponContent = "Weapon: Empty";
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
            armorContent = "Armor: Empty";
            armorVisibleLength = armorContent.length();
        }
        int armorPadding = CONTENT_WIDTH - armorVisibleLength;
        sb.append("| " + armorContent + " ".repeat(armorPadding) + " |\n");

        // bottom line
        sb.append(horizontalLine);
        System.out.print(sb.toString());
    }

    public void showInventory() {
        System.out.println(ConsoleUtils.CYAN + "\nBACKPACK CONTENTS (INVENTORY): " + ConsoleUtils.RESET);
        if (inventory.isEmpty()) {
            System.out.println("Backpack is Empty.");
            return;
        }
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
            System.out.println((i + 1) + ". " + color + item.getName() + " " + stats + ConsoleUtils.RESET);
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
            System.out.println(ConsoleUtils.RED + "Heal skill is on Cooldown! (" + this.healCooldown
                    + " more turns)" + ConsoleUtils.RESET);
            return false;
        }

        // heal calc 
        int healAmount = this.maxHp / 4;
        this.heal(healAmount);
        this.healCooldown = 3;
        System.out.println(this.name + " focuses energy and recovers " + healAmount + " HP.");
        System.out.println("Heal Skill is now on cooldown (3 turns).");
        return true;
    }
}