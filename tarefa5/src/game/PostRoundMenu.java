package game;

import characters.Hero;
import characters.Monster;
import exceptions.InsufficientCharacterLevel;
import items.Item;
import items.Weapon;

/**
 * Classe responsavel por exibir o pos round
 * 
 * {@code PostRoundMenu}:
 *      Continuar o jogo.
 *      Pegar o loot do monstro.
 *      Ver os status do jogador/heroi.
 *      Sair do jogo.
 */
public class PostRoundMenu {

    /**
     * Exibe o menu de continuacao no console para o jogador
     * 
     * Impressao estatica das opcoes
     */
    private static void printMenu() {
        String menu = """

                               U-Energy | RPG
                -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-

                 [1] Continue

                 [2] Loot the slayed enemy
                 [3] View Hero Status
                 [4] Exit to Main Menu

                -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
                """;
        
                System.out.println(menu);
    }

    /**
     * Gerencia o menu para continuar o jogo e trata as escolhas do jogador
     * 
     * @param hero heroi que esta no jogo
     * @param enemy inimigo enfretado
     * @param droppedItem item que esse inimigo dropou
     * @return {@code 0} para o jogador continuar o jogo
     *         {@code 1} para p jogador sair do jogo
     */         
    public static int manageMenu(Hero hero, Monster enemy, Item droppedItem) {
        while (true) { 
            printMenu();
            int input = InputManager.readInteger("Insert your option: ", 1, 4);

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