public class Gauntlet extends Weapon {

    public Gauntlet(String name, int minLevel, int strengthAmp, Character user) {
        super(name, Dice.roll(1, 6) + user.getStrength() * strengthAmp,
              minLevel, DamageType.Bludgeoning, user);
    }
}
