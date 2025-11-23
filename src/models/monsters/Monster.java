package models.monsters;

import models.Entity;
import models.items.Item;
import utils.ConsoleUtils;

public class Monster extends Entity {
    protected int attackPower;
    protected int defensePower;
    protected Item itemDrop;

    public Monster(String name, int maxHp, int attackPower, int defensePower, Item itemDrop) {
        super(name, maxHp);
        this.attackPower = attackPower;
        this.defensePower = defensePower;
        this.itemDrop = itemDrop;
    }

    // override from entity
    @Override
    public void attack(Entity target) {
        System.out.println(this.name + " attack " + target.getName() + "!");
        target.tookDamage(this.attackPower);
    }

    @Override
    public void tookDamage(int damage) {
        int damageDiterima = damage - this.defensePower;
        if (damageDiterima < 1) {
            damageDiterima = 1;
        }

        super.tookDamage(damageDiterima);
        // String tookDamage = ConsoleUtils.RED + damageDiterima + ConsoleUtils.RESET;
        String tookDamage = ConsoleUtils.RED + damageDiterima;
        System.out.println(ConsoleUtils.GRAY + this.name + " took " + tookDamage + " damage." + ConsoleUtils.RESET);
    }

    // drop item
    public Item dropItem() {
        return itemDrop;
    }

    // getters
    // ......
    public int getAttackPower() {
        return this.attackPower;
    }

    public int getDefensePower() {
        return this.defensePower;
    }

    // heal monster (restore HP)
    // when player getting revives
    // public void heal(int amount) {
    //     this.hp += amount;
    //     if (this.hp > this.maxHp) {
    //         this.hp = this.maxHp;
    //     }
    // }
}