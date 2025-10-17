package combat.actions;

import combat.Combatant;
import exceptions.CharacterKnocked;
import exceptions.InsufficientWillPoints;
import utils.Dice;

/**
 * Represents a healing action where the actor heals themselves.
 * This action restores health to the actor and consumes one will point.
 * It extends the {@link Heal} action with a self-targeting behavior.
 */
public class SelfHeal extends Heal {

    /**
     * Returns the name of the action.
     *
     * @return the string "Self Heal"
     */
    @Override
    public String getName() {
        return "Self Heal";
    }

    /**
     * Executes the self-healing action.
     *
     * @param actor  the combatant performing the healing
     * @param target the combatant receiving the healing (should be the actor themselves)
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

        System.out.printf("%s healed themself %d Health Points!\n",
                actor.getName(), healing);
    }
}
