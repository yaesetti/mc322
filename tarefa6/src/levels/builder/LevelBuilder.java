package levels.builder;

import java.util.ArrayList;

import characters.Hero;
import levels.CombatLevel;
import levels.Difficulty;

/**
 * Interface for building a list of {@link CombatLevel} instances.
 * Implementations of this interface are responsible for generating
 * combat levels based on a hero, desired quantity, and difficulty setting.
 */
public interface LevelBuilder {

    /**
     * Generates a list of combat levels tailored to the given hero,
     * number of levels, and difficulty.
     *
     * @param hero       the hero who will participate in the levels
     * @param num        the number of levels to generate
     * @param difficulty the difficulty setting for the levels
     * @return a list of generated {@link CombatLevel} instances
     */
    public ArrayList<CombatLevel> generateLevels(Hero hero, int num, Difficulty difficulty);
}