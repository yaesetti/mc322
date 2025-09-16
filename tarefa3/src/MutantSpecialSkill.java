public class MutantSpecialSkill extends SpecialSkill {
    @Override
    public void execute(Combatant actor, Combatant target) {
        // Name: Restore Energy
        // Description: Focus your inner powers and restore 2 Mutant Energy while also
        //              dealing 2 damage to an enemy.

        // If by using this skill the amount of Mutant Energy would bypass the limit
        // the energy is capped at the maximun instead.

        Mutant mutantActor = (Mutant) actor;

        if (mutantActor.getLuck()) {
            mutantActor.setMutantEnergy(mutantActor.getMaxMutantEnergy());
            target.receiveDamage(4);
        }
        else {
            if (mutantActor.getMutantEnergy() + 2 > mutantActor.getMaxMutantEnergy()) {
                mutantActor.setMutantEnergy(mutantActor.getMaxMutantEnergy());
            }
            else {
                mutantActor.setMutantEnergy(mutantActor.getMutantEnergy() + 2);
            }
            // Causes the damage to the enemy.
            if (mutantActor.getLuck()) {
                target.receiveDamage(2);
            }
        }
    }
}
