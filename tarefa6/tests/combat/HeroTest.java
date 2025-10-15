package combat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import characters.heroes.Mutant;
import characters.monsters.ThugGang;
import exceptions.CharacterKnocked;
import exceptions.InsufficientCharacterLevel;
import exceptions.InsufficientWillPoints;
import levels.Difficulty;
import items.weapons.Sword;

public class HeroTest {
    @Test
    public void testHeroReceivesDamage() {
        // Instance of Hero to test
        Mutant hero = new Mutant("Test Hero", 100, 50, 20);
        int initialHP = hero.getHealthPoints();
        
        // Applies damage
        int damage = 7;
        hero.receiveDamage(damage);
        
        // Checks if the HP decreased correctly
        assertEquals(initialHP - damage, hero.getHealthPoints(), "Hero should lose health points when taking damage");

        // Checks if the damage was enough to knock the monster
        // and the status IsKnocked was changed to true
        assertEquals(initialHP - damage <= 0, hero.getIsKnocked(), "Hero should be knocked if damage was enough to bring the Health Points to lower or equals 0");
    }

    @Test
    public void testHeroAttacksTarget() {
        // Since damage is calculated locally and randomly
        // this test will only verify if after the attack
        // the target has less health than before, because
        // in this RPG there is no negative or null damage

        // Instances of Hero and Monster to test
        Mutant hero = new Mutant("Test Hero", 100, 50, 25);
        Sword sword = new Sword("Test Sword", 0, 7, hero);
        try {
            hero.setWeapon(sword);
        } catch (InsufficientCharacterLevel e) {
            // Ignoring this exception now because Test Sword's
            // min level is 0
        }

        ThugGang target = new ThugGang("Test Target", 80, 30, 15, 1, Difficulty.EASY, 3);
        
        int targetInitialHP = target.getHealthPoints();
        
        // Attacks
        try {
            hero.getActions().get("Attack").execute(hero, target);
        }
        catch (InsufficientWillPoints | CharacterKnocked e) {
            // Ignoring these exceptions now because Test Hero
            // is instantiated with enough Will Power and not Knocked
        }
        
        assertTrue(target.getHealthPoints() < targetInitialHP, "Target should lose health points when attacked by hero");
    }
}
