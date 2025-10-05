package levels;

import levels.scenarios.Scenario;

/*
 * Interface de level
 */
public interface Level {
    /*
     * Comeca a fase
     */
    public void start();

    /*
     * Se o level esta completo ou nao
     */
    public boolean getIsCompleted();

    /*
     * 
     */
    public Scenario getScenario();

    public int getTurnCounter();

    public void incrementTurnCounter();
}
