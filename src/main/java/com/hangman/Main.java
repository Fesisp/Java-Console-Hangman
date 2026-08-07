package com.hangman;

import com.hangman.ui.MenuSystem;

/**
 * Application Entry Point for Java Console Hangman Enterprise.
 */
public class Main {
    public static void main(String[] args) {
        MenuSystem menuSystem = new MenuSystem();
        menuSystem.start();
    }
}
