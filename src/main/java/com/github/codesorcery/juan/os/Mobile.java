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
                    return new OsInfo(Vendors.MICROSOFT, "Windows Phone", token.getVersion());
                case "KAIOS":
                    return new OsInfo("KaiOS Technologies Inc.", "KaiOS", token.getVersion());
                case Tokens.FIREFOX:
                    if (source.getBrowserTokens().size() == 2) {
                        return new OsInfo(Vendors.MOZILLA, "Firefox OS", token.getVersion());
                    }
                default:
            }
        }
        return new OsInfo("", "", "");
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
