package com.github.codesorcery.juan.device;

import com.github.codesorcery.juan.token.StringToken;
import com.github.codesorcery.juan.token.TokenizedUserAgent;
import com.github.codesorcery.juan.util.Vendors;

import java.util.Optional;

public class SimpleAppleLookup implements DeviceLookup {
    @Override
    public Optional<DeviceInfo> getDeviceInfo(final TokenizedUserAgent tokenizedUserAgent) {
        for (final StringToken token : tokenizedUserAgent.getSystemTokens()) {
            final String value = token.getValue();
            if (value.equals("iPad") || value.equals("iPhone")) {
                return Optional.of(new DeviceInfo(Vendors.APPLE, value));
            }
        }
        return Optional.empty();
    }
}
