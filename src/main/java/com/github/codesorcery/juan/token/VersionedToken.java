package com.github.codesorcery.juan.token;

import java.util.StringJoiner;

public class VersionedToken extends StringToken {
    private final String version;

    public VersionedToken(final String value, final String  version) {
        super(value);
        this.version = version.trim();
    }

    public static VersionedToken empty() {
        return new VersionedToken("", "");
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
