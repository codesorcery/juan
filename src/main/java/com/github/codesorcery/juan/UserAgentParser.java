package com.github.codesorcery.juan;

import com.github.codesorcery.juan.agent.Agent;
import com.github.codesorcery.juan.device.*;
import com.github.codesorcery.juan.os.OperatingSystem;
import com.github.codesorcery.juan.token.TokenizedUserAgent;

import java.io.InputStream;
import java.nio.charset.Charset;

public class UserAgentParser {
    private final CombinedDeviceLookup deviceLookup;

    private UserAgentParser(final DeviceLookup... deviceLookups) {
        deviceLookup = new CombinedDeviceLookup(deviceLookups);
    }

    public static UserAgentParser withPlayStoreDeviceList(final InputStream inputStream) {
        return withPlayStoreDeviceList(inputStream, Charset.defaultCharset());
    }

    public static UserAgentParser withPlayStoreDeviceList(final InputStream inputStream,
                                                          final Charset charset) {
        return new UserAgentParser(
                PlayStoreDeviceListLookup.fromCsvFile(inputStream, charset),
                new DirectlyIdentifiableDeviceLookup(),
                new SimpleAmazonFireLookup()
        );
    }

    public static UserAgentParser withoutPlayStoreDeviceList() {
        return new UserAgentParser(
                new DirectlyIdentifiableDeviceLookup(),
                new SimpleAmazonFireLookup()
        );
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
}
