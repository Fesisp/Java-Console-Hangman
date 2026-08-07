package com.hangman.ui;

import com.hangman.model.Difficulty;
import com.hangman.model.GameResult;
import com.hangman.model.Word;
import com.hangman.model.WordCategory;
import com.hangman.repository.InMemoryWordRepository;
import com.hangman.repository.WordRepository;
import com.hangman.service.GameEngine;
import com.hangman.service.Statistics;
import com.hangman.util.StringUtils;

import java.util.Optional;
import java.util.Scanner;

/**
 * Main Interactive Menu System for the Hangman Console Game.
 */
public class MenuSystem {
    private final Scanner scanner;
    private final Statistics statistics;
    private final WordRepository wordRepository;

    public MenuSystem() {
        this.scanner = new Scanner(System.in);
        this.statistics = new Statistics();
        this.wordRepository = new InMemoryWordRepository();
    }

    public void start() {
        boolean running = true;
        while (running) {
            clearScreen();
            displayMainMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1 -> playStandardGame();
                case 2 -> playTwoPlayerMode();
                case 3 -> addNewWord();
                case 4 -> showTutorial();
                case 5 -> showStatistics();
                case 6 -> toggleColors();
                case 7 -> running = false;
                default -> pause("Opção inválida! Pressione ENTER para continuar...");
            }
        }
        System.out.println(AnsiColor.green("\nObrigado por jogar o Jogo da Forca! Até a próxima! 👋\n"));
        scanner.close();
    }

    private void displayMainMenu() {
        System.out.println(AnsiColor.cyan("╔═════════════════════════════════════════════════════╗"));
        System.out.println(AnsiColor.cyan("║           🎮 JOGO DA FORCA ENTERPRISE 🎮            ║"));
        System.out.println(AnsiColor.cyan("╠═════════════════════════════════════════════════════╣"));
        System.out.println("║ 1. 🎯 Jogar Modo Normal (Categorias & Dificuldade)   ║");
        System.out.println("║ 2. 👥 Modo 2 Jogadores (Criar Palavra Customizada)  ║");
        System.out.println("║ 3. ➕ Cadastrar Nova Palavra no Banco de Dados       ║");
        System.out.println("║ 4. 📖 Como Jogar & Regras                           ║");
        System.out.println("║ 5. 📊 Estatísticas & Placar de Líderes              ║");
        System.out.println("║ 6. 🎨 Alternar Cores do Console [" + (AnsiColor.isEnabled() ? "ON" : "OFF") + "]             ║");
        System.out.println("║ 7. 🚪 Sair                                          ║");
        System.out.println(AnsiColor.cyan("╚═════════════════════════════════════════════════════╝"));
        System.out.print(AnsiColor.bold("Escolha uma opção (1-7): "));
    }

    private int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void playStandardGame() {
        WordCategory category = selectCategory();
        Difficulty difficulty = selectDifficulty();

        Optional<Word> optionalWord = wordRepository.getRandomWord(category, difficulty);
        if (optionalWord.isEmpty()) {
            pause(AnsiColor.red("Nenhuma palavra encontrada para os critérios selecionados!"));
            return;
        }

        Word word = optionalWord.get();
        GameEngine engine = new GameEngine(word, statistics);
        GameResult result = engine.play(scanner);
        statistics.updateStatistics(result);

        pause("\nPressione ENTER para retornar ao Menu Principal...");
    }

    private WordCategory selectCategory() {
        clearScreen();
        System.out.println(AnsiColor.cyan("=== SELEÇÃO DE CATEGORIA ==="));
        WordCategory[] categories = WordCategory.values();
        for (int i = 0; i < categories.length; i++) {
            System.out.printf("%d. %s%n", (i + 1), categories[i].getDisplayName());
        }
        System.out.print(AnsiColor.bold("Escolha a categoria: "));
        int choice = getUserChoice();
        if (choice >= 1 && choice <= categories.length) {
            return categories[choice - 1];
        }
        return WordCategory.TODAS;
    }

    private Difficulty selectDifficulty() {
        clearScreen();
        System.out.println(AnsiColor.cyan("=== SELEÇÃO DE DIFICULDADE ==="));
        Difficulty[] difficulties = Difficulty.values();
        for (int i = 0; i < difficulties.length; i++) {
            System.out.printf("%d. %s%n", (i + 1), difficulties[i].toString());
        }
        System.out.print(AnsiColor.bold("Escolha a dificuldade: "));
        int choice = getUserChoice();
        if (choice >= 1 && choice <= difficulties.length) {
            return difficulties[choice - 1];
        }
        return Difficulty.MEDIO;
    }

    private void playTwoPlayerMode() {
        clearScreen();
        System.out.println(AnsiColor.cyan("=== MODO 2 JOGADORES (DESAFIO) ==="));
        System.out.println("Jogador 1: Insira a palavra secreta para o Jogador 2 adivinhar!");
        System.out.print(AnsiColor.bold("Digite a palavra secreta: "));
        String secretWordInput = scanner.nextLine().trim();

        if (secretWordInput.length() < 2) {
            pause(AnsiColor.red("Palavra muito curta! Deve ter pelo menos 2 letras."));
            return;
        }

        System.out.print(AnsiColor.bold("Digite uma dica para a palavra (opcional): "));
        String hintInput = scanner.nextLine().trim();

        Difficulty difficulty = selectDifficulty();

        Word customWord = new Word(secretWordInput, WordCategory.TODAS, difficulty, hintInput);
        
        clearScreen();
        System.out.println(AnsiColor.green("Palavra cadastrada com sucesso!"));
        pause("Passe o teclado para o Jogador 2 e pressione ENTER para iniciar...");

        GameEngine engine = new GameEngine(customWord, statistics);
        GameResult result = engine.play(scanner);
        statistics.updateStatistics(result);

        pause("\nPressione ENTER para retornar ao Menu Principal...");
    }

    private void addNewWord() {
        clearScreen();
        System.out.println(AnsiColor.cyan("=== CADASTRAR NOVA PALAVRA ==="));
        System.out.print("Digite a palavra secreta: ");
        String wordText = scanner.nextLine().trim();

        if (wordText.length() < 2) {
            pause(AnsiColor.red("A palavra deve ter pelo menos 2 caracteres."));
            return;
        }

        WordCategory category = selectCategory();
        Difficulty difficulty = selectDifficulty();

        System.out.print("Digite a dica para a palavra: ");
        String hintText = scanner.nextLine().trim();

        Word newWord = new Word(wordText, category, difficulty, hintText);
        wordRepository.addWord(newWord);

        pause(AnsiColor.green("Palavra '" + wordText + "' adicionada com sucesso ao repositório! Total de palavras: " + wordRepository.getTotalWordCount()));
    }

    private void showTutorial() {
        clearScreen();
        System.out.println(AnsiColor.cyan("╔════════════════════════ COMO JOGAR ════════════════════════╗"));
        System.out.println("║ 1. Escolha a Categoria e a Dificuldade do jogo.            ║");
        System.out.println("║ 2. Você possui um número limitado de tentativas (4 a 8).   ║");
        System.out.println("║ 3. Acentos e maiúsculas/minúsculas são aceitos            ║");
        System.out.println("║    automaticamente (ex: digitar 'A' revela 'Á', 'Ã', etc). ║");
        System.out.println("║ 4. Digite '?' para solicitar a DICA da palavra.            ║");
        System.out.println("║ 5. Digite '!' seguido da palavra para CHUTAR A PALAVRA     ║");
        System.out.println("║    inteira! (Risco: se errar, perde 2 tentativas).         ║");
        System.out.println("║ 6. Ganhe pontuações mais altas jogando nas dificuldades   ║");
        System.out.println("║    mais difíceis e mantendo sequências de vitórias!        ║");
        System.out.println(AnsiColor.cyan("╚════════════════════════════════════════════════════════════╝"));
        pause("\nPressione ENTER para voltar ao menu principal...");
    }

    private void showStatistics() {
        clearScreen();
        statistics.display();
        pause("\nPressione ENTER para voltar ao menu principal...");
    }

    private void toggleColors() {
        AnsiColor.setEnabled(!AnsiColor.isEnabled());
        pause("Cores do console agora estão: " + (AnsiColor.isEnabled() ? "ATIVADAS" : "DESATIVADAS"));
    }

    private void pause(String message) {
        System.out.println(message);
        scanner.nextLine();
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
