package characters;

import combat.CombatAction;
import combat.Combatant;
import items.Item;
import items.Lootable;
import items.weapons.*;
import levels.Difficulty;
import utils.Dice;

public abstract class Monster extends Character implements Lootable {
    private final int dangerRating;
    private final int[] expValueList = {75, 150, 225, 375, 750, 900, 1100,
                                        1400, 1600, 1900};
    private final int expValue;

    private Item[] drops = {};

    private final Item[] easyDrops = {
        new Sword("Rusted Sword", 2, 3, this),
        new Gauntlet("Rusted Gauntlet", 2, 2, this),
        new Pistol("Rusted Pistol", 2, this)
    };

    private final Item[] mediumDrops = {
        new Sword("Fine Sword", 3, 4, this),
        new Gauntlet("Fine Gauntlet", 3, 3, this),
        new Pistol("Fine Pistol", 3, this)
    };

    private final Item[] hardDrops = {
        new Sword("Shiny Sword", 4, 5, this),
        new Gauntlet("Shiny Gauntlet", 4, 4, this),
        new Pistol("Shiny Pistol", 4, this)
    };

    private boolean luck;
    
    public Monster(String name, int healthPoints, int willPoints, int strength, int dangerRating, Difficulty difficulty) {
        super(name, healthPoints * difficulty.getMultiplier(), willPoints * difficulty.getMultiplier(), strength * difficulty.getMultiplier());
        this.dangerRating = dangerRating;
        this.expValue = expValueList[this.dangerRating];
        this.luck = false;
        switch (difficulty) {
            case EASY:
                this.drops = easyDrops;
                break;
            case MEDIUM:
                this.drops = mediumDrops;
                break;
            default:
                this.drops = hardDrops;
                break;
        }
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
