package game;

import characters.Hero;
import exceptions.InsufficientCharacterLevel;
import items.Item;
import items.Weapon;

/**
 * Handles the post-round menu interface shown after defeating an enemy.
 * <p>
 * Allows the player to:
 * <ul>
 *   <li>Continue to the next round</li>
 *   <li>Loot and optionally equip dropped items</li>
 *   <li>View the hero's current status</li>
 *   <li>Save the game</li>
 *   <li>Exit to the main menu</li>
 * </ul>
 */
public class PostRoundMenu {

    /**
     * Displays the post-round menu options in the console.
     */
    private static void printMenu() {
        String menu = """

                               U-Energy | RPG
                -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-

                 [1] Continue

                 [2] Loot the slayed enemy
                 [3] View Hero Status
                 [4] Save Game
                 [5] Exit to Main Menu

                -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
                """;
        
                System.out.println(menu);
    }

    /**
     * Manages the post-round menu and handles player choices.
     * <p>
     * Based on the selected option, this method will:
     * <ul>
     *   <li>Return to the game loop</li>
     *   <li>Display and optionally equip dropped loot</li>
     *   <li>Show hero status</li>
     *   <li>Save the current game state</li>
     *   <li>Exit to the main menu</li>
     * </ul>
     *
     * @param droppedItem the item dropped by the defeated enemy (may be {@code null})
     * @param battle      the current battle session
     * @return {@code 0} to continue the game, {@code 1} to exit to main menu
     */
    public static int manageMenu(Item droppedItem, Battle battle) {
        Hero hero = battle.getHero();
        
        while (true) { 
            printMenu();
            int input = InputManager.readInteger("Insert your option: ", 1, 5);

            switch (input) {
                case 1 -> {
                    return 0;
                }
                case 2 -> {
                    if (droppedItem == null) {
                        System.out.println("There is no loot to take...");
                        InputManager.readEnter();
                        continue;
                    }

                    System.out.println();
                    droppedItem.printStatus();
                    System.out.println();

                    if (droppedItem instanceof Weapon droppedWeapon) {
                        if (InputManager.readBoolean("-> Would you like to take and equip it?")) {
                            try {
                               hero.setWeapon(droppedWeapon);
                               System.out.println("Successfully equipped!");
                            }
                            catch (InsufficientCharacterLevel e) {
                                System.err.println(e.getMessage());
                            }
                            InputManager.readEnter();
                        }
                    }
                }
                case 3 -> {
                    System.out.println();
                    System.out.println("++++++++++++++++++++ HERO ++++++++++++++++++++");
                    hero.printStatus();
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
                    System.out.println();
                    InputManager.readEnter();
                }
                case 4 -> {
                    String name = InputManager.readString("Name of the Save: ");
                    PersistenceManager.saveBattle(battle, name);

                    InputManager.readEnter();
                }
                case 5 -> {
                    boolean confirmation = InputManager.readBoolean("-> Are you sure?");
                    if (confirmation) {
                        System.out.println("-> Exiting game...");
                        
                        return 1;
                    }
                }
                default -> {
                }
            }
        }
    }
}