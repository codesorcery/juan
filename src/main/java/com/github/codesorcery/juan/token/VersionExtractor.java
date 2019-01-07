package com.github.codesorcery.juan.token;

class VersionExtractor {
    private VersionExtractor() {}

    static int versionPos(final String versionedString) {
        boolean currentValid = true;
        for (int i = versionedString.length() - 1; i >= 0; i--) {
            final char c = versionedString.charAt(i);
            if (c == ' ') {
                if (currentValid) {
                    return i + 1;
                }
                currentValid = true;
            } else if (!isPartOfVersionNumber(c)) {
                currentValid = false;
            }
        }
        return -1;
    }

    private static boolean isPartOfVersionNumber(final char c) {
        return (c >= '0' && c <= '9') || c == '.' || c == '_';
    }

}
