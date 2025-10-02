package levels;

import levels.scenarios.Scenario;

public interface Level {
    public void start();

    public boolean getIsCompleted();

    public Scenario getScenario();

    public int getTurnCounter();

    public void incrementTurnCounter();
}
