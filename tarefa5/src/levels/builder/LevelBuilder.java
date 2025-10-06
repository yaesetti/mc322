package levels.builder;

import java.util.ArrayList;

import characters.Hero;
import levels.CombatLevel;
import levels.Difficulty;

/**
 * Uma lista de CombatLevel
 */
public interface LevelBuilder {
    public ArrayList<CombatLevel> generateLevels(Hero hero, int num, Difficulty difficulty);
}