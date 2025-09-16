public class Heal implements CombatAction {
    @Override
    public String getName() {
        return "Heal";
    }

    @Override
    public boolean canExecute(Combatant actor) {
        return actor.getWillPoints() >= 1;
    }
    
    @Override
    public void execute(Combatant actor, Combatant target) {
        
    }
}
