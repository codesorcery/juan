package com.github.codesorcery.juan.os;

import com.github.codesorcery.juan.token.StringToken;
import com.github.codesorcery.juan.token.TokenizedUserAgent;
import com.github.codesorcery.juan.util.VersionExtractor;

public class Android extends OperatingSystem {
    private static final VersionExtractor VERSION_EXTRACTOR = VersionExtractor.forLimiter('.');

    private final String osVersion;
    private final String deviceId;

    Android(final TokenizedUserAgent source) {
        osVersion = extractOsVersion(source);
        deviceId = extractDeviceId(source);
    }

    private String extractOsVersion(final TokenizedUserAgent source) {
        for (final StringToken token : source.getStringTokens()) {
            if (token.getValue().contains("Android")) {
                return VERSION_EXTRACTOR.extract(token.getValue());
            }
        }
        return "";
    }

    private String extractDeviceId(final TokenizedUserAgent source) {
        for (final StringToken token : source.getStringTokens()) {
            final int tokenPos = token.getValue().indexOf("Build");
            if (tokenPos > -1) {
                return token.getValue().substring(0, tokenPos).trim();
            }
        }
        return "";
    }

    @Override
    public String getOsVendor() {
        return "Google";
    }

    @Override
    public String getOsVersion() {
        return osVersion;
    }

    @Override
    public String getOsName() {
        return "Android";
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
