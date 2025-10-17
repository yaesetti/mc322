package game;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Handles saving and loading game progress to and from disk.
 * <p>
 * The {@code PersistenceManager} class provides methods to:
 * <ul>
 *   <li>Save a {@link Battle} object to a file</li>
 *   <li>Load a saved {@link Battle} from a file</li>
 *   <li>List all available saved battles</li>
 * </ul>
 * Saves are stored in the {@code saves/} directory using binary serialization.
 */
public class PersistenceManager {

    /**
     * Saves a {@link Battle} object to disk using the specified name.
     * Creates the {@code saves/} directory if it doesn't exist.
     *
     * @param battle     the battle instance to save
     * @param battleName the name of the save file (without extension)
     */
    public static void saveBattle(Battle battle, String battleName) {
        Path dir = Paths.get("saves");
        Path path = dir.resolve(battleName + ".bin");

        try {
            Files.createDirectories(dir);

            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path.toFile()))) {
                out.writeObject(battle);
                System.out.println("-> Game successfully saved in: " + path);
            }
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Loads a saved {@link Battle} object from disk.
     *
     * @param battleName the name of the save file (with extension)
     * @return the loaded {@link Battle} object, or {@code null} if loading fails
     */
    public static Battle loadBattle(String battleName) {
        Path path = Paths.get("saves", battleName);

        if (!Files.exists(path)) {
            System.err.println("File not found: " + path);
            return null;
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path.toFile()))) {
            return (Battle) in.readObject();
        }
        catch (IOException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    /**
     * Lists all saved battle files in the {@code saves/} directory.
     * Filters out system files like {@code .DS_Store}.
     *
     * @return a list of save file names
     */
    public static ArrayList<String> listSavedBattles() {
        Path dir = Paths.get("saves");

        try {
            if (!Files.exists(dir)) {
                Files.createDirectories(dir);
            }

            ArrayList<String> fileNames = Files.list(dir)
                .filter(Files::isRegularFile)
                .map(path -> path.getFileName().toString())
                .filter(name -> !name.equals(".DS_Store"))
                .collect(Collectors.toCollection(ArrayList::new));

            return fileNames;
            
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return new ArrayList<>();
        }
    }
}
