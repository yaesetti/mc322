public abstract class Character implements Combatant{
    private final String name;
    private int healthPoints;
    private boolean isKnocked; // If a character's HP is <= 0.
    private final int willPoints; // Kind of the Mana/Energy that will be used. WIP.
    private int strength;
    private Weapon weapon;

    public Character(String name, int healthPoints, int willPoints,
                     int strength) {
        this.name = name;
        this.healthPoints = healthPoints;
        this.isKnocked = false; // A Character can not be created already knocked.
        this.willPoints = willPoints;
        this.strength = strength;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getHealthPoints() {
        return this.healthPoints;
    }

    @Override
    public boolean getIsKnocked() {
        return this.isKnocked;
    }

    @Override
    public int getStrength() {
        return this.strength;
    }

    public void setStrength(int newStrength) {
        this.strength = newStrength;
    }

    public void setHealthPoints(int newHealthPoints) {
        this.healthPoints = newHealthPoints;
    }

    public Weapon getWeapon() {
        return this.weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
        weapon.setUser(this);
    }

    @Override
    public void receiveDamage(int damage) {
        // If the damage is enough to knock a character, the healthPoints will be set to 0,
        // because the HP can not go below 0, and the atribute isKnocked will be set to true.
        if (this.healthPoints - damage <= 0) {
            this.healthPoints = 0;
            this.isKnocked = true;
            return;
        }
        this.healthPoints -= damage;
    }

    // Its not necessary to print the isKnocked atribute.
    public void printStatus() {
        System.out.printf("Name: %s\n", this.name);
        System.out.printf("Heatlh Points: %d\n", this.healthPoints);
        System.out.printf("Will Points: %d\n", this.willPoints);
        System.out.printf("Strength: %d\n", this.strength);
    }

    public abstract void attack(Character target);

    public abstract CombatAction chooseAction(Character target);
}
