package items;

import characters.Character;
import combat.DamageType;
import utils.Dice;

/**
 * Classe com o construtor de Weapon
 */
public abstract class Weapon implements Item{
    private final String name;
    private int damage;
    private final int minLevel;
    private DamageType damageType;
    private Character user;

    /**
     * Construtor da arma
     * 
     * @param name Nome da arma
     * @param damageDice dano devido a rolagem do dano
     * @param damageBonus dano adicional da arma
     * @param minLevel level minimo para usar ela
     * @param damageType tipo de dano da arma
     * @param user quem ira usar a arma
     */
    public Weapon(String name, int[] damageDice, int damageBonus, int minLevel, DamageType damageType, Character user) {
        this.name = name;
        this.damage = Dice.roll(damageDice[0], damageDice[1]) + damageBonus;
        this.minLevel = minLevel;
        this.damageType = damageType;
        this.user = user;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public int getDamage() {
        return this.damage;
    }

    /**
     * Dano que sera dano baseado na rolagem de um dado
     */
    public void setDamage(int[] damageDice, int damageBonus) {
        this.damage = Dice.roll(damageDice[0], damageDice[1]) + damageBonus;
    }

    public int getMinLevel() {
        return this.minLevel;
    }

    public DamageType getDamageType() {
        return this.damageType;
    }

    public Character getUser() {
        return this.user;
    }

    /**
     * Define quem estara utilizando essa arma
     */
    public void setUser(Character newUser) {
        this.user = newUser;
    }

    /**
     * Define qual tipo de dano a arma dara
     */
    public void setDamageType(DamageType newDamageType) {
        this.damageType = newDamageType;
    }
}