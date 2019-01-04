package com.github.codesorcery.juan;

import com.github.codesorcery.juan.token.TokenizedUserAgent;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.Charset;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ToStringFunctionTest {
    private final UserAgentParser userAgentParser;

    public ToStringFunctionTest() throws IOException {
        final ClassLoader classLoader = getClass().getClassLoader();
        userAgentParser = UserAgentParser.withPlayStoreDeviceList(
                classLoader.getResource("supported_devices_subset.csv"), Charset.forName("UTF-8"));
    }

    @Test
    public void tokenizedUserAgent() {
        final String input = "Mozilla/5.0 (iPhone; CPU iPhone OS 10_3_2 like Mac OS X) AppleWebKit/603.2.4 (KHTML, like Gecko) FxiOS/7.5b3349 Mobile/14F89 Safari/603.2.4";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);

        final String toStringResult = ua.toString();
        assertThat(toStringResult, startsWith("TokenizedUserAgent["));
        assertThat(toStringResult, containsString("prefixValue='Mozilla'"));
        assertThat(toStringResult, containsString("prefixVersion='5.0'"));
        assertThat(toStringResult, containsString("systemTokens=[VersionedToken[value='iPhone', version=''], VersionedToken[value='CPU iPhone OS', version='10.3.2 like Mac OS X']]"));
        assertThat(toStringResult, containsString("browserTokens=[VersionedToken[value='AppleWebKit', version='603.2.4'], VersionedToken[value='(KHTML, like Gecko)', version=''], VersionedToken[value='FxiOS', version='7.5b3349'], VersionedToken[value='Mobile', version='14F89'], VersionedToken[value='Safari', version='603.2.4']]"));
        assertThat(toStringResult, not(containsString("allTokens")));
    }

    @Test
    public void parsedUserAgent() {
        final String input = "Mozilla/5.0 (iPhone; CPU iPhone OS 10_3_2 like Mac OS X) AppleWebKit/603.2.4 (KHTML, like Gecko) FxiOS/7.5b3349 Mobile/14F89 Safari/603.2.4";
        final ParsedUserAgent parsedUserAgent = userAgentParser.parse(input);
        final String toStringResult = parsedUserAgent.toString();

        assertThat(toStringResult, startsWith("ParsedUserAgent["));
        assertThat(toStringResult, containsString("agent=Agent[name='Firefox Mobile', vendor='Mozilla', version='7.5b3349', type='Browser (mobile)']"));
        assertThat(toStringResult, containsString("os=IOS[vendor='Apple', name='iOS', version='10.3.2', type='Mobile']"));
        assertThat(toStringResult, containsString("device=DeviceInfo[vendor='Apple', name='iPhone']"));
    }
}
