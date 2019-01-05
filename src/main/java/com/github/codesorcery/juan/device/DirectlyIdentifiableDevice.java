package com.github.codesorcery.juan.device;

import com.github.codesorcery.juan.util.Vendors;

import java.util.HashMap;
import java.util.Map;

enum DirectlyIdentifiableDevice {
    IPAD("iPad", Vendors.APPLE, "iPad"),
    IPHONE("iPhone", Vendors.APPLE, "iPhone"),
    IPOD("iPod", Vendors.APPLE, "iPod"),
    IPOD_TOUCH("iPod touch", Vendors.APPLE, "iPod Touch"),
    MACINTOSH("Macintosh", Vendors.APPLE, "Macintosh"),
    PSP("PSP (PlayStation Portable)", Vendors.SONY, "PlayStation Portable"),
    PLAYSTATION_4("PlayStation 4", Vendors.SONY, "PlayStation 4"),
    PLAYSTATION_3("PLAYSTATION 3", Vendors.SONY, "PlayStation 3"),
    PLAYSTATION_VITA("PlayStation Vita", Vendors.SONY, "PlayStation Vita"),
    NINTENDO_WII("Nintendo Wii", Vendors.NINTENDO, "Wii"),
    NINTENDO_WIIU("Nintendo WiiU", Vendors.NINTENDO, "WiiU"),
    NINTENDO_DSI("Nintendo DSi", Vendors.NINTENDO, "DSi"),
    NINTENDO_3DS("Nintendo 3DS", Vendors.NINTENDO, "3DS"),
    NINTENDO_3DS_2("New Nintendo 3DS like iPhone", Vendors.NINTENDO, "3DS"),
    NINTENDO_SWITCH("Nintendo Switch", Vendors.NINTENDO, "Switch"),
    BLACKBERRY_PLAYBOOK("PlayBook", Vendors.BLACKBERRY, "PlayBook"),
    ;

    private final String identifier;
    private final String vendor;
    private final String name;

    DirectlyIdentifiableDevice(final String identifier, final String vendor, final String name) {
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

    static Map<String, DirectlyIdentifiableDevice> valuesAsMap() {
        final Map<String, DirectlyIdentifiableDevice> result = new HashMap<>();
        for (final DirectlyIdentifiableDevice device : DirectlyIdentifiableDevice.values()) {
            result.put(device.identifier, device);
        }
        return result;
    }
}
