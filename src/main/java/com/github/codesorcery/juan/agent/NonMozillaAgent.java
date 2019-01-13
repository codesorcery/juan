package com.github.codesorcery.juan.agent;

import com.github.codesorcery.juan.util.Vendors;

import java.util.HashMap;
import java.util.Map;

enum NonMozillaAgent {
    /* Applications */
    WINDOWS_LIVE_MAIL("Outlook-Express", Vendors.MICROSOFT, "Windows Live Mail", AgentType.DESKTOP_APPLICATION),
    EVOLUTION("Evolution", "GNOME Foundation", "Evolution (mail client)", AgentType.DESKTOP_APPLICATION),
    SAFARI_RSS("AppleSyndication", Vendors.APPLE, "Safari RSS Reader", AgentType.DESKTOP_APPLICATION),
    CYBERDUCK("Cyberduck", "iterate GmbH", "Cyberduck", AgentType.DESKTOP_APPLICATION),
    INSOMNIA("insomnia", "Floating Keyboard Software", "Insomnia REST client", AgentType.DESKTOP_APPLICATION),
    WEBDE_MAIL("WEB.DE%20Mail", "1&1", "WEB.DE Mail App", AgentType.MOBILE_APPLICATION),
    GMX_MAIL("GMX%20Mail", "1&1", "GMX Mail App", AgentType.MOBILE_APPLICATION),
    /* Command line tools */
    CURL("curl", "haxx", "curl", AgentType.CLI_APPLICATION),
    WGET("Wget", "FSF", "GNU Wget", AgentType.CLI_APPLICATION),
    MASSCAN("masscan", "Robert David Graham", "MASSCAN", AgentType.CLI_APPLICATION),
    ARIA2("aria2", "Tatsuhiro Tsujikawa", "aria2", AgentType.CLI_APPLICATION),
    /* Libaries */
    APACHE_HTTP_CLIENT("Apache-HttpClient", "Apache Foundation", "Apache HttpClient", AgentType.LIBRARY),
    OK_HTTP("okhttp", "Square", "OkHttp (Java library)", AgentType.LIBRARY),
    PYTHON_REQUESTS("python-requests", "Kenneth Reitz", "requests (Python library)", AgentType.LIBRARY),
    PYTHON_URLLIB("Python-urllib", "Python Software Foundation", "urllib (Python libary)", AgentType.LIBRARY),
    PYTHON_PATTERN("Pattern", "CLiPS Research Center", "pattern (Python libary)", AgentType.LIBRARY),
    JAVA("java", "Oracle", "Java", AgentType.LIBRARY),
    LIBSOUP("libsoup", "GNOME Foundation", "libsoup (GNOME library)", AgentType.LIBRARY),
    CFNETWORK("CFNetwork", "Apple", "CFNetwork (Apple libary)", AgentType.LIBRARY),
    HTTP_REQUESTS2("HTTP_Requests2", "Alexey Borzov", "HTTP_Requests2 (PHP library)", AgentType.LIBRARY),
    GO_HTTP_CLIENT("Go-http-client", Vendors.GOOGLE, "HTTP client (Go library)", AgentType.LIBRARY),
    /* Validators */
    W3C_CHECKLINK("W3C-checklink", "W3C", "W3C Link Checker", AgentType.VALIDATOR),
    W3C_CSS_VALIDATOR("Jigsaw", "W3C", "W3C CSS Validator", AgentType.VALIDATOR),
    W3C_INTERNATIONALIZATION_CHECKER("W3C_I18n-Checker", "W3C", "W3C Internationalization Checker", AgentType.VALIDATOR),
    W3C_UNICORN_VALIDATOR("W3C_Unicorn", "W3C", "Unicorn - W3C's Unified Validator", AgentType.VALIDATOR),
    W3C_VALIDATOR("W3C_Validator", "W3C", "W3C Validator", AgentType.VALIDATOR),
    /* Media players */
    VLC("VLC", "VideoLAN team", "VLC Media Player", AgentType.MEDIA_PLAYER),
    FOOBAR200("foobar2000", "Peter Pawlowski", "foobar2000", AgentType.MEDIA_PLAYER),
    ITUNES("iTunes", Vendors.APPLE, "iTunes", AgentType.MEDIA_PLAYER),
    PLEX("Plex", "Plex community", "Plex Medica Center", AgentType.MEDIA_PLAYER),
    ALEXA_MEDIAPLAYER("AlexaMediaPlayer", Vendors.AMAZON, "Alexa MediaPlayer", AgentType.MEDIA_PLAYER),
    APPLE_TV("AppleTV", Vendors.APPLE, "tvOS", AgentType.MEDIA_PLAYER),
    /* Crawlers */
    /* https://support.google.com/webmasters/answer/1061943?hl=en */
    GOOGLE_BOT("Googlebot", Vendors.GOOGLE, "Googlebot", AgentType.CRAWLER),
    GOOGLE_BOT_IMAGES("Googlebot-Image", Vendors.GOOGLE, "Googlebot Images", AgentType.CRAWLER),
    GOOGLE_BOT_NEWS("Googlebot-News", Vendors.GOOGLE, "Googlebot News", AgentType.CRAWLER),
    GOOGLE_BOT_VIDEO("Googlebot-Video", Vendors.GOOGLE, "Googlebot Video", AgentType.CRAWLER),
    GOOGLE_ADSBOT("AdsBot-Google", Vendors.GOOGLE, "AdsBot", AgentType.CRAWLER),
    GOOGLE_ADSENSE("Mediapartners-Google", Vendors.GOOGLE, "AdSense", AgentType.CRAWLER),
    GOOGLE_APIS("APIs-Google", Vendors.GOOGLE, "Google APIs", AgentType.CRAWLER),
    ;

    private final String identifier;
    final String vendor;
    final String name;
    final AgentType type;

    NonMozillaAgent(final String identifier, final String vendor, final String name,
                    final AgentType type) {
        this.identifier = identifier;
        this.vendor = vendor;
        this.name = name;
        this.type = type;
    }

    static Map<String, NonMozillaAgent> valuesAsMap() {
        final Map<String, NonMozillaAgent> result = new HashMap<>();
        for (final NonMozillaAgent agent : NonMozillaAgent.values()) {
            result.put(agent.identifier, agent);
        }
        return result;
    }
}
