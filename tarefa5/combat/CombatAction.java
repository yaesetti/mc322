package combat;

import exceptions.CharacterKnocked;
import exceptions.InsufficientWillPoints;

public interface CombatAction {
    public String getName();

    public void execute(Combatant actor, Combatant target) throws InsufficientWillPoints, CharacterKnocked;
}
