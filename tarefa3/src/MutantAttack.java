public class MutantAttack extends Attack {
    @Override
    public void execute(Combatant actor, Combatant target) {
        Mutant mutantActor = (Mutant) actor;

        if (canExecute(actor)) {
            int damage = actor.getWeapon().getDamage();
            // If a Mutant have enough mutant energy to buff it's damage, it will
            if (mutantActor.getMutantEnergy() >= 1) {
                damage += 2;
                mutantActor.setMutantEnergy(mutantActor.getMutantEnergy() - 1);
            }

            target.receiveDamage(damage);
            System.out.printf("%s dealt %d point(s) of damage to %s!\n",
                            this.getName(), damage, target.getName());

            super.execute(actor, target);
        }
    }
}
