package items;

import java.io.;
import java.util.List;
import java.util.function.Function;

import characters.Monster;
import items.weapons.Gauntlet;
import items.weapons.Pistol;
import items.weapons.Sword;

public final class LootFactory implements Transient {
    public static final List<Function<Monster, Item>> EASY_LOOT = List.of(
        owner -> new Sword("Rusted Sword", 2, 3, owner),
        owner -> new Gauntlet("Rusted Gauntlet", 2, 2, owner),
        owner -> new Pistol("Rusted Pistol", 2, owner)
    );
    
    public static final List<Function<Monster, Item>> MEDIUM_LOOT = List.of(
        owner -> new Sword("Fine Sword", 3, 4, owner),
        owner -> new Gauntlet("Fine Gauntlet", 3, 3, owner),
        owner -> new Pistol("Fine Pistol", 3, owner)
    );

    public static final List<Function<Monster, Item>> HARD_LOOT = List.of(
        owner -> new Sword("Shiny Sword", 4, 5, owner),
        owner -> new Gauntlet("Shiny Gauntlet", 4, 4, owner),
        owner -> new Pistol("Shiny Pistol", 4, owner)
    );
}
