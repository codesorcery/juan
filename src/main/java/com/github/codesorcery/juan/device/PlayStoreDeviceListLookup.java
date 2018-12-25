package com.github.codesorcery.juan.device;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class PlayStoreDeviceListLookup implements DeviceLookup {
   private final Map<String, DeviceListEntry> deviceList;

   private PlayStoreDeviceListLookup(final Map<String, DeviceListEntry> deviceList) {
      this.deviceList = deviceList;
   }

   public static PlayStoreDeviceListLookup fromCsvFile(final InputStream fileInputStream) throws IOException {
      try (final BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream))) {
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

   public Optional<DeviceInfo> getDeviceInfo(final String deviceId) {
       final DeviceListEntry entry = deviceList.get(deviceId);
       if (entry != null) {
           return Optional.of(new DeviceInfo(entry.vendor, entry.name));
       } else {
           return Optional.empty();
       }
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
