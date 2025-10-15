package game;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Classe responsavel por gerenciar as entradas do usuario no console.
 * Possui metodos para diferentes tipos de entradas: String, Int, Boolean ou confirmacao por enter.
 * Trata erros e/ou valores invalidos na entrada para que o programa nao quebre
 */
public class InputManager {

    /**
     * Le um inteiro em um intervalo especifico
     * 
     * @param message mensagem exibida ao usuario solicitando uma entrada
     * @param min valor minimo aceito
     * @param max valor maximo aceito
     * @return o numero inteiro valido que o usuario deu
     * @throws NumberFormatException caso a entrada nao possa ser transforma em inteiro
     * @throws NoSuchElementException se a entrada nao tiver nada, apenas enter
     */

    private static Scanner scanner = new Scanner(System.in);

    public static void resetScanner() {
        scanner = new Scanner(System.in);
    }

    public static int readInteger(String message, int min, int max) {
        int input;

        while (true) {
            try {
                System.out.print(message);
                
                String buffer = scanner.nextLine();
                input = Integer.parseInt(buffer);

                if (input < min || input > max) {
                    System.err.printf("Invalid Input: Integer must be in the range [%d, %d]\n", min, max);
                }
                else {
                    return input;
                }

            }
            catch (NumberFormatException e) {
                System.err.println("Invalid Input: Input must be an Integer");
            }
            catch (NoSuchElementException e) {
                System.err.println("Invalid Input: Input must not be empty");
            }
        }
    }

    /**
     * Le uma string nao vazia
     * 
     * @param message mensagem solicitando a entrada
     * @return a string digitada pelo usuario(sem espaco no final)
     * @throws NoSuchElementException se a entrada for nula
     */
    public static String readString(String message) {
        String input;

        while (true) {
            System.out.print(message);

            input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.err.println("Invalid Input: Input must not be empty");
            }
            else {
                return input;
            }
        }
    }

    /**
     * Le uma resposta booleana do usuario:
     * Yes ou No, e suas possiveis variacooes.
     * Y ou N, aceita elas minusuclas tambem
     * 
     * @param message
     * @return {@code true} se a entrada for 'y' ou 'yes'
     *         {@code false} se a entrada for 'n' ou 'no'
     */
    public static boolean readBoolean(String message) {
        String input;

        while (true) {
            System.out.print(message + " (y/n): ");

            input = scanner.nextLine().trim().toLowerCase();
            
            if (input.isEmpty()) {
                System.err.println("Invalid Input: Input must not be empty");
                continue;
            }

            if (input.equals("y") || input.equals("yes")) {
                return true;
            }

            if (input.equals("n") || input.equals("no")) {
                return false;
            }

            else {
                System.err.println("Invalid Input: Input must be 'y' or 'n'");
            }
        }
    }

    public static void readEnter() {
        String input;

        while (true) {
            System.out.printf("Press ENTER to continue...");

            input = scanner.nextLine();

            if (input.isEmpty()) {
                return;
            }
        }
    }

    public static void closeScanner() {
        scanner.close();
    }
}