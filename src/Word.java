import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;

public class Word {
    private String word;
    private static final Map<WordCategory, List<String>> WORD_CATEGORIES = new HashMap<>();
    
    static {
        WORD_CATEGORIES.put(WordCategory.PROGRAMACAO, Arrays.asList(
            "JAVA", "PYTHON", "JAVASCRIPT", "ALGORITMO", "VARIAVEL",
            "FUNCAO", "CLASSE", "OBJETO", "METODO", "HERANCA"
        ));
        
        WORD_CATEGORIES.put(WordCategory.TECNOLOGIA, Arrays.asList(
            "COMPUTADOR", "INTERNET", "SOFTWARE", "HARDWARE", "PROCESSADOR",
            "MEMORIA", "SERVIDOR", "REDE", "DADOS", "SISTEMA"
        ));
        
        WORD_CATEGORIES.put(WordCategory.DESENVOLVIMENTO, Arrays.asList(
            "FRAMEWORK", "BIBLIOTECA", "API", "BACKEND", "FRONTEND",
            "FULLSTACK", "DEPLOY", "CODIGO", "DEBUG", "TESTE"
        ));
    }

    public Word() {
        this.word = selectRandomWord();
    }

    private String selectRandomWord() {
        Random random = new Random();
        WordCategory[] categories = WordCategory.values();
        WordCategory selectedCategory = categories[random.nextInt(categories.length)];
        List<String> words = WORD_CATEGORIES.get(selectedCategory);
        return words.get(random.nextInt(words.size()));
    }

    public String getWord() {
        return word;
    }

    public String getDisplayWord(List<Character> guessedLetters) {
        StringBuilder display = new StringBuilder();
        for (char letter : word.toCharArray()) {
            if (guessedLetters.contains(Character.toLowerCase(letter))) {
                display.append(letter);
            } else {
                display.append("_");
            }
            display.append(" ");
        }
        return display.toString();
    }

    public boolean containsLetter(char letter) {
        return word.toLowerCase().indexOf(Character.toLowerCase(letter)) != -1;
    }

    public boolean isComplete(List<Character> guessedLetters) {
        for (char letter : word.toCharArray()) {
            if (!guessedLetters.contains(Character.toLowerCase(letter))) {
                return false;
            }
        }
        return true;
    }
}