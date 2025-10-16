package levels;

import java.io.Serializable;

import levels.scenarios.Scenario;

/**
 * Interface de level
 */
public interface Level extends Serializable {
    /**
     * Comeca a fase
     */
    public void start();

    /**
     * Se o level esta completo ou nao
     */
    public boolean getIsCompleted();

    /**
     * Da o cenario do level
     */
    public Scenario getScenario();

    /**
     * Da quantos turnos tem
     */
    public int getTurnCounter();

    /**
     * Adiciona 1 ao contador de turno
     */
    public void incrementTurnCounter();

    public int getCurrentMonsterIndex();

    public void incrementCurrentMonsterIndex();
}