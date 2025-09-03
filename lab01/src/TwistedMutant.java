package lab01.src;
import java.util.Random;

//"Twisted mutants are those who tampered with their own
// genes in a ruthless quest for power."
public class TwistedMutant extends Monster {

    private final int maxHealthPoints;

    public TwistedMutant (String name, int healthPoints, int willPoints, int strength,
                          int dangerRanting, int expValue) {
        super(name, healthPoints, willPoints, strength, dangerRanting, expValue);
        this.maxHealthPoints = healthPoints;
    }

    @Override
    public void attack(Character target) {
        if(this.getIsKnocked()) {
            System.out.printf("%s is knocked, so they can't attack!\n", this.getName());
            return;
        }
        
        int damage;

        Random randomNumber = new Random();
        int d6 = randomNumber.nextInt(6) + 1;

        if (this.getHealthPoints() <= 0.4 * this.maxHealthPoints) {
            damage = d6 + this.getStrength() + 4;
        } else {
            damage = d6 + this.getStrength();
        }
        target.receiveDamage(damage);
        System.out.printf("%s dealt %d point(s) of damage to %s!\n",
                          this.getName(), damage, target.getName());

        if (target.getHealthPoints() == 0) {
            System.out.printf("%s knocked %s!\n", this.getName(), target.getName());
        }
    }
}
