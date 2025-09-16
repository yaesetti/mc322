public abstract class Weapon implements Item{
    private final String name;
    private int damage;
    private final int minLevel;
    private final DamageType damageType;
    private Character user;

    public Weapon(String name, int damage, int minLevel, DamageType damageType, Character user) {
        this.name = name;
        this.damage = damage;
        this.minLevel = minLevel;
        this.damageType = damageType;
        this.user = user;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public int getDamage() {
        return this.damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
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
