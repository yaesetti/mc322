package game;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class InputManager {
    public static int readInteger(String message, int min, int max) {
        Scanner scanner = new Scanner(System.in);
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
                    scanner.close();
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

    public static String readString(String message) {
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            System.out.print(message);

            input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.err.println("Invalid Input: Input must not be empty");
            }
            else {
                scanner.close();
                return input;
            }
        }
    }

    public static boolean readBoolean(String message) {
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            System.out.print(message + " (y/n): ");

            input = scanner.nextLine().trim().toLowerCase();
            
            if (input.isEmpty()) {
                System.err.println("Invalid Input: Input must not be empty");
                continue;
            }

            if (input.equals("y") || input.equals("yes")) {
                scanner.close();
                return true;
            }

            if (input.equals("n") || input.equals("no")) {
                scanner.close();
                return false;
            }

            else {
                System.err.println("Invalid Input: Input must be 'y' or 'n'");
            }
        }
    }

    public static void readEnter() {
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            System.out.printf("Press ENTER to continue...");

            input = scanner.nextLine();

            if (input.isEmpty()) {
                scanner.close();
                return;
            }
        }
    }
}
