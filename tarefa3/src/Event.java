public interface Event {
    String getName();
    String getDescription();

    boolean CheckTrigger (Hero hero); // Engatilhar acao
    void Run(); // Executar o evento

    boolean isActived(); // Garantir se o evento esta acontecendo ou nao
    

}