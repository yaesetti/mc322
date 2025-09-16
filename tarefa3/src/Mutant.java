// Mutants are meta-humans that, for whatever reason, have habilities and power
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

        this.getActions().add(new MutantAttack());
        this.getActions().add(new MutantSpecialSkill());
    }

    @Override
    public void gainExp(int exp) {
        super.gainExp(exp);
        maxMutantEnergy = 2 * this.getLevel(); // Updates the maxMutantEnergy per level.
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
    public void useSpecialSkill(Character target) {
        // Name: Restore Energy
        // Description: Focus your inner powers and restore 2 Mutant Energy while also
        //              dealing 2 damage to an enemy.

        // If by using this skill the amount of Mutant Energy would bypass the limit
        // the energy is capped at the maximun instead.

        if (this.getLuck()) {
            this.mutantEnergy = this.maxMutantEnergy;
            target.receiveDamage(4);
        }
        else {
            if (this.mutantEnergy + 2 > this.maxMutantEnergy) {
            this.mutantEnergy = this.maxMutantEnergy;
            }
            else {
            this.mutantEnergy += 2;
            }

            // Causes the damage to the enemy.
            if (this.getLuck()) {
                target.receiveDamage(2);
            }
        }
    }
}
