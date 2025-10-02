package characters.heroes;

import characters.Hero;
import combat.Combatant;

// Mutants are meta-humans that, for whatever reason, have abilities and power
// beyond the common beings and use them to save people.
// Real life examples would be: Flash, Superman, Cyclops, Storm...

// The most important attribute for this class is their Mutant Energy (ME)
public class Mutant extends Hero {
    private int mutantEnergy;
    private int maxMutantEnergy;

    public Mutant(String name, int healthPoints, int willPoints, int strength) {
        super(name, healthPoints, willPoints, strength);
        this.mutantEnergy = 2 * this.getLevel();
        this.maxMutantEnergy = mutantEnergy;
    }

    @Override
    public void gainExp(int exp) {
        super.gainExp(exp);
        maxMutantEnergy = 2 * this.getLevel(); // Updates the maxMutantEnergy per level.
    }

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

    public int getMutantEnergy() {
        return this.mutantEnergy;
    }

    public void setMutantEnergy(int newMutantEnergy) {
        this.mutantEnergy = newMutantEnergy;
    }

    public int getMaxMutantEnergy() {
        return this.maxMutantEnergy;
    }

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
