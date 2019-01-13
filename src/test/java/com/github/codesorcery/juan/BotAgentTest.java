package com.github.codesorcery.juan;

import com.github.codesorcery.juan.agent.Agent;
import com.github.codesorcery.juan.token.TokenizedUserAgent;
import org.junit.jupiter.api.Test;

public class BotAgentTest {
    @Test
    public void googleBot1() {
        final String input = "Googlebot/2.1 (+http://www.google.com/bot.html)";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "Google", "Googlebot", "2.1", "Web crawler");
    }

    @Test
    public void googleBot2() {
        final String input = "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "Google", "Googlebot", "2.1", "Web crawler");
    }

    @Test
    public void googleBot3() {
        final String input = "Mozilla/5.0 (Linux; Android 6.0.1; Nexus 5X Build/MMB29P) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.96 Mobile Safari/537.36 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "Google", "Googlebot", "2.1", "Web crawler");
    }

    @Test
    public void googleBotMobile() {
        final String input = "Mozilla/5.0 (Linux; Android 6.0.1; Nexus 5X Build/MMB29P) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.96 Mobile Safari/537.36 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "Google", "Googlebot", "2.1", "Web crawler");
    }

    @Test
    public void googleBotImage() {
        final String input = "Googlebot-Image/1.0";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "Google", "Googlebot Images", "1.0", "Web crawler");
    }

    @Test
    public void googleBotNews() {
        final String input = "Googlebot-News";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "Google", "Googlebot News", "", "Web crawler");
    }

    @Test
    public void googleBotVideo() {
        final String input = "Googlebot-Video/1.0";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "Google", "Googlebot Video", "1.0", "Web crawler");
    }

    @Test
    public void googleAdsBot() {
        final String input = "AdsBot-Google (+http://www.google.com/adsbot.html)";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "Google", "AdsBot", "", "Web crawler");
    }

    @Test
    public void googleAdsBotMobile() {
        final String input = "Mozilla/5.0 (Linux; Android 5.0; SM-G920A) AppleWebKit (KHTML, like Gecko) Chrome Mobile Safari (compatible; AdsBot-Google-Mobile; +http://www.google.com/mobile/adsbot.html)";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "Google", "AdsBot Mobile Web", "", "Web crawler");
    }

    @Test
    public void googleAdSense() {
        final String input = "Mediapartners-Google";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "Google", "AdSense", "", "Web crawler");
    }

    @Test
    public void googleAPIs() {
        final String input = "APIs-Google (+https://developers.google.com/webmasters/APIs-Google.html)";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "Google", "Google APIs", "", "Web crawler");
    }

    @Test
    public void microsoftBingbot1() {
        final String input = "Mozilla/5.0 (compatible; bingbot/2.0; +http://www.bing.com/bingbot.htm)";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "Microsoft", "Bingbot", "2.0", "Web crawler");
    }

    @Test
    public void microsoftBingbot2() {
        final String input = "Mozilla/5.0 (iPhone; CPU iPhone OS 7_0 like Mac OS X) AppleWebKit/537.51.1 (KHTML, like Gecko) Version/7.0 Mobile/11A465 Safari/9537.53 (compatible; bingbot/2.0; +http://www.bing.com/bingbot.htm)";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "Microsoft", "Bingbot", "2.0", "Web crawler");
    }


    @Test
    public void microsoftBingbot3() {
        final String input = "Mozilla/5.0 (Windows Phone 8.1; ARM; Trident/7.0; Touch; rv:11.0; IEMobile/11.0; NOKIA; Lumia 530) like Gecko (compatible; bingbot/2.0; +http://www.bing.com/bingbot.htm)";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "Microsoft", "Bingbot", "2.0", "Web crawler");
    }

    @Test
    public void microsoftMSNBot() {
        final String input = "msnbot/2.0b (+http://search.msn.com/msnbot.htm)";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "Microsoft", "MSNBot", "2.0b", "Web crawler");
    }

    @Test
    public void microsoftMSNBotMedia() {
        final String input = "msnbot-media/1.1 (+http://search.msn.com/msnbot.htm)";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "Microsoft", "MSNBot-Media", "1.1", "Web crawler");
    }

    @Test
    public void microsoftAdIdxBot1() {
        final String input = "Mozilla/5.0 (compatible; adidxbot/2.0; +http://www.bing.com/bingbot.htm)";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "Microsoft", "AdIdxBot", "2.0", "Web crawler");
    }

    @Test
    public void microsoftAdIdxBot2() {
        final String input = "Mozilla/5.0 (iPhone; CPU iPhone OS 7_0 like Mac OS X) AppleWebKit/537.51.1 (KHTML, like Gecko) Version/7.0 Mobile/11A465 Safari/9537.53 (compatible; adidxbot/2.0; +http://www.bing.com/bingbot.htm)";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "Microsoft", "AdIdxBot", "2.0", "Web crawler");
    }

    @Test
    public void microsoftAdIdxBot3() {
        final String input = "Mozilla/5.0 (Windows Phone 8.1; ARM; Trident/7.0; Touch; rv:11.0; IEMobile/11.0; NOKIA; Lumia 530) like Gecko (compatible; adidxbot/2.0; +http://www.bing.com/bingbot.htm)";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "Microsoft", "AdIdxBot", "2.0", "Web crawler");
    }

    @Test
    public void microsoftBingPreview1() {
        final String input = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/534+ (KHTML, like Gecko) BingPreview/1.0b";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "Microsoft", "BingPreview", "1.0b", "Web crawler");
    }

    @Test
    public void microsoftBingPreview2() {
        final String input = "Mozilla/5.0 (Windows Phone 8.1; ARM; Trident/7.0; Touch; rv:11.0; IEMobile/11.0; NOKIA; Lumia 530) like Gecko BingPreview/1.0b";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "Microsoft", "BingPreview", "1.0b", "Web crawler");
    }
}
