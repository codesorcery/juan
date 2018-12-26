package com.github.codesorcery.juan.os;

import com.github.codesorcery.juan.token.StringToken;
import com.github.codesorcery.juan.token.TokenizedUserAgent;
import com.github.codesorcery.juan.util.OsTypes;
import com.github.codesorcery.juan.util.Vendors;
import com.github.codesorcery.juan.util.VersionExtractor;

public class Windows extends OperatingSystem {
    private static final VersionExtractor VERSION_EXTRACTOR = VersionExtractor.forLimiter('.');

    Windows(final TokenizedUserAgent source) {
        super(Vendors.MICROSOFT, "Windows", extractOsVersion(source),
                OsTypes.DESKTOP, "");
    }

    private static String extractOsVersion(final TokenizedUserAgent source) {
        for (final StringToken t : source.getStringTokens()) {
            if (t.getValue().contains("NT")) {
                return mapOsVersion(
                        VERSION_EXTRACTOR.extract(t.getValue())
                );
            }
        }
        return "";
    }

    private static String mapOsVersion(final String version) {
        switch (version) {
            case "5.0": return "2000";
            case "5.1": case "5.2": return "XP";
            case "6.0": return "Vista";
            case "6.1": return "7";
            case "6.2": return "8";
            case "6.3": return "8.1";
            case "10.0": return "10";
            default: return version;
        }
    }
}
