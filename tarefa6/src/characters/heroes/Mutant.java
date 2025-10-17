package characters.heroes;

import characters.Hero;
import combat.Combatant;

/**
 * Represents a subclass of {@link Hero} called Mutant.
 * 
 * Mutants are meta-humans with extraordinary powers used to protect others.
 * Real-world analogs include characters like Flash, Superman, Cyclops, and Storm.
 * 
 * The key attribute of this class is Mutant Energy (ME), which enhances attack damage
 * and fuels the use of special abilities.
 */
public class Mutant extends Hero {
    /**
     * Current amount of Mutant Energy available.
     */
    private int mutantEnergy;

    /**
     * Maximum capacity of Mutant Energy based on the hero's level.
     */

    private int maxMutantEnergy;

    /**
     * Constructs a Mutant hero with the given attributes.
     * Initializes Mutant Energy based on the hero's level.
     *
     * @param name         the name of the mutant
     * @param healthPoints the initial health points
     * @param willPoints   the initial will points
     * @param strength     the initial strength value
     */
    public Mutant(String name, int healthPoints, int willPoints, int strength) {
        super(name, healthPoints, willPoints, strength);
        this.mutantEnergy = 2 * this.getLevel();
        this.maxMutantEnergy = mutantEnergy;
    }

    /**
     * Grants experience to the mutant and updates their maximum Mutant Energy
     * based on the new level.
     *
     * @param exp the amount of experience gained
     */
    @Override
    public void gainExp(int exp) {
        super.gainExp(exp);
        maxMutantEnergy = 2 * this.getLevel(); // Updates the maxMutantEnergy per level.
    }

    /**
     * Calculates the attack damage dealt by the mutant.
     * If Mutant Energy is available, adds +2 damage and consumes 1 energy.
     *
     * @return the total damage dealt
     */
    @Override
    public int getAttackDamage() {
        int damage = this.getWeapon().getDamage();
            // If a Mutant have enough mutant energy to buff it's damage, it will
            if (this.getMutantEnergy() >= 1) {
                damage += 2;
                this.setMutantEnergy(this.getMutantEnergy() - 1);
            }

        return damage;
    }

    /**
     * Returns the current Mutant Energy.
     *
     * @return the current energy value
     */
    public int getMutantEnergy() {
        return this.mutantEnergy;
    }

    /**
     * Sets the current Mutant Energy.
     *
     * @param newMutantEnergy the new energy value
     */
    public void setMutantEnergy(int newMutantEnergy) {
        this.mutantEnergy = newMutantEnergy;
    }

    /**
     * Returns the maximum Mutant Energy capacity.
     *
     * @return the maximum energy value
     */
    public int getMaxMutantEnergy() {
        return this.maxMutantEnergy;
    }

    /**
     * Executes the mutant's special skill: "Restore Energy".
     *
     * @param target the combatant receiving the damage
     */
    @Override
    public void useSpecialSkill(Combatant target) {
        // Name: Restore Energy
        // Description: Focus your inner powers and restore 2 Mutant Energy while also
        //              dealing 2 damage to an enemy.

        // If by using this skill the amount of Mutant Energy would bypass the limit
        // the energy is capped at the maximum instead.

        int damage;
        int restoredEnergy = this.mutantEnergy;

        // Critical instance of the Special Skill because the Hero is lucky
        if (this.getLuck()) {
            restoredEnergy = this.maxMutantEnergy;
            damage = 4;
        }
        // Normal instance of the Special Skill
        else {
            damage = 2;

            if (this.getMutantEnergy() + 2 > this.getMaxMutantEnergy()) {
                restoredEnergy = this.maxMutantEnergy;
            }
            else {
                restoredEnergy += 2;
            }
        }
        // Causes the damage to the enemy
        target.receiveDamage(damage);
        
        // Restores the Mutant Energy
        this.mutantEnergy = restoredEnergy;

        // Prints what happened
        System.out.printf("%s restored %d Mutant Energy and dealt %d point(s) of damage to %s!\n", this.getName(), restoredEnergy, damage, target.getName());
    }
}