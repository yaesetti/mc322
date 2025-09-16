public abstract class SpecialSkill implements CombatAction{
    @Override
    public String getName() {
        return "Special Skill";
    }

    @Override
    public boolean canExecute(Combatant actor) {
        return actor.getWillPoints() >= 2;
    }

    @Override
    public abstract void execute(Combatant actor, Combatant target);
}
