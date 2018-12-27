package com.github.codesorcery.juan;

import com.github.codesorcery.juan.device.*;
import com.github.codesorcery.juan.token.TokenizedUserAgent;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeviceInfoTest {
    private final DeviceLookup deviceLookup;

    public DeviceInfoTest() throws IOException {
        final ClassLoader classLoader = getClass().getClassLoader();
        final PlayStoreDeviceListLookup playStoreDeviceList = PlayStoreDeviceListLookup
                .fromCsvFile(classLoader.getResourceAsStream("supported_devices_subset.csv"));
        deviceLookup = new CombinedDeviceLookup(playStoreDeviceList, new SimpleAmazonFireLookup(),
                new SimpleAppleLookup());
    }

    @Test
    public void samsungGalaxyS8Active() {
        final String input = "Mozilla/5.0 (Linux; Android 7.0; SM-G892A Build/NRD90M; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/60.0.3112.107 Mobile Safari/537.36";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Optional<DeviceInfo> deviceInfo = deviceLookup.getDeviceInfo(ua);
        assertTrue(deviceInfo.isPresent());
        Validators.validateDeviceInfo(deviceInfo.get(), "Samsung", "Galaxy S8 Active");
    }

    @Test
    public void samsungGalaxyS8ActiveWithoutBuildNumber() {
        final String input = "Mozilla/5.0 (Linux; Android 7.0; SM-G892A; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/60.0.3112.107 Mobile Safari/537.36";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Optional<DeviceInfo> deviceInfo = deviceLookup.getDeviceInfo(ua);
        assertTrue(deviceInfo.isPresent());
        Validators.validateDeviceInfo(deviceInfo.get(), "Samsung", "Galaxy S8 Active");
    }

    @Test
    public void samsungGalaxyS7edge() {
        final String input = "Mozilla/5.0 (Linux; U; Android 7.0; en-US; SM-G935F Build/NRD90M) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 UCBrowser/11.3.8.976 U3/0.8.0 Mobile Safari/534.30";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Optional<DeviceInfo> deviceInfo = deviceLookup.getDeviceInfo(ua);
        assertTrue(deviceInfo.isPresent());
        Validators.validateDeviceInfo(deviceInfo.get(), "Samsung", "Galaxy S7 edge");
    }

    @Test
    public void lenovoK3Note() {
        final String input = "Mozilla/5.0 (Linux; Android 6.0; Lenovo K50a40 Build/MRA58K) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.137 YaBrowser/17.4.1.352.00 Mobile Safari/537.36";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Optional<DeviceInfo> deviceInfo = deviceLookup.getDeviceInfo(ua);
        assertTrue(deviceInfo.isPresent());
        Validators.validateDeviceInfo(deviceInfo.get(), "Lenovo", "K3 Note");
    }

    @Test
    public void kindleFireHDX7() {
        final String input = "Mozilla/5.0 (Linux; Android 4.4.3; KFTHWI Build/KTU84M) AppleWebKit/537.36 (KHTML, like Gecko) Silk/47.1.79 like Chrome/47.0.2526.80 Safari/537.36";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Optional<DeviceInfo> deviceInfo = deviceLookup.getDeviceInfo(ua);
        assertTrue(deviceInfo.isPresent());
        Validators.validateDeviceInfo(deviceInfo.get(), "Amazon", "Kindle Fire Tablet");
    }

    @Test
    public void appleiPad() {
        final String input = "Mozilla/5.0 (iPad; CPU OS 7_0 like Mac OS X) AppleWebKit/537.51.1 (KHTML, like Gecko) Version/7.0 Mobile/11A465 Safari/9537.53";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Optional<DeviceInfo> deviceInfo = deviceLookup.getDeviceInfo(ua);
        assertTrue(deviceInfo.isPresent());
        Validators.validateDeviceInfo(deviceInfo.get(), "Apple", "iPad");
    }

    @Test
    public void appleiPhone() {
        final String input = "Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Optional<DeviceInfo> deviceInfo = deviceLookup.getDeviceInfo(ua);
        assertTrue(deviceInfo.isPresent());
        Validators.validateDeviceInfo(deviceInfo.get(), "Apple", "iPhone");
    }
}
