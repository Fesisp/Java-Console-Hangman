package com.hangman.service;

import com.hangman.model.Difficulty;
import com.hangman.model.GameResult;
import com.hangman.model.WordCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatisticsTest {

    private Statistics statistics;

    @BeforeEach
    void setUp() {
        statistics = new Statistics();
    }

    @Test
    @DisplayName("Should track win rate and streaks accurately")
    void testStatisticsTracking() {
        GameResult win1 = new GameResult(true, 5, 4, "JAVA", 4, WordCategory.PROGRAMACAO, Difficulty.MEDIO, 300, false);
        GameResult loss1 = new GameResult(false, 6, 2, "PYTHON", 0, WordCategory.PROGRAMACAO, Difficulty.MEDIO, 0, false);

        statistics.updateStatistics(win1);
        assertEquals(1, statistics.getGamesWon());
        assertEquals(1, statistics.getCurrentStreak());
        assertEquals(100.0, statistics.calculateWinRate());

        statistics.updateStatistics(loss1);
        assertEquals(1, statistics.getGamesWon());
        assertEquals(0, statistics.getCurrentStreak());
        assertEquals(1, statistics.getBestStreak());
        assertEquals(50.0, statistics.calculateWinRate());
    }
}
