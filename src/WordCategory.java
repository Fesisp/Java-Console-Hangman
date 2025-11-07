public enum WordCategory {
    PROGRAMACAO("Programação"),
    TECNOLOGIA("Tecnologia"),
    DESENVOLVIMENTO("Desenvolvimento");

    private final String displayName;

    WordCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}