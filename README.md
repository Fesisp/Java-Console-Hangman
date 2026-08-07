# Java Console Hangman Engine 🎮

[![Java](https://img.shields.io/badge/Java-17%2B-ED8B00?logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Maven](https://img.shields.io/badge/Build-Maven-C71A36?logo=apachemaven&logoColor=white)](pom.xml)
[![Testing](https://img.shields.io/badge/JUnit5-16%20Passed-25A162?logo=junit5&logoColor=white)]()
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

An enterprise-grade, modular Command-Line Interface (CLI) Hangman game written in Java 17. The application demonstrates Object-Oriented Programming (OOP) principles, clean package architecture, diacritic normalization, state machine game loops, dynamic difficulty scaling, session statistics tracking, and unit test coverage using JUnit 5.

---

## 📌 Overview

Traditional console games are often built as monolithic scripts with tight coupling between input/output and game state. **Java Console Hangman Engine** resolves this anti-pattern by enforcing separation of concerns. 

The application separates Domain Entities (`Word`, `Difficulty`, `GameState`), Data Repositories (`WordRepository`), Business Logic (`GameEngine`, `Statistics`), and UI Presentation (`MenuSystem`, `HangmanDrawing`, `AnsiColor`). It also incorporates Unicode diacritic normalization, allowing seamless character matching regardless of accent marks.

---

## ✨ Key Features

- **🧠 Categorized & Extensible Word Bank:** Includes pre-loaded themes (*Programming*, *Technology*, *Software Development*, *Science & Math*, *Entertainment*) with runtime support for adding custom words.
- **⚡ Diacritic & Case Normalization:** Leverages `java.text.Normalizer` (NFD mode). Guessing the character `A` reveals `Á`, `Ã`, and `Â` automatically.
- **🎯 Dynamic Difficulty System:**
  - **Easy:** 8 attempts | 1.0x score multiplier
  - **Medium:** 6 attempts | 1.5x score multiplier
  - **Hard:** 4 attempts | 2.5x score multiplier
- **👥 Local 2-Player Mode:** Player 1 defines a secret word and optional clue; Player 2 attempts to guess it.
- **💡 Clue & Hint System:** Players can request contextual hints (`?`) during active sessions.
- **💥 Full Word Guessing ("Risk/Reward"):** Allows guessing the entire word at any time (`!WORD`). Correct guesses yield high bonus points; incorrect guesses incur a 2-attempt penalty.
- **🎨 ANSI Color UI:** Terminal coloring with an option to toggle formatting on non-compatible terminals.
- **📊 Session Statistics & Leaderboard:** Real-time tracking of win rate %, accuracy %, active streaks, maximum streak, and high scores.
- **🧪 Unit Test Coverage:** Comprehensive JUnit 5 test suite validating domain entities, repository filtering, string normalization, and state transitions.

---

## 🛠️ Tech Stack

- **Language:** Java 17+
- **Build & Dependency Management:** Apache Maven 3.8+
- **Testing Framework:** JUnit 5 (Jupiter Engine & Params)
- **Paradigm:** Object-Oriented Programming (OOP) & Clean Architecture
- **Interface:** Command-Line Interface (CLI) with ANSI Terminal Escape Codes

---

## 📂 Project Architecture

```text
src/
├── main/java/com/hangman/
│   ├── Main.java                        # Application Entry Point
│   ├── model/
│   │   ├── Difficulty.java              # Enum defining attempts and score multipliers
│   │   ├── GameResult.java              # Immutable DTO capturing game metrics & score
│   │   ├── GameState.java               # Enum tracking lifecycle (PLAYING, WON, LOST)
│   │   ├── Word.java                    # Domain Entity handling word display & normalization
│   │   └── WordCategory.java            # Enum defining available categories
│   ├── repository/
│   │   ├── WordRepository.java          # Data access interface
│   │   └── InMemoryWordRepository.java  # Categorized dictionary implementation
│   ├── service/
│   │   ├── GameEngine.java              # Core turn processor, state machine & scoring engine
│   │   └── Statistics.java              # Session tracking, streaks & leaderboard service
│   ├── ui/
│   │   ├── AnsiColor.java               # ANSI terminal color styling utility
│   │   ├── HangmanDrawing.java          # ASCII art renderer with dynamic scaling
│   │   └── MenuSystem.java              # Interactive CLI navigation controller
│   └── util/
│       └── StringUtils.java             # String utilities, diacritic normalization & padding
└── test/java/com/hangman/               # Automated JUnit 5 Test Suite
    ├── model/WordTest.java
    ├── repository/WordRepositoryTest.java
    ├── service/GameEngineTest.java
    ├── service/StatisticsTest.java
    └── util/StringUtilsTest.java
```

---

## 🚀 Getting Started

### Prerequisites

- **Java Development Kit (JDK 17 or higher)**
- **Apache Maven 3.8+**

### Installation & Execution

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/your-username/java-console-hangman-engine.git
   cd java-console-hangman-engine
   ```

2. **Run Automated Unit Tests:**
   ```bash
   mvn clean test
   ```

3. **Execute the CLI Application via Maven:**
   ```bash
   mvn exec:java
   ```

4. **Or Package into an Executable JAR:**
   ```bash
   mvn clean package
   java -jar target/java-game-console-hangman-2.0.0.jar
   ```

---

## ⚙️ Game Controls & Commands

| Command | Action |
| :--- | :--- |
| `A - Z` | Input a single character guess |
| `?` | Request a contextual clue / hint |
| `!WORD` | Guess the entire secret word (Risk/Reward) |

---

## 📄 License

Distributed under the MIT License. See `LICENSE` for details.
