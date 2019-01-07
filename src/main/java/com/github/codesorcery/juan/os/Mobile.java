package com.github.codesorcery.juan.os;

import com.github.codesorcery.juan.token.TokenizedUserAgent;
import com.github.codesorcery.juan.token.VersionedToken;
import com.github.codesorcery.juan.util.Tokens;
import com.github.codesorcery.juan.util.Vendors;

class Mobile extends OperatingSystem {
    Mobile(final TokenizedUserAgent source) {
        this(extractOsInfo(source));
    }

    private Mobile(final OsInfo osInfo) {
        super(osInfo.vendor, osInfo.name, osInfo.version, OsType.MOBILE);
    }

    private static OsInfo extractOsInfo(final TokenizedUserAgent source) {
        for (final VersionedToken token : source.getAllTokens()) {
            switch (token.getValue()) {
                case Tokens.WINDOWS_PHONE:
                    return new OsInfo(Vendors.MICROSOFT, "Windows Phone",
                            extractVersion(source, Tokens.WINDOWS_PHONE));
                case "KAIOS":
                    return new OsInfo("KaiOS Technologies Inc.", "KaiOS",
                            extractVersion(source, "KAIOS"));
                case Tokens.FIREFOX:
                    if (source.getBrowserTokens().size() == 2) {
                        return new OsInfo(Vendors.MOZILLA, "Firefox OS",
                                extractVersion(source, Tokens.FIREFOX));
                    }
                default:
            }
        }
        return new OsInfo("", "", "");
    }

    private static String extractVersion(final TokenizedUserAgent source, final String value) {
        for (final VersionedToken t : source.getAllTokens()) {
            if (t.getValue().equals(value)) {
                return t.getVersion();
            }
        }
        return "";
    }

    private static class OsInfo {
        private final String vendor;
        private final String name;
        private final String version;

        private OsInfo(final String vendor, final String name, final String version) {
            this.vendor = vendor;
            this.name = name;
            this.version = version;
        }
    }
}
