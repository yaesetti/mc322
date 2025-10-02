package utils;

import java.util.Random;

public class Dice {
    public static int roll(int amount, int faces) {
        int value = 0;
        Random randomNumber = new Random();

        for (int i = 0; i < amount; i++) {
            value += randomNumber.nextInt(faces) + 1;
        }

        return value;
    }
}
