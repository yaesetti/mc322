public class Pistol extends Weapon {
    private int bullets;

    public Pistol(int minLevel, Character user) {
        super(Dice.roll(2, 4), minLevel, DamageType.Piercing, user);
        this.bullets = 15;
    }
}
