public interface Combatant {
    public String getName();

    public int getHealthPoints();

    public boolean getIsKnocked();

    public int getStrength();

    public void receiveDamage(int damage);

    public void receiveHealing(int healing);

    public CombatAction chooseAction(Combatant target);
}
