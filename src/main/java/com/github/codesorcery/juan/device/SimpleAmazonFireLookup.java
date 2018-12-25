package com.github.codesorcery.juan.device;

import java.util.Optional;

public class SimpleAmazonFireLookup implements DeviceLookup {
    @Override
    public Optional<DeviceInfo> getDeviceInfo(final String deviceId) {
        if (deviceId.startsWith("KF")) {
            return Optional.of(new DeviceInfo("Amazon", "Kindle Fire Tablet"));
        } else if (deviceId.startsWith("AF")){
            return Optional.of(new DeviceInfo("Amazon", "Fire TV"));
        } else {
                return Optional.empty();
        }
    }
}
