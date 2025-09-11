public abstract class Monster extends Character{
    private final int dangerRating;
    private final int[] expValueList = {75, 150, 225, 375, 750, 900, 1100,
                                        1400, 1600, 1900};
    private final int expValue;

    public Monster(String name, int healthPoints, int willPoints, int strength,
                   int dangerRating) {
        super(name, healthPoints, willPoints, strength);
        this.dangerRating = dangerRating;
        this.expValue = expValueList[this.dangerRating];
    }

    public int getExpValue() {
        return this.expValue;
    }

    @Override
    public void printStatus() {
        super.printStatus();
        System.out.printf("Danger Rating: %d\n", this.dangerRating);
        System.out.printf("Experience Value: %d\n", this.expValue);
    }
}
