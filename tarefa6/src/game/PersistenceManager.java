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

public class PersistenceManager {
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
