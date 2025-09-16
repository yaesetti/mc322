public class Attack implements CombatAction {
    @Override
    public String getName() {
        return "Attack";
    }

    @Override
    public boolean canExecute(Combatant actor) {
        if (actor.getWillPoints() < 1) {
            System.out.printf("%s doesn't have enough Will Points to attack!\n", this.getName());
            return false;
        }
        if (actor.getIsKnocked()) {
            System.out.printf("%s is knocked, so they can't attack!\n", this.getName());
            return false;
        }
        return true;
    }

    @Override
    public void execute(Combatant actor, Combatant target) {
        if (canExecute(actor)) {
            int damage = actor.getWeapon().getDamage();

            target.receiveDamage(damage);
        }
    }
}
