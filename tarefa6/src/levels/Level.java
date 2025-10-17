package levels;

import java.io.Serializable;

import levels.scenarios.Scenario;

/**
 * Represents a game level interface.
 * Defines the structure and behavior expected from any level implementation,
 * including scenario details, turn tracking, and completion status.
 */
public interface Level extends Serializable {

    /**
     * Starts the level.
     * Typically used to initialize the scenario and prepare the game state.
     */
    public void start();

    /**
     * Checks whether the level is completed.
     * A level is considered complete when its objectives are fulfilled.
     *
     * @return true if the level is completed, false otherwise
     */
    public boolean getIsCompleted();

    /**
     * Returns the scenario associated with the level.
     *
     * @return the Scenario object
     */
    public Scenario getScenario();

    /**
     * Returns the current turn count.
     *
     * @return the number of turns elapsed
     */
    public int getTurnCounter();

    /**
     * Increments the turn counter by one.
     */
    public void incrementTurnCounter();

    /**
     * Returns the index of the current monster being engaged.
     *
     * @return the current monster index
     */
    public int getCurrentMonsterIndex();

    /**
     * Increments the current monster index by one.
     */
    public void incrementCurrentMonsterIndex();
}
