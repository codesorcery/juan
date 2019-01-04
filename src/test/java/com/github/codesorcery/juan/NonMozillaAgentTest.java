package com.github.codesorcery.juan;

import com.github.codesorcery.juan.agent.Agent;
import com.github.codesorcery.juan.token.TokenizedUserAgent;
import org.junit.jupiter.api.Test;

public class NonMozillaAgentTest {
    @Test
    public void windowsLiveMail() {
        final String input = "Outlook-Express/7.0 (MSIE 7.0; Windows NT 6.1; WOW64; Trident/4.0; SLCC2; Media Center PC 6.0; OfficeLiveConnector.1.4; OfficeLivePatch.1.3; InfoPath.3; FDM; TmstmpExt)";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "Microsoft", "Windows Live Mail", "7.0", "Application (desktop)");
    }

    @Test
    public void curl() {
        final String input = "curl/7.15.1 (x86_64-suse-linux) libcurl/7.15.1 OpenSSL/0.9.8a zlib/1.2.3 libidn/0.6.0";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "haxx", "curl", "7.15.1", "Application (command line)");
    }

    @Test
    public void wget() {
        final String input = "Wget/1.16 (linux-gnu)";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "FSF", "GNU Wget", "1.16", "Application (command line)");
    }

    @Test
    public void apacheHttpClient() {
        final String input = "Apache-HttpClient/4.1.3 (java 1.5)";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "Apache Foundation", "Apache HttpClient", "4.1.3", "Library");
    }

    @Test
    public void okHttp() {
        final String input = "okhttp/2.5.0";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "Square", "OkHttp (Java library)", "2.5.0", "Library");
    }

    @Test
    public void pythonRequests() {
        final String input = "python-requests/0.8.1";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "Kenneth Reitz", "requests (Python library)", "0.8.1", "Library");
    }

    @Test
    public void pythonUrllib() {
        final String input = "Python-urllib/2.7";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "Python Software Foundation", "urllib (Python libary)", "2.7", "Library");
    }

    @Test
    public void aria2() {
        final String input = "aria2/1.23.0";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "Tatsuhiro Tsujikawa", "aria2", "1.23.0", "Application (command line)");
    }

    @Test
    public void w3cLinkChecker() {
        final String input = "W3C-checklink/3.6.2.3 libwww-perl/5.64";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "W3C", "W3C Link Checker", "3.6.2.3", "Validator");
    }

    @Test
    public void w3cCssValidator() {
        final String input = "Jigsaw/2.2.5 W3C_CSS_Validator_JFouffa/2.0";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "W3C", "W3C CSS Validator", "2.2.5", "Validator");
    }

    @Test
    public void w3cInternationalizationChecker() {
        final String input = "W3C_I18n-Checker/1.0";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "W3C", "W3C Internationalization Checker", "1.0", "Validator");
    }

    @Test
    public void w3cUnicorn() {
        final String input = "W3C_Unicorn/1.0 (http://validator.w3.org/services)";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "W3C", "Unicorn - W3C's Unified Validator", "1.0", "Validator");
    }

    @Test
    public void w3cValidator() {
        final String input = "W3C_Validator/1.654";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "W3C",  "W3C Validator", "1.654", "Validator");
    }

    @Test
    public void vlc() {
        final String input = "VLC/2.2.6 LibVLC/2.2.6";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "VideoLAN team", "VLC Media Player", "2.2.6", "Media player");
    }

    @Test
    public void webDeMail() {
        final String input = "WEB.DE%20Mail/4815 CFNetwork/758.4.3 Darwin/15.5.0";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "1&1", "WEB.DE Mail App", "4815", "Application (mobile)");
    }

    @Test
    public void gmxMail() {
        final String input = "GMX%20Mail/4815 CFNetwork/758.4.3 Darwin/15.5.0";
        final TokenizedUserAgent ua = TokenizedUserAgent.forUserAgentString(input);
        final Agent agent = Agent.fromUserAgent(ua);

        Validators.validateAgent(agent,
                "1&1", "GMX Mail App", "4815", "Application (mobile)");
    }
}
