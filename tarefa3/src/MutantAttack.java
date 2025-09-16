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
            
            if (target.getHealthPoints() == 0) {
                System.out.printf("%s knocked %s!\n", this.getName(), target.getName());
            }

            // Reduce sword sharpness if the Hero is not lucky this turn
            if (mutantActor.getWeapon().getClass().equals(Sword.class) && 
                !mutantActor.getLuck()) {
                Sword sword = (Sword)mutantActor.getWeapon();
                sword.setSharpness(sword.getSharpness() - 1);
            }

            // Spend one bullet per attack
            if (mutantActor.getWeapon().getClass().equals(Pistol.class)) {
                    Pistol pistol = (Pistol)mutantActor.getWeapon();
                    pistol.setBullet(pistol.getBullet() - 1);
                }

            // Rolls luck
            if (Dice.roll(1, 100) >= 70) {
                mutantActor.setLuck(true);
            }
            else {
                mutantActor.setLuck(false);
            }
        }
    }
}
