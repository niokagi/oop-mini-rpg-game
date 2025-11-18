package models.items;

public class Armor extends Item {
    private int defenseBonus;

    public Armor(String name, int defenseBonus) {
        super(name);
        this.defenseBonus = defenseBonus;
    }

    public int getDefenseBonus() {
        return defenseBonus;
    }
}
