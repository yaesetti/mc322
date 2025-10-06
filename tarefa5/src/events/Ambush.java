package events;

import characters.Hero;
import levels.CombatLevel;

/**
 * Evento de emboscada
 */
public class Ambush implements Event {
    private final String description;

    public Ambush() {
        this.description = """
                While you were distracted and fighting the crime forces,
                a little thief ambushed you!
                """;
    }

    /**
     * Verifica se o evento pode acontecer:
     * O heroi esta sem sorte e
     * o contador de turno e 5
     * 
     * @param hero heroi que ativa a armadilha
     * @param level fase
     */
    @Override
    public void checkTrigger(Hero hero, CombatLevel level) {
        if (!hero.getLuck() && level.getTurnCounter() == 5) {
            this.execute(hero, level);
        }
    }

    /**
     * Printa no console o evento que esta o ocorrendo
     * 
     * @param hero heroi que ativa a armadilha
     * @param level nivel da emboscada
     */
    @Override
    public void execute(Hero hero, CombatLevel level) {
        System.out.println("************** Ambush ***************");
        System.out.println();
        System.out.println(this.description);
        System.out.printf("The Thief stabbed %s and\n", hero.getName());
        System.out.println("dealt 3 points of damage!");
        System.out.println();
        System.out.println("*************************************");
        
        hero.receiveDamage(3);
    }
}