package levels.builder;

import java.util.ArrayList;

import characters.Hero;
import characters.Monster;
import characters.monsters.ThugGang;
import characters.monsters.TwistedMutant;
import levels.CombatLevel;
import levels.Difficulty;
import levels.scenarios.Scenario;
import utils.Dice;

/**
 * A concrete implementation of {@link LevelBuilder} that generates a fixed set of combat levels.
 * Each level includes a randomly selected scenario and a randomized group of monsters,
 * with the number of monsters constrained by the level's challenge rating.
 */
public class FixatedLevelBuilder implements LevelBuilder {

    /**
     * Generates a list of {@link CombatLevel} instances based on the given hero, number of levels,
     * and difficulty setting.
     *
     * @param hero       the hero who will participate in the levels
     * @param num        the number of levels to generate
     * @param difficulty the difficulty setting for all levels
     * @return a list of generated {@link CombatLevel} instances
     */
    @Override
    public ArrayList<CombatLevel> generateLevels(Hero hero, int num, Difficulty difficulty) {
        ArrayList<CombatLevel> levels = new ArrayList<>();

        for (int i = 1; i < num + 1; i++) {
            // Generate a random array of monsters limited by the challenge rating
            ArrayList<Monster> levelMonsters = new ArrayList<>();
            for (int j = 0; j < Dice.roll(1, i); j++) {
                int rndMonster = Dice.roll(1, 2) - 1;
                Monster monster;

                if (rndMonster == 0) {
                    monster = new ThugGang("Evil Laughers", 7, 5, 2, 1, difficulty, 3);
                } else {
                    monster = new TwistedMutant("Joke Maker", 10, 10, 1, 1, difficulty);
                }

                levelMonsters.add(monster);
            }

            // Choose a random scenario
            Scenario[] scenarios = {Scenario.DOWNTOWN, Scenario.FOREST, Scenario.ICE_CAVE};
            int rnd = Dice.roll(1, 3) - 1;

            levels.add(new CombatLevel(hero, scenarios[rnd], i, levelMonsters));
        }

        return levels;
    }
}
