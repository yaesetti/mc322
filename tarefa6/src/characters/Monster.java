package characters;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import combat.CombatAction;
import combat.Combatant;
import items.Item;
import items.LootFactory;
import items.Lootable;
import levels.Difficulty;

/**
 * Represents a monster that the hero will face in combat.
 * <p>
 * This abstract class extends {@link Character} and implements the {@link Lootable} interface,
 * meaning that monsters have combat-related attributes and can drop loot upon defeat.
 * </p>
 *
 * <p>
 * The monster’s attributes (health, will points, and strength) are scaled according
 * to the game’s {@link Difficulty} level. Experience value and loot drops are determined
 * by the monster’s danger rating and difficulty.
 * </p>
 *
 * <p>
 * Each monster also maintains a reference to a loot table, which defines the potential
 * items that can be dropped when defeated. The loot table is automatically selected
 * based on the monster’s difficulty level.
 * </p>
 *
 * @see Character
 * @see Lootable
 * @see LootFactory
 * @see Difficulty
 */
public abstract class Monster extends Character implements Lootable {

    /**
     * Represents the danger level of the monster.
     * This value affects the amount of experience points the hero gains upon defeating it.
     */
    private final int dangerRating;

    /**
     * Predefined list of experience rewards based on the monster's {@code dangerRating}.
     */
    private final int[] expValueList = {75, 150, 225, 375, 750, 900, 1100,
                                        1400, 1600, 1900};

    /**
     * The experience value awarded to the player when this monster is defeated.
     */
    private final int expValue;

    /**
     * Indicates whether the monster currently has a temporary "lucky" status,
     * which can influence certain game events (e.g., loot chance, critical hit).
     */
    private boolean luck;

    /**
     * The difficulty level of the game, used to scale attributes and select loot.
     */
    private final Difficulty difficulty;

    /**
     * A transient list of functions that generate loot items for this monster.
     * <p>
     * Each function takes the monster instance as a parameter and returns a new {@link Item}.
     * The list is selected based on the game {@link Difficulty} level from the {@link LootFactory}.
     * </p>
     * <p>
     * Marked as {@code transient} to prevent serialization of lambdas and function references,
     * which are restored after deserialization via {@link #readObject(java.io.ObjectInputStream)}.
     * </p>
     */
    private transient List<Function<Monster, Item>> drops;

    /**
     * Constructs a new monster with attributes scaled according to the given difficulty.
     *
     * @param name          the monster's name
     * @param healthPoints  the base health points before scaling
     * @param willPoints    the base will points before scaling
     * @param strength      the base strength before scaling
     * @param dangerRating  a number indicating how dangerous this monster is
     * @param difficulty    the game difficulty level
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
        this.difficulty = difficulty;

        // Select the loot table based on difficulty
        this.drops = switch (difficulty) {
            case EASY -> LootFactory.EASY_LOOT;
            case MEDIUM -> LootFactory.MEDIUM_LOOT;
            case HARD -> LootFactory.HARD_LOOT;
        };
    }

    /**
     * Returns the experience value this monster grants when defeated.
     *
     * @return the experience reward for defeating this monster
     */
    public int getExpValue() {
        return this.expValue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean getLuck() {
        return this.luck;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLuck(boolean newLuck) {
        this.luck = newLuck;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns the list of loot generation functions that define which items
     * can drop from this monster, based on difficulty.
     * </p>
     *
     * @return an unmodifiable list of loot-generating functions,
     *         or an empty list if no loot is defined
     */
    @Override
    public List<Function<Monster, Item>> lootTable() {
        if (this.drops == null || this.drops.isEmpty()) {
            return Collections.emptyList();
        }
        return this.drops;
    }

    /**
     * Custom deserialization method used to restore the transient {@code drops} field
     * after a monster instance is loaded from disk.
     *
     * @param in the {@link java.io.ObjectInputStream} used to read the object
     * @throws java.io.IOException if an I/O error occurs
     * @throws ClassNotFoundException if a class required for deserialization cannot be found
     */
    private void readObject(java.io.ObjectInputStream in)
            throws java.io.IOException, ClassNotFoundException {
        in.defaultReadObject();

        this.drops = switch (this.difficulty) {
            case EASY -> LootFactory.EASY_LOOT;
            case MEDIUM -> LootFactory.MEDIUM_LOOT;
            case HARD -> LootFactory.HARD_LOOT;
        };
    }

    /**
     * Prints the current status of the monster, including danger rating and experience value.
     */
    @Override
    public void printStatus() {
        super.printStatus();
        System.out.printf("Danger Rating: %d\n", this.dangerRating);
        System.out.printf("Experience Value: %d\n", this.expValue);
    }

    /**
     * Determines the combat action this monster will take during its turn.
     * <p>
     * By default:
     * <ul>
     *   <li>If the monster’s health points are 2 or lower, it will use the {@code SelfHeal} action.</li>
     *   <li>Otherwise, it will perform a standard {@code Attack}.</li>
     * </ul>
     *
     * @param target the intended target of the action (typically the hero)
     * @return the chosen {@link CombatAction} for this turn
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
     * Uses the monster’s special skill on the specified target.
     * <p>
     * This method should be overridden by subclasses that define unique
     * special abilities or attacks.
     * </p>
     *
     * @param target the target of the special skill
     */
    @Override
    public void useSpecialSkill(Combatant target) {
        // Default implementation does nothing.
    }
}
