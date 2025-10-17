package items;

import java.util.List;
import java.util.function.Function;

import characters.Monster;

public interface Lootable {
    public List<Function<Monster, Item>> lootTable();
}