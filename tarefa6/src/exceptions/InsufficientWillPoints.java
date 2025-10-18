package exceptions;

/**
 * Exception thrown when a character attempts to perform an action
 * but lacks sufficient Will Points.
 * <p>
 * This exception is used to enforce resource constraints during combat or ability execution.
 */
public class InsufficientWillPoints extends Exception {

    /**
     * Constructs a new {@code InsufficientWillPoints} exception
     * with a default error message.
     */
    public InsufficientWillPoints() {
        super("Error: Character doesn't have enough Will Points to perform this action");
    }
}
