public class Pistol extends Weapon {
    private int bullets;

    public Pistol(int minLevel, Character user) {
        super(Dice.roll(2, 4), minLevel, DamageType.Piercing, user);
        this.bullets = 15;
    }

    public int getBullet() {
        return this.bullets;
    }

    public void setBullet(int bullets) {
        if (bullets <= 0) {
            this.bullets = 0;
            this.setDamage(Dice.roll(1, 3) + this.getUser().getStrength());
        }
        else {
            this.bullets = bullets;
        }
    }
}
