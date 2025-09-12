public abstract class Weapon {
    private final int damage;
    private final int minLevel;
    private final DamageType damageType;
    private Character user;

    public Weapon(int damage, int minLevel, DamageType damageType, Character user) {
        this.damage = damage;
        this.minLevel = minLevel;
        this.damageType = damageType;
        this.user = user;
    }

    public int getDamage() {
        return this.damage;
    }

    public int getMinLevel() {
        return this.minLevel;
    }

    public DamageType getDamageType() {
        return this.damageType;
    }

    public Character getUser() {
        return this.user;
    }

    public void setUser(Character new_user) {
        this.user = new_user;
    }
}
