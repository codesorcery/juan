package com.github.codesorcery.juan.os;

import com.github.codesorcery.juan.token.StringToken;
import com.github.codesorcery.juan.token.TokenizedUserAgent;
import com.github.codesorcery.juan.util.VersionExtractor;

public class MacOS extends OperatingSystem {
    private static final VersionExtractor VERSION_EXTRACTOR = VersionExtractor.forLimiter('_');

    private final String osVersion;

    MacOS(final TokenizedUserAgent source) {
        osVersion = extractOsVersion(source);
    }

    private String extractOsVersion(final TokenizedUserAgent source) {
        for (final StringToken t : source.getStringTokens()) {
            if (t.getValue().contains("Intel")) {
                return VERSION_EXTRACTOR.extract(t.getValue()).replace("_", ".");
            }
        }
        return "";
    }

    @Override
    public String getOsVendor() {
        return "Apple";
    }

    @Override
    public String getOsVersion() {
        return osVersion;
    }

    @Override
    public String getOsName() {
        return "macOS";
    }

    @Override
    public String getOsType() {
        return "Desktop";
    }

    @Override
    public String getDeviceId() {
        return "";
    }
}
