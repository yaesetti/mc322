package levels;

import java.util.ArrayList;

import characters.Hero;
import characters.Monster;
import levels.scenarios.Scenario;

public class CombatLevel implements Level {
    private final Hero hero;
    private final Scenario scenario;
    private final int challenge;
    private final ArrayList<Monster> monsters;
    private int turnCounter;
    private int currentMonsterIndex;

    public CombatLevel(Hero hero, Scenario scenario, int challenge, ArrayList<Monster> monsters) {
        this.hero = hero;
        this.scenario = scenario;
        this.challenge = challenge;
        this.monsters = monsters;
        this.turnCounter = 1;
        this.currentMonsterIndex = 0;
    }

    public int getChallenge() {
        return this.challenge;
    }

    @Override
    public Scenario getScenario() {
        return this.scenario;
    }

    public ArrayList<Monster> getMonsters() {
        return this.monsters;
    }

    @Override
    public boolean getIsCompleted() {
        for (Monster monster: monsters) {
            if (!monster.getIsKnocked()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int getTurnCounter() {
        return this.turnCounter;
    }

    @Override
    public void incrementTurnCounter() {
        this.turnCounter++;
    }

    @Override
    public int getCurrentMonsterIndex() {
        return this.currentMonsterIndex;
    }

    @Override
    public void incrementCurrentMonsterIndex() {
        this.currentMonsterIndex++;
    }

    @Override
    public void start() {
        System.out.printf("Level Challenge: %d\n", this.challenge);
        System.out.printf("Level Name: %s\n", this.scenario.getName());
        System.out.printf("Level Description: \n%s\n", this.scenario.getDescription());
        this.scenario.applyEffect(this.hero);
        System.out.printf("Number of Monsters: %d\n", this.monsters.size());
        for (Monster monster: monsters) {
            System.out.printf("-> %s\n", monster.getName());
        }
    }
}