package utils;

import exceptions.InvalidDiceRoll;
import java.util.Random;

public class Dice {
    public static int roll(int ammount, int faces) {
        if (ammount <= 0) {
            throw new InvalidDiceRoll("Ammount of dices must be greater than 0");
        }

        if (faces <= 1) {
            throw new InvalidDiceRoll("Ammount of faces must be grater than 1");
        }

        int value = 0;
        Random randomNumber = new Random();

        for (int i = 0; i < ammount; i++) {
            value += randomNumber.nextInt(faces) + 1;
        }

        return value;
    }
}
