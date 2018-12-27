package com.github.codesorcery.juan;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.Charset;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserAgentParserTest {
    @Test
    public void withoutDeviceList() {
        final UserAgentParser userAgentParser = UserAgentParser.withoutPlayStoreDeviceList();

        final String input = "Mozilla/5.0 (Linux; Android 7.0; SM-G892A Build/NRD90M; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/60.0.3112.107 Mobile Safari/537.36";
        final ParsedUserAgent result = userAgentParser.parse(input);

        assertAll("agent",
                () -> assertEquals(result.agent().getVendor(), "Google"),
                () -> assertEquals(result.agent().getName(), "Chrome Mobile"),
                () -> assertEquals(result.agent().getVersion(), "60.0.3112.107")
        );
        assertAll("os",
                () -> assertEquals(result.os().getVendor(), "Google"),
                () -> assertEquals(result.os().getName(), "Android"),
                () -> assertEquals(result.os().getVersion(), "7.0"),
                () -> assertEquals(result.os().getType(), "Mobile")
        );
        assertAll("device",
                () -> assertEquals(result.device().getVendor(), ""),
                () -> assertEquals(result.device().getName(), "")
        );
    }

    @Test
    public void withDeviceList() throws IOException {
        final ClassLoader classLoader = getClass().getClassLoader();
        final UserAgentParser userAgentParser = UserAgentParser.withPlayStoreDeviceList(
                classLoader.getResource("supported_devices_subset.csv"), Charset.forName("UTF-8"));

        final String input = "Mozilla/5.0 (Linux; Android 7.0; SM-G892A Build/NRD90M; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/60.0.3112.107 Mobile Safari/537.36";
        final ParsedUserAgent result = userAgentParser.parse(input);

        assertAll("agent",
                () -> assertEquals(result.agent().getVendor(), "Google"),
                () -> assertEquals(result.agent().getName(), "Chrome Mobile"),
                () -> assertEquals(result.agent().getVersion(), "60.0.3112.107")
        );
        assertAll("os",
                () -> assertEquals(result.os().getVendor(), "Google"),
                () -> assertEquals(result.os().getName(), "Android"),
                () -> assertEquals(result.os().getVersion(), "7.0"),
                () -> assertEquals(result.os().getType(), "Mobile")
        );
        assertAll("device",
                () -> assertEquals(result.device().getVendor(), "Samsung"),
                () -> assertEquals(result.device().getName(), "Galaxy S8 Active")
        );
    }
}
