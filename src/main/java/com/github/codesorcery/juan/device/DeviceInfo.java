package com.github.codesorcery.juan.device;

import java.util.StringJoiner;

/**
 * Holds information about the device
 * extracted from the parsed user agent.
 */
public class DeviceInfo {
    private static final DeviceInfo EMPTY = new DeviceInfo("", "");

    private final String vendor;
    private final String name;

    DeviceInfo(final String vendor, final String name) {
        this.vendor = vendor;
        this.name = name;
    }

    public static DeviceInfo empty() {
        return EMPTY;
    }

    /**
     * @return The vendor of the device.
     * Empty string if unknown.
     */
    public String getVendor() {
        return vendor;
    }

    /**
     * @return The name of the device.
     * Empty string if unknown.
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DeviceInfo.class.getSimpleName() + "[", "]")
                .add("vendor='" + vendor + "'")
                .add("name='" + name + "'")
                .toString();
    }
}
