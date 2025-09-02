package lab01.src;

public abstract class Hero extends Character {
    private int level;
    private int exp;

    // Array that indicates the amount of exp a Hero needs to be the level it's position
    // indicates. For example: 2700 exp is necessary to be at level 4.
    private int[] expPerLevel = {0, 0, 300, 900, 2700, 6500, 14000, 23000, 34000, 48000,
                                 64000};

    public Hero(String name, int healthPoints, int willPoints, int strength) {
        super(name, healthPoints, willPoints, strength);
        this.level = 1; // All heros start at level 1
        this.exp = 0;
    }

    public int getLevel() {
        return this.level;
    }

    public void gainExp(int exp) {
        this.exp += exp;

        // Verify if after gaining exp the Hero can level up.
        
        // It needs to be a loop because the player can level up multiple times
        // if the amount of exp gained is big enough.
        while (this.exp >= expPerLevel[this.level + 1]) {
            this.level++;
        }
    }

    @Override
    public void printStatus() {
        super.printStatus();
        System.out.printf("Level: %d\n", this.level);
        System.out.printf("Experience: %d\n", this.exp);
    }

    public abstract void useSpecialSkill(Character target);
}
