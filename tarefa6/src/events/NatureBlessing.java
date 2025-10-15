package events;

import characters.Hero;
import levels.CombatLevel;

/**
 * Evento de fortalecimento
 */
public class NatureBlessing implements Event {
    private final String description;

    public NatureBlessing() {
        this.description = """
                You feel the breeze in the air, the energy of the leaves
                and the heat of the sun empowering you!
                """;
    }
    
    /**
     * Verifica se o evento pode ocorrer:
     * caso tenha sorte e o contador de turno for 8
     * o evento ocorrer
     * 
     * @param hero heroi alvo do evento
     * @param level nivel do fortalecimento
     */
    @Override
    public void checkTrigger(Hero hero, CombatLevel level) {
        if (hero.getLuck() && level.getTurnCounter() == 8) {
            this.execute(hero, level);
        }
    }

    /**
     * Printa no console o evento que esta o ocorrendo
     * 
     * @param hero heroi que ativa o evento
     * @param level nivel do fortalecimento
     */
    @Override
    public void execute(Hero hero, CombatLevel level) {
        System.out.println("********** Nature Blessing **********");
        System.out.println();
        System.out.println(this.description);
        System.out.printf("%s received 2 Health Points from Frare (maybe he is a some kind of divinity?)\n", hero.getName());
        System.out.println();
        System.out.println("*************************************");

        hero.receiveHealing(2);
    }
}