package levels.scenarios;

import characters.Hero;
import characters.heroes.Mutant;
import characters.heroes.Specialist;
import events.Ambush;
import events.Event;
import events.Freezing;
import events.NatureBlessing;

/**
 * Represents the possible scenarios in the game, each with a unique description,
 * associated event, and specific effects applied to the hero upon entering.
 * Scenarios may enhance or reduce hero attributes depending on their type.
 */
public enum Scenario {

    /**
     * Urban scenario with high crime and pollution.
     * Specialists gain +1 Strength in this environment.
     */
    DOWNTOWN("Downtown", """
            In the heart of the most populated city of the country
            the criminals run free! Violence is out of control!
            litter, smoke and gray concrete are all you can see...
            """, new Ambush()) {
                @Override
                public void applyEffect(Hero hero) {
                    if (hero instanceof Specialist) {
                        hero.setStrength(hero.getStrength() + 1);
                        System.out.println("* Specialists get +1 Strength in Downtown\n");
                    }
                }
            },

    /**
     * Natural forest setting with hidden dangers.
     * Mutants gain +10 Health Points in this environment.
     */
    FOREST("Forest", """
            The view is pretty and green, but the crime is evil and
            red. Why the villains are here? Maybe camping? Not sure!
            But you can not let them get away!
            """, new NatureBlessing()) {
                @Override
                public void applyEffect(Hero hero) {
                    if (hero instanceof Mutant) {
                        hero.setHealthPoints(hero.getHealthPoints() + 10);
                        System.out.println("* Mutants get +10 Health Points in Downtown\n");
                    }
                }
            },

    /**
     * Cold and isolated cave scenario.
     * All heroes lose 2 Will Points due to the freezing conditions.
     */
    ICE_CAVE("Ice Cave", """
            It's so cold you can see smoke coming out of your breath.
            For sure the comfort of a warm bed would be 100 times better,
            but the crime won't stop by itself. What are these bad guys
            up to in a desert and cold place like this?
            """, new Freezing()) {
                @Override
                public void applyEffect(Hero hero) {
                    hero.setWillPoints(hero.getWillPoints() - 2);
                    System.out.println("* The coldness makes you more tired, so -2 Will Points\n");
                }
            };
    
    /**
     * The name of the scenario.
     */
    private final String name;

    /**
     * A descriptive text explaining the scenario's atmosphere and context.
     */
    private final String description;

    /**
     * The event triggered when entering the scenario.
     */
    private final Event event;

    /**
     * Constructs a scenario with its name, description, and associated event.
     *
     * @param name        the name of the scenario
     * @param description the narrative description of the scenario
     * @param event       the event triggered upon entering the scenario
     */
    private Scenario(String name, String description, Event event) {
        this.name = name;
        this.description = description;
        this.event = event;
    }

    /**
     * Returns the name of the scenario.
     *
     * @return the scenario name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the description of the scenario.
     *
     * @return the scenario description
     */
    public String getDescription () {
        return this.description;
    }

    /**
     * Returns the event associated with the scenario.
     *
     * @return the scenario's event
     */
    public Event getEvent() {
        return this.event;
    }

    /**
     * Applies the scenario's effect to the given hero.
     * Effects may vary depending on the hero's class or attributes.
     *
     * @param hero the hero affected by the scenario
     */
    public abstract void applyEffect(Hero hero);
}