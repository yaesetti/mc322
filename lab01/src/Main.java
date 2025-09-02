package lab01.src;

public class Main {
    public static void main(String[] args) {
        Mutant hero = new Mutant("Singed", 30, 25, 2);

        // Monsters
        Monster[] enemies = {
        new Thug("Garen", 7, 7, 1),
        new CorruptedMutant("Jax", 8, 5, 2);
        new Thug("Darius", 7, 7, 1);
        };

        String story = """

                -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-

                In a world full of crime and violence only a true hero could save us...

                Maybe he is not the one we want, but the one we need...


                During a dark night, right when a poor old lady was about to get
                attacked by three awlfull monsters, our hero was there!

                %s!!

                -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
                """.formatted(hero.getName());
        
        System.out.println(story);

        System.out.println("++++++++++++++++++++ HERO ++++++++++++++++++++");
        hero.printStatus();
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");

        outerLoop: for (int i = 0; i < enemies.length; i++) {
            Monster enemy = enemies[i];
            System.out.printf("Oh no! The %s is comming!\n", enemy.getName());
            while (!enemy.getIsKnocked()) {
                hero.attack(enemy);
                enemy.attack(hero);

                if (hero.getIsKnocked()) {
                    System.out.println("XXXXXXXXXXXXXXXXX");
                    System.out.println("X   GAME OVER   X");
                    System.out.println("XXXXXXXXXXXXXXXXX");

                    break outerLoop;
                }
                System.out.println("++++++++++++++++++++ HERO ++++++++++++++++++++");
                hero.printStatus();
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");

                System.out.println("++++++++++++++++++++ ENEMY +++++++++++++++++++");
                enemy.printStatus();
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
            }

            hero.gainExp(enemy.getExpValue());
        }
        System.out.println("                     -=[VICTORY]=-");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.printf ("%s won the battle, knocked all 3 monsters and\n", hero.getName());
        System.out.printf ("saved the old lady's life! What a hero!\n\n");
        System.out.printf ("%s finished this conflict at Level %d\n", hero.getName(), hero.getLevel());
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    }
}
