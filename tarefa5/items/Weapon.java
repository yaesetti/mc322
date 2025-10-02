package items;

import characters.Character;
import combat.DamageType;
import utils.Dice;

public abstract class Weapon implements Item{
    private final String name;
    private int damage;
    private final int minLevel;
    private DamageType damageType;
    private Character user;

    public Weapon(String name, int[] damageDice, int damageBonus, int minLevel, DamageType damageType, Character user) {
        this.name = name;
        this.damage = Dice.roll(damageDice[0], damageDice[1]) + damageBonus;
        this.minLevel = minLevel;
        this.damageType = damageType;
        this.user = user;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public int getDamage() {
        return this.damage;
    }

    public void setDamage(int[] damageDice, int damageBonus) {
        this.damage = Dice.roll(damageDice[0], damageDice[1]) + damageBonus;
    }

    public int getMinLevel() {
        return this.minLevel;
    }

    public DamageType getDamageType() {
        return this.damageType;
    }

    public Character getUser() {
        return this.user;
    }

    public void setUser(Character newUser) {
        this.user = newUser;
    }

    public void setDamageType(DamageType newDamageType) {
        this.damageType = newDamageType;
    }
}
