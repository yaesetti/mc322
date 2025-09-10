public class Sword extends Weapon {
    private int sharpness;

    public Sword(int minLevel, int sharpness, Character user) {
        super(Dice.roll(1, 6) + sharpness + user.getStrength(), minLevel,
              DamageType.Slashing, user);
        this.sharpness = sharpness;
    }

    public int getSharpness() {
        return this.sharpness;
    }

    public void setSharpnesss(int sharpness) {
        this.sharpness = sharpness;
    }
}
