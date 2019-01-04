package com.github.codesorcery.juan.token;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class TokenizedUserAgent {
    private static final List<String> MOZILLA_STYLE_PREFIXES =
            Arrays.asList("Mozilla", "Dalvik", "Opera");

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
        if (prefixLimiterPos > -1) {
            final int prefixEnd = userAgentString.indexOf(' ', prefixLimiterPos);
            final String prefixValue = userAgentString.substring(0, prefixLimiterPos);
            final String prefixVersion = prefixEnd > -1
                    ? userAgentString.substring(prefixLimiterPos + 1, prefixEnd)
                    : userAgentString.substring(prefixLimiterPos + 1);
            if (MOZILLA_STYLE_PREFIXES.contains(prefixValue)) {
                final int systemOpen = userAgentString.indexOf('(');
                final int systemClose = findMatchingClosingBracket(
                        systemOpen, userAgentString, '(', ')');
                final int additionalOpen = userAgentString.indexOf('[', systemClose);
                final int additionalClose = findMatchingClosingBracket(
                        additionalOpen, userAgentString, '[', ']');
                if (systemClose > -1) {
                    final String systemString =
                            userAgentString.substring(systemOpen + 1, systemClose);
                    if (additionalOpen > -1 && additionalClose > -1) {
                        return new TokenizedUserAgent(
                                prefixValue, prefixVersion,
                                systemString,
                                userAgentString.substring(systemClose + 1, additionalOpen),
                                userAgentString.substring(additionalOpen + 1, additionalClose)
                        );
                    } else {
                        return new TokenizedUserAgent(
                                prefixValue, prefixVersion,
                                systemString,
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
        int separatorPos = -1;
        final int n = subString.length();
        int i = 0;
        while (i < n){
            final char curChar = subString.charAt(i);
            if (curChar == '/') {
                separatorPos = i;
            } else if (curChar == '(') {
                final int closing = findMatchingClosingBracket(i, subString, '(', ')');
                if (closing != -1) {
                    final String value = subString.substring(i, closing + 1);
                    result.add(new VersionedToken(value, ""));
                    i = closing;
                    valueStart = i + 1;
                    separatorPos = -1;
                }
            } else if (curChar == ' ' || i + 1 == n) {
                if (separatorPos != -1) {
                    final String value = subString.substring(valueStart, separatorPos);
                    final String version = subString.substring(separatorPos + 1, i + 1);
                    result.add(new VersionedToken(value, version));
                } else if (i > valueStart) {
                    final String value = subString.substring(valueStart, i);
                    final String version = getNextTokenIfVersion(subString, i + 1);
                    result.add(new VersionedToken(value, version));
                    i += version.length();
                }
                separatorPos = -1;
                valueStart = i + 1;
            }
            i += 1;
        }
        return result;
    }

    private static String getNextTokenIfVersion(final String string, final int start) {
        for (int i = start; i < string.length(); i++) {
            final char curChar = string.charAt(i);
            if (curChar == ' ') {
                return string.substring(start, i);
            } else if (!isPartOfVersionString(curChar)) {
                return "";
            }
        }
        return "";
    }

    private static boolean isPartOfVersionString(final char character) {
        return character == '.' || (character >= '0' && character <= '9');
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
        if (extractionResult.getStart() > -1) {
            return new VersionedToken(
                    tokenString.substring(0, extractionResult.getStart()),
                    tokenString.substring(extractionResult.getStart()).replace('_', '.')
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
