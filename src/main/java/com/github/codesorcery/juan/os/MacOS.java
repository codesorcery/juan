package com.github.codesorcery.juan.os;

import com.github.codesorcery.juan.token.TokenizedUserAgent;
import com.github.codesorcery.juan.token.VersionedToken;
import com.github.codesorcery.juan.util.OsTypes;
import com.github.codesorcery.juan.util.Vendors;

public class MacOS extends OperatingSystem {

    MacOS(final TokenizedUserAgent source) {
        super(Vendors.APPLE, "macOS", extractOsVersion(source), OsTypes.DESKTOP);
    }

    private static String extractOsVersion(final TokenizedUserAgent source) {
        for (final VersionedToken t : source.getSystemTokens()) {
            if (t.getValue().contains("Intel")) {
                return t.getVersion();
            }
        }
        return "";
    }
}
