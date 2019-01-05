package com.github.codesorcery.juan.device;

import com.github.codesorcery.juan.token.TokenizedUserAgent;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class CombinedDeviceLookup implements DeviceLookup {
    private final List<DeviceLookup> deviceLists;

    public CombinedDeviceLookup(final DeviceLookup... deviceLists) {
        this.deviceLists = Arrays.asList(deviceLists);
    }

    @Override
    public Optional<DeviceInfo> getDeviceInfo(final TokenizedUserAgent tokenizedUserAgent) {
        for (final DeviceLookup lookup : deviceLists) {
            final Optional<DeviceInfo> deviceInfo = lookup.getDeviceInfo(tokenizedUserAgent);
            if (deviceInfo.isPresent()) {
                return deviceInfo;
            }
        }
        return Optional.empty();
    }
}
