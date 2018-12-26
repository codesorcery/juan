package com.github.codesorcery.juan.device;

import com.github.codesorcery.juan.util.Vendors;

import java.util.Optional;

public class SimpleAmazonFireLookup implements DeviceLookup {
    @Override
    public Optional<DeviceInfo> getDeviceInfo(final String deviceId) {
        if (deviceId.startsWith("KF")) {
            return Optional.of(new DeviceInfo(Vendors.AMAZON, "Kindle Fire Tablet"));
        } else if (deviceId.startsWith("AF")){
            return Optional.of(new DeviceInfo(Vendors.AMAZON, "Fire TV"));
        } else {
                return Optional.empty();
        }
    }
}
