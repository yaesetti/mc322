package items;

import java.util.List;
import java.util.function.Function;

import characters.Monster;

/**
 * Represents an entity capable of dropping loot items.
 * <p>
 * Classes implementing this interface (such as {@link characters.Monster})
 * define a loot table that determines which items can be dropped,
 * usually based on game difficulty, monster type, or other conditions.
 * </p>
 *
 * <p>
 * Each entry in the loot table is represented as a {@link Function} that takes a
 * {@link Monster} instance as input and produces an {@link Item}. This allows
 * dynamic generation of loot depending on the monster’s attributes or state.
 * </p>
 *
 * <p>
 * Implementations should ensure that the returned list is either immutable
 * or a defensive copy to prevent external modification.
 * </p>
 *
 * @see Item
 * @see characters.Monster
 */
public interface Lootable {

    /**
     * Returns the list of loot-generating functions associated with this entity.
     * <p>
     * Each function maps a {@link Monster} instance to an {@link Item}, allowing
     * contextual loot generation.
     * </p>
     *
     * @return a list of loot generator functions; never {@code null}
     */
    List<Function<Monster, Item>> lootTable();
}
