package items.weapons;

import characters.Character;
import combat.DamageType;
import items.Weapon;
import utils.Dice;

public class Pistol extends Weapon {
    private int bullets;

    public Pistol(String name, int minLevel, Character user) {
        super(name, Dice.roll(2, 4) + 3 * minLevel, minLevel, DamageType.Piercing, user);
        this.bullets = 15;
    }

    public int getBullet() {
        return this.bullets;
    }

    public void setBullet(int bullets) {
        if (bullets <= 0) {
            this.bullets = 0;
            this.setDamage(Dice.roll(1, 3) + this.getUser().getStrength());
        }
        else {
            this.bullets = bullets;
        }
    }
}
