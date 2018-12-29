package com.github.codesorcery.juan.agent;

import com.github.codesorcery.juan.util.Vendors;

import java.util.HashMap;
import java.util.Map;

enum DirectlyIdentifiableMozillaAgent {
   /* Android */
   AMAZON_SILK("Silk", Vendors.AMAZON, "Silk", AgentType.MOBILE_BROWSER),
   SAMSUNG_BROWSER("SamsungBrowser", "Samsung", "Samsung Browser", AgentType.MOBILE_BROWSER),
   YANDEX_BROWSER("YaBrowser", "Yandex", "Yandex Browser", AgentType.MOBILE_BROWSER),
   IE_MOBILE("IEMobile", Vendors.MICROSOFT, "Internet Explorer Mobile", AgentType.MOBILE_BROWSER),
   MIUI_BROWSER("XiaoMi/MiuiBrowser", "XioMi", "MIUI Browser", AgentType.MOBILE_BROWSER),
   PUFFIN("Puffin", "", "Puffin Browser", AgentType.MOBILE_BROWSER),
   UC_BROWSER("UCBrowser", "UCWeb", "UC Browser", AgentType.MOBILE_BROWSER),
   OPERA_MOBILE("OPR", Vendors.OPERA, "Opera Mobile", AgentType.MOBILE_BROWSER),
   OPERA_TOUCH("OPT", Vendors.OPERA, "Opera Touch", AgentType.MOBILE_BROWSER),
   FIREFOX_FOCUS("Focus", Vendors.MOZILLA, "Firefox Focus", AgentType.MOBILE_BROWSER),
   FIREFOX_KLAR("Klar", Vendors.MOZILLA, "Firefox Klar", AgentType.MOBILE_BROWSER),
   IRON_MOBILE("MobileIron", "SRWare", "Iron Mobile", AgentType.MOBILE_BROWSER),
   OPPO_BROWSER("OppoBrowser", "OPPO", "Oppo Browser", AgentType.MOBILE_BROWSER),
   KIWI_BROWSER("Kiwi Chrome", "Geometry OU", "Kiwi Browser", AgentType.MOBILE_BROWSER),
   DOLPHIN_BROWSER("Dolfin", "Mobotap", "Dolphin Browser", AgentType.MOBILE_BROWSER),
   EDGE_ANDROID("EdgA", Vendors.MICROSOFT, "Edge Mobile", AgentType.MOBILE_BROWSER),
   /* iOS */
   FIREFOX_IOS("FxiOS", Vendors.MOZILLA, "Firefox Mobile", AgentType.MOBILE_BROWSER),
   CHROME_IOS("CriOS", Vendors.GOOGLE, "Chrome Mobile", AgentType.MOBILE_BROWSER),
   OPERA_IOS("OPiOS", Vendors.OPERA, "Opera Mobile", AgentType.MOBILE_BROWSER),
   EDGE_IOS("EdgiOS", Vendors.MICROSOFT, "Edge Mobile", AgentType.MOBILE_BROWSER),
   /* APPS */
   FACEBOOK_APP("FBAV", "Facebook", "Facebook App", AgentType.MOBILE_APPLICATION),
   SNAPCHAT("Snapchat", "Snap", "Snapchat", AgentType.MOBILE_APPLICATION),
   BING_SEARCH("BingWeb", Vendors.MICROSOFT, "Bing Search App", AgentType.MOBILE_APPLICATION),
   APPLE_NEWS("AppleNews", Vendors.APPLE, "Apple News", AgentType.MOBILE_APPLICATION),
   OWNCLOUD_ANDROID("ownCloud-android", "ownCloud GmbH", "ownCloud sync client", AgentType.MOBILE_APPLICATION),
   OWNCLOUD_DESKTOP("mirall", "ownCloud GmbH", "ownCloud sync client", AgentType.DESKTOP_APPLICATION),
   /* Mobile & Desktop */
   BRAVE("Brave", "Brave Software", "Brave Browser", AgentType.BROWSER),
   /* Desktop */
   PALE_MOON("PaleMoon", "M.C. Straver", "PaleMoon Browser", AgentType.DESKTOP_BROWSER),
   SEAMONKEY("SeaMonkey", "Mozilla", "SeaMonkey", AgentType.DESKTOP_BROWSER),
   VIVALDI("Vivaldi", "Vivaldi Technologies", "Vivaldi", AgentType.DESKTOP_BROWSER),
   IRON("Iron", "SRWare", "Iron", AgentType.DESKTOP_BROWSER),
   SLEIPNIR("Sleipnir", "Fenrir", "Sleipnir", AgentType.DESKTOP_BROWSER),
   CHROMIUM("Chromium", Vendors.GOOGLE, "Chromium", AgentType.BROWSER),
   /* Linux */
   GNOME_WEB("Epiphany", "GNOME Foundation", "GNOME Web", AgentType.DESKTOP_BROWSER),
   KONQUEROR("Konqueror", "KDE", "Konqueror", AgentType.DESKTOP_BROWSER),
   /* TV */
   VIERA("Viera", "Panasonic", "VIERA TV Browser", AgentType.EMBEDDED_BROWSER),
   /* Other */
   WEBOS_BROWSER("wOSBrowser", "HP", "WebOS Browser", AgentType.MOBILE_BROWSER),
   THUNDERBIRD("Thunderbird", "Mozilla", "Thunderbird", AgentType.DESKTOP_APPLICATION),
   KINDLE("Kindle", Vendors.APPLE, "Kindle Browser", AgentType.MOBILE_BROWSER),
   NINTENDO_BROWSER("NintendoBrowser", "Nintendo", "Nintendo Browser", AgentType.EMBEDDED_BROWSER),
   EVERNOTE_WINDOWS("Evernote Windows", "Evernote Corporation", "Evernote", AgentType.DESKTOP_APPLICATION),
   SAILFISH_BROWSER("SailfishBrowser", "Jolla", "Sailfish Browser", AgentType.MOBILE_BROWSER),
   KAIOS("KAIOS", "KaiOS Technologies", "KaiOS", AgentType.MOBILE_BROWSER),
   ADOBE_AIR("AdobeAIR", "Adobe", "Adobe AIR Runtime", AgentType.LIBRARY),
   PHANTOM_JS("PhantomJS", "Ariya Hidayat", "PhantomJS", AgentType.LIBRARY),
   POWERSHELL("WindowsPowerShell", Vendors.MICROSOFT, "PowerShell", AgentType.CLI_APPLICATION),
   ELECTRON("Electron", "Electron community", "Electron framework", AgentType.DESKTOP_APPLICATION),
   PINTEREST("Pinterest", "Pinterest", "Pinterest App", AgentType.MOBILE_APPLICATION),
   ;

   private final String identifier;
   final String vendor;
   final String name;
   final AgentType type;

   DirectlyIdentifiableMozillaAgent(final String identifier, final String vendor, final String name,
                                    final AgentType type) {
      this.identifier = identifier;
      this.vendor = vendor;
      this.name = name;
      this.type = type;
   }

   static Map<String, DirectlyIdentifiableMozillaAgent> valuesAsMap() {
      final Map<String, DirectlyIdentifiableMozillaAgent> result = new HashMap<>();
      for (final DirectlyIdentifiableMozillaAgent agent : DirectlyIdentifiableMozillaAgent.values()) {
         result.put(agent.identifier, agent);
      }
      return result;
   }
}
