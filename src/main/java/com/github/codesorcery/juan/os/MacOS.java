package com.github.codesorcery.juan.os;

import com.github.codesorcery.juan.token.StringToken;
import com.github.codesorcery.juan.token.TokenizedUserAgent;
import com.github.codesorcery.juan.util.OsTypes;
import com.github.codesorcery.juan.util.Vendors;
import com.github.codesorcery.juan.util.VersionExtractor;

public class MacOS extends OperatingSystem {
    private static final VersionExtractor VERSION_EXTRACTOR = VersionExtractor.forLimiter('_');

    MacOS(final TokenizedUserAgent source) {
        super(Vendors.APPLE, "macOS", extractOsVersion(source),
                OsTypes.DESKTOP, "");
    }

    private static String extractOsVersion(final TokenizedUserAgent source) {
        for (final StringToken t : source.getStringTokens()) {
            if (t.getValue().contains("Intel")) {
                return VERSION_EXTRACTOR.extract(t.getValue())
                        .replace("_", ".");
            }
        }
        return "";
    }
}
