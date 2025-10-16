package game;

import characters.Hero;
import exceptions.InsufficientCharacterLevel;
import items.Item;
import items.Weapon;

public class PostRoundMenu {
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