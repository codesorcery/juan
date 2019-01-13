package com.github.codesorcery.juan.token;

import java.util.ArrayList;
import java.util.List;

class Tokenizer {
    private Tokenizer() {}

    static TokenizedUserAgent tokenizeUserAgentString(final String userAgentString) {
        final TokenizedUserAgent.Builder builder = new TokenizedUserAgent.Builder();
        extractTokens(userAgentString, builder);
        return builder.build();
    }

    private static void extractTokens(final String userAgentString,
                                      final TokenizedUserAgent.Builder builder) {
        int valueStart = 0;
        int separatorPos = -1;
        final int n = userAgentString.length();
        int i = 0;
        while (i < n) {
            final char curChar = userAgentString.charAt(i);
            if (curChar == '/') {
                separatorPos = i;
            } else if (curChar == '(') {
                final int closing = findMatchingClosingBracket(i, userAgentString, '(', ')');
                if (closing != -1) {
                    final String value = userAgentString.substring(i + 1, closing);
                    builder.addSystemTokens(extractSemicolonSeparated(value));
                    i = closing;
                    valueStart = i + 1;
                    separatorPos = -1;
                }
            } else if (curChar == '[') {
                final int closing = findMatchingClosingBracket(i, userAgentString, '[', ']');
                if (closing != -1) {
                    final String value = userAgentString.substring(i + 1, closing);
                    builder.addBrowserTokens(extractSemicolonSeparated(value));
                    i = closing;
                    valueStart = i + 1;
                    separatorPos = -1;
                }
                
            } else if (curChar == ' ' || i + 1 == n) {
                i += extractBrowserToken(userAgentString, i, separatorPos, valueStart, builder);
                separatorPos = -1;
                valueStart = i + 1;
            }
            i += 1;
        }
    }

    private static int extractBrowserToken(final String userAgentString, final int currentPos,
                                           final int separatorPos, final int valueStart,
                                           final TokenizedUserAgent.Builder builder) {
        if (separatorPos != -1) {
            final String value = userAgentString.substring(valueStart, separatorPos);
            final String version = userAgentString.substring(separatorPos + 1, currentPos + 1);
            builder.addBrowserToken(new VersionedToken(value, version));
            return 0;
        } else if (currentPos > valueStart) {
            final String value = userAgentString.substring(valueStart, currentPos + 1);
            final String version = getNextTokenIfVersion(userAgentString, currentPos + 1);
            builder.addBrowserToken(new VersionedToken(value, version));
            return version.length();
        }
        return 0;
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
                open++;
            } else if (curChar == closing) {
                if (open == 0) {
                    return i;
                } else {
                    open--;
                }
            }
        }
        return -1;
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
        final int start = VersionExtractor.versionPos(tokenString);
        if (start > -1) {
            return new VersionedToken(
                    tokenString.substring(0, start),
                    tokenString.substring(start).replace('_', '.')
            );
        }
        return new VersionedToken(tokenString, "");
    }
}
