package levels;

import java.util.ArrayList;

import characters.Hero;
import characters.Monster;
import levels.scenarios.Scenario;

/**
 * Represents a combat level in the game, where a hero faces a group of monsters
 * within a specific scenario. Tracks the challenge rating, turn progression,
 * and combat completion status.
 */
public class CombatLevel implements Level {

    /**
     * The hero of the level
     */
    private final Hero hero;

    /**
     * The scenario of the level
     */
    private final Scenario scenario;

    /**
     * The challenge rating of the level
     */
    private final int challenge;

    /**
     * The array that will keep the monsters of the level
     */
    private final ArrayList<Monster> monsters;

    /**
     * The counter that indicates which turn is it
     */
    private int turnCounter;

    /**
     * The index of the current monster. Will be saved and loaded during serialization
     */
    private int currentMonsterIndex;

    /**
     * Constructs a CombatLevel with the given hero, scenario, challenge rating, and monsters.
     *
     * @param hero      the hero participating in the combat
     * @param scenario  the scenario/environment of the level
     * @param challenge the difficulty rating of the level
     * @param monsters  the list of monsters to fight
     */
    public CombatLevel(Hero hero, Scenario scenario, int challenge, ArrayList<Monster> monsters) {
        this.hero = hero;
        this.scenario = scenario;
        this.challenge = challenge;
        this.monsters = monsters;
        this.turnCounter = 1;
        this.currentMonsterIndex = 0;
    }

    /**
     * Returns the challenge rating of the level.
     *
     * @return the challenge value
     */
    public int getChallenge() {
        return this.challenge;
    }

    /**
     * Returns the scenario of the level.
     *
     * @return the scenario object
     */
    @Override
    public Scenario getScenario() {
        return this.scenario;
    }

    /**
     * Returns the list of monsters in the level.
     *
     * @return the list of Monster objects
     */
    public ArrayList<Monster> getMonsters() {
        return this.monsters;
    }

    /**
     * Checks whether the level is completed.
     * A level is completed when all monsters are knocked out.
     *
     * @return true if all monsters are knocked out, false otherwise
     */
    @Override
    public boolean getIsCompleted() {
        for (Monster monster : monsters) {
            if (!monster.getIsKnocked()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the current turn counter.
     *
     * @return the number of turns elapsed
     */
    @Override
    public int getTurnCounter() {
        return this.turnCounter;
    }

    /**
     * Increments the turn counter by one.
     */
    @Override
    public void incrementTurnCounter() {
        this.turnCounter++;
    }

    /**
     * Returns the index of the current monster being engaged.
     *
     * @return the current monster index
     */
    @Override
    public int getCurrentMonsterIndex() {
        return this.currentMonsterIndex;
    }

    /**
     * Increments the current monster index by one.
     */
    @Override
    public void incrementCurrentMonsterIndex() {
        this.currentMonsterIndex++;
    }

    /**
     * Starts the level by displaying its details and applying the scenario effect to the hero.
     */
    @Override
    public void start() {
        System.out.printf("Level Challenge: %d\n", this.challenge);
        System.out.printf("Level Name: %s\n", this.scenario.getName());
        System.out.printf("Level Description: \n%s\n", this.scenario.getDescription());
        this.scenario.applyEffect(this.hero);
        System.out.printf("Number of Monsters: %d\n", this.monsters.size());
        for (Monster monster : monsters) {
            System.out.printf("-> %s\n", monster.getName());
        }
    }
}
