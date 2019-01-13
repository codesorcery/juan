package com.github.codesorcery.juan.agent;

import com.github.codesorcery.juan.util.Vendors;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public enum CrawlerVendor {
    MICROSOFT(Vendors.MICROSOFT, value ->
            value.startsWith("bing")
                    || value.startsWith("msn")
                    || value.equals("adidxbot")
    ),
    GOOGLE(Vendors.GOOGLE, value ->
            value.contains("google")
    ),
    YANDEX("Yandex", value ->
            value.startsWith("yandex")
                    || value.equals("yadirectfetcher")
    ),
    YAHOO("Yahoo", value ->
            value.startsWith("yahoo")
    ),
    BAIDU("Baidu", value ->
            value.startsWith("baidu")
    ),
    FACEBOOK(Vendors.FACEBOOK, value ->
            value.startsWith("facebook")
    ),
    ALEXA("Alexa", value ->
            value.equals("ia_archiver")
    ),
    DUCKDUCKGO("DuckDuckGo", value ->
            value.startsWith("duckduck")
    ),
    ;

    private final String name;
    private final Predicate<String> matcher;

    CrawlerVendor(final String name, final Predicate<String> matcher) {
        this.name = name;
        this.matcher = matcher;
    }

    static List<CrawlerVendor> valuesAsList() {
        return Arrays.asList(values());
    }

    boolean matches(String id) {
        return matcher.test(id);
    }

    String getName() {
        return name;
    }
}
