package characters;

import java.util.ArrayList;
import java.util.HashMap;

import combat.CombatAction;
import combat.Combatant;
import combat.actions.Attack;
import combat.actions.SelfHeal;
import exceptions.InsufficientCharacterLevel;
import items.Weapon;
import utils.Dice;

/**
 * Classe que representa o Personagem do RPG (Heroi ou Monstro),
 * armazenando os atributos principais.
 * Fornece metodos para interagir com outros personagens
 */
public abstract class Character implements Combatant{
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

    /**
     * Construtor para inicializar o personagem
     * 
     * @param name O nome do personagem.
     * @param healthPoints A quantidade de vida inicial do personagem
     * @param willPoints A quantidade de pontos de vontade do personagem
     * @param strength O valor base de forca do personagem
     */
    public Character(String name, int healthPoints, int willPoints,
                     int strength) {
        this.name = name;
        this.healthPoints = healthPoints;
        this.isKnocked = false; // A Character can not be created already knocked.
        this.willPoints = willPoints;
        this.strength = strength;

        actions.put("Attack", new Attack());
        actions.put("SelfHeal", new SelfHeal());
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

    /**
     * Metodo para personagem equipar uma arma,
     * caso nao tiver nivel o suficiente gera uma excecao
     */
    @Override
    public void setWeapon(Weapon weapon) throws InsufficientCharacterLevel{
        this.weapon = weapon;
        weapon.setUser(this);
    }

    /**
     * Retorna um mapa de acoes possiveis.
     * String identifica a acao
     * CombatAction contem a logica da acao
     * 
     * @return mapa de acoes possiveis para o personagem 
     */
    @Override
    public HashMap<String, CombatAction> getActions() {
        return this.actions;
    }

    /**
     * Dano que o personagem ira receber. 
     * Garante tambem que se for negativa sera igual a zero no minimo
     * 
     * @param damage dano que ira receber
     */
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

    /**
     * Cura que o personagem ira receber
     * 
     * @param healing quantidade de cura que ira receber
     */
    @Override
    public void receiveHealing(int healing) {
        this.healthPoints += healing;
    }

    // Its not necessary to print the isKnocked attribute.
    /**
     * Imprime no console os principais atributos do personagem:
     * nome,
     * pontos de vida,
     * pontos de vontade,
     * forca.
     */
    public void printStatus() {
        System.out.printf("Name: %s\n", this.name);
        System.out.printf("Health Points: %d\n", this.healthPoints);
        System.out.printf("Will Points: %d\n", this.willPoints);
        System.out.printf("Strength: %d\n", this.strength);
    }

    // Did not make it abstract, even with the document instructing
    // to do so, because the behavior will be common to all characters,
    // so it makes sense to be implemented here.
    /**
     * Metodo para o personagem escolher uma acao
     * 
     * @param target alvo da acao
     * @return acao que sera tomada
     */
    @Override
    public CombatAction chooseAction(Combatant target) {
        ArrayList<CombatAction> actionsList = new ArrayList<>(this.getActions().values());
        int actionIndex = Dice.roll(1, actionsList.size()) - 1;
        return actionsList.get(actionIndex);
    }
}