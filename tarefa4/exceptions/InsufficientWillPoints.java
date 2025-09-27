package exceptions;

public class InsufficientWillPoints extends Exception {
    public InsufficientWillPoints() {
        super("Error: Character doesn't have enough Will Points to perform this action");
    }
}
