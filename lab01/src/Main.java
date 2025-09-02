package lab01.src;

public class Main {
    public static void main(String[] args) {
        Mutant hero = new Mutant("Singed", 30, 25, 2);

        // Monsters
        Monster enemies[];
        enemies[0] = new Monster();

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

        outerLoop: for (int i = 0; i < 3; i++) {
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
            }

            hero.gainExp(enemy.getExpValue());
        }
    }
}
