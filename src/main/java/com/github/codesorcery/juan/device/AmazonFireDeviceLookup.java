package com.github.codesorcery.juan.device;

import com.github.codesorcery.juan.token.TokenizedUserAgent;
import com.github.codesorcery.juan.token.VersionedToken;
import com.github.codesorcery.juan.util.Vendors;

import java.util.Map;
import java.util.Optional;

class AmazonFireDeviceLookup implements DeviceLookup {
    private static final Map<String, String> AMAZON_FIRE_DEVICES = AmazonFireDevice.valuesAsMap();

    @Override
    public Optional<DeviceInfo> getDeviceInfo(final TokenizedUserAgent tokenizedUserAgent) {
        for (final VersionedToken token : tokenizedUserAgent.getSystemTokens()) {
            final String name = AMAZON_FIRE_DEVICES.get(token.getValue().replace(" Build", ""));
            if (name != null) {
                return Optional.of(new DeviceInfo(Vendors.AMAZON, name));
            }
        }
        return Optional.empty();
    }
}
