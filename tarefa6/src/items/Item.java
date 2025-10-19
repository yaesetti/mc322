package items;

import java.io.Serializable;

/**
 * Represents a general item in the U-Energy RPG game.
 * <p>
 * All items must implement this interface, which defines basic behavior
 * for displaying item information and retrieving its name.
 * <p>
 * Items are serializable to support saving and loading game state.
 */
public interface Item extends Serializable {

    /**
     * Returns the name of the item.
     *
     * @return the item's name
     */
    public String getName();

    /**
     * Prints the item's status or description to the console.
     * <p>
     * This method should display relevant details about the item,
     * such as stats, effects, or flavor text.
     */
    public void printStatus();
}