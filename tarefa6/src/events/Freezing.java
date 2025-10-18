package events;

import characters.Hero;
import levels.CombatLevel;

/**
 * Represents a freezing event in the U-Energy RPG game.
 * <p>
 * The {@code Freezing} event simulates cold exposure in a cave environment.
 * It triggers every 5 turns and deals cold damage to the hero.
 */
public class Freezing implements Event {

    /**
     * Description of the freezing event displayed when triggered.
     */
    private final String description;

    /**
     * Constructs a new {@code Freezing} event with a predefined description.
     */
    public Freezing() {
        this.description = """
        It's been too long inside this cold cave,
        your body is starting to feel the damage...
        """;
    }

    /**
     * Checks whether the freezing event should be triggered.
     * <p>
     * The event occurs every 5 turns.
     *
     * @param hero  the hero involved in the event
     * @param level the current combat level
     */
    @Override
    public void checkTrigger(Hero hero, CombatLevel level) {
        if (level.getTurnCounter() % 5 == 0) {
            this.execute(hero, level);
        }
    }

    /**
     * Executes the freezing event and applies its effects.
     * <p>
     * Displays the event description and deals 3 cold damage to the hero.
     *
     * @param hero  the hero affected by the freezing
     * @param level the level where the freezing occurs
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
