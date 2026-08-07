package com.hangman.repository;

import com.hangman.model.Difficulty;
import com.hangman.model.Word;
import com.hangman.model.WordCategory;
import java.util.List;
import java.util.Optional;

/**
 * Interface defining operations for word retrieval and management.
 */
public interface WordRepository {
    Optional<Word> getRandomWord(WordCategory category, Difficulty difficulty);
    List<Word> getWordsByCategory(WordCategory category);
    void addWord(Word word);
    int getTotalWordCount();
}
