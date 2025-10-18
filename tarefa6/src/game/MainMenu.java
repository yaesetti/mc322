package game;

import java.util.ArrayList;
import levels.Difficulty;

/**
 * Handles the main menu interface for the U-Energy RPG game.
 * <p>
 * The {@code MainMenu} class allows players to:
 * <ul>
 *   <li>Start a new game with a chosen difficulty</li>
 *   <li>Load a previously saved game</li>
 *   <li>View information about hero classes</li>
 *   <li>View information about monster classes</li>
 *   <li>Exit the game</li>
 * </ul>
 */
public class MainMenu {

    /**
     * Displays the main menu options in the console.
     */
    private static void printMenu() {
        String menu = """
                               U-Energy | RPG
                -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-

                 [1] Start New Game
                 [2] Load Game
                 [3] View Hero Classes
                 [4] View Monster Classes
                 [5] Exit to Desktop

                -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
                """;
        System.out.println(menu);
    }

    /**
     * Displays information about available hero classes.
     * Includes descriptions and special skills for Mutant and Specialist.
     */
    private static void printHeroClasses() {
        String message = """
        ==--==--==--==--==--==--== MUTANT ==--==--==--==--==--==--==
         Mutants are meta-humans that, for whatever reason, have
         abilities and power beyond the common beings and use them
         to save people.
         Real life examples: Flash, Superman, Cyclops, Storm...

         The most important attribute for this class is their
         Mutant Energy (ME).

         Special Skill: Restore Energy
         -> Focus your inner powers and restore 2 Mutant Energy
            while also dealing 2 damage to an enemy.
        ==--==--==--==--==--==--==--=--=--==--==--==--==--==--==--==

        ==--==--==--==--==--==-- SPECIALIST --==--==--==--==--==--==
         Specialists are humans that, even without having any super
         powers, fight for what is right. They normally depend on
         weapons, and are proficient in many of them.
         Real life examples: Batman, Hawkeye, Green Arrow...

         The whole class depends on their preferred Weapon.

         Special Skill: Versatility
         -> Surprise an enemy by suddenly changing the kind of
            weapon being used, while also dealing 2 damage.
        ==--==--==--==--==--==--==--=--=--==--==--==--==--==--==--==
                """;
        System.out.println(message);
    }

    /**
     * Displays information about available monster classes.
     * Includes descriptions and traits for Twisted Mutant and Thug Gang.
     */
    private static void printMonsterClasses() {
        String message = """
        ==--==--==--==--==--== TWISTED MUTANT ==--==--==--==--==--==
         Twisted Mutants also are meta-humans that gained powers
         by whatever reason, but unlike the Mutants, they turned
         out evil and use their powers to steal, murder and
         inflict pain.
         Real life examples: Magneto, Killer Croc, Sinister...

         Twisted Mutants deal more damage if they are under
         40% Health.
        ==--==--==--==--==--==--==--=--=--==--==--==--==--==--==--==

        ==--==--==--==--==--==-- THUG GANG --==--==--==--==--==--==-
         Thug Gangs are made of thieves, bullies and scammers.
         They do not have powers, but have the advantage of
         always being in groups.
         Real life examples: Joker's Gang, KingPin's Gang...

         Thug Gangs deal more damage based on the number of
         gang members.
        ==--==--==--==--==--==--==--=--=--==--==--==--==--==--==--==
                """;
        System.out.println(message);
    }

    /**
     * Displays the difficulty selection menu.
     * Options include Easy, Medium, and Hard.
     */
    private static void printDiffMenu() {
        String menu = """
                                Difficulty
                -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-

                 [1] Easy           (really?)
                 [2] Medium         (that's better...)
                 [3] Hard           (now we talking!)

                -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
                """;
        System.out.println(menu);
    }

    /**
     * Manages the main menu loop and handles player choices.
     * <p>
     * Based on the selected option, this method will:
     * <ul>
     *   <li>Start a new game with chosen difficulty</li>
     *   <li>Load a saved game from disk</li>
     *   <li>Display hero or monster class information</li>
     *   <li>Exit the game with confirmation</li>
     * </ul>
     */
    public static void manageMenu() {
        while (true) {
            printMenu();
            int input = InputManager.readInteger("Insert your option: ", 1, 5);

            switch (input) {
                case 1 -> {
                    printDiffMenu();
                    input = InputManager.readInteger("Insert your option: ", 1, 3);
                    Difficulty difficulty = switch (input) {
                        case 1 -> Difficulty.EASY;
                        case 2 -> Difficulty.MEDIUM;
                        case 3 -> Difficulty.HARD;
                        default -> Difficulty.EASY;
                    };
                    Battle battle = new Battle(difficulty);
                    GameManager.playGame(battle);
                }
                case 2 -> {
                    ArrayList<String> battleNames = PersistenceManager.listSavedBattles();

                    if (battleNames.isEmpty()) {
                        System.out.println("\n-> No Saves available, try starting a New Game");
                        break;
                    }

                    System.out.println();
                    System.out.println("                    Saves");
                    System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\n");
                    System.out.println(" [1] Go back\n");
                    for (int i = 0; i < battleNames.size(); i++) {
                        String name = battleNames.get(i).replaceFirst("\\.bin$", "");
                        System.out.printf(" [%d] %s\n", i + 2, name);
                    }
                    System.out.println();
                    System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\n");

                    input = InputManager.readInteger("Insert your option: ", 1, battleNames.size() + 1) - 2;
                    if (input == -1) break;

                    Battle battle = PersistenceManager.loadBattle(battleNames.get(input));
                    System.out.println("-> Loading save...");
                    GameManager.playGame(battle);
                }
                case 3 -> {
                    printHeroClasses();
                    InputManager.readEnter();
                }
                case 4 -> {
                    printMonsterClasses();
                    InputManager.readEnter();
                }
                case 5 -> {
                    boolean confirmation = InputManager.readBoolean("-> Are you sure?");
                    if (!confirmation) continue;

                    System.out.println("\nThank you for playing U-Energy RPG and keeping our world more secure!\n");
                    InputManager.closeScanner();
                    return;
                }
                default -> {
                    // No action needed
                }
            }
        }
    }
}
