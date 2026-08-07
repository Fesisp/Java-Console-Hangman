package com.hangman.repository;

import com.hangman.model.Difficulty;
import com.hangman.model.Word;
import com.hangman.model.WordCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class WordRepositoryTest {

    private InMemoryWordRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryWordRepository();
    }

    @Test
    @DisplayName("Should return random word matching requested category")
    void testGetRandomWordByCategory() {
        Optional<Word> wordOpt = repository.getRandomWord(WordCategory.PROGRAMACAO, null);
        assertTrue(wordOpt.isPresent());
        assertEquals(WordCategory.PROGRAMACAO, wordOpt.get().getCategory());
    }

    @Test
    @DisplayName("Should add new custom word successfully")
    void testAddCustomWord() {
        int initialCount = repository.getTotalWordCount();
        Word custom = new Word("SPRING", WordCategory.PROGRAMACAO, Difficulty.MEDIO, "Framework Java");
        repository.addWord(custom);

        assertEquals(initialCount + 1, repository.getTotalWordCount());
    }
}
