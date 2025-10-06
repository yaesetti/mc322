package combat.actions;

import combat.CombatAction;
import combat.Combatant;
import exceptions.CharacterKnocked;
import exceptions.InsufficientWillPoints;
import utils.Dice;

/**
 * Acao de cura do personagem
 */
public class Heal implements CombatAction {

    /**
     * Da nome a acao
     * @return "Heal"
     */
    @Override
    public String getName() {
        return "Heal";
    }
    
    /**
     * Realiza a cura e
     * diminui a quantidade de pontos de vontade atual
     * 
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

        if (actor.getWillPoints() < 1) {
            throw new InsufficientWillPoints();
        }

        int healing = Dice.roll(1, 6) + 2;
        target.receiveHealing(healing);
        actor.setWillPoints(actor.getWillPoints() - 1);
        System.out.printf("%s healed %s %d Health Points!\n", actor.getName(), target.getName(), healing);
    }
}