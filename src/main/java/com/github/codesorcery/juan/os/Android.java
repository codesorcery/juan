package com.github.codesorcery.juan.os;

import com.github.codesorcery.juan.token.TokenizedUserAgent;
import com.github.codesorcery.juan.token.VersionedToken;
import com.github.codesorcery.juan.util.Tokens;
import com.github.codesorcery.juan.util.Vendors;

public class Android extends OperatingSystem {
    Android(final TokenizedUserAgent source) {
        super(Vendors.GOOGLE, "Android", extractOsVersion(source), OsType.MOBILE);
    }

    private static String extractOsVersion(final TokenizedUserAgent source) {
        for (final VersionedToken token : source.getSystemTokens()) {
            if (token.getValue().contains(Tokens.ANDROID)) {
                return token.getVersion();
            }
        }
        return "";
    }

}
