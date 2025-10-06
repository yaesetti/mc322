package items.weapons;

import characters.Character;
import combat.DamageType;
import items.Weapon;

/**
 * Subclasse de {@link Weapon}
 * arma com dano do tipo cortante
 */
public class Sword extends Weapon {
    private int sharpness;

    /**
     * Inicializa a arma com dano cortante,
     * dano bonus por dano cortante e na forca do heroi e o level
     */
    public Sword(String name, int minLevel, int sharpness, Character user) {
        super(name, new int[]{1, 6}, sharpness + user.getStrength() + 1 * minLevel, minLevel, DamageType.Slashing, user);
        this.sharpness = sharpness;
    }

    public int getSharpness() {
        return this.sharpness;
    }

    public void setSharpness(int sharpness) {
        this.sharpness = sharpness;
    }

    /**
     * Printa no console so atributos
     */
    @Override
    public void printStatus() {
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.printf("Name: %s\n", this.getName());
        System.out.println("Type: Sword");
        System.out.printf("Damage: 1d6 + Sharpness + STR + %d\n", this.getMinLevel());
        System.out.printf("Min Level: %d\n", this.getMinLevel());
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    }
}