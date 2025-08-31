package lab01.src;
import java.util.Random;

public abstract class Character {
    private String name;
    private int healthPoints;
    private boolean isKnocked;
    private int willPoints;
    private int strength;

    public Character(String name, int healthPoints, int willPoints, int strength) {
        this.name = name;
        this.healthPoints = healthPoints;
        this.isKnocked = false;
        this.willPoints = willPoints;
        this.strength = strength;
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

    public void attack(Character target) {
        Random randomNumber = new Random();
        int d6 = randomNumber.nextInt(6);
        target.receiveDamage(d6 + strength);
    }
}
