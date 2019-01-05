package com.github.codesorcery.juan.os;

import com.github.codesorcery.juan.token.TokenizedUserAgent;
import com.github.codesorcery.juan.util.Vendors;

class IOS extends OperatingSystem {
    IOS(final TokenizedUserAgent source) {
        super(
                Vendors.APPLE,
                "iOS",
                extractOsVersion(source, t -> t.getValue().contains("CPU"))
                        .replace(" like Mac OS X", ""),
                OsType.MOBILE
        );
    }
}
