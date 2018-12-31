package com.github.codesorcery.juan;

import com.github.codesorcery.juan.agent.Agent;
import com.github.codesorcery.juan.device.*;
import com.github.codesorcery.juan.os.OperatingSystem;
import com.github.codesorcery.juan.token.TokenizedUserAgent;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class UserAgentParser {
    private final CombinedDeviceLookup deviceLookup;
    private Consumer<Supplier<String>> logger;

    private UserAgentParser(final DeviceLookup... deviceLookups) {
        deviceLookup = new CombinedDeviceLookup(deviceLookups);
    }

    public static UserAgentParser withPlayStoreDeviceList(final URL location, final Charset charset)
            throws IOException {
        return new UserAgentParser(
                PlayStoreDeviceListLookup.fromCsvFile(location, charset),
                new DirectlyIdentifiableDeviceLookup(),
                new AmazonFireDeviceLookup(),
                new WindowsPhoneDeviceLookup(),
                new BlackBerryDeviceLookup()
        );
    }

    public static UserAgentParser withoutPlayStoreDeviceList() {
        return new UserAgentParser(
                new DirectlyIdentifiableDeviceLookup(),
                new AmazonFireDeviceLookup(),
                new WindowsPhoneDeviceLookup(),
                new BlackBerryDeviceLookup()
        );
    }

    public UserAgentParser withTokenizedUALogger(final Consumer<Supplier<String>> loggingFunction) {
        this.logger = loggingFunction;
        return this;
    }

    public ParsedUserAgent parse(final String userAgentString) {
        final TokenizedUserAgent tokenizedUserAgent = TokenizedUserAgent
                .forUserAgentString(userAgentString);
        if (logger != null) {
            logger.accept(tokenizedUserAgent::toString);
        }
        final Agent agent = Agent.fromUserAgent(tokenizedUserAgent);
        final OperatingSystem os = OperatingSystem.fromUserAgent(tokenizedUserAgent);
        final DeviceInfo device = deviceLookup.getDeviceInfo(tokenizedUserAgent)
                .orElse(DeviceInfo.empty());
        return new ParsedUserAgent(agent, os, device);
    }
}
