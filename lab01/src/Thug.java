package lab01.src;
import java.util.Random;


public class Thug extends Monster {
    // "Thugs never go solo — they always stick together."
    
    private int groups;
    
    public Thug (string name, int healthPoints, int willPoints, int strength, int dangerRanting, int expValue, int groups) {
        super(name, healthPoints, willPoints, strength, dangerRanting, expValue);
        this.groups = groups;
    }

    @Override
    public void attack (Character target) {
        int damage;

        Random randomNumber = new Random();
        int d4 = randomNumber.nextInt(4) + 1;

        double DamageMultiplier = Math.pow(1.2, this.groups);

        if (this.groups == 1) {
            damage = d4 + 0.8 * (getStrength());
        }
        else {
            damage = d4 + DamageMultiplier * (getStrength());
        }

        target.receiveDamage(damage);
    }

}