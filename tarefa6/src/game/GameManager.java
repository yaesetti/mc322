package game;

import java.util.List;

import characters.Hero;
import characters.Monster;
import characters.heroes.Mutant;
import characters.monsters.TwistedMutant;
import exceptions.CharacterKnocked;
import exceptions.InsufficientWillPoints;
import items.Item;
import levels.CombatLevel;
import utils.Dice;

public class GameManager {
    public static void playGame(Battle battle) {
        // Getting the hero that is in the battle
        Hero hero = battle.getHero();

        // Prints Hero's Stats
        System.out.println();
        System.out.println("-=-=-=-=-=-=-=-=- HERO STATS =-=-=-=-=-=-=-=-=-");
        hero.printStatus();
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println();

        // Getting the levels from the battle
        int currentLevelIndex = battle.getCurrentLevelIndex();
        List<CombatLevel> levels = battle.getLevels().subList(currentLevelIndex, battle.getLevels().size());

        if (currentLevelIndex > 0 || levels.get(0).getCurrentMonsterIndex() > 0) {
            if (PostRoundMenu.manageMenu(null, battle) == 1) {
                return;
            }
        }

        // Loops for the amount of levels
        for (CombatLevel level: levels) {
            System.out.printf("----------------------={LEVEL %d}=----------------\n", currentLevelIndex + 1);

            // Prints the information of the level and apply the Scenario effect
            level.start();

            // Loops for the amount of monsters
            int currentMonsterIndex = level.getCurrentMonsterIndex();
            List<Monster> levelMonsters = level.getMonsters().subList(currentMonsterIndex, level.getMonsters().size());
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
                if (hero.getLuck() || Dice.roll(1, 100) <= 25) {
                    droppedItem = enemy.dropLoot();
                }

                // Increments the current monster in the battle
                level.incrementCurrentMonsterIndex();
                
                // Runs the Post Round Menu and checks if the user
                // choose the option to abort the current game and
                // go back to the Main Menu
                if (PostRoundMenu.manageMenu(droppedItem, battle) == 1) {
                    return;
                }
            }
            // Increments the current level saved in the battle
            battle.incrementCurrentLevelIndex();
            currentLevelIndex++;
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
