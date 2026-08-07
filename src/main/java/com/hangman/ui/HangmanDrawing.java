package com.hangman.ui;

/**
 * Renders high-quality ASCII art representation of the Hangman state.
 */
public class HangmanDrawing {

    private static final String[] HANGMAN_STATES = {
        // State 0: Empty Scaffold
        "  +---+\n" +
        "  |   |\n" +
        "      |\n" +
        "      |\n" +
        "      |\n" +
        "      |\n" +
        "=========",

        // State 1: Head
        "  +---+\n" +
        "  |   |\n" +
        "  O   |\n" +
        "      |\n" +
        "      |\n" +
        "      |\n" +
        "=========",

        // State 2: Body
        "  +---+\n" +
        "  |   |\n" +
        "  O   |\n" +
        "  |   |\n" +
        "      |\n" +
        "      |\n" +
        "=========",

        // State 3: Left Arm
        "  +---+\n" +
        "  |   |\n" +
        "  O   |\n" +
        " /|   |\n" +
        "      |\n" +
        "      |\n" +
        "=========",

        // State 4: Right Arm
        "  +---+\n" +
        "  |   |\n" +
        "  O   |\n" +
        " /|\\  |\n" +
        "      |\n" +
        "      |\n" +
        "=========",

        // State 5: Left Leg
        "  +---+\n" +
        "  |   |\n" +
        "  O   |\n" +
        " /|\\  |\n" +
        " /    |\n" +
        "      |\n" +
        "=========",

        // State 6+: Game Over / Full Hanging Body
        "  +---+\n" +
        "  |   |\n" +
        "  O   |\n" +
        " /|\\  |\n" +
        " / \\  |\n" +
        "      |\n" +
        "========="
    };

    /**
     * Renders drawing based on wrong attempts count and max tries allowed.
     */
    public String draw(int wrongTries, int maxTries) {
        if (maxTries <= 0) maxTries = 6;
        // Scale wrong tries to 0-6 index
        int index = (int) Math.round(((double) wrongTries / maxTries) * (HANGMAN_STATES.length - 1));
        index = Math.max(0, Math.min(index, HANGMAN_STATES.length - 1));

        String art = HANGMAN_STATES[index];
        if (index == 0) {
            return AnsiColor.cyan(art);
        } else if (index >= HANGMAN_STATES.length - 1) {
            return AnsiColor.red(art);
        } else {
            return AnsiColor.yellow(art);
        }
    }
}
