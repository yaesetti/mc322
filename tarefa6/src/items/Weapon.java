package items;

import characters.Character;
import combat.DamageType;
import utils.Dice;

/**
 * Represents a weapon item in the U-Energy RPG game.
 * <p>
 * A weapon is an equipable item that deals damage and may have specific requirements
 * such as minimum level and damage type. It is associated with a {@link Character} user
 * and implements the {@link Item} interface for serialization and display.
 */
public abstract class Weapon implements Item {

    /**
     * The name of the weapon.
     */
    private final String name;

    /**
     * The damage value of the weapon.
     */
    private int damage;

    /**
     * The minimum character level required to equip the weapon.
     */
    private final int minLevel;

    /**
     * The type of damage this weapon deals (e.g., physical, energy).
     */
    private DamageType damageType;

    /**
     * The character currently using this weapon.
     */
    private Character user;

    /**
     * Constructs a weapon with the specified attributes.
     * <p>
     * Damage is calculated using a dice roll and bonus.
     *
     * @param name        the name of the weapon
     * @param damageDice  an array with two elements: [number of dice, sides per die]
     * @param damageBonus a flat bonus added to the rolled damage
     * @param minLevel    the minimum level required to equip the weapon
     * @param damageType  the type of damage the weapon deals
     * @param user        the character who will use the weapon
     */
    public Weapon(String name, int[] damageDice, int damageBonus, int minLevel,
                  DamageType damageType, Character user) {
        this.name = name;
        this.damage = Dice.roll(damageDice[0], damageDice[1]) + damageBonus;
        this.minLevel = minLevel;
        this.damageType = damageType;
        this.user = user;
    }

    /**
     * Returns the name of the weapon.
     *
     * @return the weapon's name
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Returns the current damage value of the weapon.
     *
     * @return the weapon's damage
     */
    public int getDamage() {
        return this.damage;
    }

    /**
     * Recalculates and sets the weapon's damage using new dice and bonus.
     *
     * @param damageDice  an array with two elements: [number of dice, sides per die]
     * @param damageBonus a flat bonus added to the rolled damage
     */
    public void setDamage(int[] damageDice, int damageBonus) {
        this.damage = Dice.roll(damageDice[0], damageDice[1]) + damageBonus;
    }

    /**
     * Returns the minimum level required to equip the weapon.
     *
     * @return the required level
     */
    public int getMinLevel() {
        return this.minLevel;
    }

    /**
     * Returns the type of damage this weapon deals.
     *
     * @return the damage type
     */
    public DamageType getDamageType() {
        return this.damageType;
    }

    /**
     * Returns the character currently using this weapon.
     *
     * @return the weapon's user
     */
    public Character getUser() {
        return this.user;
    }

    /**
     * Sets the character who will use this weapon.
     *
     * @param newUser the character to assign as user
     */
    public void setUser(Character newUser) {
        this.user = newUser;
    }

    /**
     * Sets a new damage type for the weapon.
     *
     * @param newDamageType the new damage type
     */
    public void setDamageType(DamageType newDamageType) {
        this.damageType = newDamageType;
    }
}
