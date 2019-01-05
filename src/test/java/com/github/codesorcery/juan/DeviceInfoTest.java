package com.github.codesorcery.juan;

import com.github.codesorcery.juan.device.CombinedDeviceLookup;
import com.github.codesorcery.juan.device.DeviceInfo;
import com.github.codesorcery.juan.device.DeviceLookup;
import com.github.codesorcery.juan.device.PlayStoreDeviceListLookup;
import com.github.codesorcery.juan.token.TokenizedUserAgent;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeviceInfoTest {
    private final DeviceLookup deviceLookup;

    public DeviceInfoTest() throws IOException {
        final ClassLoader classLoader = getClass().getClassLoader();
        final PlayStoreDeviceListLookup playStoreDeviceList = PlayStoreDeviceListLookup
                .fromCsvFile(classLoader.getResource("supported_devices_subset.csv"),
                        Charset.forName("UTF-8"));
        deviceLookup = new CombinedDeviceLookup(playStoreDeviceList, UserAgentParser.STATIC_DEVICE_LOOKUPS);
    }

    @Test
    public void emptyString() {
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString("");
        final Optional<DeviceInfo> deviceInfo = deviceLookup.getDeviceInfo(ua);
        assertFalse(deviceInfo.isPresent());
    }

    @Test
    public void randomString() {
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString("Mozilla/5.0 (foo; bar) foobar/1.0 [foo/2.0; bar/3.0]");
        final Optional<DeviceInfo> deviceInfo = deviceLookup.getDeviceInfo(ua);
        assertFalse(deviceInfo.isPresent());
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
    public void samsungGalaxyXcover3() {
        final String input = "Mozilla/5.0 (Linux; Android 5.1.1; SAMSUNG SM-G388F/G388FXXS1BPL2 Build/LMY48B) AppleWebKit/537.36 (KHTML, like Gecko) SamsungBrowser/7.4 Chrome/59.0.3071.125 Mobile Safari/537.36";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Optional<DeviceInfo> deviceInfo = deviceLookup.getDeviceInfo(ua);
        assertTrue(deviceInfo.isPresent());
        Validators.validateDeviceInfo(deviceInfo.get(), "Samsung", "Galaxy Xcover3");
    }

    @Test
    public void huaweiHonor5C() {
        final String input = "Mozilla/5.0 (Linux; Android 7.0; HUAWEI NEM-L51 Build/HUAWEINEM-L51) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Mobile Safari/537.36";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Optional<DeviceInfo> deviceInfo = deviceLookup.getDeviceInfo(ua);
        assertTrue(deviceInfo.isPresent());
        Validators.validateDeviceInfo(deviceInfo.get(), "Huawei", "Honor 5C");
    }

    @Test
    public void huaweiHonor8Lite() {
        final String input = "Mozilla/5.0 (Android 8.0.0; HUAWEI PRA-LX1) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/30.0.0.0 Mobile Safari/537.36 SurfBrowser/3.0";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Optional<DeviceInfo> deviceInfo = deviceLookup.getDeviceInfo(ua);
        assertTrue(deviceInfo.isPresent());
        Validators.validateDeviceInfo(deviceInfo.get(), "Huawei", "Honor 8 Lite");
    }

    @Test
    public void lenovoTab2() {
        final String input = "Mozilla/5.0 (Linux; Android 6.0; LENOVO Lenovo TAB 2 A10-70F Build/NMF26F) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.111 Mobile Safari/537.36 AlohaBrowser/1.4.0.1";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Optional<DeviceInfo> deviceInfo = deviceLookup.getDeviceInfo(ua);
        assertTrue(deviceInfo.isPresent());
        Validators.validateDeviceInfo(deviceInfo.get(), "Lenovo", "TAB 2 A10");
    }

    @Test
    public void kindleFire2013() {
        final String input = "Mozilla/5.0 (Linux; Android 4.4.3; KFTHWI Build/KTU84M) AppleWebKit/537.36 (KHTML, like Gecko) Silk/47.1.79 like Chrome/47.0.2526.80 Safari/537.36";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Optional<DeviceInfo> deviceInfo = deviceLookup.getDeviceInfo(ua);
        assertTrue(deviceInfo.isPresent());
        Validators.validateDeviceInfo(deviceInfo.get(), "Amazon", "Kindle Fire HDX (2013)");
    }

    @Test
    public void kindleTVGen2() {
        final String input = "Mozilla/5.0 (Linux; Android 5.1.1; AFTT Build/LVY48F) AppleWebKit/537.36 (KHTML, like Gecko) Silk/64.2.6 like Chrome/64.0.3282.137 Safari/537.36 ";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Optional<DeviceInfo> deviceInfo = deviceLookup.getDeviceInfo(ua);
        assertTrue(deviceInfo.isPresent());
        Validators.validateDeviceInfo(deviceInfo.get(), "Amazon", "Fire TV Stick (Gen 2)");
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

    @Test
    public void appleiPod() {
        final String input = "Mozilla/5.0 (iPod; U; CPU iPhone OS 3_1_3 like Mac OS X; en-us) AppleWebKit/528.18 (KHTML, like Gecko) Mobile/7E18";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Optional<DeviceInfo> deviceInfo = deviceLookup.getDeviceInfo(ua);
        assertTrue(deviceInfo.isPresent());
        Validators.validateDeviceInfo(deviceInfo.get(), "Apple", "iPod");
    }

    @Test
    public void appleiPodTouch() {
        final String input = "Mozilla/5.0 (iPod touch; CPU iPhone OS 10_3_2 like Mac OS X) AppleWebKit/603.2.4 (KHTML, like Gecko) Version/10.0 Mobile/14F89 Safari/602.1";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Optional<DeviceInfo> deviceInfo = deviceLookup.getDeviceInfo(ua);
        assertTrue(deviceInfo.isPresent());
        Validators.validateDeviceInfo(deviceInfo.get(), "Apple", "iPod Touch");
    }

    @Test
    public void playStation4() {
        final String input = "Mozilla/5.0 (PlayStation 4 5.01) AppleWebKit/601.2 (KHTML, like Gecko)";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Optional<DeviceInfo> deviceInfo = deviceLookup.getDeviceInfo(ua);
        assertTrue(deviceInfo.isPresent());
        Validators.validateDeviceInfo(deviceInfo.get(), "Sony", "PlayStation 4");
    }

    @Test
    public void playStation3() {
        final String input = "Mozilla/5.0 (PLAYSTATION 3 4.76) AppleWebKit/531.22.8 (KHTML, like Gecko)";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Optional<DeviceInfo> deviceInfo = deviceLookup.getDeviceInfo(ua);
        assertTrue(deviceInfo.isPresent());
        Validators.validateDeviceInfo(deviceInfo.get(), "Sony", "PlayStation 3");
    }

    @Test
    public void playStationVita() {
        final String input = "Mozilla/5.0 (PlayStation Vita 3.65) AppleWebKit/537.73 (KHTML, like Gecko) Silk/3.2";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Optional<DeviceInfo> deviceInfo = deviceLookup.getDeviceInfo(ua);
        assertTrue(deviceInfo.isPresent());
        Validators.validateDeviceInfo(deviceInfo.get(), "Sony", "PlayStation Vita");
    }

    @Test
    public void playStationPortable() {
        final String input = "Mozilla/4.0 (PSP (PlayStation Portable); 2.00)";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Optional<DeviceInfo> deviceInfo = deviceLookup.getDeviceInfo(ua);
        assertTrue(deviceInfo.isPresent());
        Validators.validateDeviceInfo(deviceInfo.get(), "Sony", "PlayStation Portable");
    }

    @Test
    public void nintendoWiiU() {
        final String input = "Mozilla/5.0 (Nintendo WiiU) AppleWebKit/536.30 (KHTML, like Gecko) NX/3.0.4.2.12 NintendoBrowser/4.3.1.11264.EU";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Optional<DeviceInfo> deviceInfo = deviceLookup.getDeviceInfo(ua);
        assertTrue(deviceInfo.isPresent());
        Validators.validateDeviceInfo(deviceInfo.get(), "Nintendo", "WiiU");
    }

    @Test
    public void nintendoDSi() {
        final String input = "Opera/9.50 (Nintendo DSi; Opera/507; U; en-US)";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Optional<DeviceInfo> deviceInfo = deviceLookup.getDeviceInfo(ua);
        assertTrue(deviceInfo.isPresent());
        Validators.validateDeviceInfo(deviceInfo.get(), "Nintendo", "DSi");
    }

    @Test
    public void nintendo3DS() {
        final String input = "Mozilla/5.0 (Nintendo 3DS; U; ; en) Version/1.7630.US";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Optional<DeviceInfo> deviceInfo = deviceLookup.getDeviceInfo(ua);
        assertTrue(deviceInfo.isPresent());
        Validators.validateDeviceInfo(deviceInfo.get(), "Nintendo", "3DS");
    }

    @Test
    public void nintendo3DSNew() {
        final String input = "Mozilla/5.0 (New Nintendo 3DS like iPhone) AppleWebKit/536.30 (KHTML, like Gecko) NX/3.0.0.5.20 Mobile NintendoBrowser/1.9.10160.EU";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Optional<DeviceInfo> deviceInfo = deviceLookup.getDeviceInfo(ua);
        assertTrue(deviceInfo.isPresent());
        Validators.validateDeviceInfo(deviceInfo.get(), "Nintendo", "3DS");
    }

    @Test
    public void nintendoSwitch() {
        final String input = "Mozilla/5.0 (Nintendo Switch; WifiWebAuthApplet) AppleWebKit/601.6 (KHTML, like Gecko) NF/4.0.0.9.3 NintendoBrowser/5.1.0.16958";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Optional<DeviceInfo> deviceInfo = deviceLookup.getDeviceInfo(ua);
        assertTrue(deviceInfo.isPresent());
        Validators.validateDeviceInfo(deviceInfo.get(), "Nintendo", "Switch");
    }

    @Test
    public void blackBerryPlayBookTablet() {
        final String input = "Mozilla/5.0 (PlayBook; U; RIM Tablet OS 2.1.0; en-US) AppleWebKit/536.2+ (KHTML, like Gecko) Version/7.2.1.0 Safari/536.2+";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Optional<DeviceInfo> deviceInfo = deviceLookup.getDeviceInfo(ua);
        assertTrue(deviceInfo.isPresent());
        Validators.validateDeviceInfo(deviceInfo.get(), "BlackBerry", "PlayBook");
    }

    @Test
    public void microsoftLumia640WindowsPhone() {
        final String input = "Mozilla/5.0 (Mobile; Windows Phone 8.1; Android 4.0; ARM; Trident/7.0; Touch; rv:11.0; IEMobile/11.0; Microsoft; Lumia 640 LTE) like iPhone OS 7_0_3 Mac OS X AppleWebKit/537 (KHTML, like Gecko) Mobile Safari/537";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Optional<DeviceInfo> deviceInfo = deviceLookup.getDeviceInfo(ua);
        assertTrue(deviceInfo.isPresent());
        Validators.validateDeviceInfo(deviceInfo.get(), "Microsoft", "Lumia 640 LTE");
    }

    @Test
    public void microsoftLumia535WindowsPhone() {
        final String input = "Mozilla/5.0 (Mobile; Windows Phone 8.1; Android 4.0; ARM; Trident/7.0; Touch; rv:11.0; IEMobile/11.0; Microsoft; Lumia 535) like iPhone OS 7_0_3 Mac OS X AppleWebKit/537 (KHTML, like Gecko) Mobile Safari/537";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Optional<DeviceInfo> deviceInfo = deviceLookup.getDeviceInfo(ua);
        assertTrue(deviceInfo.isPresent());
        Validators.validateDeviceInfo(deviceInfo.get(), "Microsoft", "Lumia 535");
    }

    @Test
    public void blackBerry9720() {
        final String input = "Mozilla/5.0 (BlackBerry; U; BlackBerry 9720; en) AppleWebKit/534.11+ (KHTML, like Gecko) Version/7.1.0.1121 Mobile Safari/534.11+";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Optional<DeviceInfo> deviceInfo = deviceLookup.getDeviceInfo(ua);
        assertTrue(deviceInfo.isPresent());
        Validators.validateDeviceInfo(deviceInfo.get(), "BlackBerry", "9720");
    }

    @Test
    public void blackBerry8330() {
        final String input = "BlackBerry8330/4.5.0.77 Profile/MIDP-2.0 Configuration/CLDC-1.1 VendorID/105";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Optional<DeviceInfo> deviceInfo = deviceLookup.getDeviceInfo(ua);
        assertTrue(deviceInfo.isPresent());
        Validators.validateDeviceInfo(deviceInfo.get(), "BlackBerry", "8330");
    }

    @Test
    public void lenovoThinkPadTablet() {
        final String input = "Mozilla/5.0 (Linux; U; Android 3.1; en-us; ThinkPad Tablet Build/ThinkPadTablet_A310_02) AppleWebKit/534.13 (KHTML, like Gecko) Version/4.0 Safari/534.13";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Optional<DeviceInfo> deviceInfo = deviceLookup.getDeviceInfo(ua);
        assertTrue(deviceInfo.isPresent());
        Validators.validateDeviceInfo(deviceInfo.get(), "Lenovo", "ThinkPad Tablet");
    }

    @Test
    public void microsoftXboxOne() {
        final String input = "Mozilla/5.0 (Windows NT 10.0; Win64; x64; Xbox; Xbox One) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2486.0 Safari/537.36 Edge/13.10553";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Optional<DeviceInfo> deviceInfo = deviceLookup.getDeviceInfo(ua);
        assertTrue(deviceInfo.isPresent());
        Validators.validateDeviceInfo(deviceInfo.get(), "Microsoft", "Xbox One");
    }

    @Test
    public void windowsPC() {
        final String input = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36 Edge/12.246";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Optional<DeviceInfo> deviceInfo = deviceLookup.getDeviceInfo(ua);
        assertTrue(deviceInfo.isPresent());
        Validators.validateDeviceInfo(deviceInfo.get(), "", "Windows PC");
    }

    @Test
    public void appleMacintosh() {
        final String input = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_2) AppleWebKit/601.3.9 (KHTML, like Gecko) Version/9.0.2 Safari/601.3.9";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Optional<DeviceInfo> deviceInfo = deviceLookup.getDeviceInfo(ua);
        assertTrue(deviceInfo.isPresent());
        Validators.validateDeviceInfo(deviceInfo.get(), "Apple", "Macintosh");
    }
}
