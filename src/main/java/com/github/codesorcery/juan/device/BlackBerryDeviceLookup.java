package com.github.codesorcery.juan.device;

import com.github.codesorcery.juan.token.TokenizedUserAgent;
import com.github.codesorcery.juan.token.VersionedToken;
import com.github.codesorcery.juan.util.Tokens;

import java.util.Optional;

class BlackBerryDeviceLookup implements DeviceLookup {
    @Override
    public Optional<DeviceInfo> getDeviceInfo(TokenizedUserAgent tokenizedUserAgent) {
        // Old devices
        if (tokenizedUserAgent.getPrefixValue().startsWith(Tokens.BLACKBERRY)) {
            return Optional.of(new DeviceInfo(
                    "BlackBerry",
                    tokenizedUserAgent.getPrefixValue().substring(Tokens.BLACKBERRY.length())
            ));
        }
        // Newer devices
        for (final VersionedToken token : tokenizedUserAgent.getSystemTokens()) {
            if (token.getValue().equals(Tokens.BLACKBERRY) && !token.getVersion().isEmpty()) {
                return Optional.of(new DeviceInfo("BlackBerry", token.getVersion()));
            }
        }
        return Optional.empty();
    }
}
