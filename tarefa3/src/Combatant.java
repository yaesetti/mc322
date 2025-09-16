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

    public void setWeapon(Weapon newWeapon);

    public void receiveDamage(int damage);

    public void receiveHealing(int healing);

    public CombatAction chooseAction(Combatant target);
}
