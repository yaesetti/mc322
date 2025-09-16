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
        target.receiveHealing(Dice.roll(1, 6) + 6);
        actor.setWillPoints(actor.getWillPoints() - 1);
        System.out.printf("%s healed %s!\n", actor.getName(), target.getName());
    }
}
