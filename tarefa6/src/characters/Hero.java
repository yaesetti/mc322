package characters;

import combat.CombatActionRegistry;
import exceptions.InsufficientCharacterLevel;
import items.Weapon;

/**
 * Represents the main playable hero character.
 * 
 * Inherits attributes and behavior from {@link Character}, and introduces
 * hero-specific features such as leveling, experience tracking, and luck.
 * 
 * Heroes start at level 1, gain experience to level up, and unlock stronger stats.
 * They also have access to a unique combat action: "Special Skill".
 */
public abstract class Hero extends Character {
    /**
     * Current level of the hero.
     */
    private int level;

    /**
     * Current experience points of the hero.
     */
    private int exp;

    /**
     * Indicates whether the hero is currently lucky (used for critical effects).
     */
    private boolean luck;

    /**
     * Experience thresholds required to reach each level.
     * For example, 2700 XP is needed to reach level 4.
     */
    private final int[] expPerLevel = {
        0, 0, 300, 900, 2700, 6500, 14000, 23000, 34000, 48000, 64000
    };

    /**
     * Constructs a hero with the specified attributes.
     * Initializes the hero at level 1 with 0 experience and no luck.
     * Adds the "Special Skill" action to the hero's action map.
     *
     * @param name         the name of the hero
     * @param healthPoints initial health points
     * @param willPoints   initial will points
     * @param strength     initial strength value
     */
    public Hero(String name, int healthPoints, int willPoints, int strength) {
        super(name, healthPoints, willPoints, strength);
        this.level = 1; // All heroes start at level 1
        this.exp = 0;
        this.luck = false;

        this.actions.put("Special Skill", CombatActionRegistry.USE_SPECIAL_SKILL);
    }

    /**
     * Returns the current level of the hero.
     *
     * @return the hero's level
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * Returns whether the hero is currently lucky.
     *
     * @return true if lucky, false otherwise
     */
    @Override
    public boolean getLuck() {
        return this.luck;
    }

    /**
     * Sets the hero's luck status.
     *
     * @param luck true if lucky, false otherwise
     */
    @Override
    public void setLuck(boolean luck) {
        this.luck = luck;
    }

    /**
     * Grants experience points to the hero and checks for level-up eligibility.
     * Heroes may level up multiple times if enough experience is gained.
     *
     * @param exp the amount of experience gained
     */
    public void gainExp(int exp) {
        this.exp += exp;

        while (this.exp >= expPerLevel[this.level + 1] &&
               this.level < this.expPerLevel.length - 1) {
            this.levelUp();
        }
    }

    /**
     * Increases the hero's level and boosts health and strength.
     */
    private void levelUp() {
        this.level++;
        this.setHealthPoints(this.getHealthPoints() + 10);
        this.setStrength(this.getStrength() + 1);
    }

    /**
     * Prints the hero's current status to the console,
     * including level and experience.
     */
    @Override
    public void printStatus() {
        super.printStatus();
        System.out.printf("Level: %d\n", this.level);
        System.out.printf("Experience: %d\n", this.exp);
    }

    /**
     * Equips a weapon to the hero if their level meets the weapon's requirement.
     *
     * @param weapon the weapon to equip
     * @throws InsufficientCharacterLevel if the hero's level is too low
     */
    @Override
    public void setWeapon(Weapon weapon) throws InsufficientCharacterLevel {
        if (this.getLevel() >= weapon.getMinLevel()) {
            super.setWeapon(weapon);
        } else {
            throw new InsufficientCharacterLevel();
        }
    }
}