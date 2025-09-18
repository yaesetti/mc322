public enum Setting {
    Downtown ("""
            In the heart of the most populated city of the contry\n
            the criminals run free! Violence is out of control!\n
            litter, smoke and gray concrete are all you can see...
            """),
    Forest ("""
            The view is pretty and green, but the crime is evil and\n
            red. Why the villains are here? Maybe camping? Not sure!\n
            But you can not let them get away!
            """),
    Ice_Cave ("""
            It's so cold you can see smoke comming out of your breath.\n
            For sure the comfort of the bed would be 100 times better,\n
            but the crime won't stop by itself. What are these bad guys\n
            up to in a desert and cold place like this?
            """);
    
    private final String description;

    // Construtor
    Setting(String description) {
        this.description = description;
    }

    // Getter
    public String getDescription () {
        return Description;
    }
}   