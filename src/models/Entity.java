package models;

import utils.ConsoleUtils;

public abstract class Entity {
    protected String name;
    protected int hp;
    protected int maxHp;

    public Entity(String name, int maxHp) {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;
    }

    // getters
    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    // base of turn-base logic/mechanics
    public abstract void attack(Entity target);

    // took dmg
    public void tookDamage(int damage) {
        this.hp -= damage;
        if (this.hp < 0) {
            this.hp = 0;
        }
    }

    // heal mechanics
    public void heal(int amount) {
        this.hp += amount;
        // scenario: 93 + 25 = 118
        if (this.hp > this.maxHp) { // 118 > 100(lvl 1) = true
            this.hp = this.maxHp; // hp = maxHp(100) depends on level
        }
        if (utils.Narrator.getLanguage() == utils.Language.ID) {
            System.out.println("\n" + ConsoleUtils.GREEN + this.name + " memulihkan " + amount + " HP." + ConsoleUtils.RESET);
        } else {
            System.out.println("\n" + ConsoleUtils.GREEN + this.name + " recovers " + amount + " HP." + ConsoleUtils.RESET);
        }
    }
}