# Java Useragent ANalyzer
[![Build Status](https://travis-ci.org/codesorcery/juan.svg?branch=master)](https://travis-ci.org/codesorcery/juan)
[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=com.github.codesorcery%3Ajuan&metric=alert_status)](https://sonarcloud.io/dashboard?id=com.github.codesorcery%3Ajuan)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=com.github.codesorcery%3Ajuan&metric=coverage)](https://sonarcloud.io/dashboard?id=com.github.codesorcery%3Ajuan)
[![License](https://img.shields.io/github/license/codesorcery/juan.svg)](https://choosealicense.com/licenses/mit/)
[![Maven Central](https://img.shields.io/maven-central/v/com.github.codesorcery/juan.svg)](https://search.maven.org/search?q=g:%22com.github.codesorcery%22%20AND%20a:%22juan%22)

A fast, thread-safe and dependency-free user agent parser for Java 8+.

## Usage

Simply add the library to the dependency section of your pom.xml (if you're using Maven):
```xml
<dependency>
  <groupId>com.github.codesorcery</groupId>
  <artifactId>juan</artifactId>
  <version>0.2.0</version>
</dependency>
```
Or add it to your build.gradle (if you're using Gradle):
````groovy
dependencies {
  compile('com.github.codesorcery:juan:0.2.0')
}
````

The library can then be used as follows:
```java
import com.github.codesorcery.juan.UserAgentParser;
import com.github.codesorcery.juan.ParsedUserAgent;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

public class UserAgentParserExample {
    private final UserAgentParser userAgentParser;
    
    public UserAgentParserExample() throws IOException {
        // To detect the device name and vendor for Android devices, we use Google's device list:
        URL deviceList =
                new URL("http://storage.googleapis.com/play_public/supported_devices.csv");
        userAgentParser =
                UserAgentParser.withPlayStoreDeviceList(deviceList, Charset.forName("UTF-16"));
        
        // If you do not need Android device info, you can use the parser without the device list:
        // userAgentParser = UserAgentParser.withoutPlayStoreDeviceList();
    }
    
    public void parsingExample() {
        String userAgentString =
                "Mozilla/5.0 (Linux; Android 7.0; SM-G892A Build/NRD90M; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/60.0.3112.107 Mobile Safari/537.36";
        ParsedUserAgent parsed =
                userAgentParser.parse(userAgentString);
        
        parsed.agent().getVendor(); // => Google
        parsed.agent().getName(); // => Chrome Mobile
        parsed.agent().getVersion(); // => 60.0.3112.107
        parsed.agent().getType(); // => Browser (mobile)
        parsed.agent().isCrawler(); // => false
        
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
----------------------------------------------------- | ----- | ---- | ----- | ------- | ------
UserAgentParserBenchmark.amazonKindleFireSilkBrowser  | avgt  |   5  | 4.039 | ± 0.083 |  us/op
UserAgentParserBenchmark.curl                         | avgt  |   5  | 1.791 | ± 0.138 |  us/op
UserAgentParserBenchmark.iPadMobileSafari             | avgt  |   5  | 4.895 | ± 0.045 |  us/op
UserAgentParserBenchmark.iPhoneInstagram              | avgt  |   5  | 5.209 | ± 0.106 |  us/op
UserAgentParserBenchmark.linuxFirefox                 | avgt  |   5  | 3.516 | ± 0.029 |  us/op
UserAgentParserBenchmark.macOsSafari                  | avgt  |   5  | 3.026 | ± 0.030 |  us/op
UserAgentParserBenchmark.samsungGalaxyS8ChromeMobile  | avgt  |   5  | 5.188 | ± 0.089 |  us/op
UserAgentParserBenchmark.windows10Edge                | avgt  |   5  | 3.886 | ± 0.064 |  us/op
UserAgentParserBenchmark.windowsPhone                 | avgt  |   5  | 8.444 | ± 0.079 |  us/op
