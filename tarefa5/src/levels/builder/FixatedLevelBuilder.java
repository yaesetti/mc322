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
 * Gera uma lista de niveis de combade
 * 
 * 
 */
public class FixatedLevelBuilder implements LevelBuilder{
    @Override

    /**
     * Responsavel por gerar a lista de niveis de combate com as dificuldades, com os monstros
     * 
     * @param hero
     * @param num
     * @param difficulty
     * @return levels
     */
    public ArrayList<CombatLevel> generateLevels(Hero hero, int num, Difficulty difficulty) {
        ArrayList<CombatLevel> levels = new ArrayList<>();

        for (int i = 1; i < num + 1; i++) { 
            // Generate a random array of monsters keeping in mind that
            // the number of monsters cannot be bigger than the level challenge
            ArrayList<Monster> levelMonsters = new ArrayList<>();
            for (int j = 0; j < Dice.roll(1, i); j++) {
                int rndMonster = Dice.roll(1, 2) - 1;
                Monster monster;

                if (rndMonster == 0) {
                    monster = new ThugGang("Evil Laughers", 7, 5, 2, 1, difficulty, 3);
                }
                else {
                    monster = new TwistedMutant("Joke Maker", 10, 10, 1, 1, difficulty);
                }

                levelMonsters.add(monster);
            }

            // List of possible scenarios to choose
            Scenario[] scenarios = {Scenario.DOWNTOWN, Scenario.FOREST, Scenario.ICE_CAVE};

            // Random picker
            int rnd = Dice.roll(1, 3) - 1;
            
             levels.add(new CombatLevel(hero, scenarios[rnd], i, levelMonsters));
        }
        return levels;
    }
}