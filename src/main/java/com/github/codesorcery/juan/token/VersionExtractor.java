package com.github.codesorcery.juan.token;

import java.util.Arrays;
import java.util.List;

class VersionExtractor {
    private final List<Character> limiters;

    private VersionExtractor(final Character ... limiters) {
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
            if (currentValid && ((c >= '0' && c <= '9') || limiters.contains(c))) {
                if (end == -1) {
                    end = i;
                }
            } else if (c == ' '){
                if (currentValid) {
                    return new ExtractionResult(i + 1,
                            versionedString.substring(i + 1, end + 1)
                                    .replace('_', '.')
                    );
                }
                currentValid = true;
            } else {
                end = -1;
                currentValid = false;
            }
        }
        if (end > -1) {
            return new ExtractionResult(0,
                    versionedString.substring(0, end + 1)
                            .replace('_', '.')
            );
        } else {
            return new ExtractionResult(-1, "");
        }
    }

    static class ExtractionResult {
        private final int pos;
        private final String value;

        private ExtractionResult(final int pos, final String value) {
            this.pos = pos;
            this.value = value;
        }

        int getPos() {
            return pos;
        }

        String getValue() {
            return value;
        }
    }
}
