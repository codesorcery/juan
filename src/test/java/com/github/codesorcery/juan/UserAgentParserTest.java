package com.github.codesorcery.juan;

import com.github.codesorcery.juan.device.PlayStoreDeviceListLookup;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

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
                () -> assertEquals(result.os().getOsVendor(), "Google"),
                () -> assertEquals(result.os().getOsName(), "Android"),
                () -> assertEquals(result.os().getOsVersion(), "7.0"),
                () -> assertEquals(result.os().getOsType(), "Mobile")
        );
        assertAll("device",
                () -> assertEquals(result.device().getVendor(), ""),
                () -> assertEquals(result.device().getName(), "")
        );
    }

    @Test
    public void withDeviceList() {
        final ClassLoader classLoader = getClass().getClassLoader();
        final UserAgentParser userAgentParser = UserAgentParser.withPlayStoreDeviceList(
                classLoader.getResourceAsStream("supported_devices_subset.csv"));

        final String input = "Mozilla/5.0 (Linux; Android 7.0; SM-G892A Build/NRD90M; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/60.0.3112.107 Mobile Safari/537.36";
        final ParsedUserAgent result = userAgentParser.parse(input);

        assertAll("agent",
                () -> assertEquals(result.agent().getVendor(), "Google"),
                () -> assertEquals(result.agent().getName(), "Chrome Mobile"),
                () -> assertEquals(result.agent().getVersion(), "60.0.3112.107")
        );
        assertAll("os",
                () -> assertEquals(result.os().getOsVendor(), "Google"),
                () -> assertEquals(result.os().getOsName(), "Android"),
                () -> assertEquals(result.os().getOsVersion(), "7.0"),
                () -> assertEquals(result.os().getOsType(), "Mobile")
        );
        assertAll("device",
                () -> assertEquals(result.device().getVendor(), "Samsung"),
                () -> assertEquals(result.device().getName(), "Galaxy S8 Active")
        );
    }

    @Test
    public void initializeWithFailingInputStream() {
        assertThrows(PlayStoreDeviceListLookup.InitializationException.class,
                () ->UserAgentParser.withPlayStoreDeviceList(new FailingInputStream())
        );
    }

    private static class FailingInputStream extends InputStream {
        @Override
        public int read() throws IOException {
            throw new IOException("here2fail");
        }
    }
}
