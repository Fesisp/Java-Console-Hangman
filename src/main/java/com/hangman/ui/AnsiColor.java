package com.hangman.ui;

/**
 * Utility for ANSI color coding in console applications.
 */
public final class AnsiColor {

    private static boolean enabled = true;

    public static final String RESET = "\u001B[0m";
    public static final String BOLD = "\u001B[1m";

    // Colors
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    // Bright Colors
    public static final String BRIGHT_RED = "\u001B[91m";
    public static final String BRIGHT_GREEN = "\u001B[92m";
    public static final String BRIGHT_YELLOW = "\u001B[93m";
    public static final String BRIGHT_CYAN = "\u001B[96m";

    private AnsiColor() {
        // Private constructor
    }

    public static void setEnabled(boolean value) {
        enabled = value;
    }

    public static boolean isEnabled() {
        return enabled;
    }

    public static String colorize(String color, String text) {
        if (!enabled) {
            return text;
        }
        return color + text + RESET;
    }

    public static String green(String text) {
        return colorize(BRIGHT_GREEN, text);
    }

    public static String red(String text) {
        return colorize(BRIGHT_RED, text);
    }

    public static String yellow(String text) {
        return colorize(BRIGHT_YELLOW, text);
    }

    public static String cyan(String text) {
        return colorize(BRIGHT_CYAN, text);
    }

    public static String bold(String text) {
        return colorize(BOLD, text);
    }
}
