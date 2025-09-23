package levels.builder;

import characters.Hero;
import java.util.ArrayList;
import levels.CombatLevel;

public interface LevelBuilder {
    public ArrayList<CombatLevel> generateLevels(Hero hero, int num);
}
