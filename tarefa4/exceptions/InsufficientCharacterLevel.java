package exceptions;

public class InsufficientCharacterLevel extends Exception {
    public InsufficientCharacterLevel() {
        super("Error: Character's level too low to equip this item");
    }
}
