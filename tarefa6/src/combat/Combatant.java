package combat;

import java.util.HashMap;

import exceptions.InsufficientCharacterLevel;
import items.Weapon;

/**
 * Represents a combat-capable character in the game.
 * Defines the actions a character can perform or be affected by during combat,
 * including health, strength, weapon handling, and special skills.
 */
public interface Combatant {

    /**
     * Returns the name of the combatant.
     *
     * @return the combatant's name
     */
    public String getName();

    /**
     * Returns the current health points of the combatant.
     *
     * @return the health points
     */
    public int getHealthPoints();

    /**
     * Sets the combatant's health points.
     *
     * @param newHealthPoints the new health value
     */
    public void setHealthPoints(int newHealthPoints);

    /**
     * Checks whether the combatant is knocked out.
     *
     * @return true if knocked out, false otherwise
     */
    public boolean getIsKnocked();

    /**
     * Sets the knocked-out status of the combatant.
     *
     * @param newIsKnocked true if knocked out, false otherwise
     */
    public void setIsKnocked(boolean newIsKnocked);

    /**
     * Returns the current will points of the combatant.
     *
     * @return the will points
     */
    public int getWillPoints();

    /**
     * Sets the combatant's will points.
     *
     * @param newWillPoints the new will value
     */
    public void setWillPoints(int newWillPoints);

    /**
     * Returns the strength value of the combatant.
     *
     * @return the strength value
     */
    public int getStrength();

    /**
     * Sets the combatant's strength.
     *
     * @param newStrength the new strength value
     */
    public void setStrength(int newStrength);

    /**
     * Returns the weapon currently equipped by the combatant.
     *
     * @return the equipped weapon
     */
    public Weapon getWeapon();

    /**
     * Equips a new weapon to the combatant.
     *
     * @param newWeapon the weapon to equip
     * @throws InsufficientCharacterLevel if the combatant's level is too low to equip the weapon
     */
    public void setWeapon(Weapon newWeapon) throws InsufficientCharacterLevel;

    /**
     * Returns whether the combatant has luck (critical hit chance).
     *
     * @return true if lucky, false otherwise
     */
    public boolean getLuck();

    /**
     * Sets the luck status of the combatant.
     *
     * @param newLuck true if lucky, false otherwise
     */
    public void setLuck(boolean newLuck);

    /**
     * Returns a map of available combat actions by name.
     *
     * @return a map of action names to {@link CombatAction} instances
     */
    public HashMap<String, CombatAction> getActions();

    /**
     * Returns the damage dealt by a basic attack.
     *
     * @return the attack damage value
     */
    public int getAttackDamage();

    /**
     * Uses the combatant's special skill on a target.
     *
     * @param target the target of the special skill
     */
    public void useSpecialSkill(Combatant target);

    /**
     * Applies damage to the combatant.
     *
     * @param damage the amount of damage received
     */
    public void receiveDamage(int damage);

    /**
     * Applies healing to the combatant.
     *
     * @param healing the amount of healing received
     */
    public void receiveHealing(int healing);

    /**
     * Chooses a combat action to perform against a target.
     *
     * @param target the target of the action
     * @return the chosen {@link CombatAction}
     */
    public CombatAction chooseAction(Combatant target);
}
