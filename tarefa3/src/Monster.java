public abstract class Monster extends Character{
    private final int dangerRating;
    private final int[] expValueList = {75, 150, 225, 375, 750, 900, 1100,
                                        1400, 1600, 1900};
    private final int expValue;

    private final Weapon[] droppableWeapons = {
        new Sword(2, 4, this),
        new Gauntlet(3, 3, this),
        new Pistol(3, this)
    };
    
    public Monster(String name, int healthPoints, int willPoints, int strength,
                   int dangerRating) {
        super(name, healthPoints, willPoints, strength);
        this.dangerRating = dangerRating;
        this.expValue = expValueList[this.dangerRating];
    }

    public int getExpValue() {
        return this.expValue;
    }

    public Weapon dropWeapon() {
        return droppableWeapons[Dice.roll(1, 3) - 1];
    }

    @Override
    public void printStatus() {
        super.printStatus();
        System.out.printf("Danger Rating: %d\n", this.dangerRating);
        System.out.printf("Experience Value: %d\n", this.expValue);
    }
}
