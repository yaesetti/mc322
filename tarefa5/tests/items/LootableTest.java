package items;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import characters.Monster;

public class LootableTest {
    @Test
    public void testMonsterImplementsLootable() {
        assertTrue(Lootable.class.isAssignableFrom(Monster.class), 
                   "Monster must implement interface Lootable");
    }
}
