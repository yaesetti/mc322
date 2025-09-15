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

        int damage = this.getWeapon().getDamage();
        // If a Mutant have enough energy to buff it's damage, it will
        if (this.mutantEnergy >= 1) {
            damage += 2;
            this.mutantEnergy -= 1;
        }

        target.receiveDamage(damage);
        System.out.printf("%s dealt %d point(s) of damage to %s!\n",
                          this.getName(), damage, target.getName());
        
        if (target.getHealthPoints() == 0) {
            System.out.printf("%s knocked %s!\n", this.getName(), target.getName());
        }

        // Reduce sword sharpness if the Hero is not lucky this turn
        if (this.getWeapon().getClass().equals(Sword.class) && 
            !this.getLuck()) {
            Sword sword = (Sword)this.getWeapon();
            sword.setSharpnesss(sword.getSharpness() - 1);
        }

        // Spend one bullet per attack
        if (this.getWeapon().getClass().equals(Pistol.class)) {
                Pistol pistol = (Pistol)this.getWeapon();
                pistol.setBullet(pistol.getBullet() - 1);
            }

        // Rolls luck
        if (Dice.roll(1, 100) >= 70) {
            this.setLuck(true);
        }
        else {
            this.setLuck(false);
        }
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
