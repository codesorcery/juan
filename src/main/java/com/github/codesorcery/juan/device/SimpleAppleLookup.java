package com.github.codesorcery.juan.device;

import com.github.codesorcery.juan.util.Vendors;

import java.util.Optional;

public class SimpleAppleLookup implements DeviceLookup {
    @Override
    public Optional<DeviceInfo> getDeviceInfo(final String deviceId) {
        if (deviceId.equals("iPad") || deviceId.equals("iPhone")) {
            return Optional.of(new DeviceInfo(Vendors.APPLE, deviceId));
        } else {
            return Optional.empty();
        }
    }
}
