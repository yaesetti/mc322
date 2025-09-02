package lab01.src;
import java.util.Random;

import lab01.src.Hero;
import lab01.src.Monster;
import lab01.src.Mutant;

public class TwistedMutant extends Monster {
    //"Twisted mutants are those who tampered with their own genes in a ruthless quest for power."
    private int maxHealth;

    public TwistedMutant (string name, int healthPoints, int willPoints, int strength, int dangerRanting, int expValue, int maxHealth) {
        super(name, healthPoints, willPoints, strength, dangerRanting, expValue);
        this.maxHealth = this.healtPoints;
    }



    public void xpValue (Hero atacante) {
        if (this.HealthPoints == 0) {
            if (atacante instanceof Mutant) {
                atacante.gainExp(this.expValue*1.6);
            } else {
                atacante.gainExp(this.expValue);
            }
            
        }
    }

    public void attack (Character target) {
        int damage;

        Random randomNumber = new Random();
        int d6 = randomNumber.nextInt(6) + 1;

        if (this.healthPoints == 04.maxHealth) {
            damage = d6 + getStrength() + 4;
        } else {
            damage = d6 + getStrength;
        }
        target.receiveDamage(damage);

    }
}