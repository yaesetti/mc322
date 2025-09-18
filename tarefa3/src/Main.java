import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Instantiating our Hero Singed and his Weapon
        Mutant hero = new Mutant("Singed", 30, 25, 3);
        Gauntlet poisonGauntlet = new Gauntlet("Singed's Poison Gauntlets",
                                                1, 3, hero);
        hero.setWeapon(poisonGauntlet);

        // Prints Hero's Stats
        System.out.println();
        System.out.println("-=-=-=-=-=-=-=-=- HERO STATS =-=-=-=-=-=-=-=-=-");
        hero.printStatus();
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println();

        // Generate the levels
        FixatedLevelBuilder levelBuilder = new FixatedLevelBuilder();
        ArrayList<CombatLevel> levels = levelBuilder.generateLevels(hero, 3); 

        // Loops for the amount of levels
        for (CombatLevel level: levels) {
            System.out.printf("----------------------={LEVEL %d}=----------------\n", levels.indexOf(level) + 1);

            // Prints the informations of the level and apply the Scenario effect
            level.start();

            // Loops for the ammount of monsters
            ArrayList<Monster> levelMonsters = level.getMonsters();
            for (Monster enemy: levelMonsters){

                // Loops while the enemy still is alive
                while (!enemy.getIsKnocked()) {
                    System.out.println();
                    System.out.printf("--------------------[TURN %d]--------------------\n", level.getTurnCounter());

                    hero.chooseAction(enemy).execute(hero, enemy);
                    enemy.chooseAction(hero).execute(enemy, hero);

                    // If hero's HP == 0 -> Game Over
                    if (hero.getIsKnocked()) {
                        System.out.println();
                        System.out.println("          XXXXXXXXXXXXXXXXX");
                        System.out.println("          X   GAME OVER   X");
                        System.out.println("          XXXXXXXXXXXXXXXXX");
                        System.out.println();

                        return;
                    }

                    // Prints the status of both Combatants after every turn
                    System.out.println();
                    System.out.println("++++++++++++++++++++ HERO ++++++++++++++++++++");
                    hero.printStatus();
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
                    System.out.println();

                    System.out.println("#################### ENEMY ###################");
                    enemy.printStatus();
                    System.out.println("##############################################");
                    System.out.println();

                    // Checks if the Event will happen or not
                    level.getScenario().getEvent().checkTrigger(hero, level);

                    // Increments the Turn Counter
                    level.incrementTurnCounter();
                }

                // If a Mutant knocks a TwistedMutant, the Mutant gains more Exp
                if (hero.getClass().equals(Mutant.class) &&
                    enemy.getClass().equals(TwistedMutant.class)) {
                    hero.gainExp(enemy.getExpValue() * 2);
                }
                else {
                    hero.gainExp(enemy.getExpValue());
                }
                
                // Verifies if the Enemy will drop loot and if
                // it is worth it for the Hero to take it
                if (hero.getLuck()) {
                    Item droppedItem = enemy.dropLoot();

                    if (droppedItem instanceof Weapon droppedWeapon) {
                        if (droppedWeapon.getDamage() > hero.getWeapon().getDamage()) {
                            hero.setWeapon(droppedWeapon);
                        }
                    }
                }
            }
        }

        System.out.println();
        System.out.println("                     -=[VICTORY]=-");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println();
        System.out.printf ("       %s saved the day once again!\n", hero.getName());
        System.out.println();
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println();
    }
}
