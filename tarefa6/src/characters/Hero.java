package characters;

import combat.actions.UseSpecialSkill;
import exceptions.InsufficientCharacterLevel;
import items.Weapon;

/**
 * Representa o heroi principal
 * 
 * Herda os atributos e comportamento de {@link Character}
 * porem possui novos getters
 */
public abstract class Hero extends Character {
    private int level;
    private int exp;
    private boolean luck;

    // Array that indicates the amount of exp a Hero needs to be the level it's position
    // indicates. For example: 2700 exp is necessary to be at level 4.
    private final int[] expPerLevel = {0, 0, 300, 900, 2700, 6500, 14000, 23000,
                                       34000, 48000, 64000};

    /**
     * {@inheritDoc}
     * 
     * No heroi, inicializa ele no level 1,
     * com 0 de exp,
     * sem sorte(luck) e
     * adiciona uma nova acao: "Special Skill" 
     */
    public Hero(String name, int healthPoints, int willPoints, int strength) {
        super(name, healthPoints, willPoints, strength);
        this.level = 1; // All heroes start at level 1
        this.exp = 0;
        this.luck = false;

        this.actions.put("Special Skill", new UseSpecialSkill());
    }

    public int getLevel() {
        return this.level;
    }

    @Override
    public boolean getLuck() {
        return this.luck;
    }

    @Override
    public void setLuck(boolean luck) {
        this.luck = luck;
    }

    /**
     * Metodo para dar exp para o heroi.
     * Verifica
     */
    public void gainExp(int exp) {
        this.exp += exp;

        // Verify if after gaining exp the Hero can level up, and if the Hero isn't
        // already max level.
        
        // It needs to be a loop because the player can level up multiple times
        // if the amount of exp gained is big enough.
        while (this.exp >= expPerLevel[this.level + 1] &&
               this.level < this.expPerLevel.length - 1) {
            this.levelUp();
        }
    }

    private void levelUp() {
        this.level++;
        this.setHealthPoints(this.getHealthPoints() + 10);
        this.setStrength(this.getStrength() + 1);
    }

    /**
     * Printa os status do heroi sendo eles
     * Level atual e
     * Experiencia atual
     */
    @Override
    public void printStatus() {
        super.printStatus();
        System.out.printf("Level: %d\n", this.level);
        System.out.printf("Experience: %d\n", this.exp);
    }

    /**
     * Defini a arma a ser equipada no heroi
     * 
     * @param weapon arma a ser equipada
     * @throws InsufficientCharacterLevel se o level do heroi for menor que o necessario para equipar
     */
    @Override
    public void setWeapon(Weapon weapon) throws InsufficientCharacterLevel {
        if (this.getLevel() >= weapon.getMinLevel()) {
            super.setWeapon(weapon);
        }
        else {
            throw new InsufficientCharacterLevel();
        }
    }
}