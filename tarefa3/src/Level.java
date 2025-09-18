import java.util.ArrayList;

public class Level implements Level_I {
    private final int challenge;
    private final String name;
    private final String description;
    private final ArrayList<Monster> monsters;

    public Level (int challenge, String name, String description, ArrayList<Monster> monsters) {
        this.challenge = challenge;
        this.name = name;
        this.description = description;
        this.monsters = monsters;
    }

    public int getChallenge() {
        return this.challenge;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public ArrayList<Monster> getMonsters() {
        return this.monsters;
    }

    @Override
    public getisCompleted (boolean completed) {
        return this.completed;
    }

    public void printInfos() {
        System.out.printf("Level Challange: %d\n", this.challenge);
        System.out.printf("Level Name: %s\n", this.name);
        System.out.printf("Level Description: %s\n", this.description);
        System.out.printf("Number of Monsters: %d", this.monsters.size());
        for (Monster monster: monsters) {
            System.out.printf("-> %s\n", monster.getName());
        }
    }
}
