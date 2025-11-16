package models.items;

public class Consumable extends Item {
    private int hpBonus;

    public Consumable(String name, int hpBonus) {
        super(name);
        this.hpBonus = hpBonus;
    }

    public int getHpBonus() {
        return hpBonus;
    }
}