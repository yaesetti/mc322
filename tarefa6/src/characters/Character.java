package characters;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import combat.CombatAction;
import combat.CombatActionRegistry;
import combat.Combatant;
import exceptions.InsufficientCharacterLevel;
import items.Weapon;
import utils.Dice;

/**
 * Abstract base class representing a character in the game.
 * 
 * Implements the {@link Combatant} interface and provides shared attributes and behaviors
 * for all playable and non-playable characters, including health, will points, strength,
 * weapon handling, and combat actions.
 */
public abstract class Character implements Combatant, Serializable{

    /**
     * The character's name.
     */
    private final String name;

    /**
     * Current health points of the character.
     */
    private int healthPoints;

    /**
     * Indicates whether the character is knocked out (HP ≤ 0).
     */
    private boolean isKnocked;

    /**
     * Will points used to perform actions (similar to energy or mana).
     */
    private int willPoints;

    /**
     * Strength attribute used in damage calculations.
     */
    private int strength;

    /**
     * Weapon currently equipped by the character.
     */
    private Weapon weapon;

    /**
     * Map of available combat actions, keyed by action name.
     */
    protected final HashMap<String, CombatAction> actions = new HashMap<>();

    /**
     * Constructs a character with the given attributes and initializes default actions.
     *
     * @param name         the character's name
     * @param healthPoints initial health points
     * @param willPoints   initial will points
     * @param strength     initial strength value
     */
    public Character(String name, int healthPoints, int willPoints,
                     int strength) {
        this.name = name;
        this.healthPoints = healthPoints;
        this.isKnocked = false; // A Character can not be created already knocked.
        this.willPoints = willPoints;
        this.strength = strength;

        actions.put("Attack", CombatActionRegistry.ATTACK);
        actions.put("SelfHeal", CombatActionRegistry.SELF_HEAL);
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return this.name;
    }

    /** {@inheritDoc} */
    @Override
    public int getHealthPoints() {
        return this.healthPoints;
    }

    /** {@inheritDoc} */
    @Override
    public boolean getIsKnocked() {
        return this.isKnocked;
    }

    /** {@inheritDoc} */
    @Override
    public void setIsKnocked(boolean newIsKnocked) {
        this.isKnocked = newIsKnocked;
    }

    /** {@inheritDoc} */
    @Override
    public int getWillPoints() {
        return this.willPoints;
    }

    /** {@inheritDoc} */
    @Override
    public void setWillPoints(int newWillPoints) {
        this.willPoints = newWillPoints;
    }

    /** {@inheritDoc} */
    @Override
    public int getStrength() {
        return this.strength;
    }

    /** {@inheritDoc} */
    @Override
    public void setStrength(int newStrength) {
        this.strength = newStrength;
    }

    /** {@inheritDoc} */
    @Override
    public void setHealthPoints(int newHealthPoints) {
        this.healthPoints = newHealthPoints;
    }

    /** {@inheritDoc} */
    @Override
    public Weapon getWeapon() {
        return this.weapon;
    }

    /**
     * Equips a weapon to the character and sets the weapon's user.
     *
     * @param weapon the weapon to equip
     * @throws InsufficientCharacterLevel if the character's level is too low for the weapon
     */
    @Override
    public void setWeapon(Weapon weapon) throws InsufficientCharacterLevel {
        this.weapon = weapon;
        weapon.setUser(this);
    }

    /**
     * Returns the map of available combat actions.
     *
     * @return a map of action names to {@link CombatAction} instances
     */
    @Override
    public HashMap<String, CombatAction> getActions() {
        return this.actions;
    }

    /**
     * Applies damage to the character and updates knockout status.
     * Health cannot drop below zero.
     *
     * @param damage the amount of damage received
     */
    @Override
    public void receiveDamage(int damage) {
        if (this.healthPoints - damage <= 0) {
            this.healthPoints = 0;
            this.isKnocked = true;
            return;
        }
        this.healthPoints -= damage;
    }

    /**
     * Applies healing to the character.
     *
     * @param healing the amount of health restored
     */
    @Override
    public void receiveHealing(int healing) {
        this.healthPoints += healing;
    }

    /**
     * Prints the character's current status to the console.
     * Includes name, health points, will points, and strength.
     */
    public void printStatus() {
        System.out.printf("Name: %s\n", this.name);
        System.out.printf("Health Points: %d\n", this.healthPoints);
        System.out.printf("Will Points: %d\n", this.willPoints);
        System.out.printf("Strength: %d\n", this.strength);
    }

    /**
     * Randomly selects an available combat action to perform.
     *
     * @param target the target of the action
     * @return the chosen {@link CombatAction}
     */
    @Override
    public CombatAction chooseAction(Combatant target) {
        ArrayList<CombatAction> actionsList = new ArrayList<>(this.getActions().values());
        int actionIndex = Dice.roll(1, actionsList.size()) - 1;
        return actionsList.get(actionIndex);
    }
}