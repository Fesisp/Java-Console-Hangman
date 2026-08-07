package com.hangman.model;

import com.hangman.util.StringUtils;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Entity representing a secret word, its category, hint, and display representation.
 */
public class Word {
    private final String rawWord;
    private final String normalizedWord;
    private final WordCategory category;
    private final Difficulty difficulty;
    private final String hint;

    public Word(String rawWord, WordCategory category, Difficulty difficulty, String hint) {
        if (rawWord == null || rawWord.trim().isEmpty()) {
            throw new IllegalArgumentException("A palavra secreta não pode ser vazia.");
        }
        this.rawWord = rawWord.trim().toUpperCase();
        this.normalizedWord = StringUtils.normalize(this.rawWord);
        this.category = category;
        this.difficulty = difficulty;
        this.hint = (hint != null && !hint.trim().isEmpty()) ? hint.trim() : "Sem dica disponível.";
    }

    public Word(String rawWord, WordCategory category, Difficulty difficulty) {
        this(rawWord, category, difficulty, null);
    }

    public String getRawWord() {
        return rawWord;
    }

    public String getNormalizedWord() {
        return normalizedWord;
    }

    public WordCategory getCategory() {
        return category;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public String getHint() {
        return hint;
    }

    /**
     * Builds the formatted string displaying discovered letters and underscores for hidden ones.
     */
    public String getDisplayWord(Set<Character> guessedLetters) {
        StringBuilder display = new StringBuilder();
        Set<Character> normalizedGuesses = getNormalizedGuesses(guessedLetters);

        for (int i = 0; i < rawWord.length(); i++) {
            char originalChar = rawWord.charAt(i);
            char normChar = normalizedWord.charAt(i);

            if (!Character.isLetter(normChar)) {
                // Non-letter characters like space or hyphen are revealed automatically
                display.append(originalChar).append(" ");
            } else if (normalizedGuesses.contains(normChar)) {
                display.append(originalChar).append(" ");
            } else {
                display.append("_ ");
            }
        }
        return display.toString().trim();
    }

    /**
     * Verifies if the guess letter matches any character in the secret word.
     */
    public boolean containsLetter(char letter) {
        char normalizedGuess = StringUtils.normalize(String.valueOf(letter)).charAt(0);
        return normalizedWord.indexOf(normalizedGuess) >= 0;
    }

    /**
     * Checks if all letters in the word have been guessed.
     */
    public boolean isComplete(Set<Character> guessedLetters) {
        Set<Character> normalizedGuesses = getNormalizedGuesses(guessedLetters);
        for (int i = 0; i < normalizedWord.length(); i++) {
            char normChar = normalizedWord.charAt(i);
            if (Character.isLetter(normChar) && !normalizedGuesses.contains(normChar)) {
                return false;
            }
        }
        return true;
    }

    private Set<Character> getNormalizedGuesses(Set<Character> guessedLetters) {
        Set<Character> set = new HashSet<>();
        if (guessedLetters != null) {
            for (char ch : guessedLetters) {
                String norm = StringUtils.normalize(String.valueOf(ch));
                if (!norm.isEmpty()) {
                    set.add(norm.charAt(0));
                }
            }
        }
        return set;
    }
}
