package items.weapons;

import characters.Character;
import combat.DamageType;
import items.Weapon;

/**
 * Subclasse de {@link Weapon}
 * arma com municao e dano de impacto
 */
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
        this.bullets = Math.max(0, bullets);
        if (this.bullets == 0) {
            int userStr = 0;
            if (this.getUser() != null) {
                userStr = this.getUser().getStrength();
            }
            this.setDamage(new int[]{1, 3}, userStr);
            this.setDamageType(DamageType.Bludgeoning);
        }
        else {
            this.setDamage(new int[]{2, 4}, 3 * this.getMinLevel());
            this.setDamageType(DamageType.Piercing);
        }
    }

    @Override
    public void setUser(Character newUser) {
        super.setUser(newUser);
        if (this.bullets == 0) {
            int userStr = 0;
            if (newUser != null) {
                userStr = newUser.getStrength();
            }
            this.setDamage(new int[]{1, 3}, userStr);
            this.setDamageType(DamageType.Bludgeoning);
        }
        else {
            this.setDamage(new int[]{2, 4}, 3 * this.getMinLevel());
            this.setDamageType(DamageType.Piercing);
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
