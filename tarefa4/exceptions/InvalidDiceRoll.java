package exceptions;

public class InvalidDiceRoll extends RuntimeException {
    public InvalidDiceRoll(String message) {
        super(message);
    }
}
