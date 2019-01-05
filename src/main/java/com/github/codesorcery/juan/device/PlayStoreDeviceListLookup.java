package com.github.codesorcery.juan.device;

import com.github.codesorcery.juan.token.TokenizedUserAgent;
import com.github.codesorcery.juan.token.VersionedToken;
import com.github.codesorcery.juan.util.Tokens;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

class PlayStoreDeviceListLookup implements DeviceLookup {
   private final Map<String, DeviceListEntry> deviceList;

   private PlayStoreDeviceListLookup(final Map<String, DeviceListEntry> deviceList) {
      this.deviceList = deviceList;
   }

   public static PlayStoreDeviceListLookup fromCsvFile(final URL location, final Charset charset)
           throws IOException {
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
   public Optional<DeviceInfo> getDeviceInfo(final TokenizedUserAgent tokenizedUserAgent) {
       if (!isAndroid(tokenizedUserAgent)) {
           return Optional.empty();
       }
       for (final VersionedToken token : tokenizedUserAgent.getSystemTokens()) {
           String value = token.getValue();
           if (!value.startsWith(Tokens.ANDROID) && !value.equals(Tokens.LINUX)) {
               final int tokenPos = token.getValue().indexOf("Build");
               if (tokenPos > -1) {
                   value = token.getValue().substring(0, tokenPos).trim();
               }
               final DeviceListEntry entry = lookupValue(value);
               if (entry != null) {
                   return Optional.of(new DeviceInfo(
                           entry.vendor,
                           entry.name.isEmpty() ? value.replace('_', ' ') : entry.name
                   ));
               }
           }
       }
       return Optional.empty();
   }

   private DeviceListEntry lookupValue(final String value) {
       final DeviceListEntry entry = deviceList.get(value);
       if (entry != null) {
           return entry;
       }
       return deviceList.get(cleanUpValue(value));
   }

   private static final String[] VENDOR_STRINGS = {
           "SAMSUNG", "LENOVO", "HUAWEI"
   };

   private String cleanUpValue(String value) {
       final int dashPos = value.indexOf('/');
       if (dashPos != -1) {
           value = value.substring(0, dashPos).trim();
       }
       for (final String vendorString : VENDOR_STRINGS) {
           final int pos = value.indexOf(vendorString + " ");
           if (pos > -1) {
               return value.substring(pos + vendorString.length() + 1);
           }
       }
       return value;
   }

   private boolean isAndroid(final TokenizedUserAgent tokenizedUserAgent) {
       for (final VersionedToken token : tokenizedUserAgent.getSystemTokens()) {
           if (token.getValue().startsWith(Tokens.ANDROID)) {
               return true;
           }
       }
       return false;
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
