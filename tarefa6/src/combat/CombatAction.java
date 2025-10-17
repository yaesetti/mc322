package combat;

import java.io.Serializable;

import exceptions.CharacterKnocked;
import exceptions.InsufficientWillPoints;

/**
 * Represents a combat action that can be performed by a {@link Combatant}.
 * Each action has a name and can be executed against a target, potentially
 * affecting health, will points, or status. Actions may throw exceptions
 * if the actor is knocked out or lacks sufficient will points.
 */
public interface CombatAction extends Serializable{
    /**
     * Returns the name of the combat action.
     *
     * @return the action name
     */
    public String getName();

    /**
     * Executes the combat action from the actor onto the target.
     * May throw exceptions if the actor is knocked out or lacks will points.
     *
     * @param actor  the combatant performing the action
     * @param target the combatant receiving the action
     * @throws InsufficientWillPoints if the actor does not have enough will points
     * @throws CharacterKnocked       if the actor is currently knocked out
     */
    public void execute(Combatant actor, Combatant target) throws InsufficientWillPoints, CharacterKnocked;
}
