package game;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Manages user input from the console.
 * <p>
 * Provides utility methods for reading and validating different types of input:
 * integers within a range, non-empty strings, boolean confirmations, and ENTER prompts.
 * Handles invalid or malformed input gracefully to prevent runtime errors.
 */
public class InputManager {

    /**
     * Scanner instance used for reading console input.
     */
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Resets the scanner instance.
     * Useful when input stream needs to be refreshed.
     */
    public static void resetScanner() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads an integer from the user within a specified range.
     * Re-prompts until a valid integer is entered.
     *
     * @param message the prompt message shown to the user
     * @param min     the minimum acceptable value
     * @param max     the maximum acceptable value
     * @return a valid integer within the specified range
     * @throws NumberFormatException    if the input cannot be parsed as an integer
     * @throws NoSuchElementException   if the input is empty
     */
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
     * Reads a non-empty string from the user.
     * Re-prompts until a valid string is entered.
     *
     * @param message the prompt message shown to the user
     * @return the trimmed string entered by the user
     * @throws NoSuchElementException if the input is empty
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
     * Reads a boolean confirmation from the user.
     * Accepts variations of "yes" or "no" (case-insensitive).
     *
     * @param message the prompt message shown to the user
     * @return {@code true} if input is "y" or "yes", {@code false} if "n" or "no"
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

    /**
     * Waits for the user to press ENTER to continue.
     * Re-prompts until ENTER is pressed.
     */
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

    /**
     * Closes the scanner instance.
     * Should be called when input is no longer needed.
     */
    public static void closeScanner() {
        scanner.close();
    }
}