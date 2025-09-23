package items.weapons;

import characters.Character;
import combat.DamageType;
import items.Weapon;
import utils.Dice;

public class Fists extends Weapon {
    public Fists(Character user) {
        super("Fists", Dice.roll(1, 3) + user.getStrength(), 0,
              DamageType.Bludgeoning, user);
    }
}
