package items.weapons;

import characters.Character;
import combat.DamageType;
import items.Weapon;

/**
 * Represents a sword weapon in the U-Energy RPG game.
 * <p>
 * A {@code Sword} is a slashing weapon that scales its damage based on sharpness,
 * the user's strength, and the weapon's minimum level requirement.
 */
public class Sword extends Weapon {

    /**
     * The sharpness value of the sword, contributing to bonus damage.
     */
    private int sharpness;

    /**
     * Constructs a new {@code Sword} weapon.
     *
     * @param name      the name of the sword
     * @param minLevel  the minimum level required to equip the weapon
     * @param sharpness the sharpness value used for bonus damage
     * @param user      the character who will use the weapon
     */
    public Sword(String name, int minLevel, int sharpness, Character user) {
        super(name, new int[]{1, 6}, sharpness + minLevel, minLevel, DamageType.Slashing, user);
        this.sharpness = sharpness;
    }

    /**
     * Returns the sharpness value of the sword.
     *
     * @return the sharpness value
     */
    public int getSharpness() {
        return this.sharpness;
    }

    /**
     * Sets a new sharpness value for the sword.
     *
     * @param sharpness the new sharpness value
     */
    public void setSharpness(int sharpness) {
        this.sharpness = sharpness;
    }

    /**
     * Sets the user of the weapon and recalculates damage based on sharpness, strength, and level.
     * <p>
     * Damage formula: {@code 1d6 + Sharpness + STR + minLevel}
     *
     * @param newUser the character who will use the weapon
     */
    @Override
    public void setUser(Character newUser) {
        super.setUser(newUser);
        int userStr = (newUser != null) ? newUser.getStrength() : 0;
        int bonus = this.sharpness + userStr + this.getMinLevel();
        this.setDamage(new int[]{1, 6}, bonus);
    }

    /**
     * Prints the weapon's status to the console.
     * <p>
     * Displays name, type, damage formula, and minimum level requirement.
     */
    @Override
    public void printStatus() {
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.printf("Name: %s\n", this.getName());
        System.out.println("Type: Sword");
        System.out.printf("Damage: 1d6 + Sharpness + STR + %d\n", this.getMinLevel());
        System.out.printf("Min Level: %d\n", this.getMinLevel());
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    }
}
