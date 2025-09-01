package lab01.src;
import java.util.Random;

public class Specialist extends Hero{
    private String[] weaponList = {"Sword", "Bow", "Pistol", "Boomerang"};
    private String weapon;

    public Specialist(String nome, int healthPoints, int willPoints,
                      int strength, int weaponIndex) {
        super(nome, healthPoints, willPoints, strength);
        this.weapon = weaponList[weaponIndex];
    }

    @Override
    public void attack(Character target) {
        int damage;
        Random randomNumber = new Random();

        int d6 = randomNumber.nextInt(6) + 1;
        
        if (this.weapon.equals("Sword")) {
            damage = d6 + getStrength();
        }
        else if (this.weapon.equals("Bow")) {
            damage = d6;
        }
        else if (this.weapon.equals("Pistol")) {
            damage = d6 + 1;
        }
        else if (this.weapon.equals("Boomerang")) {
            damage = d6/2 + 2 + getStrength();
        }
        else {
            damage = d6/2;
        }
        
        target.receiveDamage(damage);
    }
}
