package com.github.codesorcery.juan.token;

import java.util.StringJoiner;

public class VersionedToken {
    private final String value;
    private final String version;

    VersionedToken(final String value, final String  version) {
        this.value = value.trim();
        this.version = version.trim();
    }

    public String getValue() {
        return value;
    }

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
