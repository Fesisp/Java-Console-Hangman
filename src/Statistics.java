public class Statistics {
    private int gamesPlayed;
    private int gamesWon;
    private int totalGuesses;
    private int correctGuesses;
    private int bestStreak;
    private int currentStreak;

    public Statistics() {
        this.gamesPlayed = 0;
        this.gamesWon = 0;
        this.totalGuesses = 0;
        this.correctGuesses = 0;
        this.bestStreak = 0;
        this.currentStreak = 0;
    }

    public void updateStatistics(GameResult result) {
        gamesPlayed++;
        totalGuesses += result.getTotalGuesses();
        correctGuesses += result.getCorrectGuesses();

        if (result.isWon()) {
            gamesWon++;
            currentStreak++;
            if (currentStreak > bestStreak) {
                bestStreak = currentStreak;
            }
        } else {
            currentStreak = 0;
        }
    }

    public void display() {
        System.out.println("╔═══════════ ESTATÍSTICAS ════════════╗");
        System.out.println("║ Jogos Jogados: " + padRight(gamesPlayed, 21) + "║");
        System.out.println("║ Vitórias: " + padRight(gamesWon, 26) + "║");
        System.out.printf("║ Taxa de Vitória: %s║%n", padRight(String.format("%.1f%%", calculateWinRate()), 21));
        System.out.printf("║ Precisão: %s║%n", padRight(String.format("%.1f%%", calculateAccuracy()), 26));
        System.out.println("║ Melhor Sequência: " + padRight(bestStreak, 19) + "║");
        System.out.println("║ Sequência Atual: " + padRight(currentStreak, 20) + "║");
        System.out.println("╚════════════════════════════════════╝");
    }

    private double calculateWinRate() {
        return gamesPlayed > 0 ? (double) gamesWon / gamesPlayed * 100 : 0;
    }

    private double calculateAccuracy() {
        return totalGuesses > 0 ? (double) correctGuesses / totalGuesses * 100 : 0;
    }

    private String padRight(Object value, int length) {
        return String.format("%-" + length + "s", value);
    }
}