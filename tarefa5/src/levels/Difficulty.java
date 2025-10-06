package levels;

/**
 * Niveis de dificuldade
 */
public enum Difficulty {
    EASY(1),
    MEDIUM(2),
    HARD(3);

    private final int multiplier;

    /**
     * Multiplicador da dificuldade
     * 
     * @param multiplier multiplicador do nivel
     */
    private Difficulty(int multiplier) {
        this.multiplier = multiplier;
    }

    /**
     * Da o multiplicador 
     * 
     * @return retorna o multiplicador
     */
    public int getMultiplier() {
        return this.multiplier;
    }
}