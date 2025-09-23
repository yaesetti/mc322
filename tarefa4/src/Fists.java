public class Fists extends Weapon {
    public Fists(Character user) {
        super("Fists", Dice.roll(1, 3) + user.getStrength(), 0,
              DamageType.Bludgeoning, user);
    }
}
