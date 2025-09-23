package combat.actions;

import combat.CombatAction;
import combat.Combatant;
import utils.Dice;

public class Heal implements CombatAction {
    @Override
    public String getName() {
        return "Heal";
    }

    @Override
    public boolean canExecute(Combatant actor) {
        if (actor.getWillPoints() < 1) {
            System.out.printf("%s doesn't have enough Will Points to heal!\n", this.getName());
            return false;
        }
        if (actor.getIsKnocked()) {
            System.out.printf("%s is knocked, so they can't heal!\n", this.getName());
            return false;
        }
        return true;
    }
    
    @Override
    public void execute(Combatant actor, Combatant target) {
        if (!this.canExecute(actor)) {
            return;
        }

        int healing = Dice.roll(1, 6) + 2;
        target.receiveHealing(healing);
        actor.setWillPoints(actor.getWillPoints() - 1);
        System.out.printf("%s healed %s %d Health Points!\n", actor.getName(), target.getName(), healing);
    }
}
