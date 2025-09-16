public interface CombatAction {
    public void executeAction(Combatant user, Combatant target);

    public void attack(Combatant target);

    public void useSpecialSkill(Combatant target);

    public void heal(Combatant target);
}
