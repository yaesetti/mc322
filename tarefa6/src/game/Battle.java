package game;

import java.io.Serializable;
import java.util.ArrayList;

import characters.Hero;
import characters.heroes.Mutant;
import exceptions.InsufficientCharacterLevel;
import items.weapons.Gauntlet;
import levels.CombatLevel;
import levels.Difficulty;
import levels.builder.FixatedLevelBuilder;

public class Battle implements Serializable{
    private final Hero hero;
    private final ArrayList<CombatLevel> levels;
    private int currentLevelIndex;

    public Battle(Difficulty difficulty) {
        // Instantiating the Hero and their Weapon
        this.hero = new Mutant("Singed", 50, 25, 3);
        Gauntlet poisonGauntlet = new Gauntlet("Singed's Poison Gauntlets",1, 3, hero);

        // Equipping the Weapon
        try {
            this.hero.setWeapon(poisonGauntlet);
        } catch (InsufficientCharacterLevel e) {
            System.err.println("Min Level defined for the first Hero Weapon is too high!");
        }

        // Generating the levels
        FixatedLevelBuilder levelBuilder = new FixatedLevelBuilder();
        this.levels = levelBuilder.generateLevels(this.hero, 3, difficulty);
        
        this.currentLevelIndex = 0;
    }
    
    public Hero getHero() {
        return this.hero;
    }

    public ArrayList<CombatLevel> getLevels() {
        return this.levels;
    }

    public int getCurrentLevelIndex() {
        return this.currentLevelIndex;
    }

    public void incrementCurrentLevelIndex() {
        this.currentLevelIndex++;
    }
}
