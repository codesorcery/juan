package com.github.codesorcery.juan.device;

import com.github.codesorcery.juan.token.TokenizedUserAgent;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Optional;

public interface DeviceLookup {
    Optional<DeviceInfo> getDeviceInfo(TokenizedUserAgent tokenizedUserAgent);

    static DeviceLookup withPlayStoreDeviceList(final URL location, final Charset charset)
            throws IOException {
        return new CombinedDeviceLookup(
                PlayStoreDeviceListLookup.fromCsvFile(location, charset),
                withoutPlayStoreDeviceList()
        );
    }

    static DeviceLookup withoutPlayStoreDeviceList() {
        return new CombinedDeviceLookup(
                new DirectlyIdentifiableDeviceLookup(),
                new WindowsDeviceLookup(),
                new AmazonFireDeviceLookup(),
                new WindowsPhoneDeviceLookup(),
                new BlackBerryDeviceLookup()
        );
    }
}
