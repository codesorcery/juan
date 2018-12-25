package com.github.codesorcery.juan.device;

import java.util.Optional;

public class SimpleAppleLookup implements DeviceLookup {
    @Override
    public Optional<DeviceInfo> getDeviceInfo(final String deviceId) {
        if (deviceId.equals("iPad") || deviceId.equals("iPhone")) {
            return Optional.of(new DeviceInfo("Apple", deviceId));
        } else {
            return Optional.empty();
        }
    }
}
