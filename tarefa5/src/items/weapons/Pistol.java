package items.weapons;

import characters.Character;
import combat.DamageType;
import items.Weapon;

public class Pistol extends Weapon {
    private int bullets;

    public Pistol(String name, int minLevel, Character user) {
        super(name, new int[]{2, 4}, 3 * minLevel, minLevel, DamageType.Piercing, user);
        this.bullets = 15;
    }

    public int getBullet() {
        return this.bullets;
    }

    public void setBullet(int bullets) {
        if (bullets <= 0) {
            this.bullets = 0;
            this.setDamage(new int[]{1, 3}, this.getUser().getStrength());
            this.setDamageType(DamageType.Bludgeoning);
        }
        else {
            this.bullets = bullets;
        }
    }

    @Override
    public void printStatus() {
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.printf("Name: %s\n", this.getName());
        System.out.println("Type: Pistol");
        System.out.printf("Damage (bullets > 0): 2d4 + %d\n", 3*this.getMinLevel());
        System.out.println("Damage (bullets < 0): 1d3 + STR");
        System.out.printf("Min Level: %d\n", this.getMinLevel());
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    }
}
