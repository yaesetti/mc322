package lab01.src;

public abstract class Monster extends Character{
    private int dangerRating;
    private int expValue;

    public Monster(String name, int healthPoints, int willPoints, int strength,
                   int dangerRating, int expValue) {
        super(name, healthPoints, willPoints, strength);
        this.dangerRating = dangerRating;
        this.expValue = expValue;
    }

    public int getExpValue() {
        return this.expValue;
    }

    @Override
    public void printStatus() {
        super.printStatus();
        System.out.printf("Danger Rating: %d\n", this,dangerRating);
        System.out.printf("Experience Value: %d\n", this.expValue);
    }
}
