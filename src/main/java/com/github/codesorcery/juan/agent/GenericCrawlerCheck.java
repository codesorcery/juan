package com.github.codesorcery.juan.agent;

import com.github.codesorcery.juan.token.TokenizedUserAgent;
import com.github.codesorcery.juan.token.VersionedToken;

import java.util.List;

class GenericCrawlerCheck {
    private static final String[] EXCEPTIONS = {
            "w3.org"
    };

    private GenericCrawlerCheck() {}

    static VersionedToken check(final TokenizedUserAgent tokenizedUserAgent) {
        for (final List<VersionedToken> list : tokenizedUserAgent.getAllSystemTokens()) {
            for (final VersionedToken token : list) {
                if (token.getValue().startsWith("+http") || token.getValue().startsWith("http")) {
                    if (inExceptions(token.getValue())) {
                        return null;
                    } else if (list.get(0).getValue().equals("compatible")) {
                        return list.get(1);
                    } else {
                        return tokenizedUserAgent.getPrefix();
                    }
                }
            }
        }
        return null;
    }

    private static boolean inExceptions(final String url) {
        for (final String exception : EXCEPTIONS) {
            if (url.contains(exception)) {
                return true;
            }
        }
        return false;
    }
}
