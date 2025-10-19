package events;

import characters.Hero;
import levels.CombatLevel;

/**
 * Represents a nature blessing event in the U-Energy RPG game.
 * <p>
 * The {@code NatureBlessing} event is a positive reinforcement triggered when the hero is lucky
 * and the turn counter reaches 8. It restores health to the hero and adds a mystical flavor to the encounter.
 */
public class NatureBlessing implements Event {

    /**
     * Description of the nature blessing event displayed when triggered.
     */
    private final String description;

    /**
     * Constructs a new {@code NatureBlessing} event with a predefined description.
     */
    public NatureBlessing() {
        this.description = """
                You feel the breeze in the air, the energy of the leaves
                and the heat of the sun empowering you!
                """;
    }

    /**
     * Checks whether the nature blessing event should be triggered.
     * <p>
     * The event occurs if the hero has luck and the turn counter is exactly 8.
     *
     * @param hero  the hero involved in the event
     * @param level the current combat level
     */
    @Override
    public void checkTrigger(Hero hero, CombatLevel level) {
        if (hero.getLuck() && level.getTurnCounter() == 8) {
            this.execute(hero, level);
        }
    }

    /**
     * Executes the nature blessing event and applies its effects.
     * <p>
     * Displays the event description and heals the hero by 2 health points.
     *
     * @param hero  the hero affected by the blessing
     * @param level the level where the blessing occurs
     */
    @Override
    public void execute(Hero hero, CombatLevel level) {
        System.out.println("********** Nature Blessing **********");
        System.out.println();
        System.out.println(this.description);
        System.out.printf("%s received 2 Health Points from Frare (maybe he is some kind of divinity?)\n", hero.getName());
        System.out.println();
        System.out.println("*************************************");

        hero.receiveHealing(2);
    }
}