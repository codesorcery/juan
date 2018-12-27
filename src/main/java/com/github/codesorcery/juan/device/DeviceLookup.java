package com.github.codesorcery.juan.device;

import com.github.codesorcery.juan.token.TokenizedUserAgent;

import java.util.Optional;

public interface DeviceLookup {
    Optional<DeviceInfo> getDeviceInfo(TokenizedUserAgent tokenizedUserAgent);
}
