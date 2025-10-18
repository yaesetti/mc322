package game;

import java.io.Serializable;
import java.util.ArrayList;

import characters.Hero;
import characters.heroes.Mutant;
import exceptions.InsufficientCharacterLevel;
import items.Weapon;
import items.weapons.Gauntlet;
import levels.CombatLevel;
import levels.Difficulty;
import levels.builder.FixatedLevelBuilder;

/**
 * Represents a battle session in the U-Energy RPG game.
 * <p>
 * A battle consists of a {@link Hero}, a sequence of {@link CombatLevel}s,
 * and a tracker for the current level index. The hero is instantiated and equipped
 * at the start, and levels are generated based on the selected {@link Difficulty}.
 * <p>
 * This class is serializable to support game saving and loading.
 */
public class Battle implements Serializable {

    /**
     * The hero participating in the battle.
     */
    private final Hero hero;

    /**
     * The list of combat levels to be completed in this battle.
     */
    private final ArrayList<CombatLevel> levels;

    /**
     * Index of the current level in progress.
     */
    private int currentLevelIndex;

    /**
     * Constructs a new battle session with the specified difficulty.
     * <p>
     * Initializes the {@link Hero} (a {@link Mutant} named "Singed") and equips them
     * with a {@link Gauntlet}. Generates a fixed number of combat levels using
     * {@link FixatedLevelBuilder}.
     *
     * @param difficulty the difficulty setting for the battle
     */
    public Battle(Difficulty difficulty) {
        this.hero = new Mutant("Singed", 50, 25, 3);
        Gauntlet poisonGauntlet = new Gauntlet("Singed's Poison Gauntlets", 1, 3, hero);

        try {
            this.hero.setWeapon(poisonGauntlet);
        } catch (InsufficientCharacterLevel e) {
            System.err.println("Min Level defined for the first Hero Weapon is too high!");
        }

        FixatedLevelBuilder levelBuilder = new FixatedLevelBuilder();
        this.levels = levelBuilder.generateLevels(this.hero, 3, difficulty);
        this.currentLevelIndex = 0;
    }

    /**
     * Returns the hero participating in the battle.
     *
     * @return the {@link Hero} instance
     */
    public Hero getHero() {
        return this.hero;
    }

    /**
     * Returns the list of combat levels in the battle.
     *
     * @return list of {@link CombatLevel} instances
     */
    public ArrayList<CombatLevel> getLevels() {
        return this.levels;
    }

    /**
     * Returns the index of the current level being played.
     *
     * @return current level index
     */
    public int getCurrentLevelIndex() {
        return this.currentLevelIndex;
    }

    /**
     * Advances the level index to the next level.
     * <p>
     * This method is used during gameplay and ensures the index is updated
     * for serialization and save/load functionality.
     */
    public void incrementCurrentLevelIndex() {
        this.currentLevelIndex++;
    }
}
