package com.github.codesorcery.juan.device;

import com.github.codesorcery.juan.token.TokenizedUserAgent;
import com.github.codesorcery.juan.token.VersionedToken;
import com.github.codesorcery.juan.util.Tokens;

import java.util.Optional;

import static com.github.codesorcery.juan.util.StringUtils.substringUntil;

abstract class AndroidDeviceLookup implements DeviceLookup {
    private boolean notAndroid(final TokenizedUserAgent tokenizedUserAgent) {
        for (final VersionedToken token : tokenizedUserAgent.getSystemTokens()) {
            if (token.getValue().equals(Tokens.ANDROID)) {
                return false;
            } else if (token.getValue().equals(Tokens.WINDOWS_PHONE)) {
                return true;
            }
        }
        return true;
    }

    @Override
    public Optional<DeviceInfo> getDeviceInfo(final TokenizedUserAgent tokenizedUserAgent) {
        if (notAndroid(tokenizedUserAgent)) {
            return Optional.empty();
        }
        for (final VersionedToken token : tokenizedUserAgent.getSystemTokens()) {
            final String value = token.getValue();
            if (!value.equals(Tokens.ANDROID) && !value.equals(Tokens.LINUX)) {
                final DeviceInfo device = lookup(substringUntil(value, " Build"));
                if (device != null) {
                    return Optional.of(device);
                }
            }
        }
        return Optional.empty();
    }

    abstract DeviceInfo lookup(String value);
}
