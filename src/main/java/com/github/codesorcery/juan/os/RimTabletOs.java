package com.github.codesorcery.juan.os;

import com.github.codesorcery.juan.token.TokenizedUserAgent;
import com.github.codesorcery.juan.util.Vendors;

class RimTabletOs extends OperatingSystem {
    RimTabletOs(final TokenizedUserAgent source) {
        super(
                Vendors.BLACKBERRY,
                "RIM Tablet OS",
                extractOsVersion(source, t -> t.getValue().equals("RIM Tablet OS")),
                OsType.MOBILE
        );
    }
}
