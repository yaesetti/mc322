package combat.actions;

import combat.CombatAction;
import combat.Combatant;
import exceptions.CharacterKnocked;
import exceptions.InsufficientWillPoints;

public class UseSpecialSkill implements CombatAction{
    @Override
    public String getName() {
        return "Special Skill";
    }

    @Override
    public void execute(Combatant actor, Combatant target) throws InsufficientWillPoints, CharacterKnocked{
        if (actor.getIsKnocked()) {
            throw new CharacterKnocked();
        }

        if (actor.getWillPoints() < 2) {
            throw new InsufficientWillPoints();
        }

        actor.useSpecialSkill(target);
    }
}
