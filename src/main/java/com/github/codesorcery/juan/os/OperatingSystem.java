package com.github.codesorcery.juan.os;

import com.github.codesorcery.juan.token.TokenizedUserAgent;
import com.github.codesorcery.juan.token.VersionedToken;
import com.github.codesorcery.juan.util.Tokens;

import java.util.List;
import java.util.StringJoiner;
import java.util.function.Predicate;

public abstract class OperatingSystem {
    private final String vendor;
    private final String name;
    private final String version;
    private final OsType type;

    protected OperatingSystem(final String vendor,
                              final String name,
                              final String version,
                              final OsType type) {
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
            case Tokens.WINDOWS_NT:
            case "MSIE":
                return new Windows(source);
            case Tokens.LINUX:
                return linuxBasedOs(source);
            case Tokens.ANDROID:
                return new Android(source);
            case "iPad":
            case "iPhone":
            case "iPod":
            case "iPod touch":
                return new IOS(source);
            case "Macintosh":
                return new MacOS(source);
            case "X11":
                return new X11(source);
            case "Mobile":
            case Tokens.WINDOWS_PHONE:
                return new Mobile(source);
            case "compatible":
                return compatibleOs(source);
            case "hp-tablet":
                return new HPWebOs(source);
            case "PlayBook":
                return new RimTabletOs(source);
            default:
                return new UnknownOS();
        }
    }

    private static OperatingSystem linuxBasedOs(final TokenizedUserAgent source) {
        for (final VersionedToken t : source.getSystemTokens()) {
            if (t.getValue().equals(Tokens.ANDROID)) {
                return new Android(source);
            }
        }
        return new UnknownOS();
    }

    private static OperatingSystem compatibleOs(final TokenizedUserAgent source) {
        for (final VersionedToken t : source.getSystemTokens()) {
            switch (t.getValue()) {
                case Tokens.WINDOWS_NT:
                    return new Windows(source);
                case Tokens.WINDOWS_PHONE:
                    return new Mobile(source);
                default:
                    // continue search
            }
        }
        return new UnknownOS();
    }

    protected static String extractOsVersion(final TokenizedUserAgent source,
                                             final Predicate<VersionedToken> matcher) {
        for (final VersionedToken t : source.getSystemTokens()) {
            if (matcher.test(t)) {
                return t.getVersion();
            }
        }
        return "";
    }

    public String getVendor() {
        return vendor;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getType() {
        return type.toString();
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
