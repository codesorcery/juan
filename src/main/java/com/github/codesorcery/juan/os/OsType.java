package com.github.codesorcery.juan.os;

public enum OsType {
    DESKTOP("Desktop"),
    MOBILE("Mobile"),
    UNKNOWN("");

    private final String value;

    OsType(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
    
}
