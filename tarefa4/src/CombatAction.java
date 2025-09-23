public interface CombatAction {
    public String getName();

    public boolean canExecute(Combatant actor);

    public void execute(Combatant actor, Combatant target);
}
