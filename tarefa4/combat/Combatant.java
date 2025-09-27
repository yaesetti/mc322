package combat;

import exceptions.InsufficientCharacterLevel;
import items.Weapon;
import java.util.HashMap;

public interface Combatant {
    public String getName();

    public int getHealthPoints();

    public void setHealthPoints(int newHealthPoints);

    public boolean getIsKnocked();

    public void setIsKnocked(boolean newIsKnocked);

    public int getWillPoints();

    public void setWillPoints(int newWillPoints);

    public int getStrength();

    public void setStrength(int newStrength);

    public Weapon getWeapon();

    public void setWeapon(Weapon newWeapon) throws InsufficientCharacterLevel;

    public boolean getLuck();

    public void setLuck(boolean newLuck);

    public HashMap<String, CombatAction> getActions();

    public int getAttackDamage();

    public void useSpecialSkill(Combatant target);

    public void receiveDamage(int damage);

    public void receiveHealing(int healing);

    public CombatAction chooseAction(Combatant target);
}
