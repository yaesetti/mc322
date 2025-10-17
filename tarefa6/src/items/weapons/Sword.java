package items.weapons;

import characters.Character;
import combat.DamageType;
import items.Weapon;

public class Sword extends Weapon {
    private int sharpness;

    public Sword(String name, int minLevel, int sharpness, Character user) {
        super(name, new int[]{1, 6}, sharpness + 1 * minLevel, minLevel, DamageType.Slashing, user);
        this.sharpness = sharpness;
    }

    public int getSharpness() {
        return this.sharpness;
    }

    public void setSharpness(int sharpness) {
        this.sharpness = sharpness;
    }

    @Override
    public void setUser(Character newUser) {
        super.setUser(newUser);
        int userStr = 0;
        if (newUser != null) {
            userStr = newUser.getStrength();
        }
        int bonus = this.sharpness + userStr + 1 * this.getMinLevel();
        this.setDamage(new int[]{1, 6}, bonus);
    }

    @Override
    public void printStatus() {
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.printf("Name: %s\n", this.getName());
        System.out.println("Type: Sword");
        System.out.printf("Damage: 1d6 + Sharpness + STR + %d\n", this.getMinLevel());
        System.out.printf("Min Level: %d\n", this.getMinLevel());
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    }
}