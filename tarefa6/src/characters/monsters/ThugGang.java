package characters.monsters;

import characters.Monster;
import levels.Difficulty;
import utils.Dice;

// Thugs always stick together!
/**
 * Subclasse de {@link Monster}: ThugGang
 * Essa subclasse anda em grupo e seu dano depende da quantidade de unidades tem
 */
public class ThugGang extends Monster {
    private final int groupSize; // How many members in the Gang

    /**
     * {@inheritDoc}
     * Construtor inicializa o groupSize ou tamanho do grupo
     */
    public ThugGang(String name, int healthPoints, int willPoints, int strength, int dangerRanting, Difficulty difficulty, int groupSize) {
        super(name, healthPoints, willPoints, strength, dangerRanting, difficulty);
        this.groupSize = groupSize;
    }

    /**
     * Metodo para retornar o dano que a classe causa
     * @return dano que o grupo da baseado no numero de individuos
     */
    @Override
    public int getAttackDamage() {
        return this.groupSize * Dice.roll(1, 4) + this.getStrength();
    }
}