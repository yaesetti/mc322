public class Freezing implements Event {
    private final String description;

    public Freezing() {
        this.description = """
        It's been too long inside this cold cave,
        your body is starting to feel the damage...
        """;
    }

    @Override
    public void checkTrigger(Hero hero, CombatLevel level) {
        // Triggers every 5 turns
        if (level.getTurnCounter() % 5 == 0) {
            this.execute(hero, level);
        }
    }

    @Override
    public void execute(Hero hero, CombatLevel level) {
        System.out.println("************* Freezing **************");
        System.out.println();
        System.out.println(this.description);
        System.out.printf("%s recieved 3 points of Cold Damage\n", hero.getName());
        System.out.println();
        System.out.println("*************************************");
        
        hero.receiveDamage(3);
    }
}
