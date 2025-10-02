package items.weapons;

import characters.Character;
import combat.DamageType;
import items.Weapon;

public class Gauntlet extends Weapon {
    private final int strengthAmp;

    public Gauntlet(String name, int minLevel, int strengthAmp, Character user) {
        super(name, new int[]{1, 6}, user.getStrength() * strengthAmp + 1 * minLevel, minLevel, DamageType.Bludgeoning, user);
        this.strengthAmp = strengthAmp;
    }

    @Override
    public void printStatus() {
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.printf("Name: %s\n", this.getName());
        System.out.println("Type: Gauntlets");
        System.out.printf("Damage: 1d6 + STR*%d + %d\n", this.strengthAmp, this.getMinLevel());
        System.out.printf("Min Level: %d\n", this.getMinLevel());
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    }
}
