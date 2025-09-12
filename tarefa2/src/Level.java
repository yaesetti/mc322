import java.util.List;

public abstract class Level {
    private final int challenge;
    private final String name;
    private final String description;
    private final List<Monster> monsters;

    public Level (int challenge, String name, String description, List<Monster> monsters) {
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

    public List<Monster> getMonsters() {
        return this.monsters;
    }
}
