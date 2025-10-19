package exceptions;

/**
 * Exception thrown when a character is knocked out and attempts to perform an action.
 * <p>
 * This exception is used to prevent invalid operations by characters who are no longer active in combat.
 */
public class CharacterKnocked extends Exception {

    /**
     * Constructs a new {@code CharacterKnocked} exception with a default message.
     */
    public CharacterKnocked() {
        super("Error: Character is knocked, so they can't perform this action");
    }
}