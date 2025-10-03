package combat;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import characters.heroes.Mutant;
import characters.heroes.Specialist;
import characters.monsters.ThugGang;
import characters.monsters.TwistedMutant;

public class CombatantTest {
    @Test
    public void testHeroesImplementCombatant() {
        assertTrue(Combatant.class.isAssignableFrom(Mutant.class), "Mutant must implement interface Combatant");
        
        assertTrue(Combatant.class.isAssignableFrom(Specialist.class), "Specialist must implement interface Combatant");
    }

    @Test
    public void testMonstersImplementCombatant() {
        assertTrue(Combatant.class.isAssignableFrom(ThugGang.class), "ThugGang must implement interface Combatant");
        
        assertTrue(Combatant.class.isAssignableFrom(TwistedMutant.class), "TwistedMutant must implement interface Combatant");
    }
}
