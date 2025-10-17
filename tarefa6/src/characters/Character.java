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

public abstract class Character implements Combatant, Serializable{
    private final String name;
    private int healthPoints;
    private boolean isKnocked; // If a character's HP is <= 0.
    private int willPoints; // Kind of the Mana/Energy that will be used. WIP.
    private int strength;
    private Weapon weapon;
    
    // We are not using List<CombatAction>, instead, we are using
    // HashMap<String, CombatAction> in order to make it easier and more
    // scalable.

    protected final HashMap<String, CombatAction> actions = new HashMap<>();

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

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getHealthPoints() {
        return this.healthPoints;
    }

    @Override
    public boolean getIsKnocked() {
        return this.isKnocked;
    }

    @Override
    public void setIsKnocked(boolean newIsKnocked) {
        this.isKnocked = newIsKnocked;
    }

    @Override
    public int getWillPoints() {
        return this.willPoints;
    }

    @Override
    public void setWillPoints(int newWillPoints) {
        this.willPoints = newWillPoints;
    }

    @Override
    public int getStrength() {
        return this.strength;
    }

    @Override
    public void setStrength(int newStrength) {
        this.strength = newStrength;
    }

    @Override
    public void setHealthPoints(int newHealthPoints) {
        this.healthPoints = newHealthPoints;
    }

    @Override
    public Weapon getWeapon() {
        return this.weapon;
    }

    @Override
    public void setWeapon(Weapon weapon) throws InsufficientCharacterLevel{
        this.weapon = weapon;
        weapon.setUser(this);
    }

    @Override
    public HashMap<String, CombatAction> getActions() {
        return this.actions;
    }

    @Override
    public void receiveDamage(int damage) {
        // If the damage is enough to knock a character, the healthPoints will be set to 0,
        // because the HP can not go below 0, and the attribute isKnocked will be set to true.
        if (this.healthPoints - damage <= 0) {
            this.healthPoints = 0;
            this.isKnocked = true;
            return;
        }
        this.healthPoints -= damage;
    }

    @Override
    public void receiveHealing(int healing) {
        this.healthPoints += healing;
    }

    // Its not necessary to print the isKnocked attribute.
    public void printStatus() {
        System.out.printf("Name: %s\n", this.name);
        System.out.printf("Health Points: %d\n", this.healthPoints);
        System.out.printf("Will Points: %d\n", this.willPoints);
        System.out.printf("Strength: %d\n", this.strength);
    }

    // Did not make it abstract, even with the document instructing
    // to do so, because the behavior will be common to all characters,
    // so it makes sense to be implemented here.
    @Override
    public CombatAction chooseAction(Combatant target) {
        ArrayList<CombatAction> actionsList = new ArrayList<>(this.getActions().values());
        int actionIndex = Dice.roll(1, actionsList.size()) - 1;
        return actionsList.get(actionIndex);
    }
}