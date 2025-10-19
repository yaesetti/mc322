package utils;

import java.util.Random;

/**
 * Utility class for simulating dice rolls in the U-Energy RPG game.
 * <p>
 * The {@code Dice} class provides a method to roll a specified number of dice
 * with a given number of faces, returning the total result.
 */
public class Dice {

    /**
     * Rolls a number of dice and returns the total sum.
     * <p>
     * Each die produces a random value between 1 and {@code faces}, inclusive.
     *
     * @param amount the number of dice to roll
     * @param faces  the number of faces on each die
     * @return the total sum of all dice rolls
     */
    public static int roll(int amount, int faces) {
        int value = 0;
        Random randomNumber = new Random();

        for (int i = 0; i < amount; i++) {
            value += randomNumber.nextInt(faces) + 1;
        }

        return value;
    }
}