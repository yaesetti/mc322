package exceptions;

/**
 * Excecao gerada quando o nivel do heroi nao e suficiente
 */
public class InsufficientCharacterLevel extends Exception {

    /**
     * Cria a excecao com a mensagem
     */
    public InsufficientCharacterLevel() {
        super("Error: Character's level too low to equip this item");
    }
}