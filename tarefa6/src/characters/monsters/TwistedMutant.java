package characters.monsters;

import characters.Monster;
import levels.Difficulty;
import utils.Dice;

/**
 * Represents a subclass of {@link Monster} called TwistedMutant.
 * 
 * Twisted mutants are beings who have altered their own genes in a ruthless pursuit of power.
 * This monster becomes more dangerous when its health drops below a critical threshold.
 */
public class TwistedMutant extends Monster {

    /**
     * The maximum health points the mutant starts with.
     */
    private final int maxHealthPoints;

    /**
     * Constructs a TwistedMutant with the specified attributes.
     * Initializes its maximum health based on the starting health.
     *
     * @param name          the name of the mutant
     * @param healthPoints  the initial health points
     * @param willPoints    the initial will points
     * @param strength      the strength value
     * @param dangerRanting the danger rating of the mutant
     * @param difficulty    the difficulty level of the encounter
     */
    public TwistedMutant(String name, int healthPoints, int willPoints, int strength,
                         int dangerRanting, Difficulty difficulty) {
        super(name, healthPoints, willPoints, strength, dangerRanting, difficulty);
        this.maxHealthPoints = healthPoints;
    }

    /**
     * Calculates the attack damage dealt by the mutant.
     * 
     * Base damage is determined by a 1d6 roll plus strength.
     * If current health is below 40% of maximum, adds +4 bonus damage.
     *
     * @return the total damage dealt
     */
    @Override
    public int getAttackDamage() {
        int damage = Dice.roll(1, 6) + this.getStrength();

        if (this.getHealthPoints() <= 0.4 * this.maxHealthPoints) {
            damage += 4;
        }

        return damage;
    }
}
