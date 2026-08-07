package com.hangman.service;

import com.hangman.model.Difficulty;
import com.hangman.model.GameResult;
import com.hangman.model.GameState;
import com.hangman.model.Word;
import com.hangman.model.WordCategory;
import com.hangman.ui.AnsiColor;
import com.hangman.ui.HangmanDrawing;
import com.hangman.util.StringUtils;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Core engine managing game turns, input validation, state progression, and scoring.
 */
public class GameEngine {

    private final Word word;
    private final Difficulty difficulty;
    private final HangmanDrawing hangmanDrawing;
    private final Statistics statistics;
    private final Set<Character> guessedLetters;
    
    private int remainingTries;
    private GameState state;
    private int totalGuesses;
    private int correctGuesses;
    private boolean hintUsed;
    private String statusMessage;

    public GameEngine(Word word, Statistics statistics) {
        if (word == null) {
            throw new IllegalArgumentException("A palavra não pode ser nula.");
        }
        this.word = word;
        this.difficulty = word.getDifficulty();
        this.hangmanDrawing = new HangmanDrawing();
        this.statistics = statistics;
        this.guessedLetters = new HashSet<>();
        this.remainingTries = difficulty.getMaxTries();
        this.state = GameState.PLAYING;
        this.totalGuesses = 0;
        this.correctGuesses = 0;
        this.hintUsed = false;
        this.statusMessage = AnsiColor.cyan("Bem-vindo ao Jogo da Forca! Digite uma letra ou '!' para opções.");
    }

    public GameResult play(Scanner scanner) {
        clearScreen();
        
        while (state == GameState.PLAYING) {
            displayGameStatus();
            System.out.println("\n" + statusMessage);
            System.out.print(AnsiColor.bold("\nDigite uma letra (ou '?' para dica, '!' para chutar a palavra): "));
            
            String input = scanner.nextLine().trim();
            processInput(input);
            checkGameState();
        }

        displayEndGame();
        int score = calculateScore();

        return new GameResult(
            state == GameState.WON,
            totalGuesses,
            correctGuesses,
            word.getRawWord(),
            remainingTries,
            word.getCategory(),
            word.getDifficulty(),
            score,
            hintUsed
        );
    }

    public void processInput(String input) {
        if (input == null || input.trim().isEmpty()) {
            statusMessage = AnsiColor.yellow("⚠️ Entrada vazia! Digite uma letra.");
            return;
        }

        // Option: Request Hint
        if (input.equals("?")) {
            useHint();
            return;
        }

        // Option: Guess Full Word
        if (input.startsWith("!") || input.length() > 1) {
            String fullGuess = input.startsWith("!") ? input.substring(1).trim() : input.trim();
            guessFullWord(fullGuess);
            return;
        }

        char guessChar = input.charAt(0);
        if (!Character.isLetter(guessChar)) {
            statusMessage = AnsiColor.yellow("⚠️ Por favor, digite apenas letras de A-Z!");
            return;
        }

        char normChar = StringUtils.normalize(String.valueOf(guessChar)).charAt(0);

        if (guessedLetters.contains(normChar)) {
            statusMessage = AnsiColor.yellow("⚠️ Você já tentou a letra '" + normChar + "'!");
            return;
        }

        totalGuesses++;
        guessedLetters.add(normChar);

        if (word.containsLetter(normChar)) {
            correctGuesses++;
            statusMessage = AnsiColor.green("✅ Boa! A letra '" + normChar + "' está na palavra!");
        } else {
            remainingTries--;
            statusMessage = AnsiColor.red("❌ Letra '" + normChar + "' incorreta! -1 tentativa.");
        }
    }

    private void useHint() {
        if (hintUsed) {
            statusMessage = AnsiColor.yellow("⚠️ Você já utilizou a dica para esta partida!");
            return;
        }
        hintUsed = true;
        statusMessage = AnsiColor.cyan("💡 DICA: " + word.getHint());
    }

    private void guessFullWord(String guess) {
        if (guess.isEmpty()) {
            statusMessage = AnsiColor.yellow("⚠️ Digite a palavra inteira após o '!'. Exemplo: !JAVA");
            return;
        }

        totalGuesses++;
        String normalizedGuess = StringUtils.normalize(guess);
        if (normalizedGuess.equals(word.getNormalizedWord())) {
            // Fill guessed letters
            for (char c : word.getNormalizedWord().toCharArray()) {
                guessedLetters.add(c);
            }
            state = GameState.WON;
            statusMessage = AnsiColor.green("🎯 INCRÍVEL! Você acertou a palavra inteira em um único chute!");
        } else {
            remainingTries = Math.max(0, remainingTries - 2);
            statusMessage = AnsiColor.red("❌ Chute incorreto para a palavra '" + guess + "'! Penalidade de -2 tentativas.");
        }
    }

    public void checkGameState() {
        if (word.isComplete(guessedLetters)) {
            state = GameState.WON;
        } else if (remainingTries <= 0) {
            state = GameState.LOST;
        }
    }

    private int calculateScore() {
        if (state != GameState.WON) {
            return 0;
        }

        double baseScore = 200 * difficulty.getScoreMultiplier();
        double remainingBonus = remainingTries * 50 * difficulty.getScoreMultiplier();
        double streakBonus = statistics != null ? statistics.getCurrentStreak() * 25 : 0;
        int hintPenalty = hintUsed ? 40 : 0;

        int finalScore = (int) Math.max(10, (baseScore + remainingBonus + streakBonus - hintPenalty));
        return finalScore;
    }

    private void displayGameStatus() {
        clearScreen();
        int wrongTries = difficulty.getMaxTries() - remainingTries;
        
        System.out.println(AnsiColor.bold(AnsiColor.cyan("==================================================")));
        System.out.println("  Categoria: " + word.getCategory().getDisplayName() + " | Dificuldade: " + difficulty.getDisplayName());
        System.out.println(AnsiColor.bold(AnsiColor.cyan("==================================================")));
        
        System.out.println(hangmanDrawing.draw(wrongTries, difficulty.getMaxTries()));
        
        System.out.println("\nPalavra: " + AnsiColor.bold(AnsiColor.green(word.getDisplayWord(guessedLetters))));
        System.out.println("Letras tentadas: " + AnsiColor.yellow(guessedLetters.toString()));
        System.out.println("Tentativas restantes: " + AnsiColor.bold(remainingTries + " / " + difficulty.getMaxTries()));
        if (hintUsed) {
            System.out.println(AnsiColor.cyan("Dica ativa: " + word.getHint()));
        }
    }

    private void displayEndGame() {
        clearScreen();
        int wrongTries = difficulty.getMaxTries() - remainingTries;
        System.out.println(hangmanDrawing.draw(wrongTries, difficulty.getMaxTries()));
        
        System.out.println("\n==================================================");
        System.out.println("Palavra Secreta: " + AnsiColor.bold(word.getRawWord()));
        System.out.println("==================================================");

        if (state == GameState.WON) {
            System.out.println(AnsiColor.green("🎉 PARABÉNS! VOCÊ VENCEU! 🎉"));
            System.out.println("Pontuação obtida: " + AnsiColor.bold(calculateScore() + " pts"));
        } else {
            System.out.println(AnsiColor.red("💀 GAME OVER! A palavra correta era: " + word.getRawWord()));
        }
        System.out.println("==================================================");
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Getters for testing
    public GameState getState() { return state; }
    public int getRemainingTries() { return remainingTries; }
    public Word getWord() { return word; }
}
