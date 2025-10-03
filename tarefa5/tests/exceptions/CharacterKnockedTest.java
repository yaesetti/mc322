package exceptions;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import characters.heroes.Mutant;
import items.weapons.Sword;
import characters.monsters.ThugGang;
import levels.Difficulty;

public class CharacterKnockedTest {
    private final PrintStream originalSystemErr = System.err;
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @AfterEach
    void restoreStream() {
        System.setErr(originalSystemErr);
    }

    @Test
    public void testCharacterKnocked() {
        System.setErr(new PrintStream(errContent));

        // Instantiating a Test Hero
        Mutant hero = new Mutant("Test Hero", 100, 100, 10);
        Sword sword = new Sword("Test Sword", 0, 7, hero);
        try {
            hero.setWeapon(sword);
        } catch (InsufficientCharacterLevel e) {
            // Ignoring this exception now because Test Sword's
            // min level is 0
        }

        hero.receiveDamage(100);

        ThugGang monster = new ThugGang("Test Target", 80, 30, 15, 1, Difficulty.EASY, 3);

        try {
            hero.getActions().get("Attack").execute(hero, monster);
        }
        catch (InsufficientWillPoints e) {
            // Ignoring this exception for this test since
            // the Test Hero has 100 Will Points
        }
        catch (CharacterKnocked e) {
            System.err.println(e.getMessage());
        }

        String errorOutput = errContent.toString();
        assertTrue(errorOutput.contains("Error: Character is knocked, so they can't perform this action"));
    }
}
