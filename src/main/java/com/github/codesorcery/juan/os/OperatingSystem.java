package com.github.codesorcery.juan.os;

import com.github.codesorcery.juan.token.StringToken;
import com.github.codesorcery.juan.token.TokenizedUserAgent;
import com.github.codesorcery.juan.token.VersionedToken;
import com.github.codesorcery.juan.util.Tokens;

import java.util.List;
import java.util.StringJoiner;

public abstract class OperatingSystem {
    private final String vendor;
    private final String name;
    private final String version;
    private final String type;

    protected OperatingSystem(final String vendor,
                              final String name,
                              final String version,
                              final String type) {
        this.vendor = vendor;
        this.name = name;
        this.version = version;
        this.type = type;
    }

    public static OperatingSystem fromUserAgent(final TokenizedUserAgent source) {
        final List<VersionedToken> tokens = source.getSystemTokens();
        final String systemIdentifier;
        if (tokens.isEmpty()) {
            return new UnknownOS();
        } else {
            systemIdentifier = tokens.get(0).getValue();
        }
        switch (systemIdentifier) {
            case Tokens.WINDOWS:
                return new Windows(source);
            case Tokens.ANDROID:
                return new Android(source);
            case "iPad":
            case "iPhone":
                return new IOS(source);
            case "Macintosh":
                return new MacOS(source);
            case "X11":
                return new X11(source);
            case "Mobile":
                return new Mobile(source);
            default:
                return fallback(source);
        }
    }

    private static OperatingSystem fallback(final TokenizedUserAgent source) {
        for (final StringToken t : source.getSystemTokens()) {
            if (t.getValue().contains(Tokens.ANDROID)) {
                return new Android(source);
            } else if (t.getValue().contains(Tokens.WINDOWS)) {
                return new Windows(source);
            }
        }
        return new UnknownOS();
    }

    public String getOsVendor() {
        return vendor;
    }

    public String getOsName() {
        return name;
    }

    public String getOsVersion() {
        return version;
    }

    public String getOsType() {
        return type;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", getClass().getSimpleName() + "[", "]")
                .add("vendor='" + vendor + "'")
                .add("name='" + name + "'")
                .add("version='" + version + "'")
                .add("type='" + type + "'")
                .toString();
    }
}
