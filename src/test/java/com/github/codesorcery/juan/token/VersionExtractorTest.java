package com.github.codesorcery.juan.token;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VersionExtractorTest {
    @Test
    public void onlyVersionString() {
        assertEquals(0, VersionExtractor.versionPos("1.34.1"));
    }

    @Test
    public void onlyVersionStringUnderscoreAsLimiter() {
        assertEquals(0, VersionExtractor.versionPos("1_34_1"));
    }

    @Test
    public void onlySimpleVersion() {
        assertEquals(0, VersionExtractor.versionPos("24"));
    }

    @Test
    public void withPrefix() {
        assertEquals(4, VersionExtractor.versionPos("foo 1.2.3"));
    }

    @Test
    public void withPostfix() {
        assertEquals(0, VersionExtractor.versionPos("1.2.3 bar"));
    }

    @Test
    public void withPrefixEndsWithNumber() {
        assertEquals(6, VersionExtractor.versionPos("foo12 1.2.3"));
    }

    @Test
    public void withPrefixStartsWithNumber() {
        assertEquals(6, VersionExtractor.versionPos("12foo 1.2.3"));
    }

    @Test
    public void withPostfixEndsWithNumber() {
        assertEquals(0, VersionExtractor.versionPos("1.2.3 foo12"));
    }

    @Test
    public void withPostfixStartsWithNumber() {
        assertEquals(0, VersionExtractor.versionPos("1.2.3 12foo"));
    }

    @Test
    public void withPreAndPostfix() {
        assertEquals(4, VersionExtractor.versionPos("bar 1.2.3 12foo"));
    }

    @Test
    public void noVersion() {
        assertEquals(-1, VersionExtractor.versionPos("bar 12foo"));
    }

    @Test
    public void emptyString() {
        assertEquals(-1, VersionExtractor.versionPos(""));
    }
}
