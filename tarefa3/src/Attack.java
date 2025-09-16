public class Attack implements CombatAction {
    @Override
    public String getName() {
        return "Attack";
    }

    @Override
    public boolean canExecute(Combatant actor) {
        return actor.getWillPoints() > 0;
    }

    @Override
    public void execute(Combatant actor, Combatant target) {
        if (canExecute(actor)) {
            int damage = actor.getWeapon().getDamage();
            actor.setWillPoints(actor.getWillPoints() - 1);
            target.receiveDamage(damage);
            System.out.printf("%s dealt %d point(s) of damage to %s!\n",
                          this.getName(), damage, target.getName());
        }
        else {
            System.out.printf("%s is knocked, so they can't attack!\n", this.getName());
        }
            
    }
}
