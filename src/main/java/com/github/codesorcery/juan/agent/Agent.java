package com.github.codesorcery.juan.agent;

import com.github.codesorcery.juan.token.TokenizedUserAgent;
import com.github.codesorcery.juan.token.VersionedToken;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class Agent {
    private static final Map<String, DirectlyIdentifiableMozillaAgent>
            DIRECTLY_IDENTIFIABLE_BROWSERS_MAP = DirectlyIdentifiableMozillaAgent.valuesAsMap();
    private static final List<OtherIdentifiableMozillaAgent>
            OTHER_IDENTIFIABLE_BROWSERS_LIST = OtherIdentifiableMozillaAgent.valuesAsList();
    private static final Map<String, NonMozillaAgent>
            NON_MOZILLA_AGENT_MAP = NonMozillaAgent.valuesAsMap();

    private static final Agent EMPTY = new Agent("", "", "");

    private final String name;
    private final String vendor;
    private final String version;

    private Agent(final String name, final String vendor, final String version) {
        this.name = name;
        this.vendor = vendor;
        this.version = version;
    }

    public static Agent fromUserAgent(final TokenizedUserAgent source) {
        for (final VersionedToken token : source.getBrowserTokens()) {
            final DirectlyIdentifiableMozillaAgent agent = DIRECTLY_IDENTIFIABLE_BROWSERS_MAP.get(token.getValue());
            if (agent != null) {
                return new Agent(agent.getName(), agent.getVendor(), token.getVersion());
            }
        }
        for (final OtherIdentifiableMozillaAgent agent : OTHER_IDENTIFIABLE_BROWSERS_LIST) {
            if (agent.matches(source)) {
                return new Agent(agent.getName(), agent.getVendor(),
                        getVersion(source.getAllTokens(), agent.getVersionSource()));
            }
        }
        final NonMozillaAgent agent = NON_MOZILLA_AGENT_MAP.get(source.getPrefixValue());
        if (agent != null) {
            return new Agent(agent.getName(), agent.getVendor(), source.getPrefixVersion());
        }
        return EMPTY;
    }

    private static String getVersion(final List<VersionedToken> tokens, final String value) {
        for (final VersionedToken t : tokens) {
            if (t.getValue().equals(value)) {
                return t.getVersion();
            }
        }
        return "";
    }

    public String getName() {
        return name;
    }

    public String getVendor() {
        return vendor;
    }

    public String getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", getClass().getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("vendor='" + vendor + "'")
                .add("version='" + version + "'")
                .toString();
    }
}
