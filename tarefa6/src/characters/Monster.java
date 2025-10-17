package characters;

import combat.CombatAction;
import combat.Combatant;
import items.Item;
import items.Lootable;
import items.weapons.Gauntlet;
import items.weapons.Pistol;
import items.weapons.Sword;
import levels.Difficulty;
import utils.Dice;

/**
 * Represents a monster that the hero will face in combat.
 * 
 * Inherits attributes and behaviors from {@link Character} and implements the {@link Lootable} interface.
 * As a lootable entity, a monster can drop items upon defeat.
 * 
 * Monster attributes such as health, will points, and strength are scaled based on the game {@link Difficulty}.
 * Experience value and loot drops are determined by the monster's danger rating and difficulty level.
 */
public abstract class Monster extends Character implements Lootable {

    /**
     * Indicates how dangerous the monster is (used to calculate experience reward).
     */
    private final int dangerRating;

    /**
     * Experience values based on danger rating.
     */
    private final int[] expValueList = {
        75, 150, 225, 375, 750, 900, 1100, 1400, 1600, 1900
    };

    /**
     * Experience value awarded when the monster is defeated.
     */
    private final int expValue;

    /**
     * Array of items that the monster can drop.
     */
    private Item[] drops = {};

    /**
     * Predefined loot pool for easy difficulty.
     */
    private final Item[] easyDrops = {
        new Sword("Rusted Sword", 2, 3, this),
        new Gauntlet("Rusted Gauntlet", 2, 2, this),
        new Pistol("Rusted Pistol", 2, this)
    };

    /**
     * Predefined loot pool for medium difficulty.
     */
    private final Item[] mediumDrops = {
        new Sword("Fine Sword", 3, 4, this),
        new Gauntlet("Fine Gauntlet", 3, 3, this),
        new Pistol("Fine Pistol", 3, this)
    };

    /**
     * Predefined loot pool for hard difficulty.
     */
    private final Item[] hardDrops = {
        new Sword("Shiny Sword", 4, 5, this),
        new Gauntlet("Shiny Gauntlet", 4, 4, this),
        new Pistol("Shiny Pistol", 4, this)
    };

    /**
     * Indicates whether the monster is currently lucky.
     */
    private boolean luck;

    /**
     * Constructs a monster with scaled attributes and loot based on difficulty.
     *
     * @param name         the name of the monster
     * @param healthPoints base health points
     * @param willPoints   base will points
     * @param strength     base strength
     * @param dangerRating the monster's danger rating (used for experience calculation)
     * @param difficulty   the difficulty level of the game
     */
    public Monster(String name, int healthPoints, int willPoints, int strength,
                   int dangerRating, Difficulty difficulty) {
        super(name,
              healthPoints * difficulty.getMultiplier(),
              willPoints * difficulty.getMultiplier(),
              strength * difficulty.getMultiplier());

        this.dangerRating = dangerRating;
        this.expValue = expValueList[this.dangerRating];
        this.luck = false;

        switch (difficulty) {
            case EASY:
                this.drops = easyDrops;
                break;
            case MEDIUM:
                this.drops = mediumDrops;
                break;
            default:
                this.drops = hardDrops;
                break;
        }
    }

    /**
     * Returns the experience value awarded when the monster is defeated.
     *
     * @return experience value
     */
    public int getExpValue() {
        return this.expValue;
    }

    /** {@inheritDoc} */
    @Override
    public boolean getLuck() {
        return this.luck;
    }

    /** {@inheritDoc} */
    @Override
    public void setLuck(boolean newLuck) {
        this.luck = newLuck;
    }

    /**
     * Randomly selects an item from the monster's loot pool to drop.
     *
     * @return the dropped item
     */
    @Override
    public Item dropLoot() {
        return drops[Dice.roll(1, drops.length) - 1];
    }

    /**
     * Prints the monster's status to the console, including danger rating and experience value.
     */
    @Override
    public void printStatus() {
        super.printStatus();
        System.out.printf("Danger Rating: %d\n", this.dangerRating);
        System.out.printf("Experience Value: %d\n", this.expValue);
    }

    /**
     * Chooses an action for the monster to perform.
     * 
     * If health is low (<= 2), the monster will attempt to heal.
     * Otherwise, it will attack the target.
     *
     * @param target the target of the action
     * @return the chosen {@link CombatAction}
     */
    @Override
    public CombatAction chooseAction(Combatant target) {
        if (this.getHealthPoints() <= 2) {
            return this.actions.get("SelfHeal");
        } else {
            return this.actions.get("Attack");
        }
    }

    /**
     * Executes the monster's special skill.
     * 
     * This method is intended to be overridden by subclasses.
     *
     * @param target the target of the special skill
     */
    @Override
    public void useSpecialSkill(Combatant target) {
        // Default implementation does nothing
    }
}
