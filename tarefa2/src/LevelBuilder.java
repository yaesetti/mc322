import java.util.ArrayList;

public class LevelBuilder {
    private static final String NAME1 = "Downtown";
    private static final String DESCRIPTION1 = """
            In the heart of the most populated city of the contry\n
            the criminals run free! Violence is out of control!\n
            litter, smoke and gray concrete are all you can see...
            """;
    
    private static final String NAME2 = "Forest";
    private static final String DESCRIPTION2 = """
            The view is pretty and green, but the crime is evil and\n
            red. Why the villains are here? Maybe camping? Not sure!\n
            But you can not let them get away!
            """;
        
    private static final String NAME3 = "Ice Cave";
    private static final String DESCRIPTION3 = """
            It's so cold you can see smoke comming out of your breath.\n
            For sure the comfort of the bed would be 100 times better,\n
            but the crime won't stop by itself. What are these bad guys\n
            up to in a desert and cold place like this?
            """;
    
    private static final String[] NAMEs = {NAME1, NAME2, NAME3};
    private static final String[] DESCRIPTIONs = {DESCRIPTION1, DESCRIPTION2, DESCRIPTION3};

    public static ArrayList<Level> generateLevels(int num) {
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
