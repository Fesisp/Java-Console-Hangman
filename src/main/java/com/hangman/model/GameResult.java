package com.hangman.model;

/**
 * Immutable record/class capturing the final outcome of a game session.
 */
public class GameResult {
    private final boolean won;
    private final int totalGuesses;
    private final int correctGuesses;
    private final String secretWord;
    private final int remainingTries;
    private final WordCategory category;
    private final Difficulty difficulty;
    private final int scoreEarned;
    private final boolean hintUsed;

    public GameResult(boolean won, int totalGuesses, int correctGuesses, String secretWord,
                      int remainingTries, WordCategory category, Difficulty difficulty,
                      int scoreEarned, boolean hintUsed) {
        this.won = won;
        this.totalGuesses = totalGuesses;
        this.correctGuesses = correctGuesses;
        this.secretWord = secretWord;
        this.remainingTries = remainingTries;
        this.category = category;
        this.difficulty = difficulty;
        this.scoreEarned = scoreEarned;
        this.hintUsed = hintUsed;
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

    public String getSecretWord() {
        return secretWord;
    }

    public int getRemainingTries() {
        return remainingTries;
    }

    public WordCategory getCategory() {
        return category;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public int getScoreEarned() {
        return scoreEarned;
    }

    public boolean isHintUsed() {
        return hintUsed;
    }
}
