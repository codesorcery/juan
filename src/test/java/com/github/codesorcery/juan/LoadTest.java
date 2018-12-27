package com.github.codesorcery.juan;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

public class LoadTest {
    private final UserAgentParser userAgentParser;

    public LoadTest() {
        final ClassLoader classLoader = getClass().getClassLoader();
        userAgentParser = UserAgentParser.withPlayStoreDeviceList(
                classLoader.getResourceAsStream("supported_devices_subset.csv"));
    }

    @Test
    public void simpleLoadTestAndroid() {
        final String input = "Mozilla/5.0 (Linux; Android 7.0; SM-G892A Build/NRD90M; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/60.0.3112.107 Mobile Safari/537.36";
        //Stream.generate(() -> true)
        IntStream.range(0, 1000000)
                .forEach(i -> userAgentParser.parse(input));
    }
}
