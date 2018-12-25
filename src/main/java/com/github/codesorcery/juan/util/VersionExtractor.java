package com.github.codesorcery.juan.util;

public class VersionExtractor {
    private final char limiter;

    private VersionExtractor(final char limiter) {
        this.limiter = limiter;
    }

    public static VersionExtractor forLimiter(final char limiter) {
        return new VersionExtractor(limiter);
    }

    public String extract(final String versionedString) {
        int end = -1;
        boolean currentValid = true;
        for (int i = versionedString.length() - 1; i >= 0; i--) {
            final char c = versionedString.charAt(i);
            if (currentValid && ((c >= '0' && c <= '9') || c == limiter)) {
                if (end == -1) {
                    end = i;
                }
            } else if (c == ' '){
                if (currentValid) {
                    return versionedString.substring(i + 1, end + 1).replace(limiter, '.');
                }
                currentValid = true;
            } else {
                end = -1;
                currentValid = false;
            }
        }
        if (end > -1) {
            return versionedString.substring(0, end + 1).replace(limiter, '.');
        } else {
            return "";
        }
    }
}
