# Jogo da Forca em Java

Este é um projeto de implementação do clássico Jogo da Forca em Java, desenvolvido como parte de um desafio de programação. O jogo foi construído utilizando conceitos de Programação Orientada a Objetos (POO) e roda em ambiente console.

## Estrutura do Projeto

O projeto está organizado em várias classes, cada uma com sua responsabilidade específica:

- `Main.java`: Ponto de entrada do programa
- `Game.java`: Controla a lógica principal do jogo
- `Word.java`: Gerencia a palavra a ser adivinhada
- `HangmanDrawing.java`: Responsável pela representação visual da forca
- `GameState.java`: Enum que define os estados possíveis do jogo

## Conceitos de POO Aplicados

1. **Classes e Objetos**
   - Cada componente do jogo é representado por uma classe
   - Instâncias são criadas para gerenciar diferentes aspectos do jogo

2. **Encapsulamento**
   - Atributos privados com métodos de acesso quando necessário
   - Proteção dos dados internos das classes

3. **Separação de Responsabilidades**
   - Cada classe tem uma função específica e bem definida
   - Código organizado e de fácil manutenção

## Funcionalidades

- Seleção aleatória de palavras de uma lista predefinida
- Interface em console com representação visual da forca
- Controle de tentativas e letras já utilizadas
- Verificação de vitória ou derrota
- Feedback visual do progresso do jogador

## Como Jogar

1. Execute o programa
2. Uma palavra será selecionada aleatoriamente
3. Digite uma letra por vez para tentar adivinhar a palavra
4. Você tem 6 tentativas antes que o jogo termine
5. Ganhe adivinhando todas as letras ou perca após 6 erros

## Aspectos Técnicos

- Desenvolvido em Java
- Utiliza estruturas de dados como List e ArrayList
- Implementa controle de estado do jogo
- Possui tratamento de entrada do usuário
- Utiliza constantes para valores fixos

## Execução do Projeto

Para executar o projeto, você precisará:

1. Ter o Java JDK instalado
2. Compilar os arquivos .java
3. Executar a classe Main

```bash
javac *.java
java Main
```