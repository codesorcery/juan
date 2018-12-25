package com.github.codesorcery.juan.os;

import com.github.codesorcery.juan.token.TokenizedUserAgent;
import com.github.codesorcery.juan.token.VersionedToken;

import java.util.ArrayList;
import java.util.List;

public class Mobile extends OperatingSystem {
    private final String osVersion;
    private final String osName;

    public Mobile(final TokenizedUserAgent source) {
        final VersionedToken osInfo = extractOsInfo(source);
        this.osVersion = osInfo.getVersion();
        this.osName = osInfo.getValue();
    }

    private VersionedToken extractOsInfo(final TokenizedUserAgent source) {
        final List<String> tokenValues = new ArrayList<>();
        for (final VersionedToken token : source.getVersionedTokens()) {
            tokenValues.add(token.getValue());
        }
        if (tokenValues.contains("KAIOS")) {
            return new VersionedToken("KaiOS", extractVersion(source, "KAIOS"));
        } else if (tokenValues.contains("Gecko") && tokenValues.contains("Firefox")) {
            return new VersionedToken("Firefox OS", extractVersion(source, "Firefox"));
        } else {
            return VersionedToken.empty();
        }
    }

    private String extractVersion(final TokenizedUserAgent source, final String value) {
        for (final VersionedToken t : source.getVersionedTokens()) {
            if (t.getValue().equals(value)) {
                return t.getVersion();
            }
        }
        return "";
    }

    @Override
    public String getOsVendor() {
        return "";
    }

    @Override
    public String getOsVersion() {
        return osVersion;
    }

    @Override
    public String getOsName() {
        return osName;
    }

    @Override
    public String getOsType() {
        return "Mobile";
    }

    @Override
    public String getDeviceId() {
        return "";
    }
}
