package com.github.codesorcery.juan.agent;

import com.github.codesorcery.juan.token.TokenizedUserAgent;
import com.github.codesorcery.juan.util.Tokens;
import com.github.codesorcery.juan.util.Vendors;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

enum OtherIdentifiableMozillaAgent {
    CHROME(Vendors.GOOGLE, "Chrome", tokenList ->
            tokenList.contains(Tokens.APPLE_WEBKIT)
                    && tokenList.contains(Tokens.CHROME)
                    && !tokenList.contains(Tokens.MOBILE)
                    && !tokenList.contains(Tokens.EDGE)
                    && !tokenList.contains(Tokens.GECKO),
            Tokens.CHROME,
            AgentType.DESKTOP_BROWSER
    ),
    FIREFOX(Vendors.MOZILLA, "Firefox", tokenList ->
            tokenList.contains(Tokens.GECKO)
                    && tokenList.contains(Tokens.FIREFOX),
            Tokens.FIREFOX,
            AgentType.DESKTOP_BROWSER
    ),
    SAFARI(Vendors.APPLE, "Safari", tokenList ->
            tokenList.contains(Tokens.SAFARI)
                    && tokenList.contains(Tokens.VERSION)
                    && !tokenList.contains(Tokens.MOBILE)
                    && !tokenList.contains(Tokens.CHROME)
                    && !tokenList.contains(Tokens.FIREFOX)
                    && !tokenList.contains(Tokens.ANDROID),
            Tokens.VERSION,
            AgentType.DESKTOP_BROWSER
    ),
    INTERNET_EXPLORER(Vendors.MICROSOFT, "Internet Explorer", tokenList ->
            tokenList.contains("Trident"),
            "rv",
            AgentType.DESKTOP_BROWSER
    ),
    EDGE(Vendors.MICROSOFT, "Edge", tokenList ->
            tokenList.contains(Tokens.EDGE)
                    && !tokenList.contains(Tokens.MOBILE),
            Tokens.EDGE,
            AgentType.DESKTOP_BROWSER
    ),
    EDGE_MOBILE(Vendors.MICROSOFT, "Edge Mobile", tokenList ->
            tokenList.contains(Tokens.EDGE)
                    && tokenList.contains(Tokens.MOBILE),
            Tokens.EDGE,
            AgentType.MOBILE_BROWSER
    ),
    CHROME_MOBILE(Vendors.GOOGLE, "Chrome Mobile", tokenList ->
            tokenList.contains(Tokens.CHROME)
                    && tokenList.contains(Tokens.MOBILE)
                    && !tokenList.contains(Tokens.EDGE),
            Tokens.CHROME,
            AgentType.MOBILE_BROWSER
    ),
    SAFARI_MOBILE(Vendors.APPLE, "Safari Mobile", tokenList ->
            tokenList.contains(Tokens.SAFARI)
                    && tokenList.contains(Tokens.VERSION)
                    && tokenList.contains(Tokens.MOBILE)
                    && !tokenList.contains(Tokens.ANDROID)
                    && !tokenList.contains(Tokens.CHROME)
                    && !tokenList.contains(Tokens.FIREFOX),
            Tokens.VERSION,
            AgentType.MOBILE_BROWSER
    ),
    ANDROID_BROWSER(Vendors.GOOGLE, "Android Browser", tokenList ->
            tokenList.contains(Tokens.APPLE_WEBKIT)
                    && tokenList.contains(Tokens.VERSION)
                    && tokenList.contains(Tokens.ANDROID)
                    && !tokenList.contains(Tokens.CHROME)
                    && !tokenList.contains(Tokens.FIREFOX),
            Tokens.VERSION,
            AgentType.MOBILE_BROWSER
    ),
    ;

    private final Predicate<List<String>> predicate;
    final String vendor;
    final String name;
    final String versionSource;
    final AgentType type;

    OtherIdentifiableMozillaAgent(final String vendor,
                                  final String name,
                                  final Predicate<List<String>> predicate,
                                  final String versionSource,
                                  final AgentType type) {
        this.vendor = vendor;
        this.name = name;
        this.predicate = predicate;
        this.versionSource = versionSource;
        this.type = type;
    }

    boolean matches(final TokenizedUserAgent source) {
        final int n = source.getAllTokens().size();
        final List<String> tokenList = Arrays.asList(new String[n]);
        for (int i = 0; i < n; i++) {
            tokenList.set(i, source.getAllTokens().get(i).getValue());
        }
        return predicate.test(tokenList);
    }

    static List<OtherIdentifiableMozillaAgent> valuesAsList() {
        return Arrays.asList(OtherIdentifiableMozillaAgent.values());
    }
}
