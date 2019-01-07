package com.github.codesorcery.juan.agent;

import com.github.codesorcery.juan.token.TokenizedUserAgent;
import com.github.codesorcery.juan.token.VersionedToken;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Holds information about the agent (browser/application)
 * extracted from the {@link TokenizedUserAgent}.
 */
public class Agent {
    private static final Map<String, DirectlyIdentifiableMozillaAgent>
            DIRECTLY_IDENTIFIABLE_BROWSERS_MAP = DirectlyIdentifiableMozillaAgent.valuesAsMap();
    private static final List<OtherIdentifiableMozillaAgent>
            OTHER_IDENTIFIABLE_BROWSERS_LIST = OtherIdentifiableMozillaAgent.valuesAsList();
    private static final Map<String, NonMozillaAgent>
            NON_MOZILLA_AGENT_MAP = NonMozillaAgent.valuesAsMap();

    private static final Agent EMPTY = new Agent("", "", "", AgentType.UNKNOWN);

    private final String name;
    private final String vendor;
    private final String version;
    private final AgentType type;

    private Agent(final String name, final String vendor, final String version,
                  final AgentType type) {
        this.name = name;
        this.vendor = vendor;
        this.version = version;
        this.type = type;
    }

    /**
     * Extract information about the agent from a tokenized user agent.
     *
     * @param source The tokenized user agent from which
     *               the information should be extracted.
     * @return The extracted information about the agent.
     */
    public static Agent fromUserAgent(final TokenizedUserAgent source) {
        final NonMozillaAgent nmAgent = NON_MOZILLA_AGENT_MAP.get(source.getPrefixValue());
        if (nmAgent != null) {
            return new Agent(nmAgent.name, nmAgent.vendor, source.getPrefixVersion(), nmAgent.type);
        }
        for (final VersionedToken token : source.getBrowserTokens()) {
            final DirectlyIdentifiableMozillaAgent agent = DIRECTLY_IDENTIFIABLE_BROWSERS_MAP.get(token.getValue());
            if (agent != null) {
                return new Agent(agent.name, agent.vendor, token.getVersion(), agent.type);
            }
        }
        for (final OtherIdentifiableMozillaAgent agent : OTHER_IDENTIFIABLE_BROWSERS_LIST) {
            if (agent.matches(source)) {
                return new Agent(agent.name, agent.vendor,
                        getVersion(source.getAllTokens(), agent.versionSource), agent.type);
            }
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

    public String getType() {
        return type.toString();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", getClass().getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("vendor='" + vendor + "'")
                .add("version='" + version + "'")
                .add("type='" + type + "'")
                .toString();
    }
}
