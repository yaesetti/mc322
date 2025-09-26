package characters.monsters;

import characters.Monster;
import levels.Difficulty;
import utils.Dice;

// Thugs always stick together!
public class ThugGang extends Monster {
    private final int groupSize; // How many members in the Gang

    public ThugGang(String name, int healthPoints, int willPoints, int strength, int dangerRanting, Difficulty difficulty, int groupSize) {
        super(name, healthPoints, willPoints, strength, dangerRanting, difficulty);
        this.groupSize = groupSize;
    }

    @Override
    public int getAttackDamage() {
        return this.groupSize * Dice.roll(1, 4) + this.getStrength();
    }
}
