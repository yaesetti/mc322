import java.util.ArrayList;

public class LevelBuilder implements LevelBuilder_I, Setting {



    private static final String NAME1 = Setting.Downtown.getName();
    private static final String DESCRIPTION1 = Setting.Downtown.getDescription();
    
    private static final String NAME2 = Setting.Forest.getName();
    private static final String DESCRIPTION2 = Setting.Forest.getDescription();
        
    private static final String NAME3 = Setting.Ice_Cave.getName();
    private static final String DESCRIPTION3 = Setting.Ice_Cave.getDescription();
    
    private static final String[] NAMEs = {NAME1, NAME2, NAME3};
    private static final String[] DESCRIPTIONs = {DESCRIPTION1, DESCRIPTION2, DESCRIPTION3};


    @override
    public List<Level> generateLevels(int num) {
        ArrayList<Level> levels = new ArrayList<>();

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
            // Random picker for NAME and DESCRIPTION
            int rnd1 = Dice.roll(1, 3) - 1;
            
             levels.add(new Level(i, NAMEs[rnd1], DESCRIPTIONs[rnd1], levelMonsters));
        }
        return levels;
    }
}
