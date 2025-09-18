public class Ambush implements Event {
    private final String name;
    private final String description;

    public Ambush() {
        this.name = "Ambush";
        this.description = """
                While you are distracted and fighting the crime forces,
                a little thief ambushes you!
                """;
    }
    @Override
    public void checkTrigger(Hero hero, CombatLevel level) {
        if (!hero.getLuck() && level.getTurnCounter() == 5) {
            this.execute(hero, level);
        }
    }

    @Override
    public void execute(Hero hero, CombatLevel level) {
        System.out.println("********** " + this.name + " **********");
        System.out.println();
        System.out.println(this.description);
        System.out.println("The Thief has joined the bundle of enemies!!");
        System.out.println();
        System.out.println("*************************************");
        
        level.getMonsters().add(new ThugGang("Thief", 10, 4, 1, 1, 1));
    }
}
