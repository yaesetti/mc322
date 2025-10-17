package characters.heroes;

import characters.Hero;
import combat.Combatant;
import exceptions.CharacterKnocked;
import exceptions.InsufficientCharacterLevel;
import exceptions.InsufficientWillPoints;
import items.Weapon;
import items.weapons.Gauntlet;
import items.weapons.Pistol;
import items.weapons.Sword;

/**
 * Represents a subclass of {@link Hero} called Specialist.
 * 
 * Specialists are highly trained humans who fight for justice without superpowers.
 * They rely heavily on weapons and are proficient in multiple types.
 * Real-world analogs include Batman, Hawkeye, and Green Arrow.
 * 
 * This class introduces a preferred weapon mechanic, granting bonus damage
 * when the Specialist uses their favored weapon type.
 */
public class Specialist extends Hero {

    /**
     * Index of the preferred weapon type in the {@code specialistWeapons} array.
     */
    private final int preferredWeapon;

    /**
     * Index of the currently equipped weapon in the {@code specialistWeapons} array.
     */
    private int weaponIndex;

    /**
     * Array of weapon options available to the Specialist.
     * Includes a sword, gauntlet, and pistol by default.
     */
    private final Weapon[] specialistWeapons = {
        new Sword("Specialist Sword", 1, 7, this),
        new Gauntlet("Specialist Gauntlets", 1, 4, this),
        new Pistol("Specialist Pistol", 1, this)
    };

    /**
     * Constructs a Specialist hero with the given attributes and preferred weapon type.
     *
     * @param nome            the name of the Specialist
     * @param healthPoints    initial health points
     * @param willPoints      initial will points
     * @param strength        initial strength value
     * @param preferredWeapon index of the preferred weapon (0 = Sword, 1 = Gauntlet, 2 = Pistol)
     */
    public Specialist(String nome, int healthPoints, int willPoints,
                      int strength, int preferredWeapon) {
        super(nome, healthPoints, willPoints, strength);
        this.weaponIndex = 0;
        this.preferredWeapon = preferredWeapon;
    }

    /**
     * Calculates the attack damage dealt by the Specialist.
     * Adds +4 damage if the equipped weapon matches the preferred weapon type.
     *
     * @return the total damage dealt
     */
    @Override
    public int getAttackDamage() {
        int damage = this.getWeapon().getDamage();

        if (this.getWeapon().getClass().equals(this.specialistWeapons[preferredWeapon].getClass())) {
            damage += 4;
        }

        return damage;
    }

    /**
     * Equips a new weapon and updates the corresponding entry in the Specialist's weapon list.
     * Prevents duplicate weapon types in the internal array.
     *
     * @param newWeapon the weapon to equip
     */
    @Override
    public void setWeapon(Weapon newWeapon) {
        try {
            super.setWeapon(newWeapon);
        } catch (InsufficientCharacterLevel e) {
            System.err.println(e.getMessage());
        }

        if (newWeapon instanceof Sword) {
            specialistWeapons[0] = newWeapon;
        } else if (newWeapon instanceof Gauntlet) {
            specialistWeapons[1] = newWeapon;
        } else if (newWeapon instanceof Pistol) {
            specialistWeapons[2] = newWeapon;
        }
    }

    /**
     * Executes the Specialist's special skill: "Versatility".
     *
     * @param target the combatant receiving the damage
     */
    @Override
    public void useSpecialSkill(Combatant target) {
        if (this.getLuck() && this.weaponIndex != this.preferredWeapon) {
            this.weaponIndex = this.preferredWeapon;
        } else {
            this.weaponIndex++;
            if (this.weaponIndex > this.specialistWeapons.length - 1) {
                this.weaponIndex = 0;
            }
        }

        target.receiveDamage(2);

        System.out.printf("%s changed to %s, dealt 2 points of damage to %s and will attack!\n",
                this.getName(), this.getWeapon().getName(), target.getName());

        try {
            this.actions.get("Attack").execute(this, target);
        } catch (InsufficientWillPoints | CharacterKnocked e) {
            System.err.println(e.getMessage());
        }
    }
}
