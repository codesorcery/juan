package com.github.codesorcery.juan.benchmark;

import com.github.codesorcery.juan.ParsedUserAgent;
import com.github.codesorcery.juan.UserAgentParser;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 2)
@Measurement(iterations = 5)
@Fork(1)
public class UserAgentParserBenchmark {
    private final UserAgentParser userAgentParser;

    public UserAgentParserBenchmark() {
        try {
            userAgentParser = UserAgentParser.withPlayStoreDeviceList(
                    new URL("http://storage.googleapis.com/play_public/supported_devices.csv"),
                    Charset.forName("UTF-16")
            );
        } catch (IOException e) {
            throw new RuntimeException("Initialization failed", e);
        }
    }

    @Benchmark
    public ParsedUserAgent curl() {
        return userAgentParser.parse("curl/7.15.1 (x86_64-suse-linux) libcurl/7.15.1 OpenSSL/0.9.8a zlib/1.2.3 libidn/0.6.0");
    }

    @Benchmark
    public ParsedUserAgent iPadMobileSafari() {
        return userAgentParser.parse("Mozilla/5.0 (iPad; CPU OS 7_0 like Mac OS X) AppleWebKit/537.51.1 (KHTML, like Gecko) Version/7.0 Mobile/11A465 Safari/9537.53");
    }

    @Benchmark
    public ParsedUserAgent samsungGalaxyS8ChromeMobile() {
        return userAgentParser.parse("Mozilla/5.0 (Linux; Android 7.0; SM-G892A; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/60.0.3112.107 Mobile Safari/537.36");
    }

    @Benchmark
    public ParsedUserAgent amazonKindleFireSilkBrowser() {
        return userAgentParser.parse("Mozilla/5.0 (Linux; Android 4.4.3; KFTHWI Build/KTU84M) AppleWebKit/537.36 (KHTML, like Gecko) Silk/47.1.79 like Chrome/47.0.2526.80 Safari/537.36");
    }

    @Benchmark
    public ParsedUserAgent windows10Edge() {
        return userAgentParser.parse("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36 Edge/12.246");
    }

    @Benchmark
    public ParsedUserAgent windowsPhone() {
        return userAgentParser.parse("Mozilla/5.0 (Mobile; Windows Phone 8.1; Android 4.0; ARM; Trident/7.0; Touch; rv:11.0; IEMobile/11.0; Microsoft; Lumia 535) like iPhone OS 7_0_3 Mac OS X AppleWebKit/537 (KHTML, like Gecko) Mobile Safari/537");
    }

    @Benchmark
    public ParsedUserAgent macOsSafari() {
        return userAgentParser.parse("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_2) AppleWebKit/601.3.9 (KHTML, like Gecko) Version/9.0.2 Safari/601.3.9");
    }

    @Benchmark
    public ParsedUserAgent linuxFirefox() {
        return userAgentParser.parse("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_2) AppleWebKit/601.3.9 (KHTML, like Gecko) Version/9.0.2 Safari/601.3.9");
    }

    @Benchmark
    public ParsedUserAgent iPhoneInstagram() {
        return userAgentParser.parse("Mozilla/5.0 (iPhone; CPU iPhone OS 11_4_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15G77 Instagram 64.0.0.12.96 (iPhone10,6; iOS 11_4_1; en_AT; en; scale=3.00; gamut=wide; 1125x2436; 124976489)");
    }

    public static void main(String[] args) throws RunnerException {
        final Options opt = new OptionsBuilder()
                .include(UserAgentParserBenchmark.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}
