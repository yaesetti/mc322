package combat.actions;

import combat.CombatAction;
import combat.Combatant;
import exceptions.CharacterKnocked;
import exceptions.InsufficientWillPoints;

/**
 * Represents a combat action that triggers the actor's special skill.
 * This action requires at least 2 will points and cannot be executed
 * if the actor is knocked out.
 */
public class UseSpecialSkill implements CombatAction {

    /**
     * Returns the name of the action.
     *
     * @return the string "Special Skill"
     */
    @Override
    public String getName() {
        return "Special Skill";
    }

    /**
     * Executes the special skill action from the actor onto the target.
     *
     * @param actor  the combatant performing the special skill
     * @param target the combatant receiving the effect
     * @throws InsufficientWillPoints if the actor has fewer than 2 will points
     * @throws CharacterKnocked       if the actor is currently knocked out
     */
    @Override
    public void execute(Combatant actor, Combatant target)
            throws InsufficientWillPoints, CharacterKnocked {

        if (actor.getIsKnocked()) {
            throw new CharacterKnocked();
        }

        if (actor.getWillPoints() < 2) {
            throw new InsufficientWillPoints();
        }

        actor.useSpecialSkill(target);
    }
}
