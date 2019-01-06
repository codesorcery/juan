package com.github.codesorcery.juan.device;

import com.github.codesorcery.juan.token.TokenizedUserAgent;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Optional;

/**
 * Interface and factory functions for device extraction from {@link TokenizedUserAgent}.
 */
public interface DeviceLookup {
    /**
     * Extract the device information from a {@link TokenizedUserAgent}.
     * @param tokenizedUserAgent The {@link TokenizedUserAgent} to extract the information from.
     * @return The extracted device information.
     *         {@link Optional#empty()} if the device lookup did not find a match.
     */
    Optional<DeviceInfo> getDeviceInfo(TokenizedUserAgent tokenizedUserAgent);

    /**
     * Create a new DeviceLookup with Android device lookup using the device list from Google Play enabled.
     * @param location The location where the Google Play device list is stored.
     * @param charset The charset of the Google Play device list file.
     * @return A DeviceLookup instance with Android device lookup
     *         based on the Google Play device list enabled.
     * @throws IOException If the Google Play device list could not be read.
     */
    static DeviceLookup withPlayStoreDeviceList(final URL location, final Charset charset)
            throws IOException {
        return new CombinedDeviceLookup(
                PlayStoreDeviceListLookup.fromCsvFile(location, charset),
                withoutPlayStoreDeviceList()
        );
    }

    /**
     * Create a new DeviceLookup without the device list from Google Play.
     * @return A DeviceLookup instance with Android device lookup
     *         based on the Google Play device list disabled.
     */
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
