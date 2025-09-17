public class ThugGang extends Monster {
    private final int groupSize; // How many members in the Gang

    public ThugGang(String name, int healthPoints, int willPoints, int strength,
                 int dangerRanting, int groupSize) {
        super(name, healthPoints, willPoints, strength, dangerRanting);
        this.groupSize = groupSize;
    }

    @Override
    public int getAttackDamage() {
        return this.groupSize * Dice.roll(1, 4) + this.getStrength();
    }
}
