package lab01.src;
import java.util.Random;

public class Mutant extends Hero {
    private int mutantEnergy;

    public Mutant(String name, int healthPoints, int willPoints, int strength) {
        super(name, healthPoints, willPoints, strength);
        this.mutantEnergy = 2 * getLevel();
    }

    @Override
    public void attack(Character target) {
        Random randomNumber = new Random();
        int d6 = randomNumber.nextInt(6) + 1;

        if (this.mutantEnergy >= 1) {
            target.receiveDamage(d6 + getStrength() + 2);
            this.mutantEnergy -= 1;
        }
        else {
            target.receiveDamage(d6 + getStrength());
        }
    }

    @Override
    public void useSpecialSkill(Character target) {
        this.mutantEnergy += 2;
        target.receiveDamage(2);
    }
}
