package characters;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import combat.CombatAction;
import combat.Combatant;
import items.Item;
import items.LootFactory;
import items.Lootable;
import levels.Difficulty;

public abstract class Monster extends Character implements Lootable {
    private final int dangerRating;
    private final int[] expValueList = {75, 150, 225, 375, 750, 900, 1100,
                                        1400, 1600, 1900};
    private final int expValue;
    private boolean luck;
    private final Difficulty difficulty;
    private transient List<Function<Monster, Item>> drops;
    
    public Monster(String name, int healthPoints, int willPoints, int strength, int dangerRating, Difficulty difficulty) {

        super(name, healthPoints * difficulty.getMultiplier(), willPoints * difficulty.getMultiplier(), strength * difficulty.getMultiplier());

        this.dangerRating = dangerRating;
        this.expValue = expValueList[this.dangerRating];
        this.luck = false;
        this.difficulty = difficulty;
        this.drops = switch (difficulty) {
            case EASY -> LootFactory.EASY_LOOT;
            case MEDIUM -> LootFactory.MEDIUM_LOOT;
            case HARD -> LootFactory.HARD_LOOT;
        };
    }

    public int getExpValue() {
        return this.expValue;
    }

    @Override
    public boolean getLuck() {
        return this.luck;
    }

    @Override
    public void setLuck(boolean newLuck) {
        this.luck = newLuck;
    }

    @Override
    public List<Function<Monster, Item>> lootTable() {
        if (this.drops == null || this.drops.isEmpty()) {
            return Collections.emptyList();
        }
        return this.drops;
    }

    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
        in.defaultReadObject();

        this.drops = switch (this.difficulty) {
            case EASY -> LootFactory.EASY_LOOT;
            case MEDIUM -> LootFactory.MEDIUM_LOOT;
            case HARD -> LootFactory.HARD_LOOT;
        };
    }

    @Override
    public void printStatus() {
        super.printStatus();
        System.out.printf("Danger Rating: %d\n", this.dangerRating);
        System.out.printf("Experience Value: %d\n", this.expValue);
    }

    @Override
    public CombatAction chooseAction(Combatant target) {
        if (this.getHealthPoints() <= 2) {
            return this.actions.get("SelfHeal");
        }
        else {
            return this.actions.get("Attack");
        }
    }

    @Override
    public void useSpecialSkill(Combatant target) {
    }
}
