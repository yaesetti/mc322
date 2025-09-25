package game;

import java.util.Scanner;

public class InputManager {
    private static final Scanner scanner = new Scanner(System.in);

    public static int readInteger(String message, int min, int max) {
        int input;

        while (true) {
            try {
                System.out.print(message);
                
                String buffer = scanner.nextLine();
                input = Integer.parseInt(buffer);

                if (input < min || input > max) {
                    System.out.printf("Invalid Input: Integer must be in the range [%d, %d]\n", min, max);
                }
                else {
                    return input;
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid Input: Input must be an Integer");
            }
        }
    }

    public static String readString(String message) {
        String input;

        while (true) {
            System.out.print(message);

            input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Invalid Input: Input cannot be empty");
            }
            else {
                return input;
            }
        }
    }

    public static boolean readBoolean(String message) {
        String input;
        char input_char;

        while (true) {
            System.out.print(message + " (y/n): ");

            input = scanner.nextLine().trim().toLowerCase();
            input_char = input.charAt(0);
            
            if (input.isEmpty()) {
                System.out.println("Invalid Input: Input cannot be empty");
            }
            else if (input_char != 'y' && input_char != 'n') {
                System.out.println("Invalid Input: Input must be 'y' or 'n'");                
            }
            else {
                return input_char == 'y';
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
