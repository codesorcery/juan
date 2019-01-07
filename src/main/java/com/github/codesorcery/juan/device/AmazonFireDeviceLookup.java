package com.github.codesorcery.juan.device;

import com.github.codesorcery.juan.util.Vendors;

import java.util.Map;

class AmazonFireDeviceLookup extends AndroidDeviceLookup {
    private static final Map<String, String> AMAZON_FIRE_DEVICES = AmazonFireDevice.valuesAsMap();

    @Override
    DeviceInfo lookup(String value) {
        final String name = AMAZON_FIRE_DEVICES.get(value);
        if (name != null) {
            return new DeviceInfo(Vendors.AMAZON, name);
        }
        return null;
    }
}
