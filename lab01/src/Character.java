package lab01.src;

public abstract class Character {
    private String name;
    private int healthPoints;
    private boolean isKnocked;
    private int willPoints; // Kind of the Mana/Energy that will be used.
    private int strength;

    public Character(String name, int healthPoints, int willPoints, int strength) {
        this.name = name;
        this.healthPoints = healthPoints;
        this.isKnocked = false; // A Character can not be created already knocked.
        this.willPoints = willPoints;
        this.strength = strength;
    }

    public String getName() {
        return this.name;
    }

    public int getHealthPoints() {
        return this.healthPoints;
    }

    public int getWillPoints() {
        return this.willPoints;
    }

    public int getStrength() {
        return this.strength;
    }


    public void receiveDamage(int damage) {
        if (this.healthPoints - damage <= 0) {
            this.healthPoints = 0;
            this.isKnocked = true;
            return;
        }
        this.healthPoints -= damage;
    }

    public void printStatus() {
        System.out.printf("Name: %s\n", this.name);
        System.out.printf("Heatlh Points: %d\n", this.healthPoints);
        System.out.printf("Will Points: %d\n", this.willPoints);
        System.out.printf("Strength: %d\n", this.strength);
    }

    public abstract void attack(Character target);
}
