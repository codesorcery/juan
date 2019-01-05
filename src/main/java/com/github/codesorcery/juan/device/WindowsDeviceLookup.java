package com.github.codesorcery.juan.device;

import com.github.codesorcery.juan.token.TokenizedUserAgent;
import com.github.codesorcery.juan.token.VersionedToken;
import com.github.codesorcery.juan.util.Tokens;
import com.github.codesorcery.juan.util.Vendors;

import java.util.Optional;

class WindowsDeviceLookup implements DeviceLookup {
    @Override
    public Optional<DeviceInfo> getDeviceInfo(TokenizedUserAgent tokenizedUserAgent) {
        boolean isWindows = false;
        for (final VersionedToken token : tokenizedUserAgent.getSystemTokens()) {
            switch (token.getValue()) {
                case "Xbox One":
                    return Optional.of(new DeviceInfo(Vendors.MICROSOFT, token.getValue()));
                case Tokens.WINDOWS:
                case Tokens.WINDOWS_NT:
                    isWindows = true;
                    continue;
                default:
            }

        }
        if (isWindows) {
            return Optional.of(new DeviceInfo("", "Windows PC"));
        } else {
            return Optional.empty();
        }
    }
}
