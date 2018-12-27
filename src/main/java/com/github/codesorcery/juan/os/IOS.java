package com.github.codesorcery.juan.os;

import com.github.codesorcery.juan.token.TokenizedUserAgent;
import com.github.codesorcery.juan.token.VersionedToken;
import com.github.codesorcery.juan.util.OsTypes;
import com.github.codesorcery.juan.util.Vendors;

public class IOS extends OperatingSystem {
    IOS(final TokenizedUserAgent source) {
        super(Vendors.APPLE, "iOS", extractOsVersion(source), OsTypes.MOBILE);
    }

    private static String extractOsVersion(final TokenizedUserAgent source) {
        for (final VersionedToken t : source.getSystemTokens()) {
            if (t.getValue().contains("CPU")) {
                return t.getVersion();
            }
        }
        return "";
    }
}
