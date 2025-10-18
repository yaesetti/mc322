package items;

import java.util.List;
import java.util.function.Function;

import characters.Monster;
import items.weapons.Gauntlet;
import items.weapons.Pistol;
import items.weapons.Sword;

/**
 * Provides predefined loot tables for different difficulty levels in the U-Energy RPG game.
 * <p>
 * The {@code LootFactory} class defines static lists of item-generating functions,
 * categorized by difficulty: Easy, Medium, and Hard.
 * <p>
 * Each loot entry is a {@link Function} that takes a {@link Monster} as input and returns an {@link Item},
 * allowing dynamic assignment of item ownership.
 */
public final class LootFactory {

    /**
     * Loot table for Easy difficulty.
     * <p>
     * Includes rusted versions of Sword, Gauntlet, and Pistol.
     */
    public static final List<Function<Monster, Item>> EASY_LOOT = List.of(
        owner -> new Sword("Rusted Sword", 2, 3, owner),
        owner -> new Gauntlet("Rusted Gauntlet", 2, 2, owner),
        owner -> new Pistol("Rusted Pistol", 2, owner)
    );

    /**
     * Loot table for Medium difficulty.
     * <p>
     * Includes fine versions of Sword, Gauntlet, and Pistol.
     */
    public static final List<Function<Monster, Item>> MEDIUM_LOOT = List.of(
        owner -> new Sword("Fine Sword", 3, 4, owner),
        owner -> new Gauntlet("Fine Gauntlet", 3, 3, owner),
        owner -> new Pistol("Fine Pistol", 3, owner)
    );

    /**
     * Loot table for Hard difficulty.
     * <p>
     * Includes shiny versions of Sword, Gauntlet, and Pistol.
     */
    public static final List<Function<Monster, Item>> HARD_LOOT = List.of(
        owner -> new Sword("Shiny Sword", 4, 5, owner),
        owner -> new Gauntlet("Shiny Gauntlet", 4, 4, owner),
        owner -> new Pistol("Shiny Pistol", 4, owner)
    );
}
