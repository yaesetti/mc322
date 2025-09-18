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
        if (!this.canExecute(actor)) {
            return;
        }
        
        // Attack Damage is calculated differently for each class of Combatant
        int damage = actor.getAttackDamage();
        target.receiveDamage(damage);

        // Print the ammount of damage dealt and if it was enough to get them knocked.
        if (target.getIsKnocked()) {
            System.out.printf("%s dealt %d point(s) of damage to %s and knocked them!\n",
                                actor.getName(), damage, target.getName());
        }
        else {
            System.out.printf("%s dealt %d point(s) of damage to %s!\n",
                                actor.getName(), damage, target.getName());
        }

        if (actor instanceof Hero) {
            // Reduce sword sharpness if the Combatant is not lucky this turn
            if (actor.getWeapon().getClass().equals(Sword.class) && 
                !actor.getLuck()) {
                Sword sword = (Sword)actor.getWeapon();
                sword.setSharpness(sword.getSharpness() - 1);
            }
    
            // Spend one bullet per attack
            if (actor.getWeapon().getClass().equals(Pistol.class)) {
                    Pistol pistol = (Pistol)actor.getWeapon();
                    pistol.setBullet(pistol.getBullet() - 1);
            }
        }
        // Rolls luck
        if (Dice.roll(1, 100) <= 30) {
            actor.setLuck(true);
        }
        else {
            actor.setLuck(false);
        }
    }
}
