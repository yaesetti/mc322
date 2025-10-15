package characters.monsters;

import characters.Monster;
import levels.Difficulty;
import utils.Dice;

//"Twisted mutants are those who tampered with their own
// genes in a ruthless quest for power."
/**
 * Subclasse de {@link Monster}: TwistedMutant
 * Classe que quando fica com pouca vida da mais dano
 */
public class TwistedMutant extends Monster {
    private final int maxHealthPoints;

    /**
     * {@inheritDoc}
     * Inicializa o TwistedMutant com uma vida maxima
     */
    public TwistedMutant (String name, int healthPoints, int willPoints, int strength, int dangerRanting, Difficulty difficulty) {
        super(name, healthPoints, willPoints, strength, dangerRanting, difficulty);
        this.maxHealthPoints = healthPoints;
    }

    /**
     * Causa dano normal, porem quando sua vida com para menos de 40 porcento causa +4 de dnao
     * @return damage dano causado pelo TwistedMutant
     */
    @Override
    public int getAttackDamage() {
        int damage = Dice.roll(1, 6) + this.getStrength();

        // When it's life is bellow 40%, it deals 4 points of damage more
        if (this.getHealthPoints() <= 0.4 * this.maxHealthPoints) {
            damage += 4;
        }
        return damage;
    }
}