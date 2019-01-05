package com.github.codesorcery.juan;

import com.github.codesorcery.juan.agent.Agent;
import com.github.codesorcery.juan.device.DeviceInfo;
import com.github.codesorcery.juan.os.OperatingSystem;

import java.util.StringJoiner;

/**
 * Holds the information extracted from the user agent string by
 * the {@link UserAgentParser}.
 */
public class ParsedUserAgent {
    private final Agent agent;
    private final OperatingSystem os;
    private final DeviceInfo device;

    ParsedUserAgent(final Agent agent, final OperatingSystem os, final DeviceInfo device) {
        this.agent = agent;
        this.os = os;
        this.device = device;
    }

    /**
     * @return An entity containing the extracted information
     * about the agent.
     */
    public Agent agent() {
        return agent;
    }

    /**
     * @return An entity containing the extracted information
     * about the operating system.
     */
    public OperatingSystem os() {
        return os;
    }

    /**
     * @return An entity containing the extracted information
     * about the device.
     */
    public DeviceInfo device() {
        return device;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ParsedUserAgent.class.getSimpleName() + "[", "]")
                .add("agent=" + agent)
                .add("os=" + os)
                .add("device=" + device)
                .toString();
    }
}
