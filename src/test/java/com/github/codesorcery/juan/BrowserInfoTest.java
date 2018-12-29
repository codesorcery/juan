package com.github.codesorcery.juan;

import com.github.codesorcery.juan.agent.Agent;
import com.github.codesorcery.juan.token.TokenizedUserAgent;
import org.junit.jupiter.api.Test;

public class BrowserInfoTest {

    @Test
    public void emptyString() {
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString("");
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent, "", "", "", "");
    }

    @Test
    public void randomString() {
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString("Mozilla/5.0 (foo; bar) foobar/1.0 [foo/2.0; bar/3.0]");
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent, "", "", "", "");
    }

    @Test
    public void firefoxIOS() {
        final String input = "Mozilla/5.0 (iPhone; CPU iPhone OS 10_3_2 like Mac OS X) AppleWebKit/603.2.4 (KHTML, like Gecko) FxiOS/7.5b3349 Mobile/14F89 Safari/603.2.4";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "Mozilla", "Firefox Mobile", "7.5b3349", "Browser (mobile)");
    }

    @Test
    public void ucBrowser() {
        final String input = "Mozilla/5.0 (Linux; U; Android 7.0; en-US; SM-G935F Build/NRD90M) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 UCBrowser/11.3.8.976 U3/0.8.0 Mobile Safari/534.30";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "UCWeb", "UC Browser", "11.3.8.976", "Browser (mobile)");
    }

    @Test
    public void samsungBrowser() {
        final String input = "Mozilla/5.0 (Linux; Android 7.0; SAMSUNG SM-G955U Build/NRD90M) AppleWebKit/537.36 (KHTML, like Gecko) SamsungBrowser/5.4 Chrome/51.0.2704.106 Mobile Safari/537.36";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "Samsung", "Samsung Browser", "5.4", "Browser (mobile)");
    }

    @Test
    public void yandexBrowser() {
        final String input = "Mozilla/5.0 (Linux; Android 6.0; Lenovo K50a40 Build/MRA58K) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.137 YaBrowser/17.4.1.352.00 Mobile Safari/537.36";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "Yandex", "Yandex Browser", "17.4.1.352.00", "Browser (mobile)");
    }

    @Test
    public void edgeMobile() {
        final String input = "Mozilla/5.0 (Windows Phone 10.0; Android 6.0.1; Microsoft; Lumia 950) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Mobile Safari/537.36 Edge/15.14977";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "Microsoft", "Edge Mobile", "15.14977", "Browser (mobile)");
    }

    @Test
    public void edgeWindows10() {
        final String input = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36 Edge/12.246";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "Microsoft", "Edge", "12.246", "Browser (desktop)");
    }

    @Test
    public void firefoxLinux() {
        final String input = "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:15.0) Gecko/20100101 Firefox/15.0.1";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "Mozilla", "Firefox", "15.0.1", "Browser (desktop)");
    }

    @Test
    public void chromeWindows() {
        final String input = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.111 Safari/537.36";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "Google", "Chrome", "47.0.2526.111", "Browser (desktop)");
    }

    @Test
    public void safariMacOS() {
        final String input = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_2) AppleWebKit/601.3.9 (KHTML, like Gecko) Version/9.0.2 Safari/601.3.9";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "Apple", "Safari", "9.0.2", "Browser (desktop)");
    }

    @Test
    public void safariiPad() {
        final String input = "Mozilla/5.0 (iPad; CPU OS 7_0 like Mac OS X) AppleWebKit/537.51.1 (KHTML, like Gecko) Version/7.0 Mobile/11A465 Safari/9537.53";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "Apple", "Safari Mobile", "7.0", "Browser (mobile)");
    }

    @Test
    public void konquerorLinux() {
        final String input = "Mozilla/5.0 (X11; Linux x86_64) KHTML/5.28.0 (like Gecko) Konqueror/5.28";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "KDE", "Konqueror", "5.28", "Browser (desktop)");
    }

    @Test
    public void ironWindows() {
        final String input = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2050.0 Iron/38.0.2150.0 Safari/537.36";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "SRWare", "Iron", "38.0.2150.0", "Browser (desktop)");
    }

    @Test
    public void ironMobile() {
        final String input = "Mozilla/5.0 (Linux; Android 4.4.2; SM-G800F Build/KOT49H) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1985.135 MobileIron/1.6.0 Mobile Safari/537.36";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "SRWare", "Iron Mobile", "1.6.0", "Browser (mobile)");
    }

    @Test
    public void gnomeWebLinux() {
        final String input = "Mozilla/5.0 (X11; U; Linux x86_64; it-it) AppleWebKit/534.26+ (KHTML, like Gecko) Ubuntu/11.04 Epiphany/2.30.6";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "GNOME Foundation", "GNOME Web", "2.30.6", "Browser (desktop)");
    }

    @Test
    public void silkKindleFire() {
        final String input = "Mozilla/5.0 (Linux; Android 4.4.3; KFTHWI Build/KTU84M) AppleWebKit/537.36 (KHTML, like Gecko) Silk/47.1.79 like Chrome/47.0.2526.80 Safari/537.36";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "Amazon", "Silk", "47.1.79", "Browser (mobile)");
    }

    @Test
    public void nintendoWiiUBrowser() {
        final String input = "Mozilla/5.0 (Nintendo WiiU) AppleWebKit/536.30 (KHTML, like Gecko) NX/3.0.4.2.12 NintendoBrowser/4.3.1.11264.US";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "Nintendo", "Nintendo Browser", "4.3.1.11264.US", "Browser (embedded)");
    }

    @Test
    public void xBoxOneBrowser() {
        final String input = "Mozilla/5.0 (Windows Phone 10.0; Android 4.2.1; Xbox; Xbox One) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2486.0 Mobile Safari/537.36 Edge/13.10586";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "Microsoft", "Edge Mobile", "13.10586", "Browser (mobile)");
    }

    @Test
    public void androidBrowser() {
        final String input = "Mozilla/5.0 (Linux; U; Android 4.1.2; en-us; SPH-P500 Build/JZO54K) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Safari/534.30";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "Google", "Android Browser", "4.0", "Browser (mobile)");
    }

    @Test
    public void androidBrowser2() {
        final String input = "Mozilla/5.0 (Linux; U; Android 4.4.4; ko-kr; SM-G720N0 Build/KTU84P) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "Google", "Android Browser", "4.0", "Browser (mobile)");
    }

    @Test
    public void chromeAndroid() {
        final String input = "Mozilla/5.0 (Linux; Android 6.0.1; SM-G532G Build/MMB29T) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.83 Mobile Safari/537.36";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "Google", "Chrome Mobile", "63.0.3239.83", "Browser (mobile)");
    }

    @Test
    public void adobeAir() {
        final String input = "Mozilla/5.0 (Macintosh; U; Intel Mac OS X; de) AppleWebKit/533.19.4 (KHTML, like Gecko) AdobeAIR/13.0";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "Adobe", "Adobe AIR Runtime", "13.0", "Library");
    }

    @Test
    public void facebookAndroid() {
        final String input = "Mozilla/5.0 (Linux; Android 4.4.2; VS880 Build/KOT49I.VS88012A) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/30.0.0.0 Mobile Safari/537.36 [FB_IAB/FB4A;FBAV/28.0.0.20.16;]";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "Facebook", "Facebook App", "28.0.0.20.16", "Application (mobile)");
    }

    @Test
    public void facebookiOS() {
        final String input = "Mozilla/5.0 (iPad; CPU OS 6_0_1 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Mobile/10A523 [FBAN/FBIOS;FBAV/6.0.1;FBBV/180945;FBDV/iPad2,1;FBMD/iPad;FBSN/iPhone OS;FBSV/6.0.1;FBSS/1; FBCR/;FBID/tablet;FBLC/en_US;FBOP/1]";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "Facebook", "Facebook App", "6.0.1", "Application (mobile)");
    }

    @Test
    public void bingSearchAndroid() {
        final String input = "Mozilla/5.0 (Linux; Android 6.0.1; SM-G550T Build/MMB29K; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 Mobile Safari/537.36 BingWeb/6.7.25183495";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "Microsoft", "Bing Search App", "6.7.25183495", "Application (mobile)");
    }

    @Test
    public void internetExplorerWindows10() {
        final String input = "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "Microsoft", "Internet Explorer", "11.0", "Browser (desktop)");
    }

    @Test
    public void vieraTV() {
        final String input = "Mozilla/5.0 (X11; FreeBSD; U; Viera; en-IE) AppleWebKit/537.11 (KHTML, like Gecko) Viera/3.18.1 Chrome/23.0.1271.97 Safari/537.11";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "Panasonic", "VIERA TV Browser", "3.18.1", "Browser (embedded)");
    }

    @Test
    public void seaMonkeyFreeBSD() {
        final String input = "Mozilla/5.0 (X11; OpenBSD i386; rv:43.0) Gecko/20100101 Firefox/43.0 SeaMonkey/2.40";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "Mozilla", "SeaMonkey", "2.40", "Browser (desktop)");
    }

    @Test
    public void webOsBrowser() {
        final String input = "Mozilla/5.0 (hp-tablet; Linux; hpwOS/3.0.5; U; en-GB) AppleWebKit/534.6 (KHTML, like Gecko) wOSBrowser/234.83 Safari/534.6 TouchPad/1.0";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "HP", "WebOS Browser", "234.83", "Browser (mobile)");
    }

    @Test
    public void electron() {
        final String input = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_3) AppleWebKit/537.36 (KHTML, like Gecko) nagi-test/1.0.0 Chrome/58.0.3029.110 Electron/1.7.10 Safari/537.36";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "Electron community", "Electron framework", "1.7.10", "Application (desktop)");
    }

    @Test
    public void pinterestAndroid() {
        final String input = "Mozilla/5.0 (Linux; U; Android 4.0.3; en-us; KFTT Build/IML74K) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Safari/534.30 [Pinterest/Android]";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "Pinterest", "Pinterest App", "Android", "Application (mobile)");
    }

    @Test
    public void pinterestiOS() {
        final String input = "Mozilla/5.0 (iPhone; CPU iPhone OS 11_4_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15G77 [Pinterest/iOS]";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "Pinterest", "Pinterest App", "iOS", "Application (mobile)");
    }

    @Test
    public void paleMoonBrowser() {
        final String input = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.9) Gecko/20100101 Goanna/3.4 Firefox/52.9 PaleMoon/27.9.1";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "M.C. Straver", "PaleMoon Browser", "27.9.1", "Browser (desktop)");
    }

}
