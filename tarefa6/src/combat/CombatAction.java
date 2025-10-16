package combat;

import java.io.Serializable;

import exceptions.CharacterKnocked;
import exceptions.InsufficientWillPoints;

public interface CombatAction extends Serializable{
    public String getName();

    public void execute(Combatant actor, Combatant target) throws InsufficientWillPoints, CharacterKnocked;
}