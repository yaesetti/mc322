package characters.monsters;

import characters.Monster;
import levels.Difficulty;
import utils.Dice;

//"Twisted mutants are those who tampered with their own
// genes in a ruthless quest for power."
public class TwistedMutant extends Monster {
    private final int maxHealthPoints;

    public TwistedMutant (String name, int healthPoints, int willPoints, int strength, int dangerRanting, Difficulty difficulty) {
        super(name, healthPoints, willPoints, strength, dangerRanting, difficulty);
        this.maxHealthPoints = healthPoints;
    }

    @Override
    public int getAttackDamage() {
        int damage = Dice.roll(1, 6) + this.getStrength();

        // When it's life is bellow 40%, it deals 4 points of damage more
        if (this.getHealthPoints() <= 0.4 * this.maxHealthPoints) {
            damage += 4;
        }
        return damage;
    }
}
