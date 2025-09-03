package lab01.src;
import java.util.Random;

public class TwistedMutant extends Monster {
    //"Twisted mutants are those who tampered with their own genes in a ruthless quest for power."
    private final int maxHealthPoints;

    public TwistedMutant (String name, int healthPoints, int willPoints, int strength, int dangerRanting, int expValue, int maxHealth) {
        super(name, healthPoints, willPoints, strength, dangerRanting, expValue);
        this.maxHealthPoints = healthPoints;
    }

    @Override
    public void attack(Character target) {
        int damage;

        Random randomNumber = new Random();
        int d6 = randomNumber.nextInt(6) + 1;

        if (this.getHealthPoints() <= 0.4 * this.maxHealthPoints) {
            damage = d6 + this.getStrength() + 4;
        } else {
            damage = d6 + this.getStrength();
        }
        target.receiveDamage(damage);
    }
}
