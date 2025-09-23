public enum Scenario {
    DOWNTOWN("Downton", """
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

    ICE_CAVE("Ice Cave", """
            It's so cold you can see smoke comming out of your breath.
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
    
    private final String name;
    private final String description;
    private final Event event;

    private Scenario(String name, String description, Event event) {
        this.name = name;
        this.description = description;
        this.event = event;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription () {
        return this.description;
    }

    public Event getEvent() {
        return this.event;
    }

    public abstract void applyEffect(Hero hero);
}
