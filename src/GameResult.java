public class GameResult {
    private boolean won;
    private int totalGuesses;
    private int correctGuesses;
    private String word;
    private int remainingTries;

    public GameResult(boolean won, int totalGuesses, int correctGuesses, String word, int remainingTries) {
        this.won = won;
        this.totalGuesses = totalGuesses;
        this.correctGuesses = correctGuesses;
        this.word = word;
        this.remainingTries = remainingTries;
    }

    public boolean isWon() {
        return won;
    }

    public int getTotalGuesses() {
        return totalGuesses;
    }

    public int getCorrectGuesses() {
        return correctGuesses;
    }

    public String getWord() {
        return word;
    }

    public int getRemainingTries() {
        return remainingTries;
    }
}