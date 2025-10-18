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
 * Manages saving and loading game progress for U-Energy RPG.
 * <p>
 * This class handles serialization of {@link Battle} objects to disk,
 * loading saved sessions, and listing available save files.
 * <p>
 * All saves are stored in the {@code saves/} directory using binary format.
 */
public class PersistenceManager {

    /**
     * Saves a {@link Battle} object to disk using the specified name.
     * <p>
     * Automatically creates the {@code saves/} directory if it doesn't exist.
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
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Loads a saved {@link Battle} object from disk.
     *
     * @param battleName the name of the save file (with extension, e.g. {@code save1.bin})
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
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    /**
     * Lists all saved battle files in the {@code saves/} directory.
     * <p>
     * Filters out system files such as {@code .DS_Store}.
     *
     * @return a list of save file names (e.g. {@code save1.bin}, {@code heroQuest.bin})
     */
    public static ArrayList<String> listSavedBattles() {
        Path dir = Paths.get("saves");

        try {
            if (!Files.exists(dir)) {
                Files.createDirectories(dir);
            }

            return Files.list(dir)
                .filter(Files::isRegularFile)
                .map(path -> path.getFileName().toString())
                .filter(name -> !name.equals(".DS_Store"))
                .collect(Collectors.toCollection(ArrayList::new));

        } catch (IOException e) {
            System.err.println(e.getMessage());
            return new ArrayList<>();
        }
    }
}
