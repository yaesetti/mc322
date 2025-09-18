import java.util.ArrayList;

public class FixatedLevelBuilder implements LevelBuilder{
    @Override
    public ArrayList<CombatLevel> generateLevels(Hero hero, int num) {
        ArrayList<CombatLevel> levels = new ArrayList<>();

        for (int i = 1; i < num + 1; i++) { 
            // Generate a random array of monsters keeping in mind that
            // the number of monsters cannot be bigger than the level challange
            ArrayList<Monster> levelMonsters = new ArrayList<>();
            for (int j = 0; j < Dice.roll(1, i); j++) {
                int rndMonster = Dice.roll(1, 2) - 1;
                Monster monster;
                if (rndMonster == 0) {
                    monster = new ThugGang("Evil Laughers", 10, 5, 2, 1, 3);
                }
                else {
                    monster = new TwistedMutant("Joke Maker", 15, 10, 1, 1);
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
