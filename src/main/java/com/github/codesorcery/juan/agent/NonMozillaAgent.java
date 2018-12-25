package com.github.codesorcery.juan.agent;

import java.util.HashMap;
import java.util.Map;

public enum NonMozillaAgent {
    /* Applications */
    WINDOWS_LIVE_MAIL("Outlook-Express", "Microsoft", "Windows Live Mail"),
    EVOLUTION("Evolution", "GNOME Foundation", "Evolution (mail client)"),
    SAFARI_RSS("AppleSyndication", "Apple", "Safari RSS Reader"),
    CYBERDUCK("Cyberduck", "iterate GmbH", "Cyberduck"),
    INSOMNIA("insomnia", "Floating Keyboard Software", "Insomnia REST client"),
    /* Command line tools */
    CURL("curl", "haxx", "curl"),
    WGET("Wget", "FSF", "GNU Wget"),
    MASSCAN("masscan", "Robert David Graham", "MASSCAN"),
    /* Libaries */
    APACHE_HTTP_CLIENT("Apache-HttpClient", "Apache Foundation", "Apache HttpClient"),
    OK_HTTP("okhttp", "Square", "OkHttp (Java library)"),
    PYTHON_REQUESTS("python-requests", "Kenneth Reitz", "requests (Python library)"),
    PYTHON_URLLIB("Python-urllib", "Python Software Foundation", "urllib (Python libary)"),
    PYTHON_PATTERN("Pattern", "CLiPS Research Center", "pattern (Python libary)"),
    ARIA2("aria2", "Tatsuhiro Tsujikawa", "aria2"),
    JAVA("java", "Oracle", "Java"),
    LIBSOUP("libsoup", "GNOME Foundation", "libsoup (GNOME library)"),
    CFNETWORK("CFNetwork", "Apple", "CFNetwork (Apple libary)"),
    HTTP_REQUESTS2("HTTP_Requests2", "Alexey Borzov", "HTTP_Requests2 (PHP library)"),
    GO_HTTP_CLIENT("Go-http-client", "Google", "HTTP client (Go library)"),
    /* Validators */
    W3C_CHECKLINK("W3C-checklink", "W3C", "W3C Link Checker"),
    W3C_CSS_VALIDATOR("Jigsaw", "W3C", "W3C CSS Validator"),
    W3C_INTERNATIONALIZATION_CHECKER("W3C_I18n-Checker", "W3C", "W3C Internationalization Checker"),
    W3C_UNICORN_VALIDATOR("W3C_Unicorn", "W3C", "Unicorn - W3C's Unified Validator"),
    W3C_VALIDATOR("W3C_Validator", "W3C", "W3C Validator"),
    /* Media players */
    VLC("VLC", "VideoLAN team", "VLC Media Player"),
    FOOBAR200("foobar2000", "Peter Pawlowski", "foobar2000"),
    ITUNES("iTunes", "Apple", "iTunes"),
    PLEX("Plex", "Plex community", "Plex Medica Center"),
    ALEXA_MEDIAPLAYER("AlexaMediaPlayer", "Amazon", "Alexa MediaPlayer"),
    APPLE_TV("AppleTV", "Apple", "tvOS"),
    ;

    private final String identifier;
    private final String vendor;
    private final String name;

    NonMozillaAgent(final String identifier, final String vendor, final String name) {
        this.identifier = identifier;
        this.vendor = vendor;
        this.name = name;
    }

    String getVendor() {
        return vendor;
    }

    String getName() {
        return name;
    }

    static Map<String, NonMozillaAgent> valuesAsMap() {
        final Map<String, NonMozillaAgent> result = new HashMap<>();
        for (final NonMozillaAgent agent : NonMozillaAgent.values()) {
            result.put(agent.identifier, agent);
        }
        return result;
    }
}
