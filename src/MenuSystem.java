import java.util.Scanner;

public class MenuSystem {
    private Scanner scanner;
    private Statistics statistics;

    public MenuSystem() {
        this.scanner = new Scanner(System.in);
        this.statistics = new Statistics();
    }

    public void start() {
        boolean running = true;
        while (running) {
            clearScreen();
            displayMainMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    playGame();
                    break;
                case 2:
                    showTutorial();
                    break;
                case 3:
                    showStatistics();
                    break;
                case 4:
                    about();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Opção inválida! Pressione ENTER para continuar...");
                    scanner.nextLine();
            }
        }
        System.out.println("Obrigado por jogar! Até a próxima!");
        scanner.close();
    }

    private void displayMainMenu() {
        System.out.println("╔════════════════════════════════════╗");
        System.out.println("║         JOGO DA FORCA             ║");
        System.out.println("╠════════════════════════════════════╣");
        System.out.println("║ 1. Jogar                          ║");
        System.out.println("║ 2. Como Jogar                     ║");
        System.out.println("║ 3. Estatísticas                   ║");
        System.out.println("║ 4. Sobre                          ║");
        System.out.println("║ 5. Sair                           ║");
        System.out.println("╚════════════════════════════════════╝");
        System.out.print("Escolha uma opção: ");
    }

    private int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void playGame() {
        Game game = new Game(statistics);
        GameResult result = game.start();
        statistics.updateStatistics(result);
        
        System.out.println("\nPressione ENTER para voltar ao menu principal...");
        scanner.nextLine();
    }

    private void showTutorial() {
        clearScreen();
        System.out.println("╔════════════════════ COMO JOGAR ════════════════════╗");
        System.out.println("║ 1. Uma palavra aleatória será escolhida            ║");
        System.out.println("║ 2. Você tem 6 tentativas para adivinhar a palavra  ║");
        System.out.println("║ 3. Digite uma letra por vez                        ║");
        System.out.println("║ 4. Se a letra existir na palavra, será revelada    ║");
        System.out.println("║ 5. Se não existir, você perde uma tentativa        ║");
        System.out.println("║ 6. Ganhe revelando todas as letras                 ║");
        System.out.println("║ 7. Ou perca após 6 erros                          ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
        System.out.println("\nPressione ENTER para voltar ao menu principal...");
        scanner.nextLine();
    }

    private void showStatistics() {
        clearScreen();
        statistics.display();
        System.out.println("\nPressione ENTER para voltar ao menu principal...");
        scanner.nextLine();
    }

    private void about() {
        clearScreen();
        System.out.println("╔════════════════════ SOBRE ════════════════════╗");
        System.out.println("║ Jogo da Forca - Versão 1.0                    ║");
        System.out.println("║                                               ║");
        System.out.println("║ Desenvolvido como parte do portfólio de      ║");
        System.out.println("║ projetos Java, demonstrando conceitos de     ║");
        System.out.println("║ Programação Orientada a Objetos.             ║");
        System.out.println("║                                               ║");
        System.out.println("║ Características:                             ║");
        System.out.println("║ - Interface amigável em console             ║");
        System.out.println("║ - Sistema de pontuação                      ║");
        System.out.println("║ - Estatísticas de jogo                      ║");
        System.out.println("║ - Múltiplas categorias de palavras          ║");
        System.out.println("╚═══════════════════════════════════════════════╝");
        System.out.println("\nPressione ENTER para voltar ao menu principal...");
        scanner.nextLine();
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}