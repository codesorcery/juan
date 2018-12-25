package com.github.codesorcery.juan.os;

import com.github.codesorcery.juan.token.StringToken;
import com.github.codesorcery.juan.token.TokenizedUserAgent;
import com.github.codesorcery.juan.util.VersionExtractor;

public class IOS extends OperatingSystem {
    private static final VersionExtractor VERSION_EXTRACTOR = VersionExtractor.forLimiter('_');

    private final String osVersion;
    private final String deviceId;

    IOS(final String systemIdentifier, final TokenizedUserAgent source) {
        osVersion = extractOsVersion(source);
        deviceId = systemIdentifier;
    }

    private String extractOsVersion(final TokenizedUserAgent source) {
        for (final StringToken t : source.getStringTokens()) {
            if (t.getValue().contains("CPU")) {
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
        return "iOS";
    }

    @Override
    public String getOsType() {
        return "Mobile";
    }

    @Override
    public String getDeviceId() {
        return deviceId;
    }
}
