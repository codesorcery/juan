package com.github.codesorcery.juan.device;

import java.util.Optional;

public interface DeviceLookup {
    Optional<DeviceInfo> getDeviceInfo(String deviceId);
}
