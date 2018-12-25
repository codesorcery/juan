package com.github.codesorcery.juan.device;

import java.util.StringJoiner;

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

    public String getVendor() {
        return vendor;
    }

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
