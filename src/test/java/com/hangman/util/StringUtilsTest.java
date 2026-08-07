package com.hangman.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    @DisplayName("Should normalize accents and lowercases to uppercase ASCII")
    void testNormalize() {
        assertEquals("PROGRAMACAO", StringUtils.normalize("Programação"));
        assertEquals("ALGORITMO", StringUtils.normalize("algoritmo"));
        assertEquals("ESTATISTICA", StringUtils.normalize("Estatística"));
        assertEquals("HERANCA", StringUtils.normalize("HERANÇA"));
    }

    @ParameterizedTest
    @CsvSource({
        "a, Á, true",
        "c, ç, true",
        "E, ê, true",
        "x, y, false"
    })
    @DisplayName("Should correctly compare characters ignoring accents")
    void testMatchesIgnoringAccent(char ch1, char ch2, boolean expected) {
        assertEquals(expected, StringUtils.matchesIgnoringAccent(ch1, ch2));
    }

    @Test
    @DisplayName("Should handle null inputs safely")
    void testNullInput() {
        assertEquals("", StringUtils.normalize(null));
        assertEquals("    ", StringUtils.padRight(null, 4));
    }
}
