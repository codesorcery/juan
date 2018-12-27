package com.github.codesorcery.juan.device;

import com.github.codesorcery.juan.token.StringToken;
import com.github.codesorcery.juan.token.TokenizedUserAgent;
import com.github.codesorcery.juan.util.Vendors;

import java.util.Optional;

public class SimpleAmazonFireLookup implements DeviceLookup {
    @Override
    public Optional<DeviceInfo> getDeviceInfo(final TokenizedUserAgent tokenizedUserAgent) {
        for (final StringToken token : tokenizedUserAgent.getSystemTokens()) {
            final String value = token.getValue();
            if (value.startsWith("KF")) {
                return Optional.of(new DeviceInfo(Vendors.AMAZON, "Kindle Fire Tablet"));
            } else if (value.startsWith("AF")) {
                return Optional.of(new DeviceInfo(Vendors.AMAZON, "Fire TV"));
            }
        }
        return Optional.empty();
    }
}
