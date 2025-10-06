package combat.actions;

import combat.CombatAction;
import combat.Combatant;
import exceptions.CharacterKnocked;
import exceptions.InsufficientWillPoints;

/**
 * Acao de usar a habilidade especial
 */
public class UseSpecialSkill implements CombatAction{

    /**
     * Da o nome da acao
     * @return "Special Skill"
     */
    @Override
    public String getName() {
        return "Special Skill";
    }

    /**
     * Usa a habilidade especial
     * @param actor quem ira realizar a acao
     * @param target quem ira sofrer dessa acao
     * 
     * @throws InsufficientWillPoints pontos de vontade nao sao suficientes para realizar a acao
     * @throws CharacterKnocked caso o personagem esteja derrubado
     */
    @Override
    public void execute(Combatant actor, Combatant target) throws InsufficientWillPoints, CharacterKnocked{
        if (actor.getIsKnocked()) {
            throw new CharacterKnocked();
        }

        if (actor.getWillPoints() < 2) {
            throw new InsufficientWillPoints();
        }
    }
}