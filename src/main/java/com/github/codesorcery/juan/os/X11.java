package com.github.codesorcery.juan.os;

import com.github.codesorcery.juan.token.TokenizedUserAgent;
import com.github.codesorcery.juan.token.VersionedToken;
import com.github.codesorcery.juan.util.Tokens;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class X11 extends OperatingSystem {
    private static final List<String> OS_NAMES = Collections.unmodifiableList(
            Arrays.asList(Tokens.LINUX, "CrOS", "OpenBSD", "FreeBSD", "SunOS", "DragonFly",
                    "TrueOS", "HP-UX", "NetBSD"));
    private static final List<String> LINUX_DISTRBUTIONS = Collections.unmodifiableList(
            Arrays.asList("Ubuntu", "Fedora", "Debian", "Gentoo", "RedHat", "CentOS", "SUSE",
                    "Slackware", "Linux Mint", "elementaryOS", "Arch Linux"));

    X11(final TokenizedUserAgent source) {
        this(extractOsName(source));
    }

    private X11(final OsInfo extractedInfo) {
        super("", extractedInfo.name, extractedInfo.version, OsType.DESKTOP);
    }

    private static OsInfo extractOsName(final TokenizedUserAgent source) {
        for (final VersionedToken t : source.getSystemTokens()) {
            for (final String s : OS_NAMES) {
                if (t.getValue().contains(s)) {
                    return mapOsName(s, source);
                }
            }
        }
        return new OsInfo("", "");
    }

    private static OsInfo mapOsName(final String name, final TokenizedUserAgent source) {
        switch (name) {
            case Tokens.LINUX: return extractLinuxDistribution(source);
            case "CrOS": return new OsInfo("Chrome OS", "");
            case "DragonFly": return new OsInfo("DragonFly BSD", "");
            default: return new OsInfo(name, "");
        }
    }

    private static OsInfo extractLinuxDistribution(final TokenizedUserAgent source) {
        for (final VersionedToken t : source.getBrowserTokens()) {
            if (LINUX_DISTRBUTIONS.contains(t.getValue())) {
                return new OsInfo("Linux (" + t.getValue() + ")", t.getVersion());
            }
        }
        for (final VersionedToken t : source.getSystemTokens()) {
            if (LINUX_DISTRBUTIONS.contains(t.getValue())) {
                return new OsInfo("Linux (" + t.getValue() + ")", "");
            }
        }
        return new OsInfo("Linux",  "");
    }

    private static class OsInfo {
        private final String name;
        private final String version;

        private OsInfo(String name, String version) {
            this.name = name;
            this.version = version;
        }
    }
}
