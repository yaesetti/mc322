// is right. They normally depend on weapons, and are proficient in many of them.
// Real life examples would be: Batman, Hawkeye, Green Arrow...

// This whole class depends on the weapons.
public class Specialist extends Hero{
    private int prefferedWeapon;
    private int weaponIndex;
    private final Weapon[] specialistWeapons = {
        new Sword(1, 4, this),
        new Gauntlet(1, 4, this),
        new Pistol(1, this)
    };

    public Specialist(String nome, int healthPoints, int willPoints,
                      int strength, int prefferedWeapon) {
        super(nome, healthPoints, willPoints, strength);
        this.weaponIndex = 0;
        this.prefferedWeapon = prefferedWeapon;
    }

    // Each weapon will cause damage differently.
    @Override
    public void attack(Character target) {
        if(this.getIsKnocked()) {
            System.out.printf("%s is knocked, so they can't attack!\n", this.getName());
            return;
        }

        int damage = this.getWeapon().getDamage();

        // If the Specialist is using their favorite weapon.
        if (this.getWeapon().getClass().equals(this.specialistWeapons[
            prefferedWeapon].getClass())) {damage += 4;}

        target.receiveDamage(damage);

        System.out.printf("%s dealt %d point(s) of damage to %s!\n",
                          this.getName(), damage, target.getName());

        if (target.getHealthPoints() == 0) {
            System.out.printf("%s knocked %s!\n", this.getName(), target.getName());
        }

        // Reduce sword sharpness if the Hero is not lucky this turn
        if (this.getWeapon().getClass().equals(Sword.class) && 
            !this.getLuck()) {
            Sword sword = (Sword)this.getWeapon();
            sword.setSharpnesss(sword.getSharpness() - 1);
        }

        // Spend one bullet per attack
        if (this.getWeapon().getClass().equals(Pistol.class)) {
                Pistol pistol = (Pistol)this.getWeapon();
                pistol.setBullet(pistol.getBullet() - 1);
            }

        // Rolls luck
        if (Dice.roll(1, 100) >= 70) {
            this.setLuck(true);
        }
        else {
            this.setLuck(false);
        }
    }

    @Override
    public void useSpecialSkill(Character target) {
        // Name: Versatility
        // Description: Surprise an enemy by suddenly changing the kind of weapon
        //              being used, while also dealing 2 damage.

        if (this.getLuck() && this.weaponIndex != this.prefferedWeapon) {
            this.weaponIndex = this.prefferedWeapon;
        }
        else {
            this.weaponIndex++;
            
            // Verifies if the weaponIndex is bigger than the amount of possible weapons.
            if (this.weaponIndex > this.specialistWeapons.length - 1) {
                this.weaponIndex = 0;
            }
        }

        target.receiveDamage(2);
        attack(target);
    }
}
