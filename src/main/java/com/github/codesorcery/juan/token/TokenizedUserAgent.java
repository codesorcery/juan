package com.github.codesorcery.juan.token;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class TokenizedUserAgent {
    private final String prefixValue;
    private final String prefixVersion;
    private final List<StringToken> stringTokens;
    private final List<VersionedToken> versionedTokens;

    private TokenizedUserAgent(final String prefixValue, final String prefixVersion,
                               final String systemInfoString, final String browserInfoString,
                               final String additionalInfoString) {
        this.prefixValue = prefixValue;
        this.prefixVersion = prefixVersion;
        this.stringTokens = extractSystemInfo(systemInfoString);
        this.versionedTokens = extractBrowserInfo(browserInfoString);
        this.versionedTokens.addAll(extractAdditionalInfo(additionalInfoString));
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
                final int systemClose = userAgentString.indexOf(')', systemOpen);
                final int additionalOpen = userAgentString.indexOf('[', systemClose);
                final int additionalClose = userAgentString.indexOf(']', additionalOpen);
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

    private static List<StringToken> extractSystemInfo(final String subString) {
        final List<StringToken> result = new ArrayList<>();
        final int n = subString.length();
        int start = 0;
        for (int i = 0; i < n; i++) {
            final char curChar = subString.charAt(i);
            if (curChar == ';') {
                result.add(new StringToken(subString.substring(start, i)));
                start = i + 1;
            }
        }
        if (start < n) {
            result.add(new StringToken(subString.substring(start, n)));
        }
        return result;
    }

    private static List<VersionedToken> extractAdditionalInfo(final String subString) {
        final List<VersionedToken> result = new ArrayList<>();
        int valueStart = 0;
        int valueEnd = -1;
        int versionStart = -1;
        final int n = subString.length();
        for (int i = 0; i < n; i++) {
            final char curChar = subString.charAt(i);
            if (curChar == '/' && i + 1 < n) {
                valueEnd = i;
                versionStart = i + 1;
            } else if ((curChar == ';' || i + 1 == n) && valueEnd >= 0 && versionStart >= 0) {
                final String value = subString.substring(valueStart, valueEnd);
                final String version = subString.substring(versionStart, curChar == ';' ? i : i + 1);
                result.add(new VersionedToken(value, version));
                valueStart = i + 1;
                valueEnd = -1;
                versionStart = -1;
            }
        }
        return result;
    }

    public String getPrefixValue() {
        return prefixValue;
    }

    public String getPrefixVersion() {
        return prefixVersion;
    }

    public List<StringToken> getStringTokens() {
        return stringTokens;
    }

    public List<VersionedToken> getVersionedTokens() {
        return versionedTokens;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TokenizedUserAgent.class.getSimpleName() + "[", "]")
                .add("prefixValue='" + prefixValue + "'")
                .add("prefixVersion='" + prefixVersion + "'")
                .add("stringTokens=" + stringTokens)
                .add("versionedTokens=" + versionedTokens)
                .toString();
    }
}
