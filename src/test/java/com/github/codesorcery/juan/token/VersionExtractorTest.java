package com.github.codesorcery.juan.token;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VersionExtractorTest {
    @Test
    public void onlyVersionString() {
        final VersionExtractor extractor = VersionExtractor.forLimiter('.');
        final VersionExtractor.ExtractionResult result =
                extractor.extract("1.34.1");
        assertEquals("1.34.1", result.getValue());
        assertEquals(0, result.getPos());
    }

    @Test
    public void onlyVersionStringUnderscoreAsLimiter() {
        final VersionExtractor extractor = VersionExtractor.forLimiter('_');
        final VersionExtractor.ExtractionResult result =
                extractor.extract("1_34_1");
        assertEquals("1.34.1", result.getValue());
        assertEquals(0, result.getPos());
    }

    @Test
    public void onlySimpleVersion() {
        final VersionExtractor extractor = VersionExtractor.forLimiter('.');
        final VersionExtractor.ExtractionResult result =
                extractor.extract("24");
        assertEquals("24", result.getValue());
        assertEquals(0, result.getPos());
    }

    @Test
    public void withPrefix() {
        final VersionExtractor extractor = VersionExtractor.forLimiter('.');
        final VersionExtractor.ExtractionResult result =
                extractor.extract("foo 1.2.3");
        assertEquals("1.2.3", result.getValue());
        assertEquals(4, result.getPos());
    }

    @Test
    public void withPostfix() {
        final VersionExtractor extractor = VersionExtractor.forLimiter('.');
        final VersionExtractor.ExtractionResult result =
                extractor.extract("1.2.3 bar");
        assertEquals("1.2.3", result.getValue());
        assertEquals(0, result.getPos());
    }

    @Test
    public void withPrefixEndsWithNumber() {
        final VersionExtractor extractor = VersionExtractor.forLimiter('.');
        final VersionExtractor.ExtractionResult result =
                extractor.extract("foo12 1.2.3");
        assertEquals("1.2.3", result.getValue());
        assertEquals(6, result.getPos());
    }

    @Test
    public void withPrefixStartsWithNumber() {
        final VersionExtractor extractor = VersionExtractor.forLimiter('.');
        final VersionExtractor.ExtractionResult result =
                extractor.extract("12foo 1.2.3");
        assertEquals("1.2.3", result.getValue());
        assertEquals(6, result.getPos());
    }

    @Test
    public void withPostfixEndsWithNumber() {
        final VersionExtractor extractor = VersionExtractor.forLimiter('.');
        final VersionExtractor.ExtractionResult result =
                extractor.extract("1.2.3 foo12");
        assertEquals("1.2.3", result.getValue());
        assertEquals(0, result.getPos());
    }

    @Test
    public void withPostfixStartsWithNumber() {
        final VersionExtractor extractor = VersionExtractor.forLimiter('.');
        final VersionExtractor.ExtractionResult result =
                extractor.extract("1.2.3 12foo");
        assertEquals("1.2.3", result.getValue());
        assertEquals(0, result.getPos());
    }

    @Test
    public void noVersion() {
        final VersionExtractor extractor = VersionExtractor.forLimiter('.');
        final VersionExtractor.ExtractionResult result =
                extractor.extract("bar 12foo");
        assertEquals("", result.getValue());
        assertEquals(-1, result.getPos());
    }

    @Test
    public void emptyString() {
        final VersionExtractor extractor = VersionExtractor.forLimiter('.');
        final VersionExtractor.ExtractionResult result =
                extractor.extract("");
        assertEquals("", result.getValue());
        assertEquals(-1, result.getPos());
    }
}
