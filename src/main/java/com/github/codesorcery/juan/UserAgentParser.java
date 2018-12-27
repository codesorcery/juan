package com.github.codesorcery.juan;

import com.github.codesorcery.juan.agent.Agent;
import com.github.codesorcery.juan.device.CombinedDeviceLookup;
import com.github.codesorcery.juan.device.DeviceInfo;
import com.github.codesorcery.juan.device.DeviceLookup;
import com.github.codesorcery.juan.os.OperatingSystem;
import com.github.codesorcery.juan.token.TokenizedUserAgent;

public class UserAgentParser {
    private final CombinedDeviceLookup deviceLookup;

    private UserAgentParser(final DeviceLookup... deviceLookups) {
        deviceLookup = new CombinedDeviceLookup(deviceLookups);
    }

    public ParsedUserAgent parse(final String userAgentString) {
        final TokenizedUserAgent tokenizedUserAgent = TokenizedUserAgent
                .forUserAgentString(userAgentString);
        final Agent agent = Agent.fromUserAgent(tokenizedUserAgent);
        final OperatingSystem os = OperatingSystem.fromUserAgent(tokenizedUserAgent);
        final DeviceInfo device = deviceLookup.getDeviceInfo(tokenizedUserAgent)
                .orElse(DeviceInfo.empty());
        return new ParsedUserAgent(agent, os, device);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private DeviceLookup[] deviceLookups = new DeviceLookup[0];

        private Builder() {}

        public Builder withDeviceLookups(DeviceLookup ... deviceLookups) {
            this.deviceLookups = deviceLookups;
            return this;
        }

        public UserAgentParser build() {
            return new UserAgentParser(deviceLookups);
        }

    }
}
