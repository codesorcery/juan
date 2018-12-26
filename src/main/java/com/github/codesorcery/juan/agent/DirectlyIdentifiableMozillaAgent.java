package com.github.codesorcery.juan.agent;

import com.github.codesorcery.juan.util.Vendors;

import java.util.HashMap;
import java.util.Map;

enum DirectlyIdentifiableMozillaAgent {
   /* Android */
   AMAZON_SILK("Silk", Vendors.AMAZON, "Silk"),
   SAMSUNG_BROWSER("SamsungBrowser", "Samsung", "Samsung Browser"),
   YANDEX_BROWSER("YaBrowser", "Yandex", "Yandex Browser"),
   IE_MOBILE("IEMobile", Vendors.MICROSOFT, "Internet Explorer Mobile"),
   MIUI_BROWSER("XiaoMi/MiuiBrowser", "XioMi", "MIUI Browser"),
   PUFFIN("Puffin", "", "Puffin Browser"),
   UC_BROWSER("UCBrowser", "UCWeb", "UC Browser"),
   OPERA_MOBILE("OPR", Vendors.OPERA, "Opera Mobile"),
   OPERA_TOUCH("OPT", Vendors.OPERA, "Opera Touch"),
   FIREFOX_FOCUS("Focus", Vendors.MOZILLA, "Firefox Focus"),
   FIREFOX_KLAR("Klar", Vendors.MOZILLA, "Firefox Klar"),
   IRON_MOBILE("MobileIron", "SRWare", "Iron Mobile"),
   OPPO_BROWSER("OppoBrowser", "OPPO", "Oppo Browser"),
   KIWI_BROWSER("Kiwi Chrome", "Geometry OU", "Kiwi Browser"),
   DOLPHIN_BROWSER("Dolfin", "Mobotap", "Dolphin Browser"),
   EDGE_ANDROID("EdgA", Vendors.MICROSOFT, "Edge Mobile"),
   /* iOS */
   FIREFOX_IOS("FxiOS", Vendors.MOZILLA, "Firefox Mobile"),
   CHROME_IOS("CriOS", Vendors.GOOGLE, "Chrome Mobile"),
   OPERA_IOS("OPiOS", Vendors.OPERA, "Opera Mobile"),
   EDGE_IOS("EdgiOS", Vendors.MICROSOFT, "Edge Mobile"),
   /* APPS */
   FACEBOOK_APP("FBAV", "Facebook", "Facebook App"),
   SNAPCHAT("Snapchat", "Snap", "Snapchat"),
   BING_SEARCH("BingWeb", Vendors.MICROSOFT, "Bing Search App"),
   APPLE_NEWS("AppleNews", Vendors.APPLE, "Apple News"),
   OWNCLOUD_ANDROID("ownCloud-android", "ownCloud GmbH", "ownCloud sync client"),
   OWNCLOUD_DESKTOP("mirall", "ownCloud GmbH", "ownCloud sync client"),
   /* Mobile & Desktop */
   BRAVE("Brave", "Brave Software", "Brave"),
   /* Desktop */
   VIVALDI("Vivaldi", "Vivaldi Technologies", "Vivaldi"),
   IRON("Iron", "SRWare", "Iron"),
   SLEIPNIR("Sleipnir", "Fenrir", "Sleipnir"),
   CHROMIUM("Chromium", Vendors.GOOGLE, "Chromium"),
   /* Linux */
   GNOME_WEB("Epiphany", "GNOME Foundation", "GNOME Web"),
   KONQUEROR("Konqueror", "KDE", "Konqueror"),
   /* Other */
   THUNDERBIRD("Thunderbird", "Mozilla", "Thunderbird"),
   KINDLE("Kindle", Vendors.APPLE, "Kindle Browser"),
   NINTENDO_BROWSER("NintendoBrowser", "Nintendo", "Nintendo Browser"),
   EVERNOTE_WINDOWS("Evernote Windows", "Evernote Corporation", "Evernote"),
   SAILFISH_BROWSER("SailfishBrowser", "Jolla", "Sailfish Browser"),
   KAIOS("KAIOS", "KaiOS Technologies", "KaiOS"),
   ADOBE_AIR("AdobeAIR", "Adobe", "Adobe AIR Runtime"),
   PHANTOM_JS("PhantomJS", "Ariya Hidayat", "PhantomJS"),
   POWERSHELL("WindowsPowerShell", Vendors.MICROSOFT, "PowerShell"),
   ;

   private final String identifier;
   private final String vendor;
   private final String name;

   DirectlyIdentifiableMozillaAgent(final String identifier, final String vendor, final String name) {
      this.identifier = identifier;
      this.vendor = vendor;
      this.name = name;
   }

   String getVendor() {
      return vendor;
   }

   String getName() {
      return name;
   }

   static Map<String, DirectlyIdentifiableMozillaAgent> valuesAsMap() {
      final Map<String, DirectlyIdentifiableMozillaAgent> result = new HashMap<>();
      for (final DirectlyIdentifiableMozillaAgent agent : DirectlyIdentifiableMozillaAgent.values()) {
         result.put(agent.identifier, agent);
      }
      return result;
   }
}
