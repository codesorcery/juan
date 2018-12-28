package com.github.codesorcery.juan.agent;

import com.github.codesorcery.juan.token.TokenizedUserAgent;
import com.github.codesorcery.juan.util.Tokens;
import com.github.codesorcery.juan.util.Vendors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public enum OtherIdentifiableMozillaAgent {
    CHROME(Vendors.GOOGLE, "Chrome", tokenList ->
            tokenList.contains(Tokens.APPLE_WEBKIT)
                    && tokenList.contains(Tokens.CHROME)
                    && !tokenList.contains(Tokens.MOBILE_SAFARI)
                    && !tokenList.contains(Tokens.EDGE)
                    && !tokenList.contains(Tokens.GECKO),
            Tokens.CHROME
    ),
    FIREFOX(Vendors.MOZILLA, "Firefox", tokenList ->
            tokenList.contains(Tokens.GECKO)
                    && tokenList.contains(Tokens.FIREFOX),
            Tokens.FIREFOX
    ),
    SAFARI(Vendors.APPLE, "Safari", tokenList ->
            tokenList.contains(Tokens.SAFARI)
                && tokenList.contains(Tokens.VERSION)
                && !tokenList.contains(Tokens.MOBILE)
                && !tokenList.contains(Tokens.CHROME)
                && !tokenList.contains(Tokens.FIREFOX)
                && !tokenList.contains(Tokens.ANDROID),
            Tokens.VERSION
    ),
    INTERNET_EXPLORER(Vendors.MICROSOFT, "Internet Explorer", tokenList ->
            tokenList.contains("Trident"),
            "rv"
    ),
    EDGE(Vendors.MICROSOFT, "Edge", tokenList ->
            tokenList.contains(Tokens.EDGE)
                    && !tokenList.contains(Tokens.MOBILE_SAFARI),
            Tokens.EDGE
    ),
    EDGE_MOBILE(Vendors.MICROSOFT, "Edge Mobile", tokenList ->
            tokenList.contains(Tokens.EDGE)
                    && tokenList.contains(Tokens.MOBILE_SAFARI),
            Tokens.EDGE
    ),
    CHROME_MOBILE(Vendors.GOOGLE, "Chrome Mobile", tokenList ->
            tokenList.contains(Tokens.CHROME)
                    && tokenList.contains(Tokens.MOBILE_SAFARI)
                    && !tokenList.contains(Tokens.EDGE),
            Tokens.CHROME
    ),
    SAFARI_MOBILE(Vendors.APPLE, "Safari Mobile", tokenList ->
            tokenList.contains(Tokens.SAFARI)
                    && tokenList.contains(Tokens.VERSION)
                    && tokenList.contains(Tokens.MOBILE)
                    && !tokenList.contains(Tokens.CHROME)
                    && !tokenList.contains(Tokens.FIREFOX),
            Tokens.VERSION
    ),
    ANDROID_BROWSER(Vendors.GOOGLE, "Android Browser", tokenList ->
            tokenList.contains(Tokens.APPLE_WEBKIT)
                    && tokenList.contains(Tokens.VERSION)
                    && tokenList.contains(Tokens.ANDROID)
                    && !tokenList.contains(Tokens.CHROME)
                    && !tokenList.contains(Tokens.FIREFOX),
            Tokens.VERSION
    ),
;

    private final Predicate<List<String>> predicate;
    private final String vendor;
    private final String name;
    private final String versionSource;

    OtherIdentifiableMozillaAgent(final String vendor,
                                  final String name,
                                  final Predicate<List<String>> predicate,
                                  final String versionSource) {
        this.vendor = vendor;
        this.name = name;
        this.predicate = predicate;
        this.versionSource = versionSource;
    }

    boolean matches(final TokenizedUserAgent source) {
        final int n = source.getAllTokens().size();
        final List<String> tokenList = Arrays.asList(new String[n]);
        for (int i = 0; i < n; i++) {
            tokenList.set(i, source.getAllTokens().get(i).getValue());
        }
        return predicate.test(tokenList);
    }

    String getVendor() {
        return vendor;
    }

    String getName() {
        return name;
    }

    String getVersionSource() {
        return versionSource;
    }

    static List<OtherIdentifiableMozillaAgent> valuesAsList() {
        return new ArrayList<>(Arrays.asList(OtherIdentifiableMozillaAgent.values()));
    }
}
