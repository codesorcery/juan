package com.github.codesorcery.juan.token;

import java.util.StringJoiner;

public class StringToken {
    private final String value;

    public StringToken(final String value) {
        this.value = value.trim();
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]")
                .add("value='" + getValue() + "'")
                .toString();
    }
}
