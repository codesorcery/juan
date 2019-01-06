package com.github.codesorcery.juan.device;

import com.github.codesorcery.juan.token.TokenizedUserAgent;
import com.github.codesorcery.juan.token.VersionedToken;
import com.github.codesorcery.juan.util.Tokens;
import com.github.codesorcery.juan.util.Vendors;

import java.util.Map;
import java.util.Optional;

class AmazonFireDeviceLookup implements AndroidDeviceLookup {
    private static final Map<String, String> AMAZON_FIRE_DEVICES = AmazonFireDevice.valuesAsMap();

    @Override
    public Optional<DeviceInfo> getDeviceInfo(final TokenizedUserAgent tokenizedUserAgent) {
        if (notAndroid(tokenizedUserAgent)) {
            return Optional.empty();
        }
        for (final VersionedToken token : tokenizedUserAgent.getSystemTokens()) {
            String value = token.getValue();
            if (!value.startsWith(Tokens.ANDROID) && !value.equals(Tokens.LINUX)) {
                final int tokenPos = token.getValue().indexOf("Build");
                if (tokenPos > -1) {
                    value = token.getValue().substring(0, tokenPos).trim();
                }
                final String name = AMAZON_FIRE_DEVICES.get(value);
                if (name != null) {
                    return Optional.of(new DeviceInfo(Vendors.AMAZON, name));
                }
            }
        }
        return Optional.empty();
    }
}
