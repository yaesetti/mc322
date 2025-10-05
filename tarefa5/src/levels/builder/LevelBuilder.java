package levels.builder;

import characters.Hero;
import java.util.ArrayList;
import levels.CombatLevel;
import levels.Difficulty;

/*
 * Uma lista de CombatLevel
 * 
 * @param hero heroi que esta enfrentando os desafios
 * @param num numero de cenarios
 * @param difficulty dificuldade dos cenarios
 */
public interface LevelBuilder {
    public ArrayList<CombatLevel> generateLevels(Hero hero, int num, Difficulty difficulty);
}
