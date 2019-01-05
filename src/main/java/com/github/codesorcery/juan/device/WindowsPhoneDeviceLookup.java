package com.github.codesorcery.juan.device;

import com.github.codesorcery.juan.token.TokenizedUserAgent;
import com.github.codesorcery.juan.token.VersionedToken;
import com.github.codesorcery.juan.util.Tokens;

import java.util.Optional;

class WindowsPhoneDeviceLookup implements DeviceLookup {
    @Override
    public Optional<DeviceInfo> getDeviceInfo(final TokenizedUserAgent tokenizedUserAgent) {
        final int n = tokenizedUserAgent.getSystemTokens().size();
        if (n < 4 || !isWindowsPhone(tokenizedUserAgent)) {
            return Optional.empty();
        }
        final VersionedToken vendor = tokenizedUserAgent.getSystemTokens().get(n - 2);
        final VersionedToken name = tokenizedUserAgent.getSystemTokens().get(n - 1);
        return Optional.of(
                new DeviceInfo(
                        vendor.getValue(),
                        name.getVersion().isEmpty()
                                ? name.getValue()
                                : name.getValue() + " " + name.getVersion())
        );
    }

    private boolean isWindowsPhone(final TokenizedUserAgent tokenizedUserAgent) {
        for (final VersionedToken token : tokenizedUserAgent.getSystemTokens()) {
            if (token.getValue().equals(Tokens.WINDOWS_PHONE)) {
                return true;
            }
        }
        return false;
    }
}
