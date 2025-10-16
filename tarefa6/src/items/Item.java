package items;

import java.io.Serializable;

/**
 * Cria um item
 */
public interface Item extends Serializable {

    /** Da nome ao item */
    public String getName();

    /** Printa os status do item */
    public void printStatus();
}