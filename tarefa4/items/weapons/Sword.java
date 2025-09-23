package items.weapons;

import characters.Character;
import combat.DamageType;
import items.Weapon;
import utils.Dice;

public class Sword extends Weapon {
    private int sharpness;

    public Sword(String name, int minLevel, int sharpness, Character user) {
        super(name, Dice.roll(1, 6) + sharpness + user.getStrength(), minLevel,
              DamageType.Slashing, user);
        this.sharpness = sharpness;
    }

    public int getSharpness() {
        return this.sharpness;
    }

    public void setSharpness(int sharpness) {
        this.sharpness = sharpness;
    }
}
