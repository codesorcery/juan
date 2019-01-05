package com.github.codesorcery.juan.device;

import com.github.codesorcery.juan.token.TokenizedUserAgent;
import com.github.codesorcery.juan.token.VersionedToken;

import java.util.List;
import java.util.Map;
import java.util.Optional;

class DirectlyIdentifiableDeviceLookup implements DeviceLookup {
    private static final Map<String, DirectlyIdentifiableDevice>
            DIRECTLY_IDENTIFIABLE_DEVICE_MAP = DirectlyIdentifiableDevice.valuesAsMap();

    @Override
    public Optional<DeviceInfo> getDeviceInfo(TokenizedUserAgent tokenizedUserAgent) {
        final List<VersionedToken> tokens = tokenizedUserAgent.getSystemTokens();
        if (!tokens.isEmpty()) {
            final String deviceIdentifier = tokens.get(0).getValue();
            final DirectlyIdentifiableDevice device =
                    DIRECTLY_IDENTIFIABLE_DEVICE_MAP.get(deviceIdentifier);
            if (device != null) {
                return Optional.of(new DeviceInfo(device.getVendor(), device.getName()));
            }
        }
        return Optional.empty();
    }
}
