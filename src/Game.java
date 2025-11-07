import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private Word word;
    private HangmanDrawing hangman;
    private List<Character> guessedLetters;
    private int remainingTries;
    private static final int MAX_TRIES = 6;
    private Scanner scanner;
    private GameState state;
    private Statistics statistics;
    private int totalGuesses;
    private int correctGuesses;

    public Game(Statistics statistics) {
        this.word = new Word();
        this.hangman = new HangmanDrawing();
        this.guessedLetters = new ArrayList<>();
        this.remainingTries = MAX_TRIES;
        this.scanner = new Scanner(System.in);
        this.state = GameState.PLAYING;
        this.statistics = statistics;
        this.totalGuesses = 0;
        this.correctGuesses = 0;
    }

    public GameResult start() {
        clearScreen();
        System.out.println("Iniciando novo jogo...\n");
        
        while (state == GameState.PLAYING) {
            displayGameStatus();
            playTurn();
            checkGameState();
        }

        displayEndGame();
        
        return new GameResult(
            state == GameState.WON,
            totalGuesses,
            correctGuesses,
            word.getWord(),
            remainingTries
        );
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void displayGameStatus() {
        System.out.println("\n" + hangman.draw(MAX_TRIES - remainingTries));
        System.out.println("\nPalavra: " + word.getDisplayWord(guessedLetters));
        System.out.println("Letras já tentadas: " + guessedLetters);
        System.out.println("Tentativas restantes: " + remainingTries);
    }

    private void playTurn() {
        System.out.print("\nDigite uma letra: ");
        String input = scanner.nextLine().toLowerCase();
        
        if (input.length() != 1) {
            System.out.println("Por favor, digite apenas uma letra!");
            return;
        }

        char guess = input.charAt(0);
        if (!Character.isLetter(guess)) {
            System.out.println("Por favor, digite apenas letras!");
            return;
        }

        if (guessedLetters.contains(guess)) {
            System.out.println("Você já tentou esta letra!");
            return;
        }

        totalGuesses++;
        guessedLetters.add(guess);
        
        if (!word.containsLetter(guess)) {
            remainingTries--;
            System.out.println("Letra incorreta!");
        } else {
            correctGuesses++;
            System.out.println("Letra correta!");
        }
    }

    private void checkGameState() {
        if (word.isComplete(guessedLetters)) {
            state = GameState.WON;
        } else if (remainingTries <= 0) {
            state = GameState.LOST;
        }
    }

    private void displayEndGame() {
        System.out.println("\n" + hangman.draw(MAX_TRIES - remainingTries));
        System.out.println("\nPalavra: " + word.getWord());
        
        if (state == GameState.WON) {
            System.out.println("Parabéns! Você venceu!");
        } else {
            System.out.println("Game Over! A palavra era: " + word.getWord());
        }
    }
}