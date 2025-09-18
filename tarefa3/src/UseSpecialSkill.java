public class UseSpecialSkill implements CombatAction{
    @Override
    public String getName() {
        return "Special Skill";
    }

    @Override
    public boolean canExecute(Combatant actor) {
        return actor.getWillPoints() >= 2;
    }

    @Override
    public void execute(Combatant actor, Combatant target) {
        actor.useSpecialSkill(target);
    } 
}
