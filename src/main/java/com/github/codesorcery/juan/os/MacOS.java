package com.github.codesorcery.juan.os;

import com.github.codesorcery.juan.token.TokenizedUserAgent;
import com.github.codesorcery.juan.util.Vendors;

class MacOS extends OperatingSystem {
    MacOS(final TokenizedUserAgent source) {
        super(
                Vendors.APPLE,
                "macOS",
                extractOsVersion(source, t -> t.getValue().contains("Intel")),
                OsType.DESKTOP
        );
    }
}
