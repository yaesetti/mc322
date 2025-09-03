package lab01.src;
import java.util.Random;

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
    }

    @Override
    public void gainExp(int exp) {
        super.gainExp(exp);
        maxMutantEnergy = 2 * this.getLevel(); // Updates the maxMutantEnergy per level.
    }

    @Override
    public void attack(Character target) {
        if(this.getIsKnocked()) {
            System.out.printf("%s is knocked, so they can't attack!\n", this.getName());
            return;
        }
        
        Random randomNumber = new Random();
        int d6 = randomNumber.nextInt(6) + 1;

        int damage;
        // If a Mutant have enough energy to buff it's damage, it will
        if (this.mutantEnergy >= 1) {
            damage = d6 + this.getStrength() + 2;
            this.mutantEnergy -= 1;
        }
        // Else, it is a normal attack
        else {
            damage = d6 + this.getStrength();
        }

        target.receiveDamage(damage);
        System.out.printf("%s dealt %d point(s) of damage to %s!\n",
                          this.getName(), damage, target.getName());
        
        if (target.getHealthPoints() == 0) {
            System.out.printf("%s knocked %s!\n", this.getName(), target.getName());
        }
    }

    @Override
    public void useSpecialSkill(Character target) {
        // Name: Restore Energy
        // Description: Focus your inner powers and restore 2 Mutant Energy while also
        //              dealing 2 damage to an enemy.

        // If by using this skill the amount of Mutant Energy would bypass the limit
        // the energy is capped at the maximun instead.
        if (this.mutantEnergy + 2 > this.maxMutantEnergy) {
            this.mutantEnergy = this.maxMutantEnergy;
        }
        else {
            this.mutantEnergy += 2;
        }

        // Causes the damage to the enemy.
        target.receiveDamage(2);
    }
}
