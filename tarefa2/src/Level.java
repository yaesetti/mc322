public abstract class Level {
    private int challenge;
    private final String overview;
    private final String[] monsters;

    public Level (int challenge, String  overview, String monsters) {
        this.challenge = challenge;
        this.overview = overview;
        this.monsters = monsters;
    }

    public int getChallenge() {
        return this.challenge;
    }
    public String getOverview() {
        return this.overview;
    }
    public String getMonsters() {
        return this.monsters;
    }








}
