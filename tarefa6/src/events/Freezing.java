package events;

import characters.Hero;
import levels.CombatLevel;

/**
 * Evento de estar congelado
 * Comeca a tomar dano
 */
public class Freezing implements Event {
    private final String description;

    public Freezing() {
        this.description = """
        It's been too long inside this cold cave,
        your body is starting to feel the damage...
        """;
    }

    /**
     * Verifica se o evento pode acontecer:
     * Se o turno for divisivel por 5
     * se sim executa o evento
     * 
     * @param hero
     * @param level
     */
    @Override
    public void checkTrigger(Hero hero, CombatLevel level) {
        // Triggers every 5 turns
        if (level.getTurnCounter() % 5 == 0) {
            this.execute(hero, level);
        }
    }

    /**
     * Printa no console o evento que esta o ocorrendo
     * 
     * @param hero heroi que ativa a armadilha
     * @param level nivel do congelamento
     */
    @Override
    public void execute(Hero hero, CombatLevel level) {
        System.out.println("************* Freezing **************");
        System.out.println();
        System.out.println(this.description);
        System.out.printf("%s received 3 points of Cold Damage\n", hero.getName());
        System.out.println();
        System.out.println("*************************************");
        
        hero.receiveDamage(3);
    }
}