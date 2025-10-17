package levels;

/**
 * Represents difficulty levels for a game stage.
 * Each level has an associated multiplier that can be used
 * to scale challenges, rewards, or other mechanics.
 */
public enum Difficulty {
    /**
     * Easy level
     */
    EASY(1),

    /**
     * Medium level
     */
    MEDIUM(2),

    /**
     * Hard level
     */
    HARD(3);

    private final int multiplier;

    /**
     * Constructs a difficulty level with a specific multiplier.
     *
     * @param multiplier the numeric multiplier associated with the difficulty
     */
    private Difficulty(int multiplier) {
        this.multiplier = multiplier;
    }

    /**
     * Returns the multiplier value of the difficulty level.
     *
     * @return the difficulty multiplier
     */
    public int getMultiplier() {
        return this.multiplier;
    }
}
