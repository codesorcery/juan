package com.github.codesorcery.juan.device;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CombinedDeviceLookup implements DeviceLookup {
    private final List<DeviceLookup> deviceLists;

    public CombinedDeviceLookup(final DeviceLookup... deviceLists) {
        this.deviceLists = Arrays.asList(deviceLists);
    }

    public Optional<DeviceInfo> getDeviceInfo(final String deviceId) {
        for (final DeviceLookup lookup : deviceLists) {
            final Optional<DeviceInfo> deviceInfo = lookup.getDeviceInfo(deviceId);
            if (deviceInfo.isPresent()) {
                return deviceInfo;
            }
        }
        return Optional.empty();
    }
}
