package br.com.gzlabs.gzassist.config;

import java.util.prefs.Preferences;

public class AppConfig {

    private static final Preferences prefs = Preferences.userNodeForPackage(AppConfig.class);
    private static final String KEY_API = "apiKey";
    private static final String KEY_HOTKEY = "hotkey";
    private static final String KEY_TIMEOUT = "overlayTimeout";

    private AppConfig() {
    }

    public static String getApiKey() {
        return prefs.get(KEY_API, "");
    }

    public static void setApiKey(String key) {
        prefs.put(KEY_API, key);
    }

    public static String getHotkey() {
        return prefs.get(KEY_HOTKEY, "CONTROL+ALT+K");
    }

    public static void setHotkey(String hotkey) {
        prefs.put(KEY_HOTKEY, hotkey);
    }

    public static int getOverlayTimeout() {
        return prefs.getInt(KEY_TIMEOUT, 5);
    }

    public static void setOverlayTimeout(int seconds) {
        prefs.putInt(KEY_TIMEOUT, seconds);
    }
}
