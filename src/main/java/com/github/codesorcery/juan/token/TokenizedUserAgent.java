package com.github.codesorcery.juan.token;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class TokenizedUserAgent {
    private final String prefixValue;
    private final String prefixVersion;
    private final List<VersionedToken> systemTokens;
    private final List<VersionedToken> browserTokens;
    private final List<VersionedToken> allTokens;

    private TokenizedUserAgent(final String prefixValue, final String prefixVersion,
                               final String systemInfoString, final String browserInfoString,
                               final String additionalInfoString) {
        this.prefixValue = prefixValue;
        this.prefixVersion = prefixVersion;
        this.systemTokens = extractSemicolonSeparated(systemInfoString);
        browserTokens = extractBrowserInfo(browserInfoString);
        browserTokens.addAll(extractSemicolonSeparated(additionalInfoString));
        allTokens = new ArrayList<>();
        allTokens.addAll(systemTokens);
        allTokens.addAll(browserTokens);
    }

    public static TokenizedUserAgent forUserAgentString(final String userAgentString) {
        final int prefixLimiterPos = userAgentString.indexOf('/');
        final int prefixEnd = prefixLimiterPos > -1 ? userAgentString.indexOf(' ', prefixLimiterPos) : -1;
        if (prefixLimiterPos > -1) {
            final String prefixValue = userAgentString.substring(0, prefixLimiterPos);
            final String prefixVersion = prefixEnd > -1
                    ? userAgentString.substring(prefixLimiterPos + 1, prefixEnd)
                    : userAgentString.substring(prefixLimiterPos + 1);
            if (prefixValue.equals("Mozilla")
                    || prefixValue.equals("Dalvik")
                    || prefixValue.equals("Opera")) {
                final int systemOpen = userAgentString.indexOf('(');
                final int systemClose = findMatchingClosingBracket(
                        systemOpen, userAgentString, '(', ')');
                final int additionalOpen = userAgentString.indexOf('[', systemClose);
                final int additionalClose = findMatchingClosingBracket(
                        additionalOpen, userAgentString, '[', ']');
                if (systemClose > -1) {
                    if (additionalOpen > -1 && additionalClose > -1) {
                        return new TokenizedUserAgent(
                                prefixValue, prefixVersion,
                                userAgentString.substring(systemOpen + 1, systemClose),
                                userAgentString.substring(systemClose + 1, additionalOpen),
                                userAgentString.substring(additionalOpen + 1, additionalClose)
                        );
                    } else {
                        return new TokenizedUserAgent(
                                prefixValue, prefixVersion,
                                userAgentString.substring(systemOpen + 1, systemClose),
                                userAgentString.substring(systemClose + 1),
                                ""
                        );
                    }
                }
            } else {
                return new TokenizedUserAgent(prefixValue, prefixVersion, "",
                        userAgentString, "");
            }
        }
        return new TokenizedUserAgent("", "", "",
                userAgentString, "");
    }

    private static int findMatchingClosingBracket(final int pos,
                                                  final String string,
                                                  final char opening,
                                                  final char closing) {
        if (pos == -1) {
            return -1;
        }
        int open = 0;
        for (int i = pos + 1; i < string.length(); i++) {
            final char curChar = string.charAt(i);
            if (curChar == opening) {
                open ++;
            } else if (curChar == closing) {
                if (open == 0) {
                    return i;
                } else {
                    open --;
                }
            }
        }
        return -1;
    }

    private static List<VersionedToken> extractBrowserInfo(final String subString) {
        final List<VersionedToken> result = new ArrayList<>();
        int valueStart = 0;
        int valueEnd = -1;
        int versionStart = -1;
        final int n = subString.length();
        for (int i = 0; i < n; i++) {
            final char curChar = subString.charAt(i);
            if (curChar == '/' && i + 1 < n && charIsNumber(subString, i + 1)) {
                valueEnd = i;
                versionStart = i + 1;
            } else if (curChar == ')') {
                valueStart = i + 1;
            } else if ((curChar == ' ' || i + 1 == n) && valueEnd >= 0 && versionStart >= 0) {
                final String value = subString.substring(valueStart, valueEnd);
                final String version = subString.substring(versionStart, i + 1);
                result.add(new VersionedToken(value, version));
                valueStart = i + 1;
                valueEnd = -1;
                versionStart = -1;
            }
        }
        return result;
    }

    private static boolean charIsNumber(final CharSequence string, final int position) {
        final char charAtPos = string.charAt(position);
        return charAtPos >= '0' && charAtPos <= '9';
    }

    private static List<VersionedToken> extractSemicolonSeparated(final String subString) {
        final List<VersionedToken> result = new ArrayList<>();
        int valueStart = 0;
        final int n = subString.length();
        for (int i = 0; i < n; i++) {
            final char curChar = subString.charAt(i);
            if (curChar == ';') {
                result.add(extractToken(subString.substring(valueStart, i)));
                valueStart = i + 1;
            }
        }
        if (valueStart != n) {
            result.add(extractToken(subString.substring(valueStart)));
        }
        return result;
    }

    private static final VersionExtractor VERSION_EXTRACTOR = VersionExtractor.forLimiter('.', '_');

    private static VersionedToken extractToken(final String tokenString) {
        final int n = tokenString.length();
        int separatorPos = tokenString.lastIndexOf('/');
        if (separatorPos > 0 && separatorPos < n - 1) {
            return new VersionedToken(
                    tokenString.substring(0, separatorPos),
                    tokenString.substring(separatorPos + 1)
            );
        }
        separatorPos = tokenString.lastIndexOf(':');
        if (separatorPos > 0 && separatorPos < n - 1) {
            return new VersionedToken(
                    tokenString.substring(0, separatorPos),
                    tokenString.substring(separatorPos + 1)
            );
        }
        final VersionExtractor.ExtractionResult extractionResult =
                VERSION_EXTRACTOR.extract(tokenString);
        if (extractionResult.getPos() > -1) {
            return new VersionedToken(
                    tokenString.substring(0, extractionResult.getPos()),
                    extractionResult.getValue()
            );
        }
        return new VersionedToken(tokenString, "");
    }

    public String getPrefixValue() {
        return prefixValue;
    }

    public String getPrefixVersion() {
        return prefixVersion;
    }

    public List<VersionedToken> getSystemTokens() {
        return systemTokens;
    }

    public List<VersionedToken> getBrowserTokens() {
        return browserTokens;
    }

    public List<VersionedToken> getAllTokens() {
        return allTokens;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TokenizedUserAgent.class.getSimpleName() + "[", "]")
                .add("prefixValue='" + prefixValue + "'")
                .add("prefixVersion='" + prefixVersion + "'")
                .add("systemTokens=" + systemTokens)
                .add("browserTokens=" + browserTokens)
                .toString();
    }
}
