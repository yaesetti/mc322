public interface Event {
    public void checkTrigger(Hero hero, CombatLevel level);

    public void execute(Hero hero, CombatLevel level);
}
