package com.github.codesorcery.juan.os;

import com.github.codesorcery.juan.token.StringToken;
import com.github.codesorcery.juan.token.TokenizedUserAgent;
import com.github.codesorcery.juan.util.Tokens;

import java.util.List;
import java.util.StringJoiner;

public abstract class OperatingSystem {
    public abstract String getOsVendor();
    public abstract String getOsVersion();
    public abstract String getOsName();
    public abstract String getOsType();
    public abstract String getDeviceId();

    public static OperatingSystem fromUserAgent(final TokenizedUserAgent source) {
        final List<StringToken> tokens = source.getStringTokens();
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
                return new IOS(systemIdentifier, source);
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
        for (final StringToken t : source.getStringTokens()) {
            if (t.getValue().contains(Tokens.ANDROID)) {
                return new Android(source);
            } else if (t.getValue().contains(Tokens.WINDOWS)) {
                return new Windows(source);
            }
        }
        return new UnknownOS();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]")
                .add("vendor='" + this.getOsVendor() + "'")
                .add("name='" + this.getOsName() + "'")
                .add("version='" + this.getOsVersion() + "'")
                .add("type='" + this.getOsType() + "'")
                .add("deviceId='" + this.getDeviceId() + "'")
                .toString();
    }
}
