package com.github.codesorcery.juan.agent;

import com.github.codesorcery.juan.token.StringToken;
import com.github.codesorcery.juan.token.TokenizedUserAgent;
import com.github.codesorcery.juan.token.VersionedToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public enum OtherIdentifiableMozillaAgent {
    CHROME("Google", "Chrome", tokenList ->
            tokenList.contains("AppleWebKit")
                    && tokenList.contains("Chrome")
                    && !tokenList.contains("Edge")
                    && !tokenList.contains("Gecko"),
            "Chrome"
    ),
    FIREFOX("Mozilla", "Firefox", tokenList ->
            tokenList.contains("Gecko")
                    && tokenList.contains("Firefox"),
            "Firefox"
    ),
    SAFARI("Apple", "Safari", tokenList ->
            tokenList.contains("Safari")
                && tokenList.contains("Version")
                && !tokenList.contains("Mobile")
                && !tokenList.contains("Chrome")
                && !tokenList.contains("Firefox"),
            tokenizedUA -> !containsStartingWith(tokenizedUA.getStringTokens(), "Android"),
            "Version"
    ),
    EDGE("Microsoft", "Edge", tokenList ->
            tokenList.contains("Edge")
                    && !tokenList.contains("Mobile Safari"),
            "Edge"
    ),
    EDGE_MOBILE("Microsoft", "Edge Mobile", tokenList ->
            tokenList.contains("Edge")
                    && tokenList.contains("Mobile Safari"),
            "Edge"
    ),
    CHROME_MOBILE("Google", "Chrome Mobile", tokenList ->
            tokenList.contains("Chrome")
                    && tokenList.contains("Mobile Safari")
                    && !tokenList.contains("Edge"),
            "Chrome"
    ),
    SAFARI_MOBILE("Apple", "Safari Mobile", tokenList ->
            tokenList.contains("Safari")
                    && tokenList.contains("Version")
                    && tokenList.contains("Mobile")
                    && !tokenList.contains("Chrome")
                    && !tokenList.contains("Firefox"),
            "Version"
    ),
    ANDROID_BROWSER("Google", "Android Browser", tokenList ->
            tokenList.contains("AppleWebKit")
                    && tokenList.contains("Version")
                    && !tokenList.contains("Chrome")
                    && !tokenList.contains("Firefox"),
            tokenizedUA -> containsStartingWith(tokenizedUA.getStringTokens(), "Android"),
            "Version"
    ),
;

    private final Predicate<List<String>> predicate;
    private final Predicate<TokenizedUserAgent> additionalPredicate;
    private final String vendor;
    private final String name;
    private final String versionSource;

    OtherIdentifiableMozillaAgent(final String vendor,
                                  final String name,
                                  final Predicate<List<String>> predicate,
                                  final String versionSource) {
        this(vendor, name, predicate, x -> true, versionSource);
    }

    OtherIdentifiableMozillaAgent(final String vendor,
                                  final String name,
                                  final Predicate<List<String>> predicate,
                                  final Predicate<TokenizedUserAgent> additionalPredicate,
                                  final String versionSource) {
        this.vendor = vendor;
        this.name = name;
        this.predicate = predicate;
        this.additionalPredicate = additionalPredicate;
        this.versionSource = versionSource;
    }

    boolean matches(final TokenizedUserAgent source) {
        final List<String> tokenList = new ArrayList<>();
        for (final VersionedToken token : source.getVersionedTokens()) {
            tokenList.add(token.getValue());
        }
        return predicate.test(tokenList) && additionalPredicate.test(source);
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

    /* Utility functions */
    private static boolean containsStartingWith(final List<StringToken> tokenList,
                                                final String value) {
        for (final StringToken token : tokenList) {
            if (token.getValue().startsWith(value)) {
                return true;
            }
        }
        return false;
    }

    static List<OtherIdentifiableMozillaAgent> valuesAsList() {
        return new ArrayList<>(Arrays.asList(OtherIdentifiableMozillaAgent.values()));
    }
}
