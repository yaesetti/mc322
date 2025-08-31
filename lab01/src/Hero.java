package lab01.src;

public abstract class Hero extends Character {
    private int level = 1;
    private int exp = 0;

    public Hero(String name, int healthPoints, int willPoints, int strength) {
        super(name, healthPoints, willPoints, strength);
    }

    public void gainExp(int exp) {
        this.exp += exp;
    }

    @Override
    public void printStatus() {
        super.printStatus();
        System.out.printf("Level: %d\n", this.level);
        System.out.printf("Experience: %d\n", this.exp);
    }

    public abstract void useSpecialSkill(Character target);
}
