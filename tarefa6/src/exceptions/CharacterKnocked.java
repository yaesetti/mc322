package exceptions;

/**
 * Excecao gerada quando o personagem esta derrubado
 */
public class CharacterKnocked extends Exception{

    /**
     * Cria a nova excecao com a mensagem
     */
    public CharacterKnocked() {
        super("Error: Character is knocked, so they can't perform this action");
    }
}