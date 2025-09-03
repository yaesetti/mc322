package lab01.src;
import java.util.Random;

public class ThugGang extends Monster {
    private int groupSize;

    public ThugGang(String name, int healthPoints, int willPoints, int strength,
                 int dangerRanting, int expValue, int groupSize) {
        super(name, healthPoints, willPoints, strength, dangerRanting, expValue);
        this.groupSize = groupSize;
    }

    @Override
    public void attack (Character target) {
        if(this.getIsKnocked()) {
            System.out.printf("&s is knocked, so they can't attack!\n", this.getName());
            return;
        }

        Random randomNumber = new Random();
        int d4 = randomNumber.nextInt(4) + 1;

        int damage = this.groupSize * d4 + this.getStrength();

        target.receiveDamage(damage);
        System.out.printf("%s dealt %d point(s) of damage to %s!\n",
                          this.getName(), damage, target.getName());
    }
}
