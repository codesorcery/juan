package com.github.codesorcery.juan.agent;

public enum AgentType {
    BROWSER("Browser"),
    MOBILE_BROWSER("Browser (mobile)"),
    DESKTOP_BROWSER("Browser (desktop)"),
    EMBEDDED_BROWSER("Browser (embedded)"),
    MOBILE_APPLICATION("Application (mobile)"),
    DESKTOP_APPLICATION("Application (desktop)"),
    CLI_APPLICATION("Application (command line)"),
    LIBRARY("Library"),
    VALIDATOR("Validator"),
    MEDIA_PLAYER("Media player"),
    UNKNOWN(""),
    ;

    private final String value;

    AgentType(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

}
