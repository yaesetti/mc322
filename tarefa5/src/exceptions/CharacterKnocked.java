package exceptions;

public class CharacterKnocked extends Exception{
    public CharacterKnocked() {
        super("Error: Character is knocked, so they can't perform this action");
    }
}
