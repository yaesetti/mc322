package exceptions;

/**
 * Excecao gerada quando os pontos de vontade nao e o suficeinte
 */
public class InsufficientWillPoints extends Exception {

    /**
     * Cria a excecao com a mensagem
     */
    public InsufficientWillPoints() {
        super("Error: Character doesn't have enough Will Points to perform this action");
    }
}