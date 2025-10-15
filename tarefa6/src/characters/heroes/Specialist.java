package characters.heroes;

import characters.Hero;
import combat.Combatant;
import exceptions.CharacterKnocked;
import exceptions.InsufficientCharacterLevel;
import exceptions.InsufficientWillPoints;
import items.Weapon;
import items.weapons.Gauntlet;
import items.weapons.Pistol;
import items.weapons.Sword;

// Specialists are humans that, even without having any super powers, fight for what
// is right. They normally depend on weapons, and are proficient in many of them.
// Real life examples would be: Batman, Hawkeye, Green Arrow...

// This whole class depends on the weapons.
/**
 * Subclasse de {@link Hero}:Specialist
 * 
 * adiciona preferredWeapon: causa mais dano se esta equipado com arma de preferencia
 */
public class Specialist extends Hero{
    private final int preferredWeapon;
    private int weaponIndex;
    private final Weapon[] specialistWeapons = {
        new Sword("Specialist Sword", 1, 7, this),
        new Gauntlet("Specialist Gauntlets", 1, 4, this),
        new Pistol("Specialist Pistol", 1, this)
    };

    /**
     * {@inheritDoc}
     * 
     * Inicia com 0 armas equipadas e quantidade de armas que prefere
     */
    public Specialist(String nome, int healthPoints, int willPoints,
                      int strength, int preferredWeapon) {
        super(nome, healthPoints, willPoints, strength);
        this.weaponIndex = 0;
        this.preferredWeapon = preferredWeapon;
    }

    /**
     * Caso a arma que ele tiver equipado for igual a arma de preferencia causa +4
     * de dano
     * 
     * @return damage dano causado pelo Specialist
     */
    @Override
    public int getAttackDamage() {
        int damage = this.getWeapon().getDamage();
        
        if (this.getWeapon().getClass().equals(this.specialistWeapons[
            preferredWeapon].getClass())) {
                damage += 4;
            }

        return damage;
    }

    /**
     * Equipa uma arma para o Specialist e
     * muda para uma equivalente da specialistWeapons list
     * 
     * @param newWeapon nova arma
     */
    @Override
    public void setWeapon(Weapon newWeapon) {
        try {
            super.setWeapon(newWeapon);
        } catch (InsufficientCharacterLevel e) {
            System.err.println(e.getMessage());
        }

        // If the player chooses to equip another Weapon
        // the new Weapon will replace it's equivalent
        // in the specialistWeapons list, in order to
        // not have duplicates in the list
        if (newWeapon instanceof Sword) {
            specialistWeapons[0] = newWeapon;
        }
        else if (newWeapon instanceof Gauntlet) {
            specialistWeapons[1] = newWeapon;
        }
        else if (newWeapon instanceof Pistol) {
            specialistWeapons[2] = newWeapon;
        }
    }

    /**
     * Metodo para habilidade especial:
     * Caso ele estiver com sorte(critico) e a arma equipada for diferente da arma preferida troca
     * sem critar equipa a arma, tambem verifica se pode equipar e nao extrapolar o quantidade maxima
     * 
     * @param target alvo da habilidade
     */
    @Override
    public void useSpecialSkill(Combatant target) {
        // Name: Versatility
        // Description: Surprise an enemy by suddenly changing the kind of weapon
        //              being used, while also dealing 2 damage.

        // Critical instance of the Special Skill because the Hero is lucky
        if (this.getLuck() && this.weaponIndex != this.preferredWeapon) {
            this.weaponIndex = this.preferredWeapon;
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
        try {
            this.actions.get("Attack").execute(this, target);
        }
        catch (InsufficientWillPoints | CharacterKnocked e) {
            System.err.println(e.getMessage());
        }
    }
}