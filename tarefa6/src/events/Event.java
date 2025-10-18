package events;

import characters.Hero;
import levels.CombatLevel;

/**
 * Represents a game event that can be triggered during a combat level.
 * <p>
 * Implementations of the {@code Event} interface define conditions under which
 * the event is activated and the effects it applies when executed.
 */
public interface Event {

    /**
     * Checks whether the event should be triggered based on the current game state.
     *
     * @param hero  the hero involved in the event
     * @param level the combat level where the event may occur
     */
    public void checkTrigger(Hero hero, CombatLevel level);

    /**
     * Executes the event and applies its effects.
     *
     * @param hero  the hero affected by the event
     * @param level the combat level where the event takes place
     */
    public void execute(Hero hero, CombatLevel level);
}
