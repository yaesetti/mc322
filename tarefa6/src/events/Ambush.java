package events;

import characters.Hero;
import levels.CombatLevel;

/**
 * Represents an ambush event in the U-Energy RPG game.
 * <p>
 * The {@code Ambush} event is triggered when the hero lacks luck and the turn counter reaches 5.
 * When activated, a thief ambushes the hero and deals damage.
 */
public class Ambush implements Event {

    /**
     * Description of the ambush event displayed when triggered.
     */
    private final String description;

    /**
     * Constructs a new {@code Ambush} event with a predefined description.
     */
    public Ambush() {
        this.description = """
                While you were distracted and fighting the crime forces,
                a little thief ambushed you!
                """;
    }

    /**
     * Checks whether the ambush event should be triggered.
     * <p>
     * The event occurs if the hero does not have luck and the turn counter is exactly 5.
     *
     * @param hero  the hero involved in the event
     * @param level the current combat level
     */
    @Override
    public void checkTrigger(Hero hero, CombatLevel level) {
        if (!hero.getLuck() && level.getTurnCounter() == 5) {
            this.execute(hero, level);
        }
    }

    /**
     * Executes the ambush event and applies its effects.
     * <p>
     * Displays the event description and deals 3 damage to the hero.
     *
     * @param hero  the hero affected by the ambush
     * @param level the level where the ambush occurs
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
