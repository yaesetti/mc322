package combat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import characters.heroes.Mutant;
import characters.monsters.TwistedMutant;
import exceptions.CharacterKnocked;
import exceptions.InsufficientWillPoints;
import levels.Difficulty;

public class MonsterTest {
    @Test
    public void testMonsterReceivesDamage() {
        // Instance of Monster to test
        TwistedMutant monster = new TwistedMutant("Test Monster", 80, 30, 15, 1, Difficulty.EASY);
        int initialHP = monster.getHealthPoints();
        
        // Applies damage
        int damage = 7;
        monster.receiveDamage(damage);
        
        // Checks if the HP decreased correctly
        assertEquals(initialHP - damage, monster.getHealthPoints(), "Monster should lose health points when taking damage");

        // Checks if the damage was enough to knock the monster
        // and the status IsKnocked was changed to true
        assertEquals(initialHP - damage <= 0, monster.getIsKnocked(), "Monster should be knocked if damage was enough to bring the Health Points to lower or equals 0");
    }

    @Test
    public void testMonsterAttacksTarget() {
        // Since damage is calculated locally and randomly
        // this test will only verify if after the attack
        // the target has less health than before, because
        // in this RPG there is no negative or null damage

        // Instances of Hero and Monster to test
        TwistedMutant monster = new TwistedMutant("Test Monster", 80, 30, 15, 1, Difficulty.EASY);
        Mutant target = new Mutant("Test Target", 100, 50, 25);
        
        int targetInitialHP = target.getHealthPoints();
        
        // Attacks
        try {
            monster.getActions().get("Attack").execute(monster, target);
        }
        catch (InsufficientWillPoints | CharacterKnocked e) {
            // Ignoring these exceptions now because Test Hero
            // is instantiated with enough Will Power and not Knocked
        }
        
        assertTrue(target.getHealthPoints() < targetInitialHP, "Target should lose health points when attacked by hero");
    }
}
