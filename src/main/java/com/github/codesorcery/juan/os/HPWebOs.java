package com.github.codesorcery.juan.os;

import com.github.codesorcery.juan.token.TokenizedUserAgent;

class HPWebOs extends OperatingSystem {
    HPWebOs(final TokenizedUserAgent source) {
        super(
                "HP",
                "WebOS",
                extractOsVersion(source, t -> t.getValue().equals("hpwOS")),
                OsType.MOBILE
        );
    }
}
