import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Mutant hero = new Mutant("Singed", 20, 25, 3);
        Gauntlet poisonGauntlet = new Gauntlet(1, 3, hero);
        hero.setWeapon(poisonGauntlet);

        System.out.println();
        System.out.println("-=-=-=-=-=-=-=-=- HERO STATS =-=-=-=-=-=-=-=-=-");
        hero.printStatus();
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println();

        ArrayList<Level> levels = LevelBuilder.generateLevels(3); 

        // Loops for the amount of levels
        int levelCounter = 0;
        for (Level level: levels) {
            System.out.printf("                 -={LEVEL %d}=-\n", levelCounter);
            level.printInfos();
            int turnCounter = 0;
            ArrayList<Monster> levelMonsters = level.getMonsters();
            for (int i = 0; i < levelMonsters.size() - 1; i++){
                Monster enemy = levelMonsters.get(i);

                // Loops while the enemy still has HP.
                while (!enemy.getIsKnocked()) {
                    System.out.println();
                    System.out.printf("--------------------[TURN %d]---------------------\n", turnCounter);
                    hero.attack(enemy);
                    enemy.attack(hero);

                    // If hero's HP == 0 -> Game Over
                    if (hero.getIsKnocked()) {
                        System.out.println();
                        System.out.println("          XXXXXXXXXXXXXXXXX");
                        System.out.println("          X   GAME OVER   X");
                        System.out.println("          XXXXXXXXXXXXXXXXX");
                        System.out.println();

                        return;
                    }

                    // Prints the status of both fighters after every round
                    System.out.println();
                    System.out.println("++++++++++++++++++++ HERO ++++++++++++++++++++");
                    hero.printStatus();
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
                    System.out.println();

                    System.out.println("#################### ENEMY ###################");
                    enemy.printStatus();
                    System.out.println("##############################################");
                    System.out.println();

                    turnCounter++;
                }

                // If a Mutant knocks a TwistedMutant, the Mutant gain more Exp
                if (hero.getClass().equals(Mutant.class) &&
                    enemy.getClass().equals(TwistedMutant.class)) {
                    hero.gainExp(enemy.getExpValue() * 2);
                }
                else {
                    hero.gainExp(enemy.getExpValue());
                }
                
                if (hero.getLuck()) {
                    Weapon droppedWeapon = enemy.dropWeapon();
                    if (droppedWeapon.getDamage() > hero.getWeapon().getDamage()) {
                        hero.setWeapon(droppedWeapon);
                    }
                }
            }
            levelCounter++;
        }

        System.out.println();
        System.out.println("                     -=[VICTORY]=-");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.printf ("%s won the battle, knocked all 3 monsters and\n", hero.getName());
        System.out.printf ("saved the old lady's life! What a hero!\n\n");
        System.out.printf ("%s finished this conflict at Level %d\n", hero.getName(), hero.getLevel());
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println();
    }
}
