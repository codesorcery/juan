package com.github.codesorcery.juan.device;

import com.github.codesorcery.juan.token.TokenizedUserAgent;
import com.github.codesorcery.juan.token.VersionedToken;
import com.github.codesorcery.juan.util.Tokens;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class PlayStoreDeviceListLookup implements DeviceLookup {
   private final Map<String, DeviceListEntry> deviceList;

   private PlayStoreDeviceListLookup(final Map<String, DeviceListEntry> deviceList) {
      this.deviceList = deviceList;
   }

   public static PlayStoreDeviceListLookup fromCsvFile(final InputStream fileInputStream,
                                                       final Charset charset) {
      try (final BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream, charset))) {
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
      } catch (final Exception e) {
          throw new InitializationException(e);
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
               final DeviceListEntry entry = deviceList.get(value);
               if (entry != null) {
                   return Optional.of(new DeviceInfo(entry.vendor, entry.name));
               }
           }
       }
       return Optional.empty();
   }

   private boolean isAndroid(final TokenizedUserAgent tokenizedUserAgent) {
       for (final VersionedToken token : tokenizedUserAgent.getSystemTokens()) {
           if (token.getValue().startsWith(Tokens.LINUX)) {
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

   public static class InitializationException extends RuntimeException {
       private InitializationException(final Exception cause) {
           super("Failed to initialize device list", cause);
       }
   }
}
