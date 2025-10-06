package items.weapons;

import characters.Character;
import combat.DamageType;
import items.Weapon;

/**
 * Subclasse de {@link Weapon}
 * Arma com tipo de dano de impacto
 */
public class Fists extends Weapon {

    /**
     * Inicializa a arma 
     */
    public Fists(Character user) {
        super("Fists", new int[]{1, 3}, user.getStrength(), 0,
              DamageType.Bludgeoning, user);
    }
    
    /**
     * Printa no console so atributos
     */
    @Override
    public void printStatus() {
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.printf("Name: %s\n", this.getName());
        System.out.println("Type: Fists");
        System.out.println("Damage: 1d3 + STR");
        System.out.println("Bare hands :(");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    }
}