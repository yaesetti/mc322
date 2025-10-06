package utils;

import java.util.Random;

/**
 * Classe de dados
 * Roda n dados com m lados
 */
public class Dice {

    /**
     * Soma o numero que os dados deram
     * 
     * @param amount quantidade de dados que serao "rolados"
     * @param faces quantidade de lado dos dados
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