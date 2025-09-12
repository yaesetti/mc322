import java.util.List;

public abstract class Level {
    private int challenge;
    private final String overview;
    private List<Monster> monsters;

    public Level (int challenge, String  overview, List monsters) {
        this.challenge = challenge;
        this.overview = overview;
        this.monsters = monsters;
    }
    //



    public int getChallenge() {
        return this.challenge;
    }
    public String getOverview() {
        return this.overview;
    }
    public List getMonsters() {
        return this.monsters;
    }

    // Informacoes da fase
    public void getInfos() {
        System.out.println("Level" + challenge + " - " + overview);
        System.out.println("Monsters: ");
        for (Monster m: monsters) {
            System.out.println(m.getStatus);
        }
    }

}
