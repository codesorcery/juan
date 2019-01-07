package com.github.codesorcery.juan.os;

import com.github.codesorcery.juan.token.TokenizedUserAgent;
import com.github.codesorcery.juan.util.Vendors;

import static com.github.codesorcery.juan.util.StringUtils.substringUntil;

class IOS extends OperatingSystem {
    IOS(final TokenizedUserAgent source) {
        super(
                Vendors.APPLE,
                "iOS",
                substringUntil(extractOsVersion(source, t -> t.getValue().startsWith("CPU")),
                        " like Mac OS X"),
                OsType.MOBILE
        );
    }
}
