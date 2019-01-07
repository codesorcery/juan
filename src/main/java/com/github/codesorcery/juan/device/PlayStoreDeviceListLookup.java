package com.github.codesorcery.juan.device;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.stream.Collectors;

class PlayStoreDeviceListLookup extends AndroidDeviceLookup {
    private final Map<String, DeviceListEntry> deviceList;

    private PlayStoreDeviceListLookup(final Map<String, DeviceListEntry> deviceList) {
        this.deviceList = deviceList;
    }

    static PlayStoreDeviceListLookup fromCsvFile(final URL location, final Charset charset) throws IOException {
        try (final BufferedReader br = new BufferedReader(new InputStreamReader(location.openStream(), charset))) {
            final Map<String, DeviceListEntry> deviceList = br.lines()
                    .skip(1)
                    .map(line -> line.split(","))
                    .filter(row -> row.length == 4)
                    .collect(Collectors.toMap(
                            row -> row[3].trim(),
                            row -> new DeviceListEntry(row[0].trim(), row[1].trim()),
                            (a, b) -> a)
                    );
            return new PlayStoreDeviceListLookup(deviceList);
        }
    }

    @Override
    DeviceInfo lookup(final String value) {
        DeviceListEntry entry = deviceList.get(value);
        if (entry == null) {
            entry = entryForCleanedValue(value);
        }
        if (entry != null) {
            return new DeviceInfo(
                    entry.vendor,
                    entry.name.isEmpty() ? value.replace('_', ' ') : entry.name
            );

        }
        return null;
    }

    private static final String[] VENDOR_STRINGS = {
            "SAMSUNG", "LENOVO", "HUAWEI"
    };

    private DeviceListEntry entryForCleanedValue(String value) {
        final int dashPos = value.indexOf('/');
        if (dashPos != -1) {
            value = value.substring(0, dashPos).trim();
        }
        for (final String vendorString : VENDOR_STRINGS) {
            final int pos = value.indexOf(vendorString + " ");
            if (pos > -1) {
                return deviceList.get(value.substring(pos + vendorString.length() + 1));
            }
        }
        return null;
    }

    private static class DeviceListEntry {
        private final String vendor;
        private final String name;

        private DeviceListEntry(final String vendor, final String name) {
            this.vendor = vendor;
            this.name = name;
        }
    }
}
