package com.github.codesorcery.juan.device;

import java.util.HashMap;
import java.util.Map;

public enum AmazonFireDevice {
    // Source: https://developer.amazon.com/docs/fire-tablets/ft-device-and-feature-specifications.html
    KFKAWI("Fire HD 8 (2018)"),
    KFSUWI("Fire HD 10 (2017)"),
    KFAUWI("Fire (2017)"),
    KFDOWI("Fire HD 8 (2017)"),
    KFGIWI("Fire HD 8 (2016)"),
    KFFOWI("Fire (2015)"),
    KFMEWI("Fire HD 8 (2015)"),
    KFTBWI("Fire HD 10 (2015)"),
    KFARWI("Fire HD 6 (2014)"),
    KFASWI("Fire HD 7 (2014)"),
    KFSAWA("Fire HDX 8.9 (2014)"),
    KFSAWI("Fire HDX 8.9 (2014)"),
    KFAPWA("Kindle Fire HDX 8.9 (2013)"),
    KFAPWI("Kindle Fire HDX 8.9 (2013)"),
    KFTHWA("Kindle Fire HDX (2013)"),
    KFTHWI("Kindle Fire HDX (2013)"),
    KFSOWI("Kindle Fire HD (2013)"),
    KFJWA("Kindle Fire HD 8.9 (2012)"),
    KFJWI("Kindle Fire HD 8.9 (2012)"),
    KFTT("Kindle Fire HD 7(2012)"),
    KFOT("Kindle Fire (2012)"),
    // Source: https://developer.amazon.com/docs/fire-tv/identify-amazon-fire-tv-devices.html
    AFTA("Fire TV Cube"),
    AFTMM("Fire TV Stick 4K"),
    AFTN("Fire TV (Gen 3)"),
    AFTS("Fire TV (Gen 2)"),
    AFTT( "Fire TV Stick (Gen 2)"),
    AFTM("Fire TV Stick (Gen 1)"),
    AFTEAMR311("Fire TV Edition - Insignia HD (2018)"),
    AFTJMST12( "Fire TV Edition - Insignia 4K (2018)"),
    AFTBAMR311("Fire TV Edition - Toshiba HD (2018)"),
    AFTKMST12("Fire TV Edition - Toshiba 4K (2018)"),
    AFTRS( "Fire TV Edition - Element 4K (2017)"),
    ;

    private final String value;

    AmazonFireDevice(final String value) {
        this.value = value;
    }

    public static Map<String, String> valuesAsMap() {
        final Map<String, String> result = new HashMap<>();
        for (final AmazonFireDevice device : values()) {
            result.put(device.name(), device.value);
        }
        result.put("Kindle Fire", "Kindle Fire (2011)");
        return result;
    }

}
