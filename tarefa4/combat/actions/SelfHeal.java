package combat.actions;

import combat.Combatant;
import utils.Dice;

public class SelfHeal extends Heal {
    @Override
    public String getName() {
        return "Self Heal";
    }
    
    @Override
    public void execute(Combatant actor, Combatant target) {
        if (!this.canExecute(actor)) {
            return;
        }

        int healing = Dice.roll(1, 6) + 2;
        target.receiveHealing(healing);

        actor.setWillPoints(actor.getWillPoints() - 1);

        System.out.printf("%s healed themself %d Health Points!\n", actor.getName(), healing);
    }
}
