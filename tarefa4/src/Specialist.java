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

        // Critical instance of the Special Skill because the Hero is lucky
        if (this.getLuck() && this.weaponIndex != this.prefferedWeapon) {
            this.weaponIndex = this.prefferedWeapon;
        }
        // Normal instance of the Special Skill
        else {
            this.weaponIndex++;
            
            // Verifies if the weaponIndex is bigger than the amount of possible weapons.
            if (this.weaponIndex > this.specialistWeapons.length - 1) {
                this.weaponIndex = 0;
            }
        }

        // Deals the 2 points of damage;
        target.receiveDamage(2);
        
        // Prints what happened
        System.out.printf("%s changed to %s, dealt 2 points of damage to %s and will attack!\n", this.getName(), this.getWeapon().getName(), target.getName());

        // Attacks
        this.actions.get("Attack").execute(this, target);
    }
}
