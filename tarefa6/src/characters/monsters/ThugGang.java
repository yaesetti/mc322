package characters.monsters;

import characters.Monster;
import levels.Difficulty;
import utils.Dice;

/**
 * Represents a subclass of {@link Monster} called ThugGang.
 * 
 * ThugGang monsters operate in groups, and their attack damage scales
 * based on the number of members in the gang.
 */
public class ThugGang extends Monster {

    /**
     * Number of individuals in the gang.
     */
    private final int groupSize;

    /**
     * Constructs a ThugGang monster with the specified attributes.
     *
     * @param name          the name of the gang
     * @param healthPoints  the initial health points
     * @param willPoints    the initial will points
     * @param strength      the strength value
     * @param dangerRanting the danger rating of the gang
     * @param difficulty    the difficulty level of the encounter
     * @param groupSize     the number of members in the gang
     */
    public ThugGang(String name, int healthPoints, int willPoints, int strength,
                    int dangerRanting, Difficulty difficulty, int groupSize) {
        super(name, healthPoints, willPoints, strength, dangerRanting, difficulty);
        this.groupSize = groupSize;
    }

    /**
     * Calculates the attack damage dealt by the gang.
     * Damage is based on the number of members and a random roll.
     *
     * @return the total damage dealt
     */
    @Override
    public int getAttackDamage() {
        return this.groupSize * Dice.roll(1, 4) + this.getStrength();
    }
}
