package com.hangman.service;

import com.hangman.model.Difficulty;
import com.hangman.model.GameState;
import com.hangman.model.Word;
import com.hangman.model.WordCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameEngineTest {

    private Word secretWord;
    private Statistics statistics;
    private GameEngine engine;

    @BeforeEach
    void setUp() {
        secretWord = new Word("JAVA", WordCategory.PROGRAMACAO, Difficulty.MEDIO, "Linguagem popular");
        statistics = new Statistics();
        engine = new GameEngine(secretWord, statistics);
    }

    @Test
    @DisplayName("Should reduce remaining tries on wrong letter guess")
    void testWrongGuess() {
        int initialTries = engine.getRemainingTries();
        engine.processInput("X");
        assertEquals(initialTries - 1, engine.getRemainingTries());
    }

    @Test
    @DisplayName("Should win game instantly on correct full word guess with !")
    void testFullWordGuessCorrect() {
        engine.processInput("!JAVA");
        engine.checkGameState();
        assertEquals(GameState.WON, engine.getState());
    }

    @Test
    @DisplayName("Should deduct 2 tries on wrong full word guess")
    void testFullWordGuessWrong() {
        int initialTries = engine.getRemainingTries();
        engine.processInput("!PYTHON");
        assertEquals(initialTries - 2, engine.getRemainingTries());
    }

    @Test
    @DisplayName("Should transition state to WON after discovering all letters")
    void testWinByLetters() {
        engine.processInput("J");
        engine.processInput("A");
        engine.processInput("V");
        engine.checkGameState();

        assertEquals(GameState.WON, engine.getState());
    }
}
