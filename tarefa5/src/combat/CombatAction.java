package combat;

import exceptions.CharacterKnocked;
import exceptions.InsufficientWillPoints;

/**
 * Define acoes basicas de combate um personagem pode realizar em combate.
 */
public interface CombatAction {
    /**
     * Da nome a essa acao
     */
    public String getName();

    /**
     * Executa a acao
     * @param actor quem realiza a acao
     * @param target quem sofre a acao
     * @throws InsufficientWillPoints se pontos de vontade nao for o suficiente
     * @throws CharacterKnocked se o personagem esta derrubado
     */
    public void execute(Combatant actor, Combatant target) throws InsufficientWillPoints, CharacterKnocked;
}