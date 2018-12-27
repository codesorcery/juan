package com.github.codesorcery.juan;

import com.github.codesorcery.juan.agent.Agent;
import com.github.codesorcery.juan.device.DeviceInfo;
import com.github.codesorcery.juan.os.OperatingSystem;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Validators {
    private Validators() {}

    static void validateAgent(final Agent agent,
                              final String vendor, final String name, final String version) {
        assertAll("agent",
                () -> assertEquals(vendor, agent.getVendor()),
                () -> assertEquals(name, agent.getName()),
                () -> assertEquals(version, agent.getVersion())
        );
    }

    static void validateOS(final OperatingSystem os,
                           final String vendor, final String name, final String version,
                           final String type) {
        assertAll("os",
                () -> assertEquals(vendor, os.getVendor()),
                () -> assertEquals(name, os.getName()),
                () -> assertEquals(version, os.getVersion()),
                () -> assertEquals(type, os.getType())
        );
    }

    static void validateDeviceInfo(final DeviceInfo deviceInfo,
                                   final String vendor, final String name) {
        assertAll("device",
                () -> assertEquals(vendor, deviceInfo.getVendor()),
                () -> assertEquals(name, deviceInfo.getName())
        );
    }
}
