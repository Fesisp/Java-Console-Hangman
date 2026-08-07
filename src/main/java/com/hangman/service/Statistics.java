package com.hangman.service;

import com.hangman.model.GameResult;
import com.hangman.ui.AnsiColor;
import com.hangman.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Service managing player stats, scoring history, streaks, and win percentages.
 */
public class Statistics {
    private int gamesPlayed;
    private int gamesWon;
    private int gamesLost;
    private int totalGuesses;
    private int correctGuesses;
    private int bestStreak;
    private int currentStreak;
    private int totalScore;
    private int highScore;

    private final List<GameResult> recentResults = new ArrayList<>();

    public Statistics() {
        this.gamesPlayed = 0;
        this.gamesWon = 0;
        this.gamesLost = 0;
        this.totalGuesses = 0;
        this.correctGuesses = 0;
        this.bestStreak = 0;
        this.currentStreak = 0;
        this.totalScore = 0;
        this.highScore = 0;
    }

    public synchronized void updateStatistics(GameResult result) {
        if (result == null) return;

        gamesPlayed++;
        totalGuesses += result.getTotalGuesses();
        correctGuesses += result.getCorrectGuesses();
        totalScore += result.getScoreEarned();

        if (result.getScoreEarned() > highScore) {
            highScore = result.getScoreEarned();
        }

        if (result.isWon()) {
            gamesWon++;
            currentStreak++;
            if (currentStreak > bestStreak) {
                bestStreak = currentStreak;
            }
        } else {
            gamesLost++;
            currentStreak = 0;
        }

        recentResults.add(result);
    }

    public double calculateWinRate() {
        return gamesPlayed > 0 ? (double) gamesWon / gamesPlayed * 100 : 0.0;
    }

    public double calculateAccuracy() {
        return totalGuesses > 0 ? (double) correctGuesses / totalGuesses * 100 : 0.0;
    }

    public void display() {
        System.out.println(AnsiColor.cyan("╔═════════════════════ ESTATÍSTICAS DO JOGADOR ═════════════════════╗"));
        System.out.println("║ Jogos Totais:      " + StringUtils.padRight(gamesPlayed, 46) + "║");
        System.out.println("║ Vitórias:          " + StringUtils.padRight(AnsiColor.green(String.valueOf(gamesWon)), 55) + "║");
        System.out.println("║ Derrotas:          " + StringUtils.padRight(AnsiColor.red(String.valueOf(gamesLost)), 55) + "║");
        System.out.printf("║ Taxa de Vitória:   " + StringUtils.padRight(String.format("%.1f%%", calculateWinRate()), 46) + "║%n");
        System.out.printf("║ Precisão de Chutes:" + StringUtils.padRight(String.format("%.1f%%", calculateAccuracy()), 46) + "║%n");
        System.out.println("║ Sequência Atual:   " + StringUtils.padRight(currentStreak + " 🔥", 46) + "║");
        System.out.println("║ Melhor Sequência:  " + StringUtils.padRight(bestStreak + " 🏆", 46) + "║");
        System.out.println("║ Pontuação Total:   " + StringUtils.padRight(totalScore + " pts", 46) + "║");
        System.out.println("║ Maior Pontuação:   " + StringUtils.padRight(highScore + " pts ⭐", 46) + "║");
        System.out.println(AnsiColor.cyan("╚═══════════════════════════════════════════════════════════════════╝"));
    }

    public List<GameResult> getRecentResults() {
        return Collections.unmodifiableList(recentResults);
    }

    public int getGamesPlayed() { return gamesPlayed; }
    public int getGamesWon() { return gamesWon; }
    public int getGamesLost() { return gamesLost; }
    public int getCurrentStreak() { return currentStreak; }
    public int getBestStreak() { return bestStreak; }
    public int getTotalScore() { return totalScore; }
    public int getHighScore() { return highScore; }
}
