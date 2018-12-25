package com.github.codesorcery.juan;

import com.github.codesorcery.juan.agent.Agent;
import com.github.codesorcery.juan.device.DeviceInfo;
import com.github.codesorcery.juan.os.OperatingSystem;

public class ParsedUserAgent {
    private final Agent agent;
    private final OperatingSystem os;
    private final DeviceInfo device;

    ParsedUserAgent(final Agent agent, final OperatingSystem os, final DeviceInfo device) {
        this.agent = agent;
        this.os = os;
        this.device = device;
    }

    public Agent agent() {
        return agent;
    }

    public OperatingSystem os() {
        return os;
    }

    public DeviceInfo device() {
        return device;
    }
}
