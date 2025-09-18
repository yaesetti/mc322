public class NatureBlessing implements Event {
    private final String name;
    private final String description;

    public NatureBlessing() {
        this.name = "Nature Blessing";
        this.description = """
                You feel the breeze in the air, the energy of the leaves
                and the heat of the sun empowering you!
                """;
    }
    @Override
    public void checkTrigger(Hero hero, CombatLevel level) {
        if (hero.getLuck() && level.getTurnCounter() == 8) {
            this.execute(hero, level);
        }
    }

    @Override
    public void execute(Hero hero, CombatLevel level) {
        System.out.println("********** " + this.name + " **********");
        System.out.println();
        System.out.println(this.description);
        System.out.printf("%s recieved 2 Health Points from Frare (maybe he is a some kind of divinity?)\n", hero.getName());
        System.out.println();
        System.out.println("*************************************");

        hero.receiveHealing(2);
    }
}
