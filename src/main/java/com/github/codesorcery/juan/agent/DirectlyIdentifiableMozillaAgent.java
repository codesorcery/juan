package com.github.codesorcery.juan.agent;

import java.util.HashMap;
import java.util.Map;

enum DirectlyIdentifiableMozillaAgent {
   /* Android */
   AMAZON_SILK("Silk", "Amazon", "Silk"),
   SAMSUNG_BROWSER("SamsungBrowser", "Samsung", "Samsung Browser"),
   YANDEX_BROWSER("YaBrowser", "Yandex", "Yandex Browser"),
   IE_MOBILE("IEMobile", "Microsoft", "Internet Explorer Mobile"),
   MIUI_BROWSER("XiaoMi/MiuiBrowser", "XioMi", "MIUI Browser"),
   PUFFIN("Puffin", "", "Puffin Browser"),
   UC_BROWSER("UCBrowser", "UCWeb", "UC Browser"),
   OPERA_MOBILE("OPR", "Opera", "Opera Mobile"),
   OPERA_TOUCH("OPT", "Opera", "Opera Touch"),
   FIREFOX_FOCUS("Focus", "Mozilla", "Firefox Focus"),
   FIREFOX_KLAR("Klar", "Mozilla", "Firefox Klar"),
   IRON_MOBILE("MobileIron", "SRWare", "Iron Mobile"),
   OPPO_BROWSER("OppoBrowser", "OPPO", "Oppo Browser"),
   KIWI_BROWSER("Kiwi Chrome", "Geometry OU", "Kiwi Browser"),
   DOLPHIN_BROWSER("Dolfin", "Mobotap", "Dolphin Browser"),
   EDGE_ANDROID("EdgA", "Microsoft", "Edge Mobile"),
   /* iOS */
   FIREFOX_IOS("FxiOS", "Mozilla", "Firefox Mobile"),
   CHROME_IOS("CriOS", "Google", "Chrome Mobile"),
   OPERA_IOS("OPiOS", "Opera", "Opera Mobile"),
   EDGE_IOS("EdgiOS", "Microsoft", "Edge Mobile"),
   /* APPS */
   FACEBOOK_APP("FBAV", "Facebook", "Facebook App"),
   SNAPCHAT("Snapchat", "Snap", "Snapchat"),
   BING_SEARCH("BingWeb", "Microsoft", "Bing Search App"),
   APPLE_NEWS("AppleNews", "Apple", "Apple News"),
   OWNCLOUD_ANDROID("ownCloud-android", "ownCloud GmbH", "ownCloud sync client"),
   OWNCLOUD_DESKTOP("mirall", "ownCloud GmbH", "ownCloud sync client"),
   /* Mobile & Desktop */
   BRAVE("Brave", "Brave Software", "Brave"),
   /* Desktop */
   VIVALDI("Vivaldi", "Vivaldi Technologies", "Vivaldi"),
   IRON("Iron", "SRWare", "Iron"),
   SLEIPNIR("Sleipnir", "Fenrir", "Sleipnir"),
   CHROMIUM("Chromium", "Google", "Chromium"),
   /* Linux */
   GNOME_WEB("Epiphany", "GNOME Foundation", "GNOME Web"),
   KONQUEROR("Konqueror", "KDE", "Konqueror"),
   /* Other */
   THUNDERBIRD("Thunderbird", "Mozilla", "Thunderbird"),
   KINDLE("Kindle", "Amazon", "Kindle Agent"),
   NINTENDO_BROWSER("NintendoBrowser", "Nintendo", "Nintendo Browser"),
   EVERNOTE_WINDOWS("Evernote Windows", "Evernote Corporation", "Evernote"),
   SAILFISH_BROWSER("SailfishBrowser", "Jolla", "Sailfish Browser"),
   KAIOS("KAIOS", "KaiOS Technologies", "KaiOS"),
   ADOBE_AIR("AdobeAIR", "Adobe", "Adobe AIR Runtime"),
   PHANTOM_JS("PhantomJS", "Ariya Hidayat", "PhantomJS"),
   POWERSHELL("WindowsPowerShell", "Microsoft", "PowerShell"),
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
