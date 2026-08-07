package com.hangman.model;

/**
 * Game difficulty settings with max attempts and score multipliers.
 */
public enum Difficulty {
    FACIL("Fácil", 8, 1.0),
    MEDIO("Médio", 6, 1.5),
    DIFICIL("Difícil", 4, 2.5);

    private final String displayName;
    private final int maxTries;
    private final double scoreMultiplier;

    Difficulty(String displayName, int maxTries, double scoreMultiplier) {
        this.displayName = displayName;
        this.maxTries = maxTries;
        this.scoreMultiplier = scoreMultiplier;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getMaxTries() {
        return maxTries;
    }

    public double getScoreMultiplier() {
        return scoreMultiplier;
    }

    @Override
    public String toString() {
        return String.format("%s (%d Tentativas, %s multiplier)", displayName, maxTries, scoreMultiplier + "x");
    }
}
