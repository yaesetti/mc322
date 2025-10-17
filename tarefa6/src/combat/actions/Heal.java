package combat.actions;

import combat.CombatAction;
import combat.Combatant;
import exceptions.CharacterKnocked;
import exceptions.InsufficientWillPoints;
import utils.Dice;

/**
 * Represents a healing action that can be performed by a {@link Combatant}.
 * This action restores health to a target and consumes one will point from the actor.
 */
public class Heal implements CombatAction {

    /**
     * Returns the name of the action.
     *
     * @return the string "Heal"
     */
    @Override
    public String getName() {
        return "Heal";
    }

    /**
     * Executes the healing action from the actor onto the target.
     * 
     * @param actor  the combatant performing the healing
     * @param target the combatant receiving the healing
     * @throws InsufficientWillPoints if the actor has less than 1 will point
     * @throws CharacterKnocked       if the actor is currently knocked out
     */
    @Override
    public void execute(Combatant actor, Combatant target)
            throws InsufficientWillPoints, CharacterKnocked {

        if (actor.getIsKnocked()) {
            throw new CharacterKnocked();
        }

        if (actor.getWillPoints() < 1) {
            throw new InsufficientWillPoints();
        }

        int healing = Dice.roll(1, 6) + 2;
        target.receiveHealing(healing);
        actor.setWillPoints(actor.getWillPoints() - 1);

        System.out.printf("%s healed %s %d Health Points!\n",
                actor.getName(), target.getName(), healing);
    }
}
