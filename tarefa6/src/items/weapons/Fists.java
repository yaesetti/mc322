
package items.weapons;

import characters.Character;
import combat.DamageType;
import items.Weapon;

/**
 * Represents a basic melee weapon: bare fists.
 * <p>
 * This subclass of {@link Weapon} deals bludgeoning damage and scales with the user's strength.
 * It is the default weapon when no other is equipped.
 */
public class Fists extends Weapon {

    /**
     * Constructs a new {@code Fists} weapon.
     * <p>
     * Initializes with base damage of {@code 1d3}, no bonus, no level requirement,
     * and bludgeoning damage type.
     *
     * @param user the character using the weapon
     */
    public Fists(Character user) {
        super("Fists", new int[]{1, 3}, 0, 0, DamageType.Bludgeoning, user);
    }

    /**
     * Sets the user of the weapon and recalculates damage based on the user's strength.
     * <p>
     * Damage is updated to {@code 1d6 + STR}.
     *
     * @param newUser the character who will use the weapon
     */
    @Override
    public void setUser(Character newUser) {
        super.setUser(newUser);
        int userStr = (newUser != null) ? newUser.getStrength() : 0;
        this.setDamage(new int[]{1, 6}, userStr);
    }

    /**
     * Prints the weapon's status to the console.
     * <p>
     * Displays name, type, damage formula, and a flavor message.
     */
    @Override
    public void printStatus() {
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.printf("Name: %s\n", this.getName());
        System.out.println("Type: Fists");
        System.out.println("Damage: 1d3 + STR");
        System.out.println("Bare hands :(");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    }
}
