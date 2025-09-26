package levels;

public enum Difficulty {
    EASY(1),
    MEDIUM(2),
    HARD(3);

    private final int multiplier;

    private Difficulty(int multiplier) {
        this.multiplier = multiplier;
    }

    public int getMultiplier() {
        return this.multiplier;
    }
}
