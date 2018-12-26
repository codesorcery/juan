package com.github.codesorcery.juan.os;

import com.github.codesorcery.juan.token.TokenizedUserAgent;
import com.github.codesorcery.juan.token.VersionedToken;
import com.github.codesorcery.juan.util.OsTypes;
import com.github.codesorcery.juan.util.Tokens;

import java.util.ArrayList;
import java.util.List;

public class Mobile extends OperatingSystem {
    Mobile(final TokenizedUserAgent source) {
        this(extractOsInfo(source));
    }

    private Mobile(final VersionedToken osInfo) {
        super("", osInfo.getValue(), osInfo.getVersion(),
                OsTypes.MOBILE, "");
    }

    private static VersionedToken extractOsInfo(final TokenizedUserAgent source) {
        final List<String> tokenValues = new ArrayList<>();
        for (final VersionedToken token : source.getVersionedTokens()) {
            tokenValues.add(token.getValue());
        }
        if (tokenValues.contains("KAIOS")) {
            return new VersionedToken("KaiOS", extractVersion(source, "KAIOS"));
        } else if (tokenValues.contains(Tokens.GECKO) && tokenValues.contains(Tokens.FIREFOX)) {
            return new VersionedToken("Firefox OS", extractVersion(source, Tokens.FIREFOX));
        } else {
            return VersionedToken.empty();
        }
    }

    private static String extractVersion(final TokenizedUserAgent source, final String value) {
        for (final VersionedToken t : source.getVersionedTokens()) {
            if (t.getValue().equals(value)) {
                return t.getVersion();
            }
        }
        return "";
    }
}
