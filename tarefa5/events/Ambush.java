package events;

import characters.Hero;
import levels.CombatLevel;

public class Ambush implements Event {
    private final String description;

    public Ambush() {
        this.description = """
                While you were distracted and fighting the crime forces,
                a little thief ambushed you!
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
