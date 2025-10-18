package game;

import java.util.List;
import java.util.function.Function;

import characters.Hero;
import characters.Monster;
import characters.heroes.Mutant;
import characters.monsters.TwistedMutant;
import exceptions.CharacterKnocked;
import exceptions.InsufficientWillPoints;
import items.Item;
import levels.CombatLevel;
import utils.Dice;

/**
 * Manages the execution of a battle session.
 * <p>
 * Controls the game loop, including level progression, combat turns,
 * experience gain, loot drops, and victory or defeat conditions.
 */
public class GameManager {

    /**
     * Starts and runs the game using the provided {@link Battle} instance.
     * <p>
     * The game proceeds through each level and monster encounter, executing
     * combat actions for both the hero and enemies. It handles:
     * <ul>
     *   <li>Hero and enemy turns</li>
     *   <li>Combat status updates</li>
     *   <li>Event triggers</li>
     *   <li>Experience and loot rewards</li>
     *   <li>Game over and victory conditions</li>
     * </ul>
     *
     * @param battle the current battle session to play
     */
    public static void playGame(Battle battle) {
        Hero hero = battle.getHero();

        // Display initial hero stats
        System.out.println();
        System.out.println("-=-=-=-=-=-=-=-=- HERO STATS =-=-=-=-=-=-=-=-=-");
        hero.printStatus();
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println();

        int currentLevelIndex = battle.getCurrentLevelIndex();
        List<CombatLevel> levels = battle.getLevels().subList(currentLevelIndex, battle.getLevels().size());

        // Resume menu if returning mid-battle
        if (currentLevelIndex > 0 || levels.get(0).getCurrentMonsterIndex() > 0) {
            if (PostRoundMenu.manageMenu(null, battle) == 1) return;
        }

        // Iterate through each level
        for (CombatLevel level : levels) {
            System.out.printf("----------------------={LEVEL %d}=----------------\n", currentLevelIndex + 1);
            level.start();

            int currentMonsterIndex = level.getCurrentMonsterIndex();
            List<Monster> levelMonsters = level.getMonsters().subList(currentMonsterIndex, level.getMonsters().size());

            // Iterate through each monster
            for (Monster enemy : levelMonsters) {

                // Combat loop until enemy is defeated
                while (!enemy.getIsKnocked()) {
                    System.out.println();
                    System.out.printf("--------------------[TURN %d]--------------------\n", level.getTurnCounter());

                    try {
                        hero.chooseAction(enemy).execute(hero, enemy);
                    } catch (InsufficientWillPoints | CharacterKnocked e) {
                        System.err.println(e.getMessage());
                    }

                    try {
                        enemy.chooseAction(hero).execute(enemy, hero);
                    } catch (InsufficientWillPoints | CharacterKnocked e) {
                        System.err.println(e.getMessage());
                    }

                    if (hero.getIsKnocked()) {
                        System.out.println();
                        System.out.println("          XXXXXXXXXXXXXXXXX");
                        System.out.println("          X   GAME OVER   X");
                        System.out.println("          XXXXXXXXXXXXXXXXX");
                        System.out.println();
                        InputManager.readEnter();
                        return;
                    }

                    // Display status
                    System.out.println();
                    System.out.println("++++++++++++++++++++ HERO ++++++++++++++++++++");
                    hero.printStatus();
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
                    System.out.println();
                    System.out.println("#################### ENEMY ###################");
                    enemy.printStatus();
                    System.out.println("##############################################");
                    System.out.println();

                    // Trigger scenario event
                    level.getScenario().getEvent().checkTrigger(hero, level);

                    level.incrementTurnCounter();
                }

                // Experience reward (bonus for Mutant vs TwistedMutant)
                if (hero.getClass().equals(Mutant.class) &&
                    enemy.getClass().equals(TwistedMutant.class)) {
                    hero.gainExp(enemy.getExpValue() * 2);
                } else {
                    hero.gainExp(enemy.getExpValue());
                }

                // Loot drop logic using functional loot table
                Item droppedItem = null;
                if (hero.getLuck() || Dice.roll(1, 100) <= 25) {
                    List<Function<Monster, Item>> lootTable = enemy.lootTable();
                    int index = Dice.roll(1, lootTable.size()) - 1;
                    droppedItem = lootTable.get(index).apply(null);
                }

                level.incrementCurrentMonsterIndex();

                if (PostRoundMenu.manageMenu(droppedItem, battle) == 1) return;
            }

            battle.incrementCurrentLevelIndex();
            currentLevelIndex++;
        }

        // Victory message
        System.out.println();
        System.out.println("                     -=[VICTORY]=-");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println();
        System.out.printf("       %s saved the day once again!\n", hero.getName());
        System.out.println();
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println();

        InputManager.readEnter();
    }
}
