package models.monsters;

import models.Entity;
import models.items.Item;
import utils.ConsoleUtils;

public class BossMonster extends Monster {
    private int chargeMeter;
    private int specialAttackPower;

    public BossMonster(String name, int maxHp, int attackPower, int defensePower, int specialAttackPower,
            Item itemDrop) {
        super(name, maxHp, attackPower, defensePower, itemDrop);
        this.specialAttackPower = specialAttackPower;
        this.chargeMeter = 0;
    }

    // override from entity
    @Override
    public void attack(Entity target) {
        if (chargeMeter >= 2) {
            specialAttack(target);
        } else {
            super.attack(target);
            chargeMeter++;
            if (utils.Narrator.getLanguage() == utils.Language.ID) {
                System.out.println(ConsoleUtils.YELLOW + this.name + " sedang mengisi energi..." + ConsoleUtils.RESET);
            } else {
                System.out.println(ConsoleUtils.YELLOW + this.name + " charging energy..." + ConsoleUtils.RESET);
            }
        }
    }
    // 

    public void specialAttack(Entity target) {
        if (utils.Narrator.getLanguage() == utils.Language.ID) {
            System.out.println("!!! " + ConsoleUtils.RED + this.name + " menggunakan serangan spesial 'OVERLOAD PULSE' !!!"
                + ConsoleUtils.RESET);
        } else {
            System.out.println("!!! " + ConsoleUtils.RED + this.name + " using a special attack 'OVERLOAD PULSE' !!!"
                + ConsoleUtils.RESET);
        }
        target.tookDamage(this.specialAttackPower);
        this.chargeMeter = 0;
    }
}