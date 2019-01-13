package com.github.codesorcery.juan.token;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

/**
 * Tokenizes user agent strings into {@link VersionedToken}s and holds the results.
 */
public class TokenizedUserAgent {

    private final VersionedToken prefix;
    private final List<List<VersionedToken>> systemTokens;
    private final List<VersionedToken> browserTokens;
    private final List<VersionedToken> allTokens;

    private TokenizedUserAgent(final List<VersionedToken> allTokens,
                               final List<VersionedToken> browserTokens,
                               final List<List<VersionedToken>> systemTokens) {
        this.allTokens = allTokens;
        this.browserTokens = browserTokens;
        this.systemTokens = systemTokens;
        if (allTokens.isEmpty()) {
            prefix = new VersionedToken("", "");
        } else {
            prefix = allTokens.get(0);
        }
        
    }

    /**
     * Tokenize a user agent string.
     *
     * @param userAgentString The user agent string to be tokenized.
     * @return A TokenizedUserAgent entity holding the tokenized user agent string.
     */
    public static TokenizedUserAgent forUserAgentString(final String userAgentString) {
        return Tokenizer.tokenizeUserAgentString(userAgentString);
    }

    /**
     * The first token of the user agent string.
     *
     * @return E.g. ('Mozilla', '5.0') for "Mozilla/5.0 (foo 1.0; bar) foobar/1.0 [foo/2.0; bar/3.0]"
     */
    public VersionedToken getPrefix() {
        return prefix;
    }

    /**
     * The {@link VersionedToken}s contained in the user agent string part
     * which usually holds information about the system.
     *
     * @return E.g. [('foo','1.0'), ('bar','')]
     * for "Mozilla/5.0 (foo 1.0; bar) foobar/1.0 [foo/2.0; bar/3.0]"
     */
    public List<VersionedToken> getSystemTokens() {
        if (systemTokens.isEmpty()) {
            return Collections.emptyList();
        } else {
            return systemTokens.get(0);
        }
    }

    /**
     * The {@link VersionedToken}s contained in allt user agent string parts
     * which may hold information about the system.
     *
     * @return E.g. [[('foo','1.0'), ('bar','')], ['ack', '2.3']]
     * for "Mozilla/5.0 (foo 1.0; bar) foobar/1.0 (ack 2.3) [foo/2.0; bar/3.0]"
     */
    public List<List<VersionedToken>> getAllSystemTokens() {
        return systemTokens;
    }

    /**
     * The {@link VersionedToken}s contained in the user agent string parts
     * which usually holds information about the browser.
     *
     * @return E.g. [('foobar','1.0'), ('foo','2.0'), ('bar'/'3.0')]
     * for "Mozilla/5.0 (foo 1.0; bar) foobar/1.0 [foo/2.0; bar/3.0]"
     */
    public List<VersionedToken> getBrowserTokens() {
        return browserTokens;
    }

    /**
     * The list of all {@link VersionedToken}s parsed from the user agent string (excluding the prefix).
     * I.e. the combined result of {@link #getSystemTokens()} and {@link #getBrowserTokens()}.
     *
     * @return E.g. [('foo','1.0'), ('bar',''), ('foobar','1.0'), ('foo','2.0'), ('bar'/'3.0')]
     * for "Mozilla/5.0 (foo 1.0; bar) foobar/1.0 [foo/2.0; bar/3.0]"
     */
    public List<VersionedToken> getAllTokens() {
        return allTokens;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TokenizedUserAgent.class.getSimpleName() + "[", "]")
                .add("prefixValue='" + prefix.getValue() + "'")
                .add("prefixVersion='" + prefix.getVersion() + "'")
                .add("systemTokens=" + systemTokens)
                .add("browserTokens=" + browserTokens)
                .toString();
    }

    /*
     * Builder
     */

    static class Builder {
        private final List<VersionedToken> allTokens;
        private final List<VersionedToken> browserTokens;
        private final List<List<VersionedToken>> systemTokens;

        Builder() {
            allTokens = new ArrayList<>();
            browserTokens = new ArrayList<>();
            systemTokens = new ArrayList<>();
        }

        void addBrowserToken(final VersionedToken token) {
            allTokens.add(token);
            browserTokens.add(token);
        }


        void addBrowserTokens(final List<VersionedToken> tokens) {
            allTokens.addAll(tokens);
            browserTokens.addAll(tokens);
        }

        void addSystemTokens(final List<VersionedToken> tokens) {
            allTokens.addAll(tokens);
            systemTokens.add(tokens);
        }

        TokenizedUserAgent build() {
            return new TokenizedUserAgent(allTokens, browserTokens, systemTokens);
        }
    }
}
