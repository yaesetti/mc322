package events;

import characters.Hero;
import levels.CombatLevel;

/**
 * Define se o evento estao apto
 */
public interface Event {
    /**
     * Checa se o evento pode ocorrer
     * 
     * @param hero heroi que ativou o evento
     * @param level nivel do evento
     */
    public void checkTrigger(Hero hero, CombatLevel level);

    /**
     * Realiza/executa o evento
     * 
     * @param hero heroi alvo
     * @param level nivel do evento
     */
    public void execute(Hero hero, CombatLevel level);
}