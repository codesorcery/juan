package com.github.codesorcery.juan.os;

import com.github.codesorcery.juan.token.TokenizedUserAgent;
import com.github.codesorcery.juan.token.VersionedToken;
import com.github.codesorcery.juan.util.Tokens;
import com.github.codesorcery.juan.util.Vendors;

class Windows extends OperatingSystem {

    Windows(final TokenizedUserAgent source) {
        super(Vendors.MICROSOFT, "Windows", extractOsVersion(source), OsType.DESKTOP);
    }

    private static String extractOsVersion(final TokenizedUserAgent source) {
        for (final VersionedToken t : source.getSystemTokens()) {
            if (t.getValue().equals(Tokens.WINDOWS_NT)) {
                return mapOsVersion(t.getVersion());
            }
        }
        return "";
    }

    private static String mapOsVersion(final String version) {
        switch (version) {
            case "5.0":
                return "2000";
            case "5.1":
            case "5.2":
                return "XP";
            case "6.0":
                return "Vista";
            case "6.1":
                return "7";
            case "6.2":
                return "8";
            case "6.3":
                return "8.1";
            case "10.0":
                return "10";
            default:
                return version;
        }
    }
}
