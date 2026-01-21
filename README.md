# Console Hangman Game with OOP & State Management 🎮

[![Java](https://img.shields.io/badge/Java-17%2B-ED8B00?logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Status](https://img.shields.io/badge/Status-Completed-success)]()
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

> A classic Hangman game re-engineered to demonstrate **Object-Oriented Programming (OOP)** principles, modular architecture, and application state management in pure Java.

## 📋 Project Overview

This project goes beyond a simple "guess the word" script. It is structured to simulate a real software application lifecycle, separating the **User Interface** (MenuSystem) from the **Business Logic** (Game, Statistics). It serves as a reference for handling game loops, input validation, and object state in a stateless console environment.

## ✨ Key Features

* **🧠 Categorized Word Bank:** Players can choose specific themes (e.g., Technology, Food) powered by the `WordCategory` Enum strategy.
* **📊 Session Statistics:** The `Statistics` class tracks wins, losses, and streaks during the runtime session.
* **🔄 State Management:** The game flow is controlled by a dedicated `GameState` handler, ensuring valid transitions between menus, gameplay, and results.
* **🎨 Dynamic ASCII Art:** The `HangmanDrawing` class renders progressive visual feedback based on the player's remaining attempts.
* **🛡️ Input Validation:** Robust handling of user inputs to prevent crashes or invalid character guesses.

## 🛠️ Tech Stack

* **Language:** Java (JDK 17+)
* **Paradigm:** Object-Oriented Programming (OOP)
* **Interface:** Command Line Interface (CLI)

## 📂 Project Structure

The code is organized to enforce separation of concerns:

```text
src/
├── Main.java            # Application Entry Point
├── Game.java            # Core Game Loop & Logic Controller
├── GameState.java       # Enum/Class for State Management (Playing, Won, Lost)
├── MenuSystem.java      # UI/Console Interaction Layer
├── Statistics.java      # Scoreboard & Session Data Tracking
├── Word.java            # Model representing the secret word
├── WordCategory.java    # Enum defining available themes
└── HangmanDrawing.java  # View component for ASCII rendering
