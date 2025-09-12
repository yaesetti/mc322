import java.util.ArrayList;
import java.util.List;

public class LevelBuilder {

    /*
     * Bar's gang
     * 
     * A rundown bar hidden in a dark alley, where rival gangs gather under a silent truce. 
     * The walls are scarred with bullet holes, gang symbols, and threatening graffiti. 
     * The air is thick with smoke, cheap booze, and tension. 
     * Every corner is claimed-eyes clash like blades, 
     * and a single wrong word could turn the bar into a battlefield.
     */

    /*
     * Abandoned Toxic Factory
     * 
     * Industrial ruins shrouded in chemical mist and rust.
     * The air is heavy, the ground pulses with living waste. 
     * Anyone who enters always finds a Twisted Mutant—deformed, hostile, and hungry.
     */

    public static List<Level> nLevels (int n) {
        List<Level> levels = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            List<Monster> monsters = new ArrayList<>();

            monsters.add(new Monster("name", 15 * i, 5 * i, 200 * i));
            Level level = new Level(i, "descricao", monsters);
        }

        return levels;
    }

    

}