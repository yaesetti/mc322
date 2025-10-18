package exceptions;

/**
 * Exception thrown when a character attempts to equip an item
 * but does not meet the required level.
 * <p>
 * This exception helps enforce level restrictions on equipment usage.
 */
public class InsufficientCharacterLevel extends Exception {

    /**
     * Constructs a new {@code InsufficientCharacterLevel} exception
     * with a default error message.
     */
    public InsufficientCharacterLevel() {
        super("Error: Character's level too low to equip this item");
    }
}
