package game;

/**
 * Entry point for the game application.
 * <p>
 * Initializes and launches the main menu, allowing the player to start or manage a game session.
 */
public class Main {

    /**
     * Main method that starts the game.
     * <p>
     * Delegates control to {@link MainMenu#manageMenu()} to handle user interaction.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        MainMenu.manageMenu();
    }
}
