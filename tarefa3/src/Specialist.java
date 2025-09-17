// is right. They normally depend on weapons, and are proficient in many of them.
// Real life examples would be: Batman, Hawkeye, Green Arrow...

// This whole class depends on the weapons.
public class Specialist extends Hero{
    private final int prefferedWeapon;
    private int weaponIndex;
    private final Weapon[] specialistWeapons = {
        new Sword("Specialist Sword", 1, 4, this),
        new Gauntlet("Specialist Gauntlets", 1, 4, this),
        new Pistol("Specialist Pistol", 1, this)
    };

    public Specialist(String nome, int healthPoints, int willPoints,
                      int strength, int prefferedWeapon) {
        super(nome, healthPoints, willPoints, strength);
        this.weaponIndex = 0;
        this.prefferedWeapon = prefferedWeapon;
    }

    @Override
    public int getAttackDamage() {
        int damage = this.getWeapon().getDamage();
        
        if (this.getWeapon().getClass().equals(this.specialistWeapons[
            prefferedWeapon].getClass())) {
                damage += 4;
            }

        return damage;
    }

    @Override
    public void useSpecialSkill(Combatant target) {
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
        this.actions.get("Attack").execute(this, target);
    }
}
