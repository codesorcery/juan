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
    public ParsedUserAgent samsungGalaxyS8chromeMobile() {
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

    public static void main(String[] args) throws RunnerException {
        final Options opt = new OptionsBuilder()
                .include(UserAgentParserBenchmark.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}
