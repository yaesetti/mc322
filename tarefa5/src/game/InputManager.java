package game;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class InputManager {
    // One shared scanner for all input
    private static Scanner scanner = new Scanner(System.in);
    
    // Method to reset scanner for testing
    public static void resetScanner() {
        scanner = new Scanner(System.in);
    }

    public static int readInteger(String message, int min, int max) {
        int input;

        while (true) {
            try {
                System.out.print(message);
                String buffer = scanner.nextLine().trim();

                input = Integer.parseInt(buffer);

                if (input < min || input > max) {
                    System.err.printf("Invalid Input: Integer must be in the range [%d, %d]\n", min, max);
                } else {
                    return input;
                }

            } catch (NumberFormatException e) {
                System.err.println("Invalid Input: Input must be an Integer");
            } catch (NoSuchElementException e) {
                System.err.println("Invalid Input: Input must not be empty");
            }
        }
    }

    public static String readString(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.err.println("Invalid Input: Input must not be empty");
            } else {
                return input;
            }
        }
    }

    public static boolean readBoolean(String message) {
        while (true) {
            System.out.print(message + " (y/n): ");
            String input = scanner.nextLine().trim().toLowerCase();

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

            System.err.println("Invalid Input: Input must be 'y' or 'n'");
        }
    }

    public static void readEnter() {
        System.out.print("Press ENTER to continue...");
        scanner.nextLine();
    }

    public static void closeScanner() {
        scanner.close();
    }
}
