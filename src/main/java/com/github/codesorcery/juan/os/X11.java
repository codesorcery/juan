package com.github.codesorcery.juan.os;

import com.github.codesorcery.juan.token.StringToken;
import com.github.codesorcery.juan.token.TokenizedUserAgent;
import com.github.codesorcery.juan.token.VersionedToken;
import com.github.codesorcery.juan.util.OsTypes;
import com.github.codesorcery.juan.util.Tokens;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class X11 extends OperatingSystem {
    private static final List<String> OS_NAMES = Collections.unmodifiableList(
            Arrays.asList(Tokens.LINUX, "CrOS", "OpenBSD", "FreeBSD", "SunOS", "DragonFly",
                    "TrueOS", "HP-UX"));
    private static final List<String> LINUX_DISTRBUTIONS = Collections.unmodifiableList(
            Arrays.asList("Ubuntu", "Fedora", "Debian", "Gentoo", "RedHat", "CentOS", "SUSE",
                    "Slackware", "Linux Mint", "elementaryOS", "Arch Linux"));

    private final String osName;
    private final String osVersion;

    X11(final TokenizedUserAgent source) {
        final OsInfo extracted = extractOsName(source);
        osName = extracted.name;
        osVersion = extracted.version;
    }

    private OsInfo extractOsName(final TokenizedUserAgent source) {
        for (final StringToken t : source.getStringTokens()) {
            for (final String s : OS_NAMES) {
                if (t.getValue().contains(s)) {
                    return mapOsName(s, source);
                }
            }
        }
        return new OsInfo("", "");
    }

    private OsInfo mapOsName(final String name, final TokenizedUserAgent source) {
        switch (name) {
            case Tokens.LINUX: return extractLinuxDistribution(source);
            case "CrOS": return new OsInfo("Chrome OS", "");
            case "DragonFly": return new OsInfo("DragonFly BSD", "");
            default: return new OsInfo(name, "");
        }
    }

    private OsInfo extractLinuxDistribution(final TokenizedUserAgent source) {
        for (final VersionedToken t : source.getVersionedTokens()) {
            if (LINUX_DISTRBUTIONS.contains(t.getValue())) {
                return new OsInfo("Linux (" + t.getValue() + ")", t.getVersion());
            }
        }
        for (final StringToken t : source.getStringTokens()) {
            if (LINUX_DISTRBUTIONS.contains(t.getValue())) {
                return new OsInfo("Linux (" + t.getValue() + ")", "");
            }
        }
        return new OsInfo("Linux",  "");
    }

    @Override
    public String getOsVendor() {
        return "";
    }

    @Override
    public String getOsVersion() {
        return osVersion;
    }

    @Override
    public String getOsName() {
        return osName;
    }

    @Override
    public String getOsType() {
        return OsTypes.DESKTOP;
    }

    @Override
    public String getDeviceId() {
        return "";
    }

    private class OsInfo {
        private final String name;
        private final String version;

        private OsInfo(String name, String version) {
            this.name = name;
            this.version = version;
        }
    }
}
