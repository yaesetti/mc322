public class Gauntlet extends Weapon {

    public Gauntlet(int minLevel, int strengthAmp, Character user) {
        super(Dice.roll(1, 6) + user.getStrength() * strengthAmp,
              minLevel, DamageType.Bludgeoning, user);
    }
}
