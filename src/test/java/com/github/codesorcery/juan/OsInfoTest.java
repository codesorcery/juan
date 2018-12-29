package com.github.codesorcery.juan;

import com.github.codesorcery.juan.os.OperatingSystem;
import com.github.codesorcery.juan.token.TokenizedUserAgent;
import org.junit.jupiter.api.Test;

public class OsInfoTest {

    @Test
    public void emptyString() {
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString("");
        final OperatingSystem os = OperatingSystem.fromUserAgent(ua);

        Validators.validateOS(os, "", "", "", "");
    }

    @Test
    public void randomString() {
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString("Mozilla/5.0 (foo; bar) foobar/1.0 [foo/2.0; bar/3.0]");
        final OperatingSystem os = OperatingSystem.fromUserAgent(ua);

        Validators.validateOS(os, "", "", "", "");
    }

    @Test
    public void samsungGalaxyS8() {
        final String input = "Mozilla/5.0 (Linux; Android 7.0; SM-G892A Build/NRD90M; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/60.0.3112.107 Mobile Safari/537.36";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final OperatingSystem os = OperatingSystem.fromUserAgent(ua);

        Validators.validateOS(os, "Google", "Android", "7.0", "Mobile");
    }

    @Test
    public void htcOneX10() {
        final String input = "Mozilla/5.0 (Linux; Android 6.0; HTC One X10 Build/MRA58K; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/61.0.3163.98 Mobile Safari/537.36";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final OperatingSystem os = OperatingSystem.fromUserAgent(ua);
        
        Validators.validateOS(os, "Google", "Android", "6.0", "Mobile");
    }

    @Test
    public void kindleFireHDX7() {
        final String input = "Mozilla/5.0 (Linux; Android 4.4.3; KFTHWI Build/KTU84M) AppleWebKit/537.36 (KHTML, like Gecko) Silk/47.1.79 like Chrome/47.0.2526.80 Safari/537.36";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final OperatingSystem os = OperatingSystem.fromUserAgent(ua);

        Validators.validateOS(os, "Google", "Android", "4.4.3", "Mobile");
    }

    @Test
    public void unknownLinuxBased() {
        final String input = "Mozilla/5.0 (Linux; foobar)";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final OperatingSystem os = OperatingSystem.fromUserAgent(ua);

        Validators.validateOS(os, "", "", "", "");
    }

    @Test
    public void iPhoneX() {
        final String input = "Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final OperatingSystem os = OperatingSystem.fromUserAgent(ua);

        Validators.validateOS(os, "Apple", "iOS", "11.0", "Mobile");
    }

    @Test
    public void windows10Edge() {
        final String input = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36 Edge/12.246";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final OperatingSystem os = OperatingSystem.fromUserAgent(ua);

        Validators.validateOS(os, "Microsoft", "Windows", "10", "Desktop");
    }

    @Test
    public void unknownWithCompatibleToken() {
        final String input = "Mozilla/4.0 (compatible; foobar)";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final OperatingSystem os = OperatingSystem.fromUserAgent(ua);

        Validators.validateOS(os, "", "", "", "");
    }

    @Test
    public void windows200InternetExplorer() {
        final String input = "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727)";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final OperatingSystem os = OperatingSystem.fromUserAgent(ua);

        Validators.validateOS(os, "Microsoft", "Windows", "2000", "Desktop");
    }

    @Test
    public void windowsXPInternetExplorer() {
        final String input = "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322)";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final OperatingSystem os = OperatingSystem.fromUserAgent(ua);

        Validators.validateOS(os, "Microsoft", "Windows", "XP", "Desktop");
    }

    @Test
    public void windowsVistaInternetExplorer() {
        final String input = "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0)";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final OperatingSystem os = OperatingSystem.fromUserAgent(ua);

        Validators.validateOS(os, "Microsoft", "Windows", "Vista", "Desktop");
    }

    @Test
    public void windows7Chrome() {
        final String input = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.111 Safari/537.36";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final OperatingSystem os = OperatingSystem.fromUserAgent(ua);

        Validators.validateOS(os, "Microsoft", "Windows", "7", "Desktop");
    }

    @Test
    public void windows8Chrome() {
        final String input = "Mozilla/5.0 (Windows NT 6.2; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.90 Safari/537.36";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final OperatingSystem os = OperatingSystem.fromUserAgent(ua);

        Validators.validateOS(os, "Microsoft", "Windows", "8", "Desktop");
    }

    @Test
    public void windows8_1Chrome() {
        final String input = "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final OperatingSystem os = OperatingSystem.fromUserAgent(ua);

        Validators.validateOS(os, "Microsoft", "Windows", "8.1", "Desktop");
    }

    @Test
    public void macOSSafari() {
        final String input = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_2) AppleWebKit/601.3.9 (KHTML, like Gecko) Version/9.0.2 Safari/601.3.9";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final OperatingSystem os = OperatingSystem.fromUserAgent(ua);

        Validators.validateOS(os, "Apple", "macOS", "10.11.2", "Desktop");
    }

    @Test
    public void ubuntuFirefox() {
        final String input = "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:15.0) Gecko/20100101 Firefox/15.0.1";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final OperatingSystem os = OperatingSystem.fromUserAgent(ua);

        Validators.validateOS(os, "", "Linux (Ubuntu)", "", "Desktop");
    }

    @Test
    public void freeBSDChrome() {
        final String input = "Mozilla/5.0 (X11; FreeBSD; U; Viera; en-IE) AppleWebKit/537.11 (KHTML, like Gecko) Viera/3.18.1 Chrome/23.0.1271.97 Safari/537.11";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final OperatingSystem os = OperatingSystem.fromUserAgent(ua);

        Validators.validateOS(os, "", "FreeBSD", "", "Desktop");
    }

    @Test
    public void openBSDChrome() {
        final String input = "Mozilla/5.0 (X11; OpenBSD amd64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.82 Safari/537.36";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final OperatingSystem os = OperatingSystem.fromUserAgent(ua);

        Validators.validateOS(os, "", "OpenBSD", "", "Desktop");
    }

    @Test
    public void netBSDChrome() {
        final String input = "Mozilla/5.0 (X11; NetBSD i386) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.104 Safari/537.36";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final OperatingSystem os = OperatingSystem.fromUserAgent(ua);

        Validators.validateOS(os, "", "NetBSD", "", "Desktop");
    }

    @Test
    public void dragonFlyBSDFirefox() {
        final String input = "Mozilla/5.0 (X11; U; DragonFly i386; de; rv:1.9.1) Gecko/20090720 Firefox/3.5.1";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final OperatingSystem os = OperatingSystem.fromUserAgent(ua);

        Validators.validateOS(os, "", "DragonFly BSD", "", "Desktop");
    }

    @Test
    public void chromeBook() {
        final String input = "Mozilla/5.0 (X11; CrOS x86_64 8172.45.0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.64 Safari/537.36";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final OperatingSystem os = OperatingSystem.fromUserAgent(ua);

        Validators.validateOS(os, "", "Chrome OS", "", "Desktop");
    }

    @Test
    public void kindle4() {
        final String input = "Mozilla/5.0 (X11; U; Linux armv7l like Android; en-us) AppleWebKit/531.2+ (KHTML, like Gecko) Version/5.0 Safari/533.2+ Kindle/3.0+";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final OperatingSystem os = OperatingSystem.fromUserAgent(ua);

        Validators.validateOS(os, "", "Linux", "", "Desktop");
    }

    @Test
    public void ubuntuGnomeWeb() {
        final String input = "Mozilla/5.0 (X11; U; Linux x86_64; it-it) AppleWebKit/534.26+ (KHTML, like Gecko) Ubuntu/11.04 Epiphany/2.30.6";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final OperatingSystem os = OperatingSystem.fromUserAgent(ua);

        Validators.validateOS(os, "", "Linux (Ubuntu)", "11.04", "Desktop");
    }

    @Test
    public void fedoraGnomeWeb() {
        final String input = "Mozilla/5.0 (X11; U; Linux x86_64; en; rv:1.9.0.12) Gecko/20080528 Fedora/2.24.3-8.fc10 Epiphany/2.22 Firefox/3.0";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final OperatingSystem os = OperatingSystem.fromUserAgent(ua);

        Validators.validateOS(os, "", "Linux (Fedora)", "2.24.3-8.fc10", "Desktop");
    }

    @Test
    public void kaiOS() {
        final String input = "Mozilla/5.0 (Mobile; LYF/F90M/LYF-F90M-000-02-21-131117; rv:48.0) Gecko/48.0 Firefox/48.0 KAIOS/2.0";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final OperatingSystem os = OperatingSystem.fromUserAgent(ua);

        Validators.validateOS(os, "KaiOS Technologies Inc.", "KaiOS", "2.0", "Mobile");
    }

    @Test
    public void mobileFirefoxOs() {
        final String input = "Mozilla/5.0 (Mobile; ZTEOPEN; rv:18.1) Gecko/18.1 Firefox/18.1";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final OperatingSystem os = OperatingSystem.fromUserAgent(ua);

        Validators.validateOS(os, "Mozilla", "Firefox OS", "18.1", "Mobile");
    }

    @Test
    public void microsoftLumia640WindowsPhone() {
        final String input = "Mozilla/5.0 (Mobile; Windows Phone 8.1; Android 4.0; ARM; Trident/7.0; Touch; rv:11.0; IEMobile/11.0; Microsoft; Lumia 640 LTE) like iPhone OS 7_0_3 Mac OS X AppleWebKit/537 (KHTML, like Gecko) Mobile Safari/537";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final OperatingSystem os = OperatingSystem.fromUserAgent(ua);

        Validators.validateOS(os, "Microsoft", "Windows Phone", "8.1", "Mobile");
    }

    @Test
    public void microsoftLumia535WindowsPhone() {
        final String input = "Mozilla/5.0 (Mobile; Windows Phone 8.1; Android 4.0; ARM; Trident/7.0; Touch; rv:11.0; IEMobile/11.0; Microsoft; Lumia 535) like iPhone OS 7_0_3 Mac OS X AppleWebKit/537 (KHTML, like Gecko) Mobile Safari/537";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final OperatingSystem os = OperatingSystem.fromUserAgent(ua);

        Validators.validateOS(os, "Microsoft", "Windows Phone", "8.1", "Mobile");
    }

    @Test
    public void hpWebOsTablet() {
        final String input = "Mozilla/5.0 (hp-tablet; Linux; hpwOS/3.0.5; U; en-GB) AppleWebKit/534.6 (KHTML, like Gecko) wOSBrowser/234.83 Safari/534.6 TouchPad/1.0";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final OperatingSystem os = OperatingSystem.fromUserAgent(ua);

        Validators.validateOS(os, "HP", "WebOS", "3.0.5", "Mobile");
    }

    @Test
    public void blackBerryPlayBookTablet() {
        final String input = "Mozilla/5.0 (PlayBook; U; RIM Tablet OS 2.1.0; en-US) AppleWebKit/536.2+ (KHTML, like Gecko) Version/7.2.1.0 Safari/536.2+";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final OperatingSystem os = OperatingSystem.fromUserAgent(ua);

        Validators.validateOS(os, "BlackBerry", "RIM Tablet OS", "2.1.0", "Mobile");
    }
}
