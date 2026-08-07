package com.hangman.repository;

import com.hangman.model.Difficulty;
import com.hangman.model.Word;
import com.hangman.model.WordCategory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * In-memory repository containing a rich word dictionary with hints and difficulty ratings.
 */
public class InMemoryWordRepository implements WordRepository {

    private final List<Word> words = new ArrayList<>();
    private final Random random = new Random();

    public InMemoryWordRepository() {
        populateInitialWords();
    }

    private void populateInitialWords() {
        // --- PROGRAMAÇÃO ---
        addWord(new Word("JAVA", WordCategory.PROGRAMACAO, Difficulty.FACIL, "Linguagem Orientada a Objetos fortemente tipada"));
        addWord(new Word("PYTHON", WordCategory.PROGRAMACAO, Difficulty.FACIL, "Linguagem famosa por sua sintaxe limpa e Ciência de Dados"));
        addWord(new Word("ALGORITMO", WordCategory.PROGRAMACAO, Difficulty.MEDIO, "Sequência finita de passos para resolver um problema"));
        addWord(new Word("HERANCA", WordCategory.PROGRAMACAO, Difficulty.MEDIO, "Conceito de POO onde uma classe herda atributos de outra"));
        addWord(new Word("POLIMORFISMO", WordCategory.PROGRAMACAO, Difficulty.DIFICIL, "Capacidade de um objeto assumir diferentes formas em POO"));
        addWord(new Word("ENCAPSULAMENTO", WordCategory.PROGRAMACAO, Difficulty.DIFICIL, "Proteção de dados internos de uma classe usando modificadores"));

        // --- TECNOLOGIA ---
        addWord(new Word("COMPUTADOR", WordCategory.TECNOLOGIA, Difficulty.FACIL, "Máquina eletrônica capaz de processar dados"));
        addWord(new Word("INTERNET", WordCategory.TECNOLOGIA, Difficulty.FACIL, "Rede mundial de computadores interconectados"));
        addWord(new Word("PROCESSADOR", WordCategory.TECNOLOGIA, Difficulty.MEDIO, "O cérebro do computador responsável por executar instruções"));
        addWord(new Word("CRIPTOGRAFIA", WordCategory.TECNOLOGIA, Difficulty.DIFICIL, "Técnica de transformar dados para garantir sigilo e segurança"));
        addWord(new Word("MICROSERVICOS", WordCategory.TECNOLOGIA, Difficulty.DIFICIL, "Arquitetura de software baseada em serviços independentes"));

        // --- DESENVOLVIMENTO ---
        addWord(new Word("DOCKER", WordCategory.DESENVOLVIMENTO, Difficulty.FACIL, "Plataforma popular para conteinerização de aplicações"));
        addWord(new Word("FRAMEWORK", WordCategory.DESENVOLVIMENTO, Difficulty.MEDIO, "Estrutura modular de suporte para desenvolvimento de software"));
        addWord(new Word("REFACTORING", WordCategory.DESENVOLVIMENTO, Difficulty.MEDIO, "Processo de reestruturar código mantendo seu comportamento externo"));
        addWord(new Word("ARQUITETURA", WordCategory.DESENVOLVIMENTO, Difficulty.DIFICIL, "Estrutura fundamental e organização dos componentes de um sistema"));

        // --- CIÊNCIA & MATEMÁTICA ---
        addWord(new Word("GRAVIDADE", WordCategory.CIENCIA, Difficulty.FACIL, "Força fundamental de atração entre massas"));
        addWord(new Word("ESTATISTICA", WordCategory.CIENCIA, Difficulty.MEDIO, "Ramo da matemática que lida com coleta e análise de dados"));
        addWord(new Word("QUANTICA", WordCategory.CIENCIA, Difficulty.DIFICIL, "Física que estuda fenômenos em escala atômica e subatômica"));

        // --- ENTRETENIMENTO ---
        addWord(new Word("CINEMA", WordCategory.ENTRETENIMENTO, Difficulty.FACIL, "Arte de projetar imagens em movimento"));
        addWord(new Word("STREAMING", WordCategory.ENTRETENIMENTO, Difficulty.FACIL, "Transmissão contínua de áudio e vídeo pela internet"));
        addWord(new Word("VIDEOGAME", WordCategory.ENTRETENIMENTO, Difficulty.MEDIO, "Jogo eletrônico interativo jogado em telas"));
    }

    @Override
    public Optional<Word> getRandomWord(WordCategory category, Difficulty difficulty) {
        List<Word> filtered = words.stream()
                .filter(w -> category == WordCategory.TODAS || w.getCategory() == category)
                .filter(w -> difficulty == null || w.getDifficulty() == difficulty)
                .collect(Collectors.toList());

        if (filtered.isEmpty()) {
            // Fallback to category only if specific difficulty has no words
            filtered = words.stream()
                    .filter(w -> category == WordCategory.TODAS || w.getCategory() == category)
                    .collect(Collectors.toList());
        }

        if (filtered.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(filtered.get(random.nextInt(filtered.size())));
    }

    @Override
    public List<Word> getWordsByCategory(WordCategory category) {
        if (category == WordCategory.TODAS) {
            return Collections.unmodifiableList(words);
        }
        return words.stream()
                .filter(w -> w.getCategory() == category)
                .collect(Collectors.toList());
    }

    @Override
    public void addWord(Word word) {
        if (word != null) {
            words.add(word);
        }
    }

    @Override
    public int getTotalWordCount() {
        return words.size();
    }
}
