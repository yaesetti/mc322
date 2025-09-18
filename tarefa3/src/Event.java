public interface Event {
    public String getName();

    public String getDescription();

    public boolean checkTrigger (Hero hero);

    public void execute();
}
