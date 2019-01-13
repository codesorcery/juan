package com.github.codesorcery.juan;

import com.github.codesorcery.juan.agent.Agent;
import com.github.codesorcery.juan.device.DeviceInfo;
import com.github.codesorcery.juan.device.DeviceLookup;
import com.github.codesorcery.juan.os.OperatingSystem;
import com.github.codesorcery.juan.token.TokenizedUserAgent;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * The user agent parser.
 * Takes user agent strings as input and returns the parsed data as
 * {@link ParsedUserAgent}.
 */
public class UserAgentParser {
    private final DeviceLookup deviceLookup;
    private Consumer<Supplier<String>> logger;

    private UserAgentParser(final DeviceLookup deviceLookup) {
        this.deviceLookup = deviceLookup;
    }

    /**
     * Create a new UserAgentParser with Android device lookup using the device list from Google Play.
     *
     * @param location The location where the Google Play device list is stored.
     * @param charset  The charset of the Google Play device list file.
     * @return A UserAgentParser instance with Android device lookup
     * based on the Google Play device list enabled.
     * @throws IOException If the Google Play device list could not be read.
     */
    public static UserAgentParser withPlayStoreDeviceList(final URL location, final Charset charset)
            throws IOException {
        return new UserAgentParser(DeviceLookup.withPlayStoreDeviceList(location, charset));
    }

    /**
     * Create a new UserAgentParser without the device list from Google Play.
     *
     * @return A UserAgentParser instance with Android device lookup
     * based on the Google Play device list disabled.
     */
    public static UserAgentParser withoutPlayStoreDeviceList() {
        return new UserAgentParser(DeviceLookup.withoutPlayStoreDeviceList());
    }

    /**
     * Configure a logging function for the string representation of the
     * intermediary tokenized user agent (for debugging purposes).
     *
     * @param loggingFunction A function which consumes the
     *                        {@link TokenizedUserAgent#toString()} function
     *                        of the intermediary tokenized user agent.
     * @return The UserAgentParser instance.
     */
    public UserAgentParser withTokenizedUALogger(final Consumer<Supplier<String>> loggingFunction) {
        this.logger = loggingFunction;
        return this;
    }

    /**
     * Parse a user agent string.
     *
     * @param userAgentString The user agent string which should be parsed.
     * @return The parsed user agent information.
     */
    public ParsedUserAgent parse(final String userAgentString) {
        final TokenizedUserAgent tokenizedUserAgent = TokenizedUserAgent
                .forUserAgentString(userAgentString);
        if (logger != null) {
            logger.accept(tokenizedUserAgent::toString);
        }
        final Agent agent = Agent.fromUserAgent(tokenizedUserAgent);
        if (agent.isCrawler()) {
            // Crawlers send no reliable os or device info, so leave them empty
            return new ParsedUserAgent(agent, OperatingSystem.unknown(), DeviceInfo.empty());
        }
        final OperatingSystem os = OperatingSystem.fromUserAgent(tokenizedUserAgent);
        final DeviceInfo device = deviceLookup.getDeviceInfo(tokenizedUserAgent)
                .orElse(DeviceInfo.empty());
        return new ParsedUserAgent(agent, os, device);
    }
}
