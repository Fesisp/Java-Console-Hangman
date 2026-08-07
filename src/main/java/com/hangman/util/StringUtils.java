package com.hangman.util;

import java.text.Normalizer;

/**
 * Utility class for string manipulation, accent normalization, and formatting.
 */
public final class StringUtils {

    private StringUtils() {
        // Utility class
    }

    /**
     * Removes diacritics/accents from text (e.g., 'Á' -> 'A', 'ç' -> 'c').
     *
     * @param input Raw text string
     * @return Normalized string in UPPERCASE without diacritics.
     */
    public static String normalize(String input) {
        if (input == null) {
            return "";
        }
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        return normalized.replaceAll("\\p{M}", "").toUpperCase().trim();
    }

    /**
     * Checks if a single character matches another character regardless of accent or case.
     *
     * @param ch1 First character
     * @param ch2 Second character
     * @return true if characters match base values
     */
    public static boolean matchesIgnoringAccent(char ch1, char ch2) {
        String s1 = normalize(String.valueOf(ch1));
        String s2 = normalize(String.valueOf(ch2));
        return s1.equals(s2);
    }

    /**
     * Pads a string with spaces on the right to achieve a fixed length.
     *
     * @param value Object/String to pad
     * @param length Desired total string length
     * @return Right-padded string
     */
    public static String padRight(Object value, int length) {
        String str = (value == null) ? "" : value.toString();
        if (str.length() >= length) {
            return str;
        }
        return String.format("%-" + length + "s", str);
    }
}
