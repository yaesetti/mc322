package levels;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import characters.monsters.TwistedMutant;

public class DifficultyTest {
    // HP and Strength tests are implemented separately in order to
    // preserve readability, even though they do basically the same thing
    @Test
    public void testDifficultyMultiplierOverHP() {
        // Instantiating three monster with same initial HP
        // but different Difficulty Levels
        int initialHP = 10;
        TwistedMutant monsterEasy = new TwistedMutant("Test Monster Easy", initialHP, 10, 1, 1, Difficulty.EASY);

        TwistedMutant monsterMedium = new TwistedMutant("Test Monster Medium", initialHP, 10, 1, 1, Difficulty.MEDIUM);
    
        TwistedMutant monsterHard = new TwistedMutant("Test Monster Hard", initialHP, 10, 1, 1, Difficulty.HARD);

        // First test if the HP is scaling up correctly
        assertTrue(monsterEasy.getHealthPoints() == initialHP * 1);

        assertTrue(monsterMedium.getHealthPoints() == initialHP * 2);

        assertTrue(monsterHard.getHealthPoints() == initialHP * 3);

        // Then test if the HP from monsterHard if higher than monsterEasy
        assertTrue(monsterHard.getHealthPoints() > monsterEasy.getHealthPoints());
    }

    @Test
    public void testDifficultyMultiplierOverStrength() {
        // Instantiating three monster with same initial Strength
        // but different Difficulty Levels
        int initialStrength = 1;
        TwistedMutant monsterEasy = new TwistedMutant("Test Monster Easy", 10, 10, initialStrength, 1, Difficulty.EASY);

        TwistedMutant monsterMedium = new TwistedMutant("Test Monster Medium", 10, 10, initialStrength, 1, Difficulty.MEDIUM);
    
        TwistedMutant monsterHard = new TwistedMutant("Test Monster Hard", 10, 10, initialStrength, 1, Difficulty.HARD);

        // First test if the Strength is scaling up correctly
        assertTrue(monsterEasy.getStrength() == initialStrength * 1);

        assertTrue(monsterMedium.getStrength() == initialStrength * 2);

        assertTrue(monsterHard.getStrength() == initialStrength * 3);

        // Then test if the HP from monsterHard if higher than monsterEasy
        assertTrue(monsterHard.getStrength() > monsterEasy.getStrength());
    }
}
