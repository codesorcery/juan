# Java Useragent ANalyzer
[![Build Status](https://travis-ci.org/codesorcery/juan.svg?branch=master)](https://travis-ci.org/codesorcery/juan)
[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=com.github.codesorcery%3Ajuan&metric=alert_status)](https://sonarcloud.io/dashboard?id=com.github.codesorcery%3Ajuan)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=com.github.codesorcery%3Ajuan&metric=coverage)](https://sonarcloud.io/dashboard?id=com.github.codesorcery%3Ajuan)

## Usage

```java
import com.github.codesorcery.juan.UserAgentParser;
import com.github.codesorcery.juan.ParsedUserAgent;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

public class UserAgentParserExample {
    private final UserAgentParser userAgentParser;
    
    public UserAgentParserExample() throws IOException {
        URL deviceList =
                new URL("http://storage.googleapis.com/play_public/supported_devices.csv");
        UserAgentParser userAgentParser =
                UserAgentParser.withPlayStoreDeviceList(deviceList, Charset.forName("UTF-16"));
        
        // If you do not need Android device info, you can use the parser without the device list:
        // UserAgentParser userAgentParser = UserAgentParser.withoutPlayStoreDeviceList();
    }
    
    public void parsingExample() {
        String userAgentString =
                "Mozilla/5.0 (Linux; Android 7.0; SM-G892A Build/NRD90M; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/60.0.3112.107 Mobile Safari/537.36";
        ParsedUserAgent parsed =
                userAgentParser.parse(userAgentString);
        
        parsed.agent().getVendor(); // => Google
        parsed.agent().getName(); // => Chrome
        parsed.agent().getVersion(); // => 60.0.3112.107
        
        parsed.os().getVendor(); // => Google
        parsed.os().getName(); // => Android
        parsed.os().getVersion(); // => 7.0
        parsed.os().getType(); // => Mobile
        
        parsed.device().getVendor(); // => Samsung
        parsed.device().getName(); // => Galaxy S8 Active
    }
    
}
```

## Benchmark

Benchmark performed on an Intel&reg; Core&trade; 2 Duo P8600 CPU @ 2.4GHz:

Benchmark                                             | Mode  | Cnt  | Score | Error   | Units
----------------------------------------------------- | ----- | ---- | ----- | ------- | -----
UserAgentParserBenchmark.amazonKindleFireSilkBrowser  | avgt  |   5  | 3.100 | ± 0.026 | us/op
UserAgentParserBenchmark.curl                         | avgt  |   5  | 2.951 | ± 0.180 | us/op
UserAgentParserBenchmark.iPadMobileSafari             | avgt  |   5  | 4.527 | ± 0.505 | us/op
UserAgentParserBenchmark.samsungGalaxyS8chromeMobile  | avgt  |   5  | 5.417 | ± 0.411 | us/op
UserAgentParserBenchmark.windows10Edge                | avgt  |   5  | 4.390 | ± 0.304 | us/op
