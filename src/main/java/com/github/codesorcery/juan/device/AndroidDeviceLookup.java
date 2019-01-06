package com.github.codesorcery.juan.device;

import com.github.codesorcery.juan.token.TokenizedUserAgent;
import com.github.codesorcery.juan.token.VersionedToken;
import com.github.codesorcery.juan.util.Tokens;

interface AndroidDeviceLookup extends DeviceLookup {
    default boolean notAndroid(final TokenizedUserAgent tokenizedUserAgent) {
        for (final VersionedToken token : tokenizedUserAgent.getSystemTokens()) {
            if (token.getValue().equals(Tokens.ANDROID)) {
                return false;
            } else if (token.getValue().equals(Tokens.WINDOWS_PHONE)) {
                return true;
            }
        }
        return true;
    }
}
