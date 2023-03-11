package org.koar.koariloggerbeta.ilogger;

import java.util.HashMap;
import java.util.Map;

public class LoggingContext {
    private static final ThreadLocal<Map<String, String>> context = new ThreadLocal<>();

    public static void put(String key, String value) {
        Map<String, String> map = context.get();
        if (map == null) {
            map = new HashMap<>();
            context.set(map);
        }
        map.put(key, value);
    }

    public static void remove(String key) {
        Map<String, String> map = context.get();
        if (map != null) {
            map.remove(key);
        }
    }

    public static Map<String, String> get() {
        return context.get();
    }

    public static void clear() {
        context.remove();
    }
}
