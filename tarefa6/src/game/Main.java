package game;

/**
 * Entry point for the U-Energy RPG game application.
 * <p>
 * This class launches the game by invoking the main menu, allowing players to start a new game,
 * load a saved session, view class information, or exit the application.
 */
public class Main {

    /**
     * Starts the game by displaying the main menu.
     * <p>
     * Delegates control to {@link MainMenu#manageMenu()} to handle user interaction.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        MainMenu.manageMenu();
    }
}
