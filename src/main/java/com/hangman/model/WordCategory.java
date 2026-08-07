package com.hangman.model;

/**
 * Enumeration of word categories for the Hangman game.
 */
public enum WordCategory {
    PROGRAMACAO("Programação"),
    TECNOLOGIA("Tecnologia"),
    DESENVOLVIMENTO("Desenvolvimento"),
    CIENCIA("Ciência & Matemática"),
    ENTRETENIMENTO("Pop & Entretenimento"),
    TODAS("Todas as Categorias");

    private final String displayName;

    WordCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
