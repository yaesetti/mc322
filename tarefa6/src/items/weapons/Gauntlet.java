package items.weapons;

import characters.Character;
import combat.DamageType;
import items.Weapon;

/**
 * Represents a gauntlet weapon in the U-Energy RPG game.
 * <p>
 * A {@code Gauntlet} is a bludgeoning weapon that scales its damage based on the user's strength
 * and the weapon's minimum level requirement. It is ideal for strength-based characters.
 */
public class Gauntlet extends Weapon {

    /**
     * Amplification factor applied to the user's strength when calculating bonus damage.
     */
    private final int strengthAmp;

    /**
     * Constructs a new {@code Gauntlet} weapon.
     *
     * @param name        the name of the gauntlet
     * @param minLevel    the minimum level required to equip the weapon
     * @param strengthAmp the multiplier applied to the user's strength for bonus damage
     * @param user        the character who will use the weapon
     */
    public Gauntlet(String name, int minLevel, int strengthAmp, Character user) {
        super(name, new int[]{1, 6}, 1 * minLevel, minLevel, DamageType.Bludgeoning, user);
        this.strengthAmp = strengthAmp;
    }

    /**
     * Sets the user of the weapon and recalculates damage based on the user's strength and weapon level.
     * <p>
     * Damage formula: {@code 1d6 + STR * strengthAmp + minLevel}
     *
     * @param newUser the character who will use the weapon
     */
    @Override
    public void setUser(Character newUser) {
        super.setUser(newUser);
        int userStr = (newUser != null) ? newUser.getStrength() : 0;
        int bonus = userStr * this.strengthAmp + this.getMinLevel();
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
        System.out.println("Type: Gauntlets");
        System.out.printf("Damage: 1d6 + STR*%d + %d\n", this.strengthAmp, this.getMinLevel());
        System.out.printf("Min Level: %d\n", this.getMinLevel());
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    }
}