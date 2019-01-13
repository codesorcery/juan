package com.github.codesorcery.juan.token;

import java.util.StringJoiner;

/**
 * A single extracted token with value and version information.
 */
public class VersionedToken {
    private final String value;
    private final String version;

    VersionedToken(final String value, final String version) {
        this.value = value.trim();
        this.version = extraTrim(version.trim());
    }

    private String extraTrim(final String value) {
        if (!value.isEmpty() && value.charAt(value.length() - 1) == ';') {
            return value.substring(0, value.length() -1);
        }
        return value;
    }

    /**
     * @return The value string of the token.
     */
    public String getValue() {
        return value;
    }

    /**
     * @return The version string of the token.
     * Empty string if no version was extracted.
     */
    public String getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]")
                .add("value='" + getValue() + "'")
                .add("version='" + getVersion() + "'")
                .toString();
    }
}
