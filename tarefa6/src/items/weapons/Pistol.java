package items.weapons;

import characters.Character;
import combat.DamageType;
import items.Weapon;

/**
 * Represents a pistol weapon in the U-Energy RPG game.
 * <p>
 * A {@code Pistol} is a ranged weapon that deals piercing damage when loaded with bullets.
 * When out of ammunition, it switches to bludgeoning damage and scales with the user's strength.
 */
public class Pistol extends Weapon {

    /**
     * The number of bullets currently loaded in the pistol.
     */
    private int bullets;

    /**
     * Constructs a new {@code Pistol} weapon.
     *
     * @param name     the name of the pistol
     * @param minLevel the minimum level required to equip the weapon
     * @param user     the character who will use the weapon
     */
    public Pistol(String name, int minLevel, Character user) {
        super(name, new int[]{2, 4}, 3 * minLevel, minLevel, DamageType.Piercing, user);
        this.bullets = 15;
    }

    /**
     * Returns the number of bullets currently loaded in the pistol.
     *
     * @return the bullet count
     */
    public int getBullet() {
        return this.bullets;
    }

    /**
     * Sets the number of bullets and updates damage and type accordingly.
     * <p>
     * If bullets reach zero, the pistol switches to melee mode with bludgeoning damage.
     * Otherwise, it retains its piercing damage profile.
     *
     * @param bullets the new bullet count
     */
    public void setBullet(int bullets) {
        this.bullets = Math.max(0, bullets);
        if (this.bullets == 0) {
            int userStr = 0;
            if (this.getUser() != null) {
                userStr = this.getUser().getStrength();
            }
            this.setDamage(new int[]{1, 3}, userStr);
            this.setDamageType(DamageType.Bludgeoning);
        }
        else {
            this.setDamage(new int[]{2, 4}, 3 * this.getMinLevel());
            this.setDamageType(DamageType.Piercing);
        }
    }

    /**
     * Sets the user of the weapon and recalculates damage based on bullet status.
     * <p>
     * If out of bullets, damage scales with strength. Otherwise, uses default ranged damage.
     *
     * @param newUser the character who will use the weapon
     */
    @Override
    public void setUser(Character newUser) {
        super.setUser(newUser);
        if (this.bullets == 0) {
            int userStr = 0;
            if (newUser != null) {
                userStr = newUser.getStrength();
            }
            this.setDamage(new int[]{1, 3}, userStr);
            this.setDamageType(DamageType.Bludgeoning);
        }
        else {
            this.setDamage(new int[]{2, 4}, 3 * this.getMinLevel());
            this.setDamageType(DamageType.Piercing);
        }
    }

    /**
     * Prints the weapon's status to the console.
     * <p>
     * Displays name, type, damage formulas for both loaded and empty states, and level requirement.
     */
    @Override
    public void printStatus() {
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.printf("Name: %s\n", this.getName());
        System.out.println("Type: Pistol");
        System.out.printf("Damage (bullets > 0): 2d4 + %d\n", 3*this.getMinLevel());
        System.out.println("Damage (bullets < 0): 1d3 + STR");
        System.out.printf("Min Level: %d\n", this.getMinLevel());
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    }
}
