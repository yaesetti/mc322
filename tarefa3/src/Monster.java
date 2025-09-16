import java.util.ArrayList;

public abstract class Monster extends Character implements Lootable{
    private final int dangerRating;
    private final int[] expValueList = {75, 150, 225, 375, 750, 900, 1100,
                                        1400, 1600, 1900};
    private final int expValue;
    private ArrayList<CombatAction> actions;

    private final Item[] drops = {
        new Sword("Monster Sword", 2, 4, this),
        new Gauntlet("Monster Gauntlet", 3, 3, this),
        new Pistol("Monster Pistol", 3, this)
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

    @Override
    public Item dropLoot() {
        return drops[Dice.roll(1, drops.length) - 1];
    }

    @Override
    public void printStatus() {
        super.printStatus();
        System.out.printf("Danger Rating: %d\n", this.dangerRating);
        System.out.printf("Experience Value: %d\n", this.expValue);
    }

    @Override
    public CombatAction chooseAction(Combatant target) {

    }
}
