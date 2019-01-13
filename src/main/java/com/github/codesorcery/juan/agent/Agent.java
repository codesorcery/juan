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
    private static final List<CrawlerVendor> crawlerVendors = CrawlerVendor.valuesAsList();

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
        final VersionedToken crawler = GenericCrawlerCheck.check(source);
        if (crawler != null) {
            return new Agent(crawler.getValue(), getCrawlerVendor(crawler.getValue()),
                    crawler.getVersion(), AgentType.CRAWLER);
        }
        final NonMozillaAgent nmAgent = NON_MOZILLA_AGENT_MAP.get(source.getPrefix().getValue());
        if (nmAgent != null) {
            return new Agent(nmAgent.name, nmAgent.vendor, source.getPrefix().getVersion(), nmAgent.type);
        }
        for (final VersionedToken token : source.getAllTokens()) {
            final DirectlyIdentifiableMozillaAgent agent = DIRECTLY_IDENTIFIABLE_BROWSERS_MAP.get(token.getValue());
            if (agent != null) {
                final String version = token.getVersion().isEmpty()
                        ? getVersion(source.getBrowserTokens(), token.getValue())
                        : token.getVersion();
                return new Agent(agent.name, agent.vendor, version, agent.type);
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

    private static String getCrawlerVendor(final String id) {
        final String lowercaseId = id.toLowerCase();
        for (final CrawlerVendor vendor : crawlerVendors) {
            if (vendor.matches(lowercaseId)) {
                return vendor.getName();
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
