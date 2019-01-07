package com.github.codesorcery.juan.token;

import java.util.Arrays;
import java.util.List;

class VersionExtractor {
    private final List<Character> limiters;

    private VersionExtractor(final Character... limiters) {
        this.limiters = Arrays.asList(limiters);
    }

    static VersionExtractor forLimiter(final Character... limiters) {
        return new VersionExtractor(limiters);
    }

    ExtractionResult extract(final String versionedString) {
        int end = -1;
        boolean currentValid = true;
        for (int i = versionedString.length() - 1; i >= 0; i--) {
            final char c = versionedString.charAt(i);
            if (currentValid && isPartOfVersionNumber(c)) {
                if (end == -1) {
                    end = i;
                }
            } else if (c == ' ') {
                if (currentValid && end > -1) {
                    return getResult(i + 1, end + 1);
                }
                currentValid = true;
            } else {
                end = -1;
                currentValid = false;
            }
        }
        if (end > -1) {
            return getResult(0, end + 1);
        } else {
            return new ExtractionResult(-1, -1);
        }
    }

    private boolean isPartOfVersionNumber(final char c) {
        return (c >= '0' && c <= '9') || limiters.contains(c);
    }

    private ExtractionResult getResult(final int start, final int end) {
        return new ExtractionResult(start, end);

    }

    static class ExtractionResult {
        private final int start;
        private final int end;

        private ExtractionResult(final int start, final int end) {
            this.start = start;
            this.end = end;
        }

        int getStart() {
            return start;
        }

        int getEnd() {
            return end;
        }
    }
}
