package combat;

import java.util.HashMap;

import exceptions.InsufficientCharacterLevel;
import items.Weapon;

/**
 * Define acoes que um personagem pode sofrer ou realizar
 */
public interface Combatant {
    /**
     * Da nome a acao a ser realizada
     */
    public String getName();

    /**
     * Le o numero de pontos de vida
     */
    public int getHealthPoints();

    /**
     * Altera a vida
     * 
     * @param newHealthPoints nova vida
     */
    public void setHealthPoints(int newHealthPoints);

    /**
     * Le se esta derrubado
     */
    public boolean getIsKnocked();

    /**
     * Altera se esta derrubado
     * 
     * @param newIsKnocked se esta derrubado ou nao
     */
    public void setIsKnocked(boolean newIsKnocked);

    /**
     * Le o numero de pontos de vontade
     */
    public int getWillPoints();

    /**
     * Altera os pontos de vontade
     * 
     * @param newWillPoints novo ponto de vontade
     */
    public void setWillPoints(int newWillPoints);

    /**
     * Le a forca do personagem
     */
    public int getStrength();

    /**
     * Altera a forca do personagem
     * 
     * @param newStrength nova forca
     */
    public void setStrength(int newStrength);

    /**
     * Le uma arma
     */
    public Weapon getWeapon();

    /**
     * Equipa uma nova arma
     * 
     * @param newWeapon nova arma a ser equipada
     * @throws InsufficientCharacterLevel caso nao tenha nivel o suficiente para equipar a arma
     */
    public void setWeapon(Weapon newWeapon) throws InsufficientCharacterLevel;

    /**
     * Le a sorte (critico) do personagem
     */
    public boolean getLuck();

    /**
     * Modfica se o personagem crita ou nao
     * 
     * @param newLuck nova sorte
     */
    public void setLuck(boolean newLuck);

    /**
     * Mapa com o nome da acao
     */
    public HashMap<String, CombatAction> getActions();

    /**
     * Le o dano do ataque
     */
    public int getAttackDamage();

    /**
     * Usa a habilidade especial do heroi
     * 
     * @param target o alvo da habilidade especial
     */
    public void useSpecialSkill(Combatant target);

    /**
     * Dano que o alvo ira receber
     * 
     * @param damage dano que sofrera
     */
    public void receiveDamage(int damage);

    /**
     * Cura que o alvo ira recever
     * 
     * @param healing cura que sofrera
     */
    public void receiveHealing(int healing);

    /**
     * Escolhe a acao a ser realizada
     * 
     * @param target alvo da acao
     */
    public CombatAction chooseAction(Combatant target);
}