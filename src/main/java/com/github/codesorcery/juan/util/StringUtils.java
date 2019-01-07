package com.github.codesorcery.juan.util;

/**
 * Internal utility functions for string manipulation.
 */
public class StringUtils {
    private StringUtils() {}

    /**
     * Compute the substring of a string until a certain other string.
     *
     * @param value The string on which this operation is executed.
     * @param stopAt Stop when this string is reached.
     * @return The computed substring.
     */
    public static String substringUntil(final String value, final String stopAt) {
        final int pos = value.indexOf(stopAt);
        if (pos != -1) {
            return value.substring(0, pos);
        } else {
            return value;
        }
    }
}
