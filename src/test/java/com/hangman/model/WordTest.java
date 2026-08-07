package com.hangman.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class WordTest {

    @Test
    @DisplayName("Should correctly display hidden word with underscores and revealed letters")
    void testDisplayWord() {
        Word word = new Word("PROGRAMAÇÃO", WordCategory.PROGRAMACAO, Difficulty.MEDIO, "Linguagem");
        Set<Character> guessed = new HashSet<>();
        
        assertEquals("_ _ _ _ _ _ _ _ _ _ _", word.getDisplayWord(guessed));

        guessed.add('P');
        guessed.add('A');
        assertEquals("P R O G R A M A Ç Ã O", word.getDisplayWord(Set.of('p', 'r', 'o', 'g', 'a', 'm', 'c')));
    }

    @Test
    @DisplayName("Should detect when word guessing is complete regardless of accents")
    void testIsComplete() {
        Word word = new Word("JAVA", WordCategory.PROGRAMACAO, Difficulty.FACIL);
        Set<Character> guessed = new HashSet<>(Set.of('J', 'A', 'V'));

        assertTrue(word.isComplete(guessed));
    }

    @Test
    @DisplayName("Should throw exception when creating Word with null or empty text")
    void testInvalidWordCreation() {
        assertThrows(IllegalArgumentException.class, () -> new Word("", WordCategory.TODAS, Difficulty.FACIL));
        assertThrows(IllegalArgumentException.class, () -> new Word(null, WordCategory.TODAS, Difficulty.FACIL));
    }
}
