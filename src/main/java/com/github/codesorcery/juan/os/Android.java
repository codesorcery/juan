package com.github.codesorcery.juan.os;

import com.github.codesorcery.juan.token.StringToken;
import com.github.codesorcery.juan.token.TokenizedUserAgent;
import com.github.codesorcery.juan.util.OsTypes;
import com.github.codesorcery.juan.util.Tokens;
import com.github.codesorcery.juan.util.Vendors;
import com.github.codesorcery.juan.util.VersionExtractor;

public class Android extends OperatingSystem {
    private static final VersionExtractor VERSION_EXTRACTOR = VersionExtractor.forLimiter('.');

    Android(final TokenizedUserAgent source) {
        super(Vendors.GOOGLE, "Android", extractOsVersion(source),
                OsTypes.MOBILE, extractDeviceId(source));
    }

    private static String extractOsVersion(final TokenizedUserAgent source) {
        for (final StringToken token : source.getStringTokens()) {
            if (token.getValue().contains(Tokens.ANDROID)) {
                return VERSION_EXTRACTOR.extract(token.getValue());
            }
        }
        return "";
    }

    private static String extractDeviceId(final TokenizedUserAgent source) {
        for (final StringToken token : source.getStringTokens()) {
            final int tokenPos = token.getValue().indexOf("Build");
            if (tokenPos > -1) {
                return token.getValue().substring(0, tokenPos).trim();
            }
        }
        return "";
    }

}
