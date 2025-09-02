package lab01.src;
import java.util.Random;

// Specialists are humans that, even without having any super powers, fight for what
// is right. They normally depend on weapons, and are proficient in many of them.
// Real life examples would be: Batman, Hawkeye, Green Arrow...

// This whole class depends on the weapons.
public class Specialist extends Hero{
    // Altough this list is not necessary for the funtionalities. It makes
    // it easier to imagine and keep track of what is happening.
    private final String[] weaponList = {"Sword", "Bow", "Pistol", "Boomerang"};

    private int weaponIndex;
    
    public Specialist(String nome, int healthPoints, int willPoints,
    int strength, int weaponIndex) {
        super(nome, healthPoints, willPoints, strength);
        this.weaponIndex = weaponIndex;
    }

    // Each weapon will cause damage differently.
    @Override
    public void attack(Character target) {
        int damage;
        Random randomNumber = new Random();

        int d6 = randomNumber.nextInt(6) + 1;
        
        if (this.weaponList[weaponIndex].equals("Sword")) {
            damage = d6 + getStrength();
        }
        else if (this.weaponList[weaponIndex].equals("Bow")) {
            damage = d6;
        }
        else if (this.weaponList[weaponIndex].equals("Pistol")) {
            damage = d6 + 1;
        }
        else if (this.weaponList[weaponIndex].equals("Boomerang")) {
            damage = d6/2 + 2 + getStrength();
        }
        else {
            damage = d6/2;
        }

        target.receiveDamage(damage);
    }

    @Override
    public void useSpecialSkill(Character target) {
        // Name: Versatility
        // Description: Surprise an enemy by suddenly changing the kind of weapon
        //              being used, while also dealing 2 damage.

        this.weaponIndex++;
        
        // Verifies if the weaponIndex is bigger than the amount of possible weapons.
        if (this.weaponIndex > this.weaponList.length - 1) {
            this.weaponIndex = 0;
        }

        target.receiveDamage(2);
        attack(target);
    }
}
