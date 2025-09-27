package game;

import characters.Monster;
import characters.heroes.Mutant;
import characters.monsters.TwistedMutant;
import exceptions.CharacterKnocked;
import exceptions.InsufficientCharacterLevel;
import exceptions.InsufficientWillPoints;
import items.Item;
import items.Lootable;
import items.weapons.Gauntlet;
import java.util.ArrayList;
import levels.CombatLevel;
import levels.Difficulty;
import levels.builder.FixatedLevelBuilder;
import utils.Dice;

public class GameManager {
    public static void playGame(Difficulty difficulty) {
        // Instantiating our Hero Singed and his Weapon
        Mutant hero = new Mutant("Singed", 50, 25, 3);
        Gauntlet poisonGauntlet = new Gauntlet("Singed's Poison Gauntlets",1, 3, hero);
        try {
            hero.setWeapon(poisonGauntlet);
        } catch (InsufficientCharacterLevel e) {
            System.err.println("Min Level defined for the first Hero Weapon is too high!");
        }

        // Prints Hero's Stats
        System.out.println();
        System.out.println("-=-=-=-=-=-=-=-=- HERO STATS =-=-=-=-=-=-=-=-=-");
        hero.printStatus();
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println();

        // Generate the levels
        FixatedLevelBuilder levelBuilder = new FixatedLevelBuilder();
        ArrayList<CombatLevel> levels = levelBuilder.generateLevels(hero, 3, difficulty); 

        // Loops for the amount of levels
        for (CombatLevel level: levels) {
            System.out.printf("----------------------={LEVEL %d}=----------------\n", levels.indexOf(level) + 1);

            // Prints the information of the level and apply the Scenario effect
            level.start();

            // Loops for the amount of monsters
            ArrayList<Monster> levelMonsters = level.getMonsters();
            for (Monster enemy: levelMonsters){

                // Loops while the enemy still is alive
                while (!enemy.getIsKnocked()) {
                    System.out.println();
                    System.out.printf("--------------------[TURN %d]--------------------\n", level.getTurnCounter());
                    try {
                        hero.chooseAction(enemy).execute(hero, enemy);
                    }
                    catch (InsufficientWillPoints | CharacterKnocked e) {
                        System.err.println(e.getMessage());
                    }
                    
                    try {
                        enemy.chooseAction(hero).execute(enemy, hero);
                    }
                    catch (InsufficientWillPoints | CharacterKnocked e) {
                        System.err.println(e.getMessage());
                    }

                    // If hero's HP == 0 -> Game Over
                    if (hero.getIsKnocked()) {
                        System.out.println();
                        System.out.println("          XXXXXXXXXXXXXXXXX");
                        System.out.println("          X   GAME OVER   X");
                        System.out.println("          XXXXXXXXXXXXXXXXX");
                        System.out.println();

                        InputManager.readEnter();

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
                Item droppedItem = null;
                if ((hero.getLuck() || Dice.roll(1, 100) <= 25) && enemy instanceof Lootable) {
                    droppedItem = enemy.dropLoot();
                }

                // Runs the Post Round Menu and checks if the user
                // choose the option to abort the current game and
                // go back to the Main Menu
                if (PostRoundMenu.manageMenu(hero, enemy, droppedItem) == 1) {
                    return;
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

    InputManager.readEnter();
    }
}
