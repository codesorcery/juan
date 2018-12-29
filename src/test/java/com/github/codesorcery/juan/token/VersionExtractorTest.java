package com.github.codesorcery.juan.token;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VersionExtractorTest {
    @Test
    public void onlyVersionString() {
        final VersionExtractor extractor = VersionExtractor.forLimiter('.');
        final VersionExtractor.ExtractionResult result =
                extractor.extract("1.34.1");
        assertEquals(0, result.getStart());
        assertEquals(6, result.getEnd());
    }

    @Test
    public void onlyVersionStringUnderscoreAsLimiter() {
        final VersionExtractor extractor = VersionExtractor.forLimiter('_');
        final VersionExtractor.ExtractionResult result =
                extractor.extract("1_34_1");
        assertEquals(0, result.getStart());
    }

    @Test
    public void onlySimpleVersion() {
        final VersionExtractor extractor = VersionExtractor.forLimiter('.');
        final VersionExtractor.ExtractionResult result =
                extractor.extract("24");
        assertEquals(0, result.getStart());
        assertEquals(2, result.getEnd());
    }

    @Test
    public void withPrefix() {
        final VersionExtractor extractor = VersionExtractor.forLimiter('.');
        final VersionExtractor.ExtractionResult result =
                extractor.extract("foo 1.2.3");
        assertEquals(4, result.getStart());
        assertEquals(9, result.getEnd());
    }

    @Test
    public void withPostfix() {
        final VersionExtractor extractor = VersionExtractor.forLimiter('.');
        final VersionExtractor.ExtractionResult result =
                extractor.extract("1.2.3 bar");
        assertEquals(0, result.getStart());
        assertEquals(5, result.getEnd());
    }

    @Test
    public void withPrefixEndsWithNumber() {
        final VersionExtractor extractor = VersionExtractor.forLimiter('.');
        final VersionExtractor.ExtractionResult result =
                extractor.extract("foo12 1.2.3");
        assertEquals(6, result.getStart());
        assertEquals(11, result.getEnd());
    }

    @Test
    public void withPrefixStartsWithNumber() {
        final VersionExtractor extractor = VersionExtractor.forLimiter('.');
        final VersionExtractor.ExtractionResult result =
                extractor.extract("12foo 1.2.3");
        assertEquals(6, result.getStart());
        assertEquals(11, result.getEnd());
    }

    @Test
    public void withPostfixEndsWithNumber() {
        final VersionExtractor extractor = VersionExtractor.forLimiter('.');
        final VersionExtractor.ExtractionResult result =
                extractor.extract("1.2.3 foo12");
        assertEquals(0, result.getStart());
        assertEquals(5, result.getEnd());
    }

    @Test
    public void withPostfixStartsWithNumber() {
        final VersionExtractor extractor = VersionExtractor.forLimiter('.');
        final VersionExtractor.ExtractionResult result =
                extractor.extract("1.2.3 12foo");
        assertEquals(0, result.getStart());
        assertEquals(5, result.getEnd());
    }

    @Test
    public void withPreAndPostfix() {
        final VersionExtractor extractor = VersionExtractor.forLimiter('.');
        final VersionExtractor.ExtractionResult result =
                extractor.extract("bar 1.2.3 12foo");
        assertEquals(4, result.getStart());
        assertEquals(9, result.getEnd());
    }

    @Test
    public void noVersion() {
        final VersionExtractor extractor = VersionExtractor.forLimiter('.');
        final VersionExtractor.ExtractionResult result =
                extractor.extract("bar 12foo");
        assertEquals(-1, result.getStart());
        assertEquals(-1, result.getEnd());
    }

    @Test
    public void emptyString() {
        final VersionExtractor extractor = VersionExtractor.forLimiter('.');
        final VersionExtractor.ExtractionResult result =
                extractor.extract("");
        assertEquals(-1, result.getStart());
        assertEquals(-1, result.getEnd());
    }
}
